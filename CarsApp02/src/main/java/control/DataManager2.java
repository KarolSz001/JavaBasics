package control;

import enums.CarBodyType;
import enums.Criterion2;
import enums.EngineType;
import exception.MyUncheckedException2;
import model.CarBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DataManager2 {

    private final Scanner sc = new Scanner(System.in);

    public Integer getInt(String message) {
        System.out.println(message);
        String line = sc.nextLine();
        if (line == null || !line.matches("\\d+")) {
            throw new MyUncheckedException2(" WRONG DATA try again ");
        }
        return Integer.parseInt(line);
    }

    public String getLine(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public boolean getBoolean(String message) {
        System.out.println(message + "press -> [y/n] ?");
        return sc.nextLine().toUpperCase().charAt(0) == 'Y';
    }

    public Criterion2 getChoice(String type) {
        Criterion2[] crits = Criterion2.values();

        if (type.matches("1")) {
            // PRICE,MILEAGE,POWER,NUMBER_COMPONENTS,SIZE_WHEEL,
            AtomicInteger atomicInteger = new AtomicInteger(1);
            Arrays.stream(crits).forEach(choice -> System.out.println(atomicInteger.getAndIncrement() + " " + choice));
            System.out.println("Make a choice");
            String number = sc.nextLine();
            if (!number.matches("[1-" + crits.length + "]")) {
                throw new MyUncheckedException2(" you press wrong number ");
            }
            return crits[Integer.parseInt(number) - 1];

        } else {
            // price, power, mileage
            System.out.println(" Make a choice press number 1 -> price, 2 -> mileage, 3 -> power ");
            String number = sc.nextLine();

            if (!number.matches("[123]")) {
                throw new MyUncheckedException2(" you press wrong number ");
            }
            return crits[Integer.parseInt(number) - 1];
        }
    }

    public void close() {
        if (sc != null) {
            sc.close();
        }
    }

    public CarBodyType getCarBodyType() {
        CarBodyType[] cbt = CarBodyType.values();
        AtomicInteger atm = new AtomicInteger(1);

        Arrays.stream(cbt).forEach(s -> System.out.println(atm.getAndIncrement() + " ----> " + s));
        System.out.println(" Make a choice");
        String number = sc.nextLine();

        if (!number.matches("[1-" + cbt.length + "]")) {
            throw new MyUncheckedException2(" wrong number");
        }
        return cbt[Integer.parseInt(number) - 1];
    }

    //getEngineType();
    public EngineType getEngineType() {
        EngineType[] et = EngineType.values();
        AtomicInteger atm = new AtomicInteger(1);

        Arrays.stream(et).forEach(s -> System.out.println(atm.getAndIncrement() + " ----> " + s));
        System.out.println(" Make a choice");
        String number = sc.nextLine();

        if (!number.matches("[1-" + et.length + "]")) {
            throw new MyUncheckedException2(" wrong number");
        }
        return et[Integer.parseInt(number) - 1];
    }


}
