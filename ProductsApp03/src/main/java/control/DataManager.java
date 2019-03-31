package control;

import exception.MyUncheckedException;

import java.util.Scanner;

public class DataManager {

    private final  Scanner sc = new Scanner(System.in);

    public  Integer getInt(String message) {
        System.out.println(message);

        String line = sc.nextLine();
        if (line == null || !line.matches("\\d+")) {
            throw new MyUncheckedException("WRONG DATA TRY AGIAN");
        }

        return Integer.parseInt(line);
    }

    public String getLine(String message){
        System.out.println(message);
        return sc.nextLine();
    }


    public  void close() {
        if (sc != null) {
            sc.close();
        }
    }

}
