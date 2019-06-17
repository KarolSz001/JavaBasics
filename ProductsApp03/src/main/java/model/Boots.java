package model;


import java.math.BigDecimal;

public class Boots extends Product {
    private Integer size;
    private boolean isWaterProof;

    public Boots(Integer id, String productName, BigDecimal price, Float weight, String color, Integer productCount, Integer size, boolean isWaterProof) {
        super(id, productName, price, weight, color, productCount);
        this.size = size;
        this.isWaterProof = isWaterProof;
    }

    public Integer getSize() {
        return size;
    }

    public boolean isWaterProof() {
        return isWaterProof;
    }

    @Override
    public String toString() {
        return super.toString() + "Boots{" +
                "size='" + size + '\'' +
                ", isWaterProof=" + isWaterProof +
                '}';
    }
}