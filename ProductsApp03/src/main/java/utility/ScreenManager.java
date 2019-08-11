package utility;

public class ScreenManager {


    public static void clearScreen2() {
        System.out.println(new String(new char[10]).replace("\0", "\r\n"));
    }


    public static void printMenu() {

        System.out.println("Make a choice");
        System.out.println("-------------------------");
        System.out.println("0 - Exit program");
        System.out.println("1 - find Customer who paid the most for all purchases and " +
                " in the selected category BOOK, ELECTRONIC, FOOD ");
        System.out.println("2 - grouping  categories of products that were most often purchased at this age");
        System.out.println("3 - find average Price in Category, most expansive and cheapest Product in  Category");
        System.out.println("4 - Customer and  Category which is most often choose");
        System.out.println("5 - Customers and their debts");
        System.out.println("6 - Print raw data download from file");

    }

}
