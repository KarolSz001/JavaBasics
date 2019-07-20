package sz.karol;

import converters.CarsStoresJsonConverter;
import exception.MyUncheckedException;
import model.Car;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    private final String fileName = "jsonFile.json";


    @Test(expected = MyUncheckedException.class)
    public void shouldThrowExceptionForNullArgument() {
        final CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(fileName);
        carsJson.toJson(null);

    }


    @Test(expected = MyUncheckedException.class)
    public void shouldThrowExceptionForNullFile() {
        final CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(null);
        List<Car> carList = new ArrayList<>();
        carsJson.toJson(carList);
    }

    @Test(expected = MyUncheckedException.class)
    public void shouldThrowExceptionForNullFile2() {
        final CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(null);
        List<Car> carList = carsJson.fromJson().get();
        carList.forEach(System.out::println);

    }
}
