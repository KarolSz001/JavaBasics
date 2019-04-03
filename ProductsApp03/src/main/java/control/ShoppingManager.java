package control;

import converters.ShoppingJsonConverter;
import enums.Category;
import exception.MyUncheckedException;
import model.Customer;
import model.CustomerWithProducts;
import model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingManager {

    private Map<Customer, List<Product>> customerSetMap;

    public ShoppingManager(String... filenames) {

        this.customerSetMap = loadData(filenames);
    }

    /**
     * This method return object as a Map of Customer and Set<Product>
     *
     * @param filenames get Object from Optional and checked Class , expected List<CustomerWithProducts> or Map<Customer, Set<Product>>
     *                  for List there is conversion to Map
     * @return Map<Customer, List < Product>>
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
                        CustomerWithProducts::getCustomer,
                        CustomerWithProducts::getProducts,
                        (v1, v2) -> v1,
                        LinkedHashMap::new
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
     * @return Customer
     */
    public Customer whoPaidTheMost() {

        Customer customer = customerSetMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .entrySet()
                .stream().min(Comparator.comparing(Map.Entry::getValue))
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
    public Customer whoPaidTheMostInSelectedCategory(Category category) {
        Customer customer;
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
     * This is private method return Customer who paid most in selected category
     * reduce code for main method
     *
     * @param cat selected category
     * @return Optional Customer
     */
    private Customer convertCustomerFromMap(Category cat) {
        return customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().filter(f -> f.getCategory() == cat).collect(Collectors.toSet())
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
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
     * @return Map<Integer, Category>
     */
    public Map<Integer, Category> mappingByAgeCategory() {
        Map<Customer, List<Category>> map = customerSetMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .map(Product::getCategory)
                                .collect(Collectors.toList())
                ));

        Map<Integer, List<Category>> map2 = map
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(f -> f.getKey().getAge()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Map.Entry::getValue).flatMap(Collection::stream).collect(Collectors.toList())
                ));
        //Task 2 A ---->>>  Grouping by Age and List of Category Products


        Map<Integer, Map<Category, Long>> map3 = map2.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .stream()
                                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))));
        //Task 2 B ---->>>  Grouping by Age and Map Category numbers

        Map<Integer, Category> map4 = map3.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet()
                                .stream().max(Comparator.comparingLong(l -> l.getValue().intValue()))
                                .orElseThrow(() -> new NullPointerException("ERROR"))
                                .getKey()
                ));
//        System.out.println("Task 2 C ---->>>  Grouping by Age and Category Most Taken in This Age ");
        map4.entrySet().stream().forEach(System.out::println);

        return map4;
    }
    /////////////////////////////////////TASK3////////////////////////////////////

    /**
     * This  method return  map  category and  averagePrice in  Category
     *
     * @return Map<Integer, Category>
     */
    public Map<Category, Double> showAveragePriceInCategory() {

        return customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .map(f -> f.getPrice().intValue()).mapToDouble(t -> t).average().getAsDouble()
                ));
    }

    /**
     * This  method return  map  category and most expansive Product in this category
     *
     * @return Map<Integer, Category>
     */
    public Map<Category, Product> mostExpProductInCategory() {
        return customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream()
                                .max(Comparator.comparing(Product::getPrice)).get()
                ));
    }

    /**
     * This  method return  map  category and cheapest Product in this category
     *
     * @return Map<Integer, Category>
     */
    public Map<Category, Product> cheapestProductInCategory() {
        return customerSetMap
                .entrySet()
                .stream()
                .flatMap(f -> f.getValue().stream())
                .distinct()
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().min(Comparator.comparing(Product::getPrice)).get()
                ));
    }
    /////////////////////////////////////TASK4////////////////////////////////////

    /**
     * This  method return  map  with Customer and  Category which is most often choose
     *
     * @return Map<Customer, Category>
     */
    public Map<Customer, Category> customersWithCategoryMostChosen() {

        Map<Customer, Map<Category, Long>> map = customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                ));

        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet()
                                .stream()
                                .max(Comparator.comparing(Map.Entry::getValue))
                                .get()
                                .getKey()
                ));
    }
    /////////////////////////////////////TASK5////////////////////////////////////

    /**
     * This  method return  map  with Customer and  Debt which is a difference between cash and product's price
     *
     * @return Map<Customer, Integer>
     */

    public Map<Customer, Integer> customersWithDebts() {

        return customerSetMap.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(p -> p.getPrice().intValue()).reduce(0, (n1, n2) -> n1 + n2)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getKey().getCash().intValue() - e.getValue()
                ));
    }
}

