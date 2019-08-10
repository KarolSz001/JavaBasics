package sz.karol;

import org.junit.Test;
import service.DataLoaderService;
import exception.MyUncheckedException;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class DataLoaderServiceTest
{

    @Test
    @DisplayName(" run constructor with null arg file Test 1 ")
    public void shouldThrowExceptionForNullArgumentInConstructor() {

        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> new DataLoaderService(null));
        Assertions.assertEquals(" null file args for DataLoaderService constructor ",e.getMessage());
    }

    @Test
    @DisplayName(" saveFile method with wrong args Test 2 ")
    public void shouldThrowExceptionForWrongArgInMethod() {

        DataLoaderService dataLoaderService = new DataLoaderService("test.json");
        MyUncheckedException e = Assertions.assertThrows(MyUncheckedException.class, ()-> dataLoaderService.saveToFile(-4));
        Assertions.assertEquals(" wrong args for saveToFile ",e.getMessage());
    }

}
