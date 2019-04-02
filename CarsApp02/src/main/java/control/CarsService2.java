package control;

import converters.CarJsonConverter2;
import enums.CarBodyType;
import enums.Criterion2;
import enums.EngineType;
import enums.TyreType;
import exception.MyUncheckedException2;
import model.Car2;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CarsService2 {

    private Set<Car2> car2Set;

    public CarsService2(List<String> stringList) {

        this.car2Set = dataLoader(stringList);
    }

    public Set<Car2> getCar2Set() {
        return car2Set;
    }

    @Override
    public String toString() {
        return "CarService{" +
                "car2Set=" + car2Set +
                '}';
    }

    private static Set<Car2> dataLoader(List<String> filesNameList) {
        Set<Car2> car2Strore = new HashSet<>();
        for (String s : filesNameList) {
            CarJsonConverter2 cJC = new CarJsonConverter2(s);
            Car2 car2 = null;
            try {
                car2 = cJC.fromJson().get();
            } catch (MyUncheckedException2 e) {
                e.printStackTrace();
            }
            car2Strore.add(car2);
        }
        return car2Strore;
    }

    //////////////////////////////////////////////////TASK1///////////////////////////////////////////////////////
// is it good solution ??

    /**
     * This method return Set Collection of filtered by number of components, power of engine or wheels size
     * and sort natural or reverse order
     *
     * @param choice
     * @param isReversOrder
     * @return Set<Car2>
     */
    public Set<Car2> sortMethodByParam(Criterion2 choice, boolean isReversOrder) {
        System.out.println("solution for task nr 1 --------------->>>>>>>>>>>>");

        if (choice == null) {
            throw new MyUncheckedException2("choice is null");
        }

        Stream<Car2> car2Stream = null;

        switch (choice) {
            case NUMBER_COMPONENTS:
                car2Stream = car2Set.stream().sorted(Comparator.comparing(m -> m.getCarBody().getComponents().size()));
            case MILEAGE:
                car2Stream = car2Set.stream().sorted(Comparator.comparing(Car2::getMileage));
            case SIZE_WHEEL:
                car2Stream = car2Set.stream().sorted(Comparator.comparing(c -> c.getWheel().getSize()));
            case PRICE:
                car2Stream = car2Set.stream().sorted(Comparator.comparing(Car2::getPrice));
            case POWER:
                car2Stream = car2Set.stream().sorted(Comparator.comparing(s -> s.getEngine().getPower()));
        }

        Set<Car2> cars = car2Stream.collect(Collectors.toCollection(LinkedHashSet::new));

        if (isReversOrder) {
            Collections.reverse((List<?>) cars);
        }

        return cars;
    }
    //////////////////////////////////////////////////TASK2///////////////////////////////////////////////////////

    /**
     * This method return Set Collection of filtered by CarBodyType and min , max price
     *
     * @param cBT -> CarBodyType
     * @param min -> minimum price
     * @param max -> minimum price
     * @return Set<String>
     */
    public Set<Car2> carBodyCollectionByPrice(CarBodyType cBT, BigDecimal min, BigDecimal max) {
        System.out.println("solution for task nr 2 --------------->>>>>>>>>>>>");
        return car2Set.stream()
                .filter(f -> f.getCarBody().getType() == cBT)
                .filter(ff -> ff.getPrice().compareTo(min) > 0 && ff.getPrice().compareTo(max) < 0)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    //////////////////////////////////////////////////TASK3///////////////////////////////////////////////////////

    /**
     * This method return Set Collection of Model filtered by engineType
     * and sorted Collection by Model
     * @param engineType
     * @return Set<String>
     */

    public Set<String> carsWithEngineType(EngineType engineType) {
        System.out.println(" solution for task nr 3 --------------->>>>>>>>>>>> ");
        return car2Set.stream()
                .filter(f -> f.getEngine().getEngineType() == engineType)
                .sorted(Comparator.comparing(Car2::getModel))
                .map(Car2::getModel)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    //////////////////////////////////////////////////TASK4///////////////////////////////////////////////////////

    /**
     * This method show statistic by parameter , price, mileage, power
     *
     * @param parameter
     */
    // is it good solution ??
    public void showStatisticByParameter(Criterion2 parameter) {
        System.out.println("solution for task nr 4 --------------->>>>>>>>>>>>");

        IntSummaryStatistics iss;
        switch (parameter) {

            case PRICE: {
                BigDecimal sum = car2Set.stream().map(Car2::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
                BigDecimal aver = sum.divideToIntegralValue(new BigDecimal(car2Set.size()));
                BigDecimal min  = car2Set.stream().min(Comparator.comparing(Car2::getPrice)).get().getPrice();
                BigDecimal max  = car2Set.stream().max(Comparator.comparing(Car2::getPrice)).get().getPrice();
                System.out.println(aver + " " + max + " " + min );
                break;
            }
            case MILEAGE: {

                iss = car2Set.stream().collect(Collectors.summarizingInt(Car2::getMileage));
                System.out.println(iss.getAverage() + " " + iss.getMax() + " " + iss.getMin());
                break;
            }
            // czy poprawnie ??
            case POWER: {
                double sum  = car2Set.stream().map(m->m.getEngine().getPower()).reduce(0.0,(r1,r2)->r1 + r2);
                double aver = sum/car2Set.size();
                double min = car2Set.stream().min(Comparator.comparing(m->m.getEngine().getPower())).get().getPrice().doubleValue();
                double max = car2Set.stream().min(Comparator.comparing(m->m.getEngine().getPower())).get().getPrice().doubleValue();
                DecimalFormat dc  = new DecimalFormat("#0.00");
                System.out.println("aver -> " + dc.format(aver) + " max -> " + dc.format(max) + " min -> " + dc.format(min));
                break;
            }
        }
    }
    //////////////////////////////////////////////////TASK5///////////////////////////////////////////////////////

    /**
     * This method return  Map , Key Car2 and Value number of Mileage
     * sorted by mileage
     * @return Map<Car2, Integer>
     */

    public Map<Car2, Integer> mapByCarsAndMileage() {
        System.out.println("solution for task nr 5 --------------->>>>>>>>>>>>");
        car2Set.stream()
                .collect(Collectors.toMap(
                        e -> e,
                        Car2::getMileage,
                        Integer::max,
                        LinkedHashMap::new
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::max,
                        LinkedHashMap::new
                ));

//        car2Set.stream()
//                .collect(Collectors.toMap(Function.identity(),
//
//                )

        return null;
    }
    //////////////////////////////////////////////////TASK6///////////////////////////////////////////////////////

    /**
     * This method return  Map , Key TyreType and Value list of cars how have one
     * sorted by number of Cars
     *
     * @return Map<TyreType, List < Car2>>
     */
// jak sortowac ??? w koncowej mapie nie moga nigdy wystapic podobne klucze ??
    public Map<TyreType, List<Car2>> mapByTyreTypeTask6() {
        System.out.println("solution for task nr 6 --------------->>>>>>>>>>>>");
        Map<TyreType, List<Car2>> map = car2Set.stream()
                .collect(Collectors.groupingBy(e -> e.getWheel().getTyreType()
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().filter(f -> f.getWheel().getTyreType().equals(e.getKey())).collect(Collectors.toCollection(ArrayList::new))
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(c -> c.getValue().size()))
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> v1,
                        () -> new LinkedHashMap<>()
                ));
        return map;
    }

}
