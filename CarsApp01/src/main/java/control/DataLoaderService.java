package control;

import converters.CarsStoresJsonConverter;
import model.Car;

import java.util.List;

public class DataLoaderService {

    private final CarsStoresJsonConverter carsJson;

    public DataLoaderService(final String jsonFilename) {
        carsJson = new CarsStoresJsonConverter(jsonFilename);
    }

    public List<Car> loadData(){
        return carsJson.fromJson().get();
    }

    public void saveToFile(){
        List<Car> carList = CarService.carsCreator();
        carsJson.toJson(carList);
    }


}
