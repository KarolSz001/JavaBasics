package control;

import converters.CarJsonConverter2;
import enums.CarBodyType;
import enums.Criterion;
import enums.EngineType;
import enums.TyreType;
import exception.MyUncheckedException2;
import model.Car2;

import java.util.*;
import java.util.stream.Collectors;

public class CarsService2 {

    private Set<Car2> car2s;

    public CarsService2(List<String> stringList) {

        this.car2s = dataLoader(stringList);
    }

    public Set<Car2> getCar2s() {
        return car2s;
    }

    @Override
    public String toString() {
        return "CarService{" +
                "car2s=" + car2s +
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
     * @param choice
     * @param isReversOrder
     * @return Set<Car2>
     */
    public Set<Car2> sortMethodByArgumTask1(Criterion choice, boolean isReversOrder) {
        Set<Car2> temp = new HashSet<>(car2s);
        switch (choice) {
            case NUMBER_COMPONENTS: {
                temp = (!isReversOrder) ?
                        temp.stream().sorted(Comparator.comparing(s -> s.getCarBody().getComponents().size())).collect(Collectors.toCollection(LinkedHashSet::new)) :
                        temp.stream().sorted(Comparator.comparing(s -> s.getCarBody().getComponents().size(), Comparator.reverseOrder())).collect(Collectors.toCollection(LinkedHashSet::new));
            }
            case POWER: {
                temp = (!isReversOrder) ?
                        temp.stream().sorted(Comparator.comparingDouble(s -> s.getEngine().getPower() * (-1))).collect(Collectors.toCollection(LinkedHashSet::new)) :
                        temp.stream().sorted(Comparator.comparing(s -> s.getEngine().getPower())).collect(Collectors.toCollection(LinkedHashSet::new));
                break;
            }
            case SIZE_WHEEL:{
                temp = (!isReversOrder) ?
                        temp.stream().sorted(Comparator.comparing(s -> s.getWheel().getSize() * (-1))).collect(Collectors.toCollection(LinkedHashSet::new)) :
                        temp.stream().sorted(Comparator.comparing(s -> s.getWheel().getSize())).collect(Collectors.toCollection(LinkedHashSet::new));
                break;
            }
        }
        System.out.println("solution for task nr 1 --------------->>>>>>>>>>>>");
        temp.forEach(s -> System.out.println(s));
        return temp;
    }
    //////////////////////////////////////////////////TASK2///////////////////////////////////////////////////////
    /**
     * This method return Set Collection of filtered by CarBodyType and min , max price
     * @param cBT -> CarBodyType
     * @param min -> minimum price
     * @param max -> minimum price
     * @return Set<String>
     */
    public Set<Car2> carBodyCollectionTask2(CarBodyType cBT, int min, int max) {
        System.out.println("solution for task nr 2 --------------->>>>>>>>>>>>");
        Set<Car2> temp = new HashSet<>(car2s);
        return temp.stream()
                .filter(f -> f.getCarBody().getType() == cBT)
                .filter(ff -> ff.getPrice().intValue() > min && ff.getPrice().intValue() < max)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    //////////////////////////////////////////////////TASK3///////////////////////////////////////////////////////
    /**
     * This method return Set Collection of Model filtered by engineType
     * @param engineType
     * @return Set<String>
     */

    public Set<String> modelsWithEngineTypeTask3(EngineType engineType) {
        System.out.println(" solution for task nr 3 --------------->>>>>>>>>>>> ");
        return car2s.stream()
                .filter(f -> f.getEngine().getEngineType() == engineType)
                .sorted(Comparator.comparing(s -> s.getModel()))
                .map(m -> m.getModel())
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    //////////////////////////////////////////////////TASK4///////////////////////////////////////////////////////
    /**
     * This method show statistic by parameter , price, mileage, power
     * @param parameter
     */
    // is it good solution ??
    public void showStatisticByParameterTask4(Criterion parameter) {
        System.out.println("solution for task nr 4 --------------->>>>>>>>>>>>");
        IntSummaryStatistics iss;
        switch (parameter) {
            case PRICE: {
                iss = car2s.stream().collect(Collectors.summarizingInt(s -> s.getPrice().intValue()));
                System.out.println(iss.getAverage() + " " + iss.getMax() + " " + iss.getMin());
                break;
            }
            case MILEAGE: {
                iss = car2s.stream().collect(Collectors.summarizingInt(s -> s.getMileage()));
                System.out.println(iss.getAverage() + " " + iss.getMax() + " " + iss.getMin());
                break;
            }
            // czy poprawnie ??
            case POWER: {
                iss = car2s.stream().collect(Collectors.summarizingInt(s -> (int) s.getEngine().getPower()));
                System.out.println(iss.getAverage() + " " + iss.getMax() + " " + iss.getMin());
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
    public Map<Car2, Integer> mapByCarsAndMileageTask5() {
        System.out.println("solution for task nr 5 --------------->>>>>>>>>>>>");
        Map<Car2, Integer> map = car2s.stream()
                .collect(Collectors.toMap(
                        e -> e,
                        e -> e.getMileage(),
                        (v1, v2) -> Integer.max(v1.intValue(), v2.intValue()),
                        () -> new LinkedHashMap<>()
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(s -> s.getValue(), Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (v1, v2) -> Integer.max(v1, v2),
                        () -> new LinkedHashMap<>()
                ));
        return map;
    }
    //////////////////////////////////////////////////TASK6///////////////////////////////////////////////////////
    /**
     * This method return  Map , Key TyreType and Value list of cars how have one
     * sorted by number of Cars
     * @return Map<TyreType, List<Car2>>
     */
// jak sortowac ??? w koncowej mapie nie moga nigdy wystapic podobne klucze ??
    public Map<TyreType, List<Car2>> mapByTyreTypeTask6() {
        System.out.println("solution for task nr 6 --------------->>>>>>>>>>>>");
        Map<TyreType, List<Car2>> map = car2s.stream()
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
