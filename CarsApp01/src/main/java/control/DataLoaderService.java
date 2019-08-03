package control;

import converters.CarsStoresJsonConverter;
import exception.MyUncheckedException;
import model.Car;

import java.util.List;

public class DataLoaderService {

    private final CarsStoresJsonConverter carsJson;


    public DataLoaderService(final String jsonFilename) {
        if (jsonFilename == null) {
            throw new MyUncheckedException(" null file args for DataLoaderService constructor ");
        }
        carsJson = new CarsStoresJsonConverter(jsonFilename);
    }

    public List<Car> loadData() {
        return carsJson.fromJson().get();
    }

    public void saveToFile(int numberOfCars) {

        if (numberOfCars <= 0) {
            throw new MyUncheckedException(" wrong args for saveToFile ");
        }
        List<Car> carList = CarService.carsCreator(numberOfCars);
        carsJson.toJson(carList);
    }


}
