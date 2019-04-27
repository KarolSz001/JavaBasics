package main;

import control.ControlAppService2;

public class Main02 {
    public static void main(String[] args) {

        final String appName = "CarsApp02 v1.01 27.04.2019 _K.Szot";
        ControlAppService2 ca2 = new ControlAppService2();
        System.out.println(appName);
        ca2.runApp();
    }
}
