package screen;

public class ScreenManager {

    public static void clearScreen2() {
        System.out.println(new String(new char[10]).replace("\0", "\r\n"));
    }
    public static void printMenu()  {
        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("0 - Exit program");
        System.out.println("1 - Command task nr 1 -> print Raw Data - override to String method");
        System.out.println("2 - Command task nr 2 -> create list of Cars which is sorted by parameter");
        System.out.println("3 - Command task nr 3 -> create List of Cars filtered by parameter mileage");
        System.out.println("4 - Command task nr 4 -> create Map of Color and number of Cars who have this color");
        System.out.println("5 - Command task nr 5 -> create Map of Model and most expansive Car in this model");
        System.out.println("6 - Command task nr 6 -> shows statistic min, max, aver of price and mileage");
        System.out.println("7 - Command task nr 7 -> sort List and return the most expansive Car");
        System.out.println("8 - Command task nr 8 -> sort List of Car with sorted list of Components");
        System.out.println("9 - Command task nr 9 -> Map where a Key is name of Component and Value is a list of CarService with this component");
        System.out.println("10 - Command task nr 10 -> sort List List filtered by parameters of min and max price");
    }

}
