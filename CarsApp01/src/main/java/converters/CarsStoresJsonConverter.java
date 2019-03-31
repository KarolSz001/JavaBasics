package converters;

import model.Car;

import java.util.List;


public class CarsStoresJsonConverter extends JsonConverter<List<Car>> {
    public CarsStoresJsonConverter(String jsonFilename) {
        super(jsonFilename);
    }
}
