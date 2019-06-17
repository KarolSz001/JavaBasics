package parser;

import model.Boots;
import model.Product;
import model.Tshirt;

import java.math.BigDecimal;

public class ProductParser {

    public static Product stringToProduct(String productStr, String productType) {
        if (productType.equals("PRODUCT")) {
            return convertToProduct(productStr);
        } else if (productType.equals("TSHIRT")) {
            return convertToTshirt(productStr);
        } else if (productType.equals("BOOTS")) {
            return convertToBoots(productStr);
        }
        return null;
    }



    private static Boots convertToBoots(String productStr) {
        String [] productInformations = productStr.split(Product.SEPARATOR);

        Integer id = Integer.parseInt(productInformations[0]);
        String productName = productInformations[1];
        BigDecimal price = BigDecimal.valueOf(Float.valueOf(productInformations[2]));
        Float weight = Float.parseFloat(productInformations[3]);
        String color = productInformations[4];
        Integer productCount = Integer.parseInt(productInformations[5]);
        Integer size = Integer.parseInt(productInformations[6]);
        Boolean isNaturalSkin = Boolean.parseBoolean(productInformations[7]);

        return new Boots(id, productName, price, weight, color, productCount, size, isNaturalSkin);
    }

    private static Tshirt convertToTshirt(String productStr) {
        String [] productInformations = productStr.split(Product.SEPARATOR);

        Integer id = Integer.parseInt(productInformations[0]);
        String productName = productInformations[1];
        BigDecimal price = BigDecimal.valueOf(Float.valueOf(productInformations[2]));
        Float weight = Float.parseFloat(productInformations[3]);
        String color = productInformations[4];
        Integer productCount = Integer.parseInt(productInformations[5]);
        String size = productInformations[6];
        String material = productInformations[7];

        return new Tshirt(id, productName, price, weight, color, productCount, size, material);
    }

    private static Product convertToProduct(String productStr) {
        String [] productInformations = productStr.split(Product.SEPARATOR);

        Integer id = Integer.parseInt(productInformations[0]);
        String productName = productInformations[1];
        BigDecimal price = BigDecimal.valueOf(Float.valueOf(productInformations[2]));
        Float weight = Float.parseFloat(productInformations[3]);
        String color = productInformations[4];
        Integer productCount = Integer.parseInt(productInformations[5]);

        return new Product(id, productName, price, weight, color, productCount);
    }
}
