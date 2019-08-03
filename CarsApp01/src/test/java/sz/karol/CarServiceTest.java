package sz.karol;

import control.CarService;
import control.DataLoaderService;
import model.Car;
import org.junit.Test;
import exception.MyUncheckedException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.math.BigDecimal;
import java.util.List;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CarServiceTest {

    private DataLoaderService dataLoaderService = new DataLoaderService("jsonTestFile.json");
    private CarService carService = new CarService(dataLoaderService);


    @Test
    @DisplayName(" carsCreator method with wrong args Test 1 ")
    public void shouldThrowExceptionForWrongArgInCarsCreatorMethod() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> CarService.carsCreator(-10));
        Assertions.assertEquals(" wrong args in 'carsCreator' method ",e.getMessage());

    }

    @Test
    @DisplayName("find all cars Test 2")
    public void shouldReturnCorrectNumberOfCars(){

        List<Car> carsList = carService.findAllCars();
        Assertions.assertEquals(5,carsList.size());

    }

    @Test
    @DisplayName("find all people Test 3")
    public void shouldReturnNotExpectedNumberOfCars(){

        List<Car> carsList = carService.findAllCars();
        Assertions.assertNotEquals(4,carsList.size());

    }

    @Test
    @DisplayName(" method sort with null args Test 4 ")
    public void shouldThrowExceptionForWrongArgInSortMethod(){

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> carService.sort(null,true));
        Assertions.assertEquals(" null args in sort method ",e.getMessage());
    }

    @Test
    @DisplayName(" method filteredByPriceInRange with wrong args Test 5 ")
    public void shouldThrowExceptionForWrongArgInFilteredByPriceInRange(){

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> carService.filteredByPriceInRange(new BigDecimal(100),new BigDecimal(50)));
        Assertions.assertEquals(" Range is not correct in filteredByPriceInRange method ",e.getMessage());
    }

    @Test
    @DisplayName(" method filteredByPriceInRange should return correct numbers of Cars from Range Test 6 ")
    public void shouldReturnCorrectNumberOfCarsFromMethod(){

        List<Car> carList =  carService.filteredByPriceInRange(new BigDecimal(0),new BigDecimal(10000000));
        Assertions.assertEquals(5,carList.size());

    }





}
