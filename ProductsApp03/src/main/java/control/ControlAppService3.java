package control;

import enums.Category;
import exception.MyUncheckedException;
import model.Customer;
import screen.ScreenManager;

public class ControlAppService3 {

    private final DataManager dataManager = new DataManager();

    public void runApp() {
        do {

            try {
                dataManager.getLine(" press ENTER to CONTINUE ");
                final ShoppingManager shoppingManager = new ShoppingManager("cars02jsonFile01.json", "cars02jsonFile02.json");
                ScreenManager.printMenu();
                int number = dataManager.getInt(" MAKE A CHOICE PRESS FROM 0 TO 6 ");

                switch (number) {
                    case 0: {
                        ScreenManager.clearScreen2();
                        System.out.println(" -------------------GOOD BYE------------------------ ");
                        return;
                    }
                    case 1: {
                        task1(shoppingManager);
                        break;
                    }
                    case 2: {
                        task2(shoppingManager);
                        break;
                    }
                    case 3: {
                        task3(shoppingManager);
                        break;
                    }
                    case 4: {
                        task4(shoppingManager);
                        break;
                    }
                    case 5: {
                        task5(shoppingManager);
                        break;
                    }
                    case 6: {
                        printRawData(shoppingManager);
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


    private static void printRawData(ShoppingManager s) {
        System.out.println(" Loading data.......... \n ");
        s.toString();

    }

    private Category getCategoryFromNumber(String message) {
        int parameter = dataManager.getInt(message);
        return Category.convertFromNumber(parameter);
    }

    private void task1(ShoppingManager ss) {
        System.out.println(" Result of task 1A ........Customer who paid the most for all purchases ");
        Customer result = ss.whoPaidTheMost();
        System.out.println(result);
        Category category = getCategoryFromNumber("Choose category press 1 - BOOK, 2 - ELECTRONIC, 3 - FOOD ");
        Customer result2 = ss.whoPaidTheMostInSelectedCategory(category);
        System.out.println("Result of task 1B ........Customer who paid the most in Category " + category.toString());
        System.out.println(result2);
    }

    private void task2(ShoppingManager ss) {
        System.out.println("Result of task 2A ........map  with age of customers and\n" +
                "     categories of products that were most often purchased at this age");
        ss.mappingByAgeCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + ":::" + s.getValue()));
    }

    private void task3(ShoppingManager ss) {
        System.out.println("average Price in Category");
        ss.showAveragePriceInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("most expansive Product in  Category");
        ss.mostExpProductInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
        System.out.println("Cheapest product in Category");
        ss.cheapestProductInCategory().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task4(ShoppingManager ss) {
        System.out.println(" Customer and  Category which is most often choose");
        ss.customersWithCategoryMostChosen().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }

    private void task5(ShoppingManager ss) {
        System.out.println("Customers and their debts");
        ss.customersWithDebts().entrySet().stream().forEach(s -> System.out.println(s.getKey() + "::::" + s.getValue()));
    }
}

