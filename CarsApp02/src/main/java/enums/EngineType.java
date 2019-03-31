package enums;

import java.util.Random;

public enum EngineType {
    DIESEL, GASOLINE, LPG;


    public static EngineType engineTypeGenerator(){
        int size = EngineType.values().length;
        int index = new Random().nextInt(size);
        return EngineType.values()[index];
    }
}
