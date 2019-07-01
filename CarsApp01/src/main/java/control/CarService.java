package control;

import control.enums.Criterion;
import converters.AverageCollector;
import dataGenerator.CarGenerator;
import exception.MyUncheckedException;
import model.Car;
import model.enums.Color;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



class CarService {

    private final List<Car> cars;

    public CarService(final DataLoaderService dataLoaderService) {
        this.cars = dataLoaderService.loadData();
    }

    public static List<Car> carsCreator() {
        List<Car> temp = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            temp.add(CarGenerator.carGenerator());
        }
        return temp;
    }

    //////////////////////////////////////////task1////////////////////////////////////////////

    /**
     * Method override toString method to show raw list of Cars
     */
    @Override
    public String toString() {
        return cars.stream().map(Car::toString).collect(Collectors.joining("\n"));
    }

    //////////////////////////////////////////task2////////////////////////////////////////////

    /**
     * Method return new List of CarService which is sorted by parameter
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

        List<Car> sortedCars = switch (criterion) {
            case MODEL -> cars.stream().sorted(Comparator.comparing(Car::getModel)).collect(Collectors.toList());
            case PRICE -> cars.stream().sorted(Comparator.comparing(Car::getPrice)).collect(Collectors.toList());
            case COLOR -> cars.stream().sorted(Comparator.comparing(Car::getColor)).collect(Collectors.toList());
            case MILEAGE -> cars.stream().sorted(Comparator.comparing(Car::getMileage)).collect(Collectors.toList());
        };

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

        return cars.stream()
                .collect(Collectors.groupingBy(Car::getModel, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Car::getPrice)), Optional::orElseThrow)));

    }
    //////////////////////////////////////////task6////////////////////////////////////////////

    /**
     * Method shows statistic min, max, aver of price and mileage
     */
    public void showStatisticsOfPriceAndMileage() {
        // to correct!!!!
        BigDecimal maxPrice = cars.stream().map(Car::getPrice).reduce(BigDecimal::max).get();
        BigDecimal minPrice = cars.stream().map(Car::getPrice).reduce(BigDecimal::min).get();
        BigDecimal aver = cars.stream().collect(new AverageCollector());
        System.out.println(MessageFormat.format("Statistic for PRICE -> maxPrice {0} ::::: minPrice {1} :::::: averPrice {2} ", maxPrice.setScale(2), minPrice.setScale(2), aver.setScale(2)));
        DecimalFormat dc = new DecimalFormat("#0.00");
        IntSummaryStatistics issMileage = cars.stream().collect(Collectors.summarizingInt(Car::getMileage));
        System.out.println(MessageFormat.format("Statistic for MILEAGE -> maxMileage{0} ::::: minMileage {1} :::::: averMileage {2} ", dc.format(issMileage.getMax()), dc.format(issMileage.getMin()), dc.format(issMileage.getAverage())));
    }

    //////////////////////////////////////////task7////////////////////////////////////////////
    /**
     * Method sort List by Price and return List with the most expansive Cars
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
     * Method show List of Car with sorted list of Components
     * @return List<Car>
     */
    public List<Car> withSortedComponents() {
        return cars
                .stream()
                .peek(c -> c.setComponents(c.getComponents().stream().sorted().collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    /**
     * Method returns new Map where a Key is name of Component and Value is a list of Car with this component
     * sorted by numbers of Car
     */

    public Map<String, List<Car>> componentsWithListOfCars() {

        return cars.stream()
                .flatMap(car -> car.getComponents().stream())
                .distinct()
                .collect(Collectors.toMap(Function.identity(), component -> cars.stream().filter(car -> car.getComponents().contains(component)).collect(Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(c -> c.getValue().size(), Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));


}

    /**
     * Method return new List filtered by parameters
     * @param min min price
     * @param max max price
     * @return List<Car>
     */
    public List<Car> filteredByPriceInRange(BigDecimal min, BigDecimal max) {

        if (min.compareTo(max) >= 0) {
            throw new MyUncheckedException(" Range is not correct ");
        }
        return cars.stream()
                .filter(p -> p.getPrice().compareTo(min) > 0 && p.getPrice().compareTo(max) < 0)
                .sorted(Comparator.comparing(Car::getModel))
                .collect(Collectors.toList());
    }

}
