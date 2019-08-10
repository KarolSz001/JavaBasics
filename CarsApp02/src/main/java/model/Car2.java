package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Car2 {


    private String model;
    private BigDecimal price;
    private int mileage;
    private Engine engine;
    private CarBody carBody;
    private Wheel wheel;

    public Car2(String model, BigDecimal price, int mileage, Engine engine, CarBody carBody, Wheel wheel) {
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.engine = engine;
        this.carBody = carBody;
        this.wheel = wheel;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = (model != null || model.matches("[A-Z\\s]+")) ? model : "FIAT";
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = (price.intValue() > 0) ? price : BigDecimal.ZERO;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = (mileage > 0) ? mileage : 0;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car2 car2 = (Car2) o;
        return mileage == car2.mileage &&
                Objects.equals(model, car2.model) &&
                Objects.equals(price, car2.price) &&
                Objects.equals(engine, car2.engine) &&
                Objects.equals(carBody, car2.carBody) &&
                Objects.equals(wheel, car2.wheel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, price, mileage, engine, carBody, wheel);
    }

    @Override
    public String toString() {
        return "Car2{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", mileage=" + mileage +
                ", engine=" + engine +
                ", carBody=" + carBody +
                ", wheel=" + wheel +
                '}';
    }

}
