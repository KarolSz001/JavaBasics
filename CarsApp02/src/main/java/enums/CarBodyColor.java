package enums;

import java.util.Random;

public enum CarBodyColor {
    BLACK, SILVER, WHITE, RED, BLUE,
    GREEN;

    public static CarBodyColor carBodyColorGenerator(){
        int size  = CarBodyColor.values().length;
        int index = new Random().nextInt(size);
        return CarBodyColor.values()[index];
    }


}
