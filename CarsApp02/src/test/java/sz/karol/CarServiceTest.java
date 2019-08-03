package sz.karol;

import control.CarsService2;
import enums.CarBodyType;
import exception.MyUncheckedException2;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.util.List;


public class CarServiceTest {

    CarsService2 carsService2 = new CarsService2(List.of("App02jsonTestFile21.json","App02jsonTestFile22.json"));

    @Test()
    @DisplayName("shouldNotThrowNoException Test 1")
    public void shouldNotThrowNoException() {
        System.out.println(carsService2.toString());
    }



    @Test()
    @DisplayName("shouldThrowExceptionForNullArgs Test 2")
    public void shouldThrowExceptionForNullArgs() {

        MyUncheckedException2 e = Assertions.assertThrows(MyUncheckedException2.class, ()-> new CarsService2(null));
        Assertions.assertEquals(" Argument is null in CarsService2 Constructor ",e.getMessage());
    }

    @Test()
    @DisplayName("shouldThrowExceptionForNullArgsInMethod Test 3")
    public void shouldThrowExceptionForNullArgsInMethod(){
        MyUncheckedException2 e = Assertions.assertThrows(MyUncheckedException2.class, ()-> carsService2.sortMethodByParam(null,true));
        Assertions.assertEquals(" null argument in sortMethodByParam method ",e.getMessage());
    }

    @Test()
    @DisplayName("shouldThrowExceptionForWrongArgsInMethod Test 4")
    public void shouldThrowExceptionForWrongArgsInMethod(){

        MyUncheckedException2 e = Assertions.assertThrows(MyUncheckedException2.class, ()-> carsService2.carBodyCollectionByPrice(CarBodyType.COMBI,new BigDecimal(100),new BigDecimal(50)));
        Assertions.assertEquals("WRONG RANGE MIN IS BIGGER THAN MAX",e.getMessage());

    }

    @Test()
    @DisplayName("shouldThrowExceptionForWrongArgsInMethod Test 5")
    public void shouldThrowExceptionForNullArgsInMethod2(){

        MyUncheckedException2 e = Assertions.assertThrows(MyUncheckedException2.class, ()-> carsService2.carBodyCollectionByPrice(null,new BigDecimal(1),new BigDecimal(50)));
        Assertions.assertEquals("ARGUMENT IN carBodyCollectionByPrice is NULL",e.getMessage());

    }

    @Test()
    @DisplayName("shouldReturnCorrectNUmberOfCarsWithProperEngine Test 6")
    public void shouldReturnCorrectNUmberOfCarsWithProperEngine(){

        Assertions.assertEquals(2,carsService2.getCar2Set().size()  );

    }


}
