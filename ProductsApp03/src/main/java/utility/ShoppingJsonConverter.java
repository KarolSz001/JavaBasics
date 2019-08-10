package utility;

import model.CustomerWithProductsFile;

public class ShoppingJsonConverter extends JsonConverter<CustomerWithProductsFile> {
    public ShoppingJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
