package dataGenerator;

import model.Car;
import model.enums.Color;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CarGenerator {
    //String model, BigDecimal price, Color color, int mileage, List<Component> components
    public static Car carGenerator(){
        String model = modelGenerator();
        BigDecimal price = priceGenereator();
        Color color = Color.colorGenerator();
        int mileage  = mileageGenerator();
        List<String> components = componentsListCreator();

        Car car = Car.builder()
                .model(model)
                .price(price)
                .color(color)
                .mileage(mileage)
                .components(components)
                .build();
        return car;
    }
    private static String modelGenerator(){
        String[] models = {"AUDI","TESLA","HONDA","SCODA"};
        int id = new Random().nextInt(models.length);
        return models[id];
    }
    private static BigDecimal priceGenereator(){
        int number = new Random().nextInt(500000 - 100000 + 1) + 100000;
        return new BigDecimal(number);
    }
    private static int mileageGenerator(){
        return new Random().nextInt(500000 - 100000 + 1) + 100000;
    }
    private static String componentGenerator(){
        List<String> list = Arrays.asList("TV","ABS","RADIO","GPS");
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }
    private static List<String> componentsListCreator(){
        List<String> componentList = new ArrayList<>();
        int counter  = 0;
        while(counter < 3) {
            String component = componentGenerator();
            if (!componentList.contains(component)) {
                componentList.add(component);
                counter++;
            }
        }
        return componentList;
    }

}
