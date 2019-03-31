package model;

import enums.EngineType;

import java.util.Objects;
import java.util.Random;

import static enums.EngineType.engineTypeGenerator;

public class Engine {

    private EngineType engineType;
    private double power;

    public Engine() {
    }

    public Engine(EngineType engineType, double power) {
        setEngineType(engineType);
        setPower(power);
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = (power > 0) ? power : 1;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "engineType=" + engineType +
                ", power=" + power +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Engine engine = (Engine) o;
        return Double.compare(engine.power, power) == 0 &&
                engineType == engine.engineType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(engineType, power);
    }

    public static Engine engineGenerator(){
        EngineType engineType = EngineType.engineTypeGenerator();
        double power = powerNumberGenerator();
        return new Engine(engineType,power);
    }

    private static double powerNumberGenerator(){
        double[] powerArr = {81.0, 100.0, 102.2, 120.5 };
        int arrIndex =  new Random().nextInt(powerArr.length);
        return powerArr[arrIndex];
    }
}
