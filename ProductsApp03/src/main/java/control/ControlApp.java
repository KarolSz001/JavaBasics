package control;

import enums.Category;
import exception.MyUncheckedException;
import model.Customer;
import screen.ScreenManager;

public class ControlApp {

    private final DataManager dataManager = new DataManager();
    private final DataLoaderGenerator dlg = new DataLoaderGenerator();
    boolean runFirstTime = false;

    public void runApp() {
        do {
            try {
                if(runFirstTime == false) {
                    dlg.saveDataToFiles();
                    runFirstTime = true;
                }
                dataManager.getLine(" press ENTER to CONTINUE ");
                final ShoopingManagement shoopingManagement = new ShoopingManagement("cars02jsonFile01.json", "cars02jsonFile02.json");
                ScreenManager.printMenu();
                int number = dataManager.getInt(" MAKE A CHOICE PRESS FROM 0 TO 6 ");

                switch (number) {
                    case 0: {
                        ScreenManager.clearScreen2();
                        System.out.println(" -------------------GOOD BYE------------------------ ");
                        return;
                    }
                    case 1: {
                        task1(shoopingManagement);
                        break;
                    }
                    case 2: {
                        task2(shoopingManagement);
                        break;
                    }
                    case 3: {
                        task3(shoopingManagement);
                        break;
                    }
                    case 4: {
                        task4(shoopingManagement);
                        break;
                    }
                    case 5: {
                        task5(shoopingManagement);
                        break;
                    }
                    case 6: {
                        printRawData(shoopingManagement);
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


    private static void printRawData(ShoopingManagement s) {
        System.out.println(" Loading data.......... \n ");
        s.toString();

    }

    private Category getCategoryFromNumber(String message) {
        int parameter = dataManager.getInt(message);
        return Category.convertFromNumber(parameter);
    }

    private void task1(ShoopingManagement ss) {
        System.out.println(" Result of task 1A ........Customer who paid the most for all purchases ");
        Customer result = ss.paidTheMostTask1A();
        System.out.println(result);
        Category category = getCategoryFromNumber("Choose category press 1 - BOOK, 2 - ELECTRONIC, 3 - FOOD ");
        Customer result2 = ss.paidTheMostTask1B(category);
        System.out.println("Result of task 1B ........Customer who paid the most in Category " + category.toString());
        System.out.println(result2);
    }

    private void task2(ShoopingManagement ss) {
        System.out.println("Result of task 2A ........map  with age of customers and\n" +
                "     categories of products that were most often purchased at this age");
        ss.mappingByAgeCategoryTask2().entrySet().stream().forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
    }

    private void task3(ShoopingManagement ss) {
        System.out.println("average Price in Category");
        ss.showAveragePriceInCategoryTask3A().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("most expansive Product in  Category");
        ss.mostExpProductInCategoryTask3B().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("Cheapest product in Category");
        ss.cheapestProductInCategoryTask3C().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task4(ShoopingManagement ss) {
        System.out.println(" Customer and  Category which is most often choose");
        ss.showCutomersOfCategoryTask4().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task5(ShoopingManagement ss) {
        System.out.println("Customers and their debts");
        ss.showDebtTask5().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }
}

