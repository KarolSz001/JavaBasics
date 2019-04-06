package main;

import control.*;
public class Main {

    public static void main(String[] args) {
        final String appName = " App03 v1.2 06.04.2019 ";
        System.out.println(appName);
        ControlAppService3 ca = new ControlAppService3();
        ca.runApp();
    }
}
// save to json file as a list , next load and convert to map !!
// changed list to map as a major container of data
