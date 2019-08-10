package utility;

public class ScreenManager2 {

    public static void clearScreen2() {
        System.out.println(new String(new char[10]).replace("\0", "\r\n"));
    }

    public static void printMenu() {

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("0 ->  Exit program ");
        System.out.println("1 - > Collection  filtered by number of components, power of engine or wheels ");
        System.out.println("2 - > Collection of filtered by CarBodyType and min , max price");
        System.out.println("3 - > Collection of Model filtered by engineType");
        System.out.println("4 - > Statistic of parameter price, mileage, power ");
        System.out.println("5 - > Collection map Cars and mileages" );
        System.out.println("6 - > Collection map TyreType and number of Cars with this TyreType");
        System.out.println("7 - > Print raw data download from file");

    }

}
