package sz.karol;

import control.DataManager2;
import org.junit.Test;

public class DataManagerTest {

    DataManager2 dataManager2 =  new DataManager2();

    @Test
    public void shouldThrowAnyExceptionWhileArgIsNotInt(){
        Integer number  = dataManager2.getInt("AAAA");


    }


}
