package control;

import converters.CarJsonConverter2;
import enums.CarBodyType;
import enums.Criterion2;
import enums.EngineType;
import exception.MyUncheckedException2;
import model.Car2;
import model.CarGenerator2;
import screen.ScreenManager2;
import java.util.*;

public class ControlApp2 {

    public ControlApp2() {
        carsFileConstructor();
    }

    private final List<String> filesNameList = Arrays.asList("jsonFileCar1.json", "jsonFileCar2.json",
            "jsonFileCar3.json", "jsonFileCar4.json", "jsonFileCar5.json");
    private final DataManager2 dataManager = new DataManager2();

    public void runApp() {

        final CarsService2 carsService = new CarsService2(filesNameList);
        ScreenManager2.printMenu();
        do {
            try {
                dataManager.getLine("PRESS ENTER TO CONTINUE");
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
        }while(true);
    }

    private void printRawData(Set<Car2> store) {
        System.out.println(" Loading data.......... raw data \n");
        store.stream().forEach(s -> System.out.println(s));
    }

    public void carsFileConstructor() {
        filesNameList.forEach(name -> {
            try {
                new CarJsonConverter2(name).toJson(CarGenerator2.carGenerator());
            } catch (MyUncheckedException2 e) {
                e.printStackTrace();
            }
        });
    }

    private void task1(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        String st = dataManager.getLine(" choose parameter -> components, power, size of wheel ").toUpperCase();
        Criterion2 choice = Criterion2.valueOf(st);
        int number = dataManager.getInt(" press 0 for natural sort and other number for reverseSort ");
        boolean isRev = number == 0;
        carService.sortMethodByParam(choice, isRev);
    }

    private void task2(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        CarBodyType carBodyType = CarBodyType.valueOf(dataManager.getLine(" choose parameter -> SEDAN, HATCHBACK, COMBI ").toUpperCase());
        int minPrice = dataManager.getInt(" press minPrice ");
        int maxPrice = dataManager.getInt(" press maxPrice ");
        carService.carBodyCollectionByPrice(carBodyType, minPrice, maxPrice).stream().forEach(s -> System.out.println());
    }

    private void task3(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        EngineType engineType = EngineType.valueOf(dataManager.getLine(" choose parameter -> DIESEL, GASOLINE, LPG "));
        carService.carsWithEngineType(engineType).stream().forEach(s -> System.out.println());
    }

    private void task4(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        String st = dataManager.getLine(" choose parameter -> price, mileage, power ");
        Criterion2 parameter = Criterion2.valueOf(st);
        carService.showStatisticByParameter(parameter);
    }

    private void task5(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        carService.mapByCarsAndMileage().entrySet().stream().forEach(s -> System.out.println(s));
    }

    private void task6(CarsService2 carService) {
        ScreenManager2.clearScreen2();
        carService.mapByTyreTypeTask6().entrySet().stream().forEach(s -> System.out.println(s));
    }

}
