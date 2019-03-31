package screen;

public class ScreenManager2 {

    public static void clearScreen2() {
        System.out.println(new String(new char[10]).replace("\0", "\r\n"));
    }

    public static void printMenu() {

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("0 - Exit program");
        System.out.println("1 - Command task nr 1 -> Set Collection of filtered by number of components, power of engine or wheels ");
        System.out.println("2 - Command task nr 2 - > Set Collection of filtered by CarBodyType and min , max price");
        System.out.println("3 - Command task nr 3 -> Set Collection of Model filtered by engineType");
        System.out.println("4 - Command task nr 4 -> show statistic of parameter price, mileage, power ");
        System.out.println("5 - Command task nr 5 - > get Map of Car2 and Value of Mileage  " );
        System.out.println("6 - Command task nr 6 - > get Map where Key is TyreType and Value is number of Car2 with this TyreType");
        System.out.println("7 - Print raw data download from file");

    }

}
