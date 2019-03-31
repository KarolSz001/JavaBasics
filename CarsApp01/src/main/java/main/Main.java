package main;
import control.ControlAppService;

public class Main {

    public static void main(String[] args) {
        final String appName = "App01 v0.3 30.03.2019 _K.Szot";
        System.out.println(appName);

        final String jsonFilename = "cars01JsonFilename.json";
        ControlAppService controlAppService = new ControlAppService(jsonFilename);
        controlAppService.runApp();
    }
}
