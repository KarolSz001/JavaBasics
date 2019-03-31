package enums;

import model.CarBody;

import java.util.Random;

public enum CarBodyType {
    SEDAN, HATCHBACK,
    COMBI;

    public static CarBodyType carBodyTypeGenerator(){
        int size = CarBodyType.values().length;
        int index  = new Random().nextInt(size);
        return CarBodyType.values()[index];

    }
}
