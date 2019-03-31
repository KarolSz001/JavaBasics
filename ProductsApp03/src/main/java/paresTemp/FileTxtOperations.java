package paresTemp;


import enums.Category;
import model.Customer;
import model.Product;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class FileTxtOperations {
    public static final String REGEX_DATA = "(\\D+;){2}\\d+;\\d+ \\[((\\D+;){2}\\d+)+ (\\D+;){2}\\d+\\]";


    public static Map<Customer, Map<Product, Integer>> readAllFiles (String fileName){
        String[] lines = null;
        try(Scanner sc =  new Scanner(new FileReader(fileName))) {
            while(sc.hasNextLine()){
                lines = (lines == null) ? new String[1] : Arrays.copyOf(lines,lines.length + 1);
                lines[lines.length - 1] = sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<Customer, Map<Product, Integer>> customerMap = new HashMap<Customer, Map<Product, Integer>>();
        for (int i = 0; i < lines.length ; i++) {
            customerMap = readTxtFile(customerMap,lines[i]);
        }
        return customerMap;
    }

    public static Map<Customer, Map<Product, Integer>> readTxtFile (Map<Customer, Map<Product, Integer>> map, String fileName) {
        Map<Customer, Map<Product, Integer>> outerMap = map;
        Map<Product, Integer> innerMap = new HashMap<>();
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            //Jan;Kos;18;2000 [Komputer;Elektronika;2400 PanTadeusz;Ksiazka;120]
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                if (text != null || text.matches(REGEX_DATA)) {
                    ArrayList<Product> productArrayList = parseProductFromLine(text);
                    Customer customer = parseCustomerFromLine(text);
                    if (outerMap.isEmpty() || !outerMap.containsKey(customer)) { // map is empty or no key
                        innerMap = updateInnerMap(outerMap.get(customer), productArrayList);
                        outerMap.put(customer, innerMap);
                    } else { // if map exist the same customer -> key
                        innerMap = outerMap.get(customer);
                        Map<Product, Integer> upMap = updateInnerMap(innerMap, productArrayList);
                        outerMap.put(customer, upMap);
                    }
                }
            }
//            Set<Product> keys  = innerMap.keySet();
//            for (Product p :keys) {
//                System.out.println(p + " " + innerMap.get(p));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outerMap;
    }
    //  \[((\D+;){2}\d+)+ (\D+;){2}\d+\
    public static ArrayList<Product> parseProductFromLine(String text) {
        String convert = text.replaceAll("(\\D+;){2}\\d+;\\d+","").replaceAll("[\\[\\]]","").trim();
//        System.out.println(convert);
        String[] products = convert.split(" ");
//        System.out.println(Arrays.toString(products));
        ArrayList<Product> list = new ArrayList<>();
        for (int i = 0; i < products.length; i++) {
            list.add(parseProduct(products[i]));
        }
        return list;
    }

    private static Product parseProduct(String text) {
        String[] productTxt = text.split(";");
//        System.out.println(Arrays.toString(productTxt));
        String name = productTxt[0];
        Category category = Category.valueOf(productTxt[1]);
        BigDecimal price = BigDecimal.valueOf(Integer.parseInt(productTxt[2]));
        return new Product(name, category, price);
    }

    public static Customer parseCustomerFromLine(String text) {
        String convert = text.replaceAll("^(\\D+;){2}\\d+;\\d+]", "").trim();
        String[] customerTxt = text.split(";"); // [3] name + surname [0] + [1]
        String name = customerTxt[0] + " " + customerTxt[1];
        int age = Integer.parseInt(customerTxt[2]);
        BigDecimal cash = BigDecimal.valueOf(Double.parseDouble(customerTxt[2]));
        return new Customer(name, age, cash);
    }

    private static Map<Product, Integer> updateInnerMap(Map<Product, Integer> map, ArrayList<Product> list) {
        Map<Product, Integer> freshMap;
        if (map == null) {
            freshMap = new HashMap<>();
        } else {
            freshMap = map;
        }
        for (int i = 0; i < list.size(); i++) {
            Product product = list.get(i);
            if (freshMap.containsKey(product)) {
                Integer upValue = freshMap.get(product) + 1;
                freshMap.put(product, upValue);
            } else{
                freshMap.put(product, 1);
            }
        }
        return freshMap;
    }
    public static void saveFile(String fileName, boolean append) {

        try (PrintWriter printWriter = new PrintWriter(new FileWriter(fileName, append))) {
            printWriter.println("PIERWSZA LINIA");
            for (int i = 0; i < 10; i++) {
                printWriter.print(i + " ");
            }
            printWriter.println("OSTATNIA LINIA");
            // printWriter.flush(); // oprozniam bufor daych do pliku
        } catch (IOException e) {
            System.err.println("SAVE FILE EXCEPTION");
        }
    }

}
