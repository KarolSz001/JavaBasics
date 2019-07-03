package sz.karol;

import control.CarsService2;
import exception.MyUncheckedException2;
import org.junit.Test;


public class CarServiceTest {


    @Test(expected = MyUncheckedException2.class)
    public void shouldNotThrowAnyExceptionWhileListIsNull() {

        CarsService2 carsService2 = new CarsService2(null);
        carsService2.toString();

    }

}
