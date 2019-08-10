package main;
import service.ControlAppService;

public class Main01 {

    public static void main(String[] args) {
        final String appName = "App01 v1.03 27.04.2019 _K.Szot";
        System.out.println(appName);

        final String jsonFilename = "App01JsonFilename.json";
        ControlAppService controlAppService = new ControlAppService(jsonFilename);
        controlAppService.runApp();
    }
}
