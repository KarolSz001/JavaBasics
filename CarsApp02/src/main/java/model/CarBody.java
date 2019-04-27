package model;

import enums.CarBodyColor;
import enums.CarBodyType;

import java.util.*;


public class CarBody {
    private final static String componentRegex = "[A-Z\\s]+";
    private CarBodyColor color;
    private CarBodyType type;
    private List<String> components;

    public CarBody() {
    }

    public CarBody(CarBodyColor color, CarBodyType type, List<String> components) {
        this.color = color;
        this.type = type;
        this.components = components;
    }


    public CarBodyType getType() {
        return type;
    }

    public List<String> getComponents() {
        return components;
    }

    public void setComponents(List<String> components) {
        this.components = components
                .stream()
                .allMatch(c -> c != null && c.matches(componentRegex))
                ? components : new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBody carBody = (CarBody) o;
        return color == carBody.color &&
                type == carBody.type &&
                Objects.equals(components, carBody.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, components);
    }

    @Override
    public String toString() {
        return "CarBody{" +
                "color=" + color +
                ", type=" + type +
                ", components=" + components +
                '}';
    }

    public static CarBody carBodyGenerator() {
        CarBodyColor color = CarBodyColor.carBodyColorGenerator();
        CarBodyType carBodyType = CarBodyType.carBodyTypeGenerator();
        List<String> componentList = componentsListCreator();
        return new CarBody(color, carBodyType, componentList);
    }

    private static String componentGenerator() {
        List<String> list = Arrays.asList("TV", "ABS", "RADIO", "GPS");
        int index = new Random().nextInt(list.size());
        return list.get(index);
    }

    private static List<String> componentsListCreator() {
        List<String> componentList = new ArrayList<>();
        int counter = 0;
        while (counter < 3) {
            String component = componentGenerator();
            if (!componentList.contains(component)) {
                componentList.add(component);
                counter++;
            }
        }
        return componentList;

    }
}
