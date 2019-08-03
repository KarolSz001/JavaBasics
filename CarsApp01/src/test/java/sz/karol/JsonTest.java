package sz.karol;

import converters.CarsStoresJsonConverter;
import exception.MyUncheckedException;
import model.Car;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

public class JsonTest {

    private final String fileName = "jsonTestFile.json";


    @Test(expected = MyUncheckedException.class)
    @DisplayName("Test 1 -> shouldThrowExceptionForNullArgument ")
    public void shouldThrowExceptionForNullArgument() {
        CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(fileName);
        carsJson.toJson(null);
    }


    @Test(expected = MyUncheckedException.class)
    @DisplayName("Test 1 -> shouldThrowExceptionForNullFile ")
    public void shouldThrowExceptionForNullFile() {
        CarsStoresJsonConverter carsJson = new CarsStoresJsonConverter(null);
        List<Car> carList = new ArrayList<>();
        carsJson.toJson(carList);
    }

    @Test
    @DisplayName("Test 1 -> shouldThrowExceptionForNullFile2 ")
    public void shouldThrowExceptionForNullFile2() {
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()->new CarsStoresJsonConverter(null));
        Assertions.assertEquals( "null argument for jsonFile",e.getMessage());

    }




}
