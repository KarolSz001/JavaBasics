package model;

import model.enums.Color;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Car {
    private String model;
    private BigDecimal price;
    private Color color;
    private int mileage;
    private List<String> components;

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setComponents(List<String> components) {
        this.components = components;
    }

    public Car() {
    }

    public Car( CarBuilder carBuilder) {
        this.model = carBuilder.model;
        this.price = carBuilder.price;
        this.color = carBuilder.color;
        this.mileage = carBuilder.mileage;
        this.components = carBuilder.components;
    }

    public String getModel() {
        return model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Color getColor() {
        return color;
    }

    public int getMileage() {
        return mileage;
    }

    public List<String> getComponents() {
        return components;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return mileage == car.mileage &&
                Objects.equals(model, car.model) &&
                Objects.equals(price, car.price) &&
                color == car.color &&
                Objects.equals(components, car.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, color, mileage, components);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", color=" + color +
                ", mileage=" + mileage +
                ", components=" + components +
                '}';
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public static class CarBuilder{
        private String model;
        private BigDecimal price;
        private int mileage;
        private Color color;
        private List<String> components;

        private final static String modelRegex = "[A-Z\\s]+";
        private final static String componentRegex = "[A-Z\\s]+";

        public CarBuilder model(String model){
            this.model = (model != null) && model.matches(modelRegex) ? model  : "MODEL" ;
            return this;
        }
        public CarBuilder price(BigDecimal price){
            this.price = (price.intValue() > 0) ? price : BigDecimal.ZERO;
            return this;
        }
        public CarBuilder color (Color color){
            this.color = color;
            return this;
        }
        public CarBuilder mileage (int mileage){
            this.mileage = (mileage > 0) ? mileage : 0;
            return this;
        }
        public CarBuilder components (List<String> components ){
            this.components = components
                    .stream()
                    .allMatch(c -> c != null && c.matches(componentRegex))
                    ? components : new ArrayList<>();
            return this;
        }
        public Car build() {
            return new Car(this);
        }

    }


}
