package model;

import java.util.List;
import java.util.Objects;

public class CustomerWithProductsFile {
    List<CustomerWithProducts> customerWithProducts;

    public CustomerWithProductsFile() {
    }

    public CustomerWithProductsFile(List<CustomerWithProducts> customerWithProducts) {
        this.customerWithProducts = customerWithProducts;
    }

    public List<CustomerWithProducts> getCustomerWithProducts() {
        return customerWithProducts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerWithProductsFile that = (CustomerWithProductsFile) o;
        return Objects.equals(customerWithProducts, that.customerWithProducts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerWithProducts);
    }

    @Override
    public String toString() {
        return "CustomerWithProductsFile{" +
                "customerWithProducts=" + customerWithProducts +
                '}';
    }
}
