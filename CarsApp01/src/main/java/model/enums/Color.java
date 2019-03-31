package model.enums;

import java.util.Random;

public enum Color {

    RED,GREEN,BLACK,ORANGE,WHITE,GREY;


    public static Color colorGenerator(){
        int size  = Color.values().length;
        int id = new Random().nextInt(size);
        return Color.values()[id];
    }
}
