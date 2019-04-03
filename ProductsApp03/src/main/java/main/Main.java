package main;

import control.*;
public class Main {

    public static void main(String[] args) {
        final String appName = " App03 v1.2 03.04.2019 ";
        System.out.println(appName);
        ControlAppService3 ca = new ControlAppService3();
        ca.runApp();
    }
}
