package service;

import utility.ShoppingJsonConverter;
import enums.Category;
import exception.MyUncheckedException3;
import model.Customer;
import model.CustomerWithProducts;
import model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.nCopies;

public class ShoppingService {

    private Map<Customer, Map<Product, Long>> customersWithProducts;

    public ShoppingService(String... filenames) {

        this.customersWithProducts = loadData(filenames);
    }
    /**
     * This method return  Map of Customer and Map<Product,Long> where Product is a key and Long is number of purchases
     * @param fileNames get list of fileNames, convert to List <CustomerWithProduct> and than to Map<Customer, Map<Product,Long>
     * @return Map<Customer, Map < Product, Long>
     */
    private Map<Customer, Map<Product, Long>> loadData(String... fileNames) {
        return Arrays
                .stream(fileNames)
                .flatMap(filename -> new ShoppingJsonConverter(filename)
                        .fromJson()
                        .orElseThrow(() -> new MyUncheckedException3(""))
                        .getCustomerWithProducts().stream()
                )
                .collect(Collectors.toList())
                .stream()
                .collect(Collectors.toMap(
                        CustomerWithProducts::getCustomer,
                        customerWithProducts -> customerWithProducts.getProducts().stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())),
                        // m1// A -> 2// B -> 3
                        // m2// A -> 3// C -> 1
                        (m1, m2) -> {
                            m2.forEach((k, v) -> m1.merge(k, v, (v1, v2) -> v1 + v2));
                            return m1;
                        },
                        LinkedHashMap::new
                ));
    }

    @Override
    public String toString() {
        customersWithProducts.entrySet()
                .stream()
                .forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
        return "";
    }

    /////////////////////////////////////TASK1A////////////////////////////////////

    /**
     * This method return  Customer who paid the most for all purchases
     * @return Customer
     */
    public Customer whoPaidTheMost() {
        return customersWithProducts
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream().map(m -> m.getKey().getPrice().multiply(new BigDecimal(m.getValue()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .entrySet()
                .stream().max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(NullPointerException::new)
                .getKey();

    }
    /////////////////////////////////////TASK1B////////////////////////////////////
    /**
     * This method return  Customer the customer who paid the most
     * for shopping in the selected category BOOK, ELECTRONIC, FOOD
     * @param category selected category
     * @return Customer
     */
    public Customer paidMostInSelectedCategory(Category category) {
        if (category == null) {
            throw new MyUncheckedException3("category is null");
        }

        switch (category) {
            case BOOK : return customerWhoPaidMostInCategory(Category.BOOK);
            case ELECTRONIC : return customerWhoPaidMostInCategory(Category.ELECTRONIC);
            case FOOD : return customerWhoPaidMostInCategory(Category.FOOD);
            default : throw new MyUncheckedException3("category is wrong");
        }
    }
    /**
     * This is private method return Customer who paid most in selected category
     * reduce code for main method
     * @param cat selected category
     * @return Optional Customer
     */

    private Customer customerWhoPaidMostInCategory(Category cat) {
        return customersWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream().filter(f -> f.getKey().getCategory().equals(cat)).map(m -> m.getKey().getPrice().multiply(new BigDecimal(m.getValue()))).reduce(BigDecimal.ZERO, BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(NullPointerException::new)
                .getKey();
    }
    /////////////////////////////////////TASK2////////////////////////////////////
    /**
     * This  method return  map  with age of customers and
     * categories of products that were most often purchased at this age.
     * @return Map<Integer, Category>
     */
    public Map<Integer, Category> mappingByAgeCategory() {
        return customersWithProducts
                .entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getKey().getAge()))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        // {ARBUZ, FOOD} -> 3
                        // {OLEJ, MOTO} -> 2
                        // chce uzyskac
                        // FOOD, FOOD, FOOD, MOTO, MOTO
                        e -> e.getValue()
                                .stream()
                                .flatMap(ee -> ee.getValue().entrySet().stream().flatMap(eee -> nCopies(eee.getValue().intValue(), eee.getKey()).stream()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()
                                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()))
                                .entrySet().stream()
                                .max(Comparator.comparing(Map.Entry::getValue))
                                .orElseThrow()
                                .getKey()
                ));

    }
    /////////////////////////////////////TASK3////////////////////////////////////

    /**
     * This  method return  map  category and  averagePrice in  Category
     * @return Map<Integer, Category>
     */
    public Map<Category, BigDecimal> showAveragePriceInCategory() {
// to correct Bigdecimal !!!!
        return customersWithProducts
                .values()
                .stream()
                .flatMap(f -> f.entrySet().stream())
                .map(Map.Entry::getKey)
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add).divide(new BigDecimal(e.getValue().size()), RoundingMode.DOWN)
                ));
    }
    /**
     * This  method return  map  category and most expansive Product in this category
     * @return Map<Integer, Category>
     */

    public Map<Category, Product> mostExpProductInCategory() {
        return customersWithProducts
                .values()
                .stream()
                .flatMap(f -> f.entrySet().stream())
                .map(Map.Entry::getKey)
                .collect(Collectors.groupingBy(Product::getCategory))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().stream().max(Comparator.comparing(Product::getPrice)).get()
                ));
    }

    /**
     * This  method return  map  category and cheapest Product in this category
     * @return Map<Integer, Category>
     */
    public Map<Category, Product> cheapestProductInCategory() {
        return customersWithProducts
                .values()
                .stream()
                .flatMap(f -> f.entrySet().stream())
                .map(Map.Entry::getKey)
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
     * @return Map<Customer, Category>
     */
    public Map<Customer, Category> customersWithCategoryMostChosen() {
        return  customersWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream().collect(Collectors.toMap(ee->ee.getKey().getCategory(), Map.Entry::getValue, (v1,v2)->v1 + v2, LinkedHashMap::new))
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e->e.getValue().entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey()
                ));
    }
    /////////////////////////////////////TASK5////////////////////////////////////

    /**
     * This  method return  map  with Customer and  Debt which is a difference between cash and product's price
     * @return Map<Customer, Integer>
     */

    public Map<Customer, BigDecimal> customersWithDebts() {

        return customersWithProducts.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().entrySet().stream().map(m->m.getKey().getPrice().multiply(new BigDecimal(m.getValue()))).reduce(BigDecimal.ZERO,BigDecimal::add)
                ))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getKey().getCash().subtract( e.getValue())
                ));

    }
}

