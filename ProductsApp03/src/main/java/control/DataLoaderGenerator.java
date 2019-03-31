package control;

import converters.ShoppingJsonConverter;
import generators.CustomerGenerator;
import generators.ProductGenerator;
import model.CustomerWithProducts;
import model.CustomerWithProductsFile;

import java.util.*;

public class DataLoaderGenerator {

    /**
     * This method return List of CustomerWithProducts objects
     * creat object Store include Customer and Set<Product>
     * @ return List of  elements
     * work correctly
     */

    private List<CustomerWithProducts> customerWithProductListGenerator() {
        List<CustomerWithProducts> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            CustomerWithProducts cp = new CustomerWithProducts(CustomerGenerator.customerGenerator(),
                    Arrays.asList(ProductGenerator.productBookGenerator(), ProductGenerator.productElectronicGenerator()));
            list.add(cp);
        }
        return list;
    }
    /**
     * This method save data to Json Files
     * creat List<CustomerWithProducts> and CustomerWithProductsFile
     * and ShoppingJsonConverter to send Data to Json File;
     */

    public void saveDataToFiles() {
        for (int i = 1; i < 3; i++) {
            List<CustomerWithProducts> customerWithProductsList = customerWithProductListGenerator();
            String fileName = "cars02jsonFile0" + i + ".json";
            CustomerWithProductsFile cpf = new CustomerWithProductsFile(customerWithProductsList);
            ShoppingJsonConverter shoppingJsonConverter = new ShoppingJsonConverter(fileName);
            shoppingJsonConverter.toJson(cpf);
        }
    }

//cars02jsonFile01.json
}
