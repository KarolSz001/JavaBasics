package control;

import control.enums.Criterion;
import exception.MyUncheckedException;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DataManager {

    private final Scanner sc = new Scanner(System.in);

    public Integer getInt(String message) {
        System.out.println(message);

        String line = sc.nextLine();
        if (line == null || !line.matches("\\d+")) {
            throw new MyUncheckedException("WRONG DATA TRY AGAIN");
        }

        return Integer.parseInt(line);
    }


    public String getLine(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public Criterion getChoice() {
        Criterion[] criteria = Criterion.values();
        AtomicInteger counter = new AtomicInteger(1);

        Arrays.stream(criteria).forEach(criterion -> System.out.println(counter.getAndIncrement() + ". " + criterion));
        System.out.println("Enter choice number:");
        String text = sc.nextLine();

        if (!text.matches("[1-" + criteria.length + "]")) {
            throw new MyUncheckedException(" Choice number is not correct ");
        }
        return criteria[Integer.parseInt(text) - 1];
    }

    public boolean getBoolean(String message) {
        System.out.println(message + "[y/n]?");
        return sc.nextLine().toUpperCase().charAt(0) == 'Y';
    }

    public void close() {
        if (sc != null) {
            sc.close();
        }
    }

}
