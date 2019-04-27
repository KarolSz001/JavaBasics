package model;

import java.util.List;
import java.util.Objects;

public class CustomerWithProducts {

    private Customer customer;
    private List<Product> products;

    public CustomerWithProducts(Customer customer, List<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "CustomerWithProducts{" +
                "customer=" + customer +
                ", products=" + products +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerWithProducts that = (CustomerWithProducts) o;
        return Objects.equals(customer, that.customer) &&
                Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, products);
    }


}
