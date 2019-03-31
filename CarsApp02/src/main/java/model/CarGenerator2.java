package model;

import java.math.BigDecimal;
import java.util.Random;

public class CarGenerator2 {

    public static Car2 carGenerator(){
        String model = modelCarGenerator();
        BigDecimal price = priceGenerator();
        int mileage = mileageGenerator();
        Engine engine = Engine.engineGenerator();
        CarBody carBody = CarBody.carBodyGenerator();
        Wheel wheel = Wheel.wheelGenerator();
        return new Car2(model,price,mileage,engine,carBody,wheel);
    }

    private static String modelCarGenerator() {
        String[] modelCarArr = {"AUDI", "OPEL", "TESLA", "FIAT", "SYRENKA", "FERRARI"};
        int size = modelCarArr.length;
        int indexOfArr = new Random().nextInt(size);
        return modelCarArr[indexOfArr];
    }

    private static BigDecimal priceGenerator() {
        int minPrice = 300000;
        int maxPrice = 1000000;
        int price = new Random().nextInt(maxPrice - minPrice + 1) + minPrice;
        return new BigDecimal(price);
    }
    private static int mileageGenerator(){

        return new Random().nextInt(500000 - 100000 + 1) + 100000;
    }
}
