package model;


import java.math.BigDecimal;

public class Tshirt extends Product {

    private String size;
    private String material;

    public Tshirt (Integer id, String productName, BigDecimal price, Float weight, String color, Integer productCount, String size, String material) {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.material = material;
    }

    public String getSize() {
        return size;
    }
    public String getMaterial() {
        return material;
    }
    @Override
    public String toString() {
        return super.toString() + "Tshirt{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}