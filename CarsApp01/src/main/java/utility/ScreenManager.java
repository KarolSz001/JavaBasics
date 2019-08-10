package utility;

public class ScreenManager {

    public static void clearScreen2() {
        System.out.println(new String(new char[10]).replace("\0", "\r\n"));
    }
    public static void printMenu()  {
        System.out.println(" Choose from these choices ");
        System.out.println("-------------------------\n");
        System.out.println("0 -> Exit program");
        System.out.println("1 -> print Raw Data - override to String method");
        System.out.println("2 -> List of Cars which is sorted by parameter");
        System.out.println("3 -> List of Cars filtered by parameter mileage");
        System.out.println("4 -> Map Color and number of Cars who have this color");
        System.out.println("5 -> Map Model and most expansive Car in this model");
        System.out.println("6 -> shows statistic min, max, aver for PRICE and MILEAGE");
        System.out.println("7 -> sorted List and return the most expansive Car");
        System.out.println("8 -> List of Car with sorted list of components");
        System.out.println("9 -> Map where a Key is name of Component and Value is a list of CarService with this component");
        System.out.println("10 -> show sorted List filtered by parameters of min and max price");
    }

}
