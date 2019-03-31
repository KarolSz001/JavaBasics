package control;

import converters.ShoppingJsonConverter;
import enums.Category;
import exception.MyUncheckedException;
import model.Customer;
import model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoopingManagement {

    private Map<Customer, List<Product>> customerSetMap;

    public ShoopingManagement(String... filenames) {

        this.customerSetMap = loadData(filenames);
    }


    /**
     * This method return object as a Map of Customer and Set<Product>
     * @param filenames get Object from Optional and checked Class , expected List<CustomerWithProducts> or Map<Customer, Set<Product>>
     *                 for List there is conversion to Map
     * @return Map<Customer, List<Product>>
     */
    private Map<Customer, List<Product>> loadData(String... filenames) {
        return Arrays
                .stream(filenames)
                .flatMap(filename -> new ShoppingJsonConverter(filename)
                        .fromJson()
                        .orElseThrow(() -> new MyUncheckedException(""))
                        .getCustomerWithProducts().stream()
                )
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getCustomer(),
                        e -> e.getProducts(),
                        (v1, v2) -> v1,
                        () -> new LinkedHashMap<>()
                ));
    }

    @Override
    public String toString() {
        customerSetMap.entrySet()
                .stream()
                .forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
        return "";
    }


    /////////////////////////////////////TASK1A////////////////////////////////////

    /**
     * This method return  Customer who paid the most for all purchases
     *
     * @return Optional Customer
     */
    public Customer paidTheMostTask1A() {

        Customer customer = customerSetMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().map(p -> p.getPrice().intValue()).reduce(0, (n1, n2) -> n1 + n2)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparingInt(s -> s.getValue() * (-1)))
                .findFirst()
                .orElseThrow(NullPointerException::new)
                .getKey();

        return customer;
    }
    /////////////////////////////////////TASK1B////////////////////////////////////

    /**
     * This method return  Customer the customer who paid the most
     * for shopping in the selected category BOOK, ELECTRONIC, FOOD
     *
     * @param category selected category
     * @return Optional Customer
     */
    public Customer paidTheMostTask1B(Category category) {
        Customer customer = null;
        switch (category) {
            case BOOK: {
                customer = convertCustomerFromMap(Category.BOOK);
                break;
            }
            case ELECTRONIC: {
                customer = convertCustomerFromMap(Category.ELECTRONIC);
                break;
            }
            case FOOD: {
                customer = convertCustomerFromMap(Category.FOOD);
                break;
            }
            default: {
                System.out.println(" WRONG COMMAND ---> return By Default with BOOK Category ");
                customer = convertCustomerFromMap(Category.BOOK);
                break;
            }
        }
        return customer;
    }

    /**
     * This is private method for up one return  Customer in selected category
     * reduce code for  main method
     *
     * @param cat selected category
     * @return Optional Customer
     */
    private Customer convertCustomerFromMap(Category cat) {
        return customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().filter(f -> f.getCategory() == cat).collect(Collectors.toSet())
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().map(m -> m.getPrice()).reduce(BigDecimal.ZERO, (n1, n2) -> n1.add(n2))
                ))
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(s -> s.getValue().intValue(), Comparator.reverseOrder()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(" NO RESULT"))
                .getKey();
    }
    /////////////////////////////////////TASK2////////////////////////////////////

    /**
     * This  method return  map  with age of customers and
     * categories of products that were most often purchased at this age.
     *
     * @return Map<Integer   ,   Category>
     */
    public Map<Integer, Category> mappingByAgeCategoryTask2() {
        Map<Customer, List<Category>> map = customerSetMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                                .stream()
                                .map(s -> s.getCategory())
                                .collect(Collectors.toList())
                ));

        Map<Integer, List<Category>> map2 = map
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(f -> f.getKey().getAge()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().map(f -> f.getValue()).flatMap(d -> d.stream()).collect(Collectors.toList())
                ));
        //Task 2 A ---->>>  Grouping by Age and List of Category Products


        Map<Integer, Map<Category, Long>> map3 = map2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))));
        //Task 2 B ---->>>  Grouping by Age and Map Category numbers

        Map<Integer, Category> map4 = map3.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().entrySet()
                                .stream()
                                .sorted(Comparator.comparingLong(l -> l.getValue().intValue() * (-1)))
                                .findFirst()
                                .orElseThrow(() -> new NullPointerException("ERROR"))
                                .getKey()
                ));
//        System.out.println("Task 2 C ---->>>  Grouping by Age and Category Most Taken in This Age ");
        map4.entrySet().stream().forEach(k -> System.out.println(k));

        return map4;
    }
    /////////////////////////////////////TASK3////////////////////////////////////

    /**
     * This  method return  map  category and  averagePrice in  Category
     *
     * @return Map<Integer               ,               Category>
     */
    public Map<Category, Double> showAveragePriceInCategoryTask3A() {
        Map<Category, Double> mapWithAverPrice = customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .collect(Collectors.groupingBy(t -> t.getCategory()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream()
                                .map(f -> f.getPrice().intValue()).mapToDouble(t -> t).average().getAsDouble()
                ));
        return mapWithAverPrice;
    }

    /**
     * This  method return  map  category and most expansive Product in this category
     *
     * @return Map<Integer   ,   Category>
     */
    public Map<Category, Product> mostExpProductInCategoryTask3B() {
        Map<Category, Product> map2 = customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .collect(Collectors.groupingBy(a -> a.getCategory()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(t -> t.getPrice(), Comparator.reverseOrder()))
                                .findFirst()
                                .orElseThrow(() -> new NullPointerException("ERROR"))
                ));
        return map2;
    }

    /**
     * This  method return  map  category and cheapest Product in this category
     *
     * @return Map<Integer       ,       ategory>
     */
    public Map<Category, Product> cheapestProductInCategoryTask3C() {
        Map<Category, Product> map2 = customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .collect(Collectors.groupingBy(a -> a.getCategory()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream()
                                .sorted(Comparator.comparing(t -> t.getPrice()))
                                .findFirst()
                                .orElseThrow(() -> new NullPointerException("ERROR"))
                ));
        return map2;
    }
    /////////////////////////////////////TASK4////////////////////////////////////

    /**
     * This  method return  map  with Customer and  Category which is most often choose
     *
     * @return Map<Customer       ,       Category>
     */
    public Map<Customer, Category> showCutomersOfCategoryTask4() {

        Map<Customer, Map<Category, Long>> map = customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().collect(Collectors.groupingBy(f -> f.getCategory(), Collectors.counting()))
                ));
        Map<Customer, Category> map2 = map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().entrySet()
                                .stream()
                                .sorted(Comparator.comparing(f -> f.getValue(), Comparator.reverseOrder()))
                                .findFirst()
                                .orElseThrow(NullPointerException::new)
                                .getKey()
                ));
        return map2;
    }
    /////////////////////////////////////TASK5////////////////////////////////////

    /**
     * This  method return  map  with Customer and  Debt which is a difference between cash and product's price
     *
     * @return Map<Customer       ,       Integer>
     */
    public Map<Customer, Integer> showDebtTask5() {
        Map<Customer, Integer> map = customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue().stream().map(p -> p.getPrice().intValue()).reduce(0, (n1, n2) -> n1 + n2)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getKey().getCash().intValue() - e.getValue()
                ));
        return map;

    }
}

