package control;

import exception.MyUncheckedException2;

import java.util.Scanner;

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

    public  void close() {
        if (sc != null) {
            sc.close();
        }
    }

}
