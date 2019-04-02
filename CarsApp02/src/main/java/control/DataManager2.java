package control;

import enums.Criterion2;
import exception.MyUncheckedException2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class DataManager2 {

    private final   Scanner sc = new Scanner(System.in);

    public  Integer getInt(String message) {
        System.out.println(message);
        String line = sc.nextLine();
        if (line == null || !line.matches("\\d+")) {
            throw new MyUncheckedException2(" WRONG DATA try again ");
        }
        return Integer.parseInt(line);
    }

    public  String getLine(String message) {
        System.out.println(message);
        return sc.nextLine();
    }

    public boolean getBoolean(String message){
        System.out.println("[y/n] ?");
        return sc.nextLine().toUpperCase().charAt(0) == 'Y';
    }

    public Criterion2 getChoice(){
        Criterion2[] crits = Criterion2.values();
        AtomicInteger atomicInteger = new AtomicInteger(1);

        Arrays.stream(crits).forEach(choice -> System.out.println(atomicInteger.getAndIncrement() + " " + choice));
        System.out.println(" Make a choice press number ");
        String number = sc.nextLine();
        if(!number.matches("[1-" + crits.length + "]" )){
            throw new MyUncheckedException2(" you press wrong number ");
        }
        return crits[Integer.parseInt(number) - 1];
    }

    public  void close() {
        if (sc != null) {
            sc.close();
        }
    }

}
