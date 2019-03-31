package model;

import enums.TyreType;

import java.util.Objects;
import java.util.Random;

public class Wheel {

    private String model;
    private int size;
    private TyreType tyreType;

    public Wheel() {
    }

    public Wheel(String model, int size, TyreType tyreType) {
        setModel(model);
        setSize(size);
        this.tyreType = tyreType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = (model != null || model.matches("[A-Z\\s]+")) ? model : "DEBICA" ;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = (size > 0) ? size : 20;
    }

    public TyreType getTyreType() {
        return tyreType;
    }

    public void setTyreType(TyreType tyreType) {
        this.tyreType = tyreType;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "model='" + model + '\'' +
                ", size=" + size +
                ", tyreType=" + tyreType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wheel wheel = (Wheel) o;
        return size == wheel.size &&
                Objects.equals(model, wheel.model) &&
                tyreType == wheel.tyreType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size, tyreType);
    }

    public static Wheel wheelGenerator(){
        String model = modelGenerator();
        int size = sizeGenerator();
        TyreType tyreType = TyreType.tyreTypeGenerator();
        return new Wheel(model,size,tyreType);
    }
    private static String modelGenerator(){
        String[] modelArr = {"DEBICA","Motiv","Pacer"};
        int index  = new Random().nextInt(modelArr.length);
        return modelArr[index];
    }

    private static int sizeGenerator(){
        int[] sizeArr = {17, 19, 21};
        int index  = new Random().nextInt(sizeArr.length);
        return sizeArr[index];
    }

}
