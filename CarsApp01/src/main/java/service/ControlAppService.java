package service;

import enums.Criterion;
import exception.MyUncheckedException;
import utility.DataManager;
import utility.ScreenManager;

import java.math.BigDecimal;


public class ControlAppService {

    private DataLoaderService dataLoaderService;
    private final DataManager dataManager;
    private final CarService carService;
    private final int numberOfCars = 4;

    public ControlAppService(final String jsonFilename) {
        dataLoaderService = new DataLoaderService(jsonFilename);
        dataLoaderService.saveToFile(numberOfCars);
        dataManager = new DataManager();
        carService = new CarService(dataLoaderService);
    }


    public void runApp() {
        do {
            try {
                dataManager.getLine("PRESS KEY TO CONTINUE");
                ScreenManager.printMenu();
                int number = dataManager.getInt(" MAKE A CHOICE PRESS FROM 0 TO 10 ");
                switch (number) {
                    case 0: {
                        ScreenManager.clearScreen2();
                        dataManager.close();
                        System.out.println(" -------------------GOOD BYE------------------------ ");
                        return;
                    }
                    case 1: {
                        task1(carService);
                        break;
                    }
                    case 2: {
                        task2(carService);
                        break;
                    }
                    case 3: {
                        task3(carService);
                        break;
                    }
                    case 4: {
                        task4(carService);
                        break;
                    }
                    case 5: {
                        task5(carService);
                        break;
                    }
                    case 6: {
                        task6(carService);
                        break;
                    }
                    case 7: {
                        task7(carService);
                        break;
                    }
                    case 8: {
                        task8(carService);
                        break;
                    }
                    case 9: {
                        task9(carService);
                        break;
                    }
                    case 10: {
                        task10(carService);
                        break;
                    }
                    default: {
                        System.out.println(" wrong choice try again ");
                        ScreenManager.clearScreen2();
                        break;
                    }
                }
            } catch (MyUncheckedException e) {
                e.printStackTrace();
            }

        } while (true);
    }


    private void task1(CarService carService) {
        System.out.println(" Loading data.......... raw data \n");
        System.out.println(carService);

    }

    private void task2(CarService carService) {
        ScreenManager.clearScreen2();
        Criterion criterion = dataManager.getChoice();
        boolean descending = dataManager.getBoolean("Descending ???");
        carService.sort(criterion, descending).forEach(System.out::println);
    }

    private void task3(CarService carService) {
        ScreenManager.clearScreen2();
        int number = dataManager.getInt(" Filtered by mileage , press number ");
        carService.collectionByMileage(number).forEach(System.out::println);
    }

    private void task4(CarService carService) {
        ScreenManager.clearScreen2();
        carService.groupedAndCountedByColor().entrySet().stream().forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
    }

    private void task5(CarService carService) {
        ScreenManager.clearScreen2();
        carService.mostExpensiveInModel().entrySet().forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
    }

    private void task6(CarService carService) {

        carService.showStatisticsOfPriceAndMileage();
    }

    private void task7(CarService carService) {
        ScreenManager.clearScreen2();
        System.out.println(carService.mostExpansiveInList());
    }

    private void task8(CarService carService) {
        ScreenManager.clearScreen2();
        carService.withSortedComponents().stream().forEach(System.out::println);
    }

    private void task9(CarService carService) {
        ScreenManager.clearScreen2();
        carService.componentsWithListOfCars().entrySet().stream().forEach((k -> System.out.println(k.getKey() + "::" + k.getValue())));
    }

    private void task10(CarService carService) {
        ScreenManager.clearScreen2();
        BigDecimal minPrice = new BigDecimal(dataManager.getInt(" give min price"));
        BigDecimal maxPrice = new BigDecimal(dataManager.getInt(" give max price"));
        carService.filteredByPriceInRange(minPrice, maxPrice).forEach(System.out::println);
    }
}
