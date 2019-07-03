package sz.karol;
import converters.CarJsonConverter2;
import exception.MyUncheckedException2;
import model.Car2;
import model.CarGenerator2;
import org.junit.Test;
public class jsonConverterTest {



    @Test(expected = MyUncheckedException2.class)
    public void shouldThrowAnyExceptionWhileFileIsNullFromJson(){
        CarJsonConverter2 carJsonConverter2 = new CarJsonConverter2(null);
        carJsonConverter2.fromJson();

    }

    @Test(expected = MyUncheckedException2.class)
    public void shouldThrowAnyExceptionWhileFileIsNullToJson(){
        CarJsonConverter2 carJsonConverter2 = new CarJsonConverter2(null);
        Car2 car2 = CarGenerator2.carGenerator();
        carJsonConverter2.toJson(car2);

    }


}
