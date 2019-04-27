package model;

import java.math.BigDecimal;
import java.util.Objects;


public class Customer {
    private String name;
    private int age;
    private BigDecimal cash;

    public Customer() {
    }

    public Customer(String name, int age, BigDecimal cash) {
        this.name = name;
        this.age = age;
        this.cash = cash;
    }

    public int getAge() {
        return age;
    }
    public BigDecimal getCash() {
        return cash;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cash=" + cash +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age &&
                Objects.equals(name, customer.name) &&
                Objects.equals(cash, customer.cash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, cash);
    }




}
