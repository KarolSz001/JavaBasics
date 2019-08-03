package control;

import converters.CarJsonConverter2;
import enums.CarBodyType;
import enums.Criterion2;
import enums.EngineType;
import exception.MyUncheckedException2;
import model.Car2;
import model.CarGenerator2;
import screen.ScreenManager2;
import java.math.BigDecimal;
import java.util.*;


public class ControlAppService2 {

    public ControlAppService2() {
        saveDataToJsonFile();
    }

    private final List<String> filesNameList = Arrays.asList("App02jsonTestFile21.json", "App02jsonTestFile22.json",
            "App02JsonFileCar3.json", "App02JsonFileCar4.json", "App02JsonFileCar5.json");
    private final DataManager2 dataManager = new DataManager2();

    public void runApp() {

        final CarsService2 carsService = new CarsService2(filesNameList);

        do {
            try {
                dataManager.getLine(" PRESS ENTER TO CONTINUE ");
                ScreenManager2.printMenu();
                int number = dataManager.getInt(" MAKE A CHOICE PRESS FROM 0 TO 7 ");
                switch (number) {
                    case 0: {
                        ScreenManager2.clearScreen2();
                        System.out.println(" -------------------GOOD BYE------------------------ ");
                        return;
                    }
                    case 1: {
                        task1(carsService);
                        break;
                    }
                    case 2: {
                        task2(carsService);
                        break;
                    }
                    case 3: {
                        task3(carsService);
                        break;
                    }
                    case 4: {
                        task4(carsService);
                        break;
                    }
                    case 5: {
                        task5(carsService);
                        break;
                    }
                    case 6: {
                        task6(carsService);
                        break;
                    }
                    case 7: {
                        printRawData(carsService.getCar2Set());
                        break;
                    }
                    default: {
                        System.out.println(" wrong choice try again ");
                        ScreenManager2.clearScreen2();
                        break;
                    }
                }

            } catch (MyUncheckedException2 e) {
                e.printStackTrace();
            }
        } while (true);
    }

    private void printRawData(Set<Car2> store) {
        System.out.println(" Loading data.......... raw data \n");
        store.stream().forEach(System.out::println);
    }

    public void saveDataToJsonFile() {
        filesNameList.forEach(name -> {
            try {
                new CarJsonConverter2(name).toJson(CarGenerator2.carGenerator());
            } catch (MyUncheckedException2 e) {
                e.printStackTrace();
            }
        });
    }

    private void task1(CarsService2 carService) {
        //components, power, size of wheel
        ScreenManager2.clearScreen2();
        Criterion2 choice = dataManager.getChoice("1");
        boolean isRev = dataManager.getBoolean(" natural sort ? ");
        carService.sortMethodByParam(choice, isRev).stream().forEach(System.out::println);
    }

    private void task2(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        //choose parameter -> SEDAN, HATCHBACK, COMBI //
        CarBodyType carBodyType = dataManager.getCarBodyType();
        BigDecimal minPrice = new BigDecimal(dataManager.getInt(" press minPrice "));
        BigDecimal maxPrice = new BigDecimal(dataManager.getInt(" press maxPrice "));
        carService.carBodyCollectionByPrice(carBodyType, minPrice, maxPrice).stream().forEach(System.out::println);
    }

    private void task3(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        EngineType engineType = dataManager.getEngineType();
        //choose parameter -> SEDAN, HATCHBACK, COMBI //
        carService.carsWithEngineType(engineType).stream().forEach(System.out::println);
    }

    private void task4(CarsService2 carService) {
        // price, mileage, power
        ScreenManager2.clearScreen2();
        Criterion2 parameter = dataManager.getChoice("2");
        carService.showStatisticByParameter(parameter);
    }

    private void task5(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        carService.mapByCarsAndMileage().entrySet().stream().forEach(System.out::println);
    }

    private void task6(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        carService.groupingByTyreType().entrySet().stream().forEach(System.out::println);
    }

}
