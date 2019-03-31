package control;

import control.enums.Criterion;
import dataGenerator.CarGenerator;
import exception.MyUncheckedException;
import model.Car;
import model.enums.Color;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;



class CarService {

    private final List<Car> cars;

    public CarService(final DataLoaderService dataLoaderService) {
        this.cars = dataLoaderService.loadData();
    }

    public static List<Car> carListCreator() {
        List<Car> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            temp.add(CarGenerator.carGenerator());
        }
        return temp;
    }

    //////////////////////////////////////////task1////////////////////////////////////////////

    /**
     * Method override toString method to show in best way raw list of Cars
     */
    @Override
    public String toString() {
        return cars.stream().map(Car::toString).collect(Collectors.joining("\n"));
    }

    //////////////////////////////////////////task2////////////////////////////////////////////

    /**
     * Methode return new List of CarService which is sorted by parameter
     *
     * @param criterion  - model, price, color, mileage;
     * @param descending - sort naturalOrder or reverseOrder
     *                   <p>
     *                   return collection List<Car>
     */

    public List<Car> sort(Criterion criterion, boolean descending) {

        if (criterion == null) {
            throw new MyUncheckedException("Criterion is null");
        }

        Stream<Car> carStream = null;

        switch (criterion) {
            case COLOR:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getColor));
            case MODEL:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getModel));
            case PRICE:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getPrice));
            case MILEAGE:
                carStream = cars.stream().sorted(Comparator.comparing(Car::getMileage));
        }
        List<Car> sortedCars = carStream.collect(Collectors.toList());

        if (descending) {
            Collections.reverse(sortedCars);
        }

        return sortedCars;

    }
    //////////////////////////////////////////task3////////////////////////////////////////////

    /**
     * Method return new List of CarService filtered by parameter mileage
     *
     * @param mileage;
     * @return collection List<Car>
     */

    public List<Car> collectionByMileage(int mileage) {
        return cars
                .stream()
                .filter(p -> p.getMileage() > mileage)
                .collect(Collectors.toList());
    }
    //////////////////////////////////////////task4////////////////////////////////////////////

    /**
     * Method return new Map of  Color and  number of Car which have this color
     * grouping By -> Color
     * return collection  Map<Color, Long>
     */

    public Map<Color, Long> groupedAndCountedByColor() {
        return cars
                .stream()
                .collect(Collectors.groupingBy(Car::getColor, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Long::max, LinkedHashMap::new));
    }
    //////////////////////////////////////////task5////////////////////////////////////////////

    /**
     * Method return new Map of Model and Car
     * Car -> the most expansive in this model
     *
     * @return collection  Map<String,Car>
     */
    public Map<String, Car> mostExpensiveInModel() {

        // przerobic na collectingAndThen
        Map<String, Car> map = cars.stream()
                .collect(Collectors.groupingBy(c -> c.getModel()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        m -> m.getKey(),
                        m -> m.getValue().stream()
                                .sorted(Comparator.comparing(s -> s.getPrice(), Comparator.reverseOrder()))
                                .findFirst()
                                .orElseThrow(() -> new IllegalStateException(" CAN'T found ")
                                )));
        return map;
    }
    //////////////////////////////////////////task6////////////////////////////////////////////

    /**
     * Method shows statistic min, max, aver of price and mileage
     */
    public void showStatisticsOfPriceAndMileage() {
        // obejrzec na YT o collectorach - poprawic dla BigDecimal, nie zaciaga eclipse org ??

        Stream<BigDecimal> bigDecimalStream = cars.stream().filter(Objects::nonNull).map(Car::getPrice);
        BigDecimal maxPrice = bigDecimalStream.reduce(BigDecimal::max).get();
        Stream<BigDecimal> bigDecimalStream2= cars.stream().filter(Objects::nonNull).map(Car::getPrice);
        BigDecimal minPrice = bigDecimalStream2.reduce(BigDecimal::min).get();
        Stream<BigDecimal> bigDecimalStream3= cars.stream().filter(Objects::nonNull).map(Car::getPrice);
        BigDecimal[] sumPrice = bigDecimalStream3.map(m->new BigDecimal[]{m,BigDecimal.ONE}).reduce((a,b)-> new BigDecimal[]{a[0].add(b[0]),a[1].add(BigDecimal.ONE)}).get();
        BigDecimal averPrice = sumPrice[0].divideToIntegralValue(sumPrice[1]);
        System.out.println(" price statics ");
        System.out.println(" max price ->" + maxPrice);
        System.out.println(" min price ->" + minPrice);
        System.out.println(" aver price ->" + averPrice);

        IntSummaryStatistics issMileage = cars
                .stream()
                .collect(Collectors.summarizingInt(s -> s.getMileage()));
        System.out.println(" mileage statics ");
        System.out.println(" aver mileage ->" + issMileage.getMax());
        System.out.println(" aver mileage ->" + issMileage.getMin());
        System.out.println(" aver mileage ->" + issMileage.getAverage());
    }
    //////////////////////////////////////////task7////////////////////////////////////////////

    /**
     * Method sort List and return List wit the most expansive Cars
     *
     * @return List of Car
     */
    public List<Car> mostExpansiveInList() {


        return cars
                .stream()
                .map(Car::getPrice)
                .max(Comparator.naturalOrder())
                .map(maxPrice -> cars.stream().filter(car -> car.getPrice().equals(maxPrice)))
                .orElseThrow(() -> new MyUncheckedException("Cars with max price not found"))
                .collect(Collectors.toList());

    }

    /**
     * Method sort List of Car with sorted list of Components
     *
     * @return List<Car>
     */
    public List<Car> withSortedComponents() {
        return cars
                .stream()
                .peek(c -> c.setComponents(c.getComponents().stream().sorted().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    /**
     * Method return new Map where a Key is name of Component and Value is a list of Car with this component
     * sorted by numbers of Car
     */

    public Map<String, List<Car>> componentsWithListOfCars() {
        return cars
                .stream()
                .flatMap(car -> car.getComponents().stream())
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        component -> cars.stream().filter(car -> car.getComponents().contains(component)).collect(Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(c -> c.getValue().size() * (-1)))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
//        .stream()
//                .flatMap(f->f.getComponents().stream())
//                .distinct()
//                .collect(Collectors.groupingBy(Function.identity(),Collectors.collectingAndThen(cars.stream().filter(car->car.getComponents().contains(Function.identity())).collect(Collectors.toList()))))
    }

    /**
     * Method return new List filtered by parameters
     *
     * @param min min price
     * @param max max price
     * @return List<Car>
     */
    public List<Car> carsByPriceTask10(BigDecimal min, BigDecimal max) {

        if (min.compareTo(max) >= 0) {
            throw new MyUncheckedException("Date range is not correct");
        }

        return cars.stream()
                .filter(p -> p.getPrice().compareTo(min) > 0 && p.getPrice().compareTo(max) < 0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());

    }


}
