package enums;

import java.util.Random;

public enum TyreType {
    WINTER, SUMMER;

    public static TyreType tyreTypeGenerator(){
        int size  = TyreType.values().length;
        int index = new Random().nextInt(size);
        return TyreType.values()[index];
    }
}
