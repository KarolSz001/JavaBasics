package generators;

import model.Customer;

import java.math.BigDecimal;
import java.util.Random;

public class CustomerGenerator {

    public static Customer customerGenerator(){
        String name = nameGenerator();
        int age  = ageGenerator();
        BigDecimal cash = cahsGenerator();
        return new Customer(name, age, cash);
    }

    private static String nameGenerator(){
        String[] surname = {"Kowalski","Nowak","Kruk","Nowicki","Niewiadomska","Zlotopolski"};
        int size  = surname.length;
        int index = new Random().nextInt(size);
        String[] name = {"Jan","Adam","Karol","Janusz","Zofia","Agata","Teresa","Genowefa","Mariaa"};
        int size2  = name.length;
        int index2 = new Random().nextInt(size2);
        return name[index2]+surname[index];
    }

    private static int ageGenerator(){
        return new Random().nextInt(50 - 20 + 1) + 20;
    }

    private static BigDecimal cahsGenerator(){
        int cash  = new Random().nextInt(1000 - 100 + 1) + 100;
        return new BigDecimal(cash);
    }
}
