package iface;

import model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Integer getCountProducts();

    Product getProductByName(String productName);

    boolean isProductOnStore(String productName);

    boolean isProductExist(String productName);

    boolean isProductExist(Long productId);
}
