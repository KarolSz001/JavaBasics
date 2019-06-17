package acces;

import iface.ProductDataAccess;
import model.Product;
import parser.ProductParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDataAccessImpl implements ProductDataAccess {

    private  final String fileName;
    private  final String productType;

    public ProductDataAccessImpl(String fileName, String productType) {
        this.fileName = fileName;
        this.productType = productType;
    }

    @Override
    public void saveProduct(Product product) {
        List<Product> productList = getAllProducts();
        productList.add(product);
        saveProducts(productList);
    }

    @Override
    public void saveProducts(List<Product> products) {
        try (PrintWriter printWriter = new PrintWriter(fileName)) {
            for (int i = 0; i < products.size(); i++) {
                printWriter.println(products.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeProductById(Integer productId) {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(productId)) {
                productList.remove(i);
            }
        }
        saveProducts(productList);
    }

    @Override
    public void removeProductByName(String productName) {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals(productName)) {
                productList.remove(i);
            }
        }
        saveProducts(productList);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            while (sc.hasNextLine()) {
                Product product = ProductParser.stringToProduct(sc.nextLine(),productType);
                productList.add(product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return productList;
    }



    @Override
    public Product getProductById(Integer productId) {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(productId)) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public Product getProductByProductName(String productName) {
        List<Product> productList = getAllProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals(productName)) {
                return productList.get(i);
            }
        }
        return null;
    }
}
