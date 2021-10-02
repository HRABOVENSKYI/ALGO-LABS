package ua.lviv.iot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class App {

    private static final String DISCNT_IN = "discnt.in";
    private static final String DISCNT_OUT = "discnt.out";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws IOException {
        writeProductsAndDiscountToFile(DISCNT_IN, 11);
        int[] products = readProductsFromFile(DISCNT_IN);
        int discount = readDiscountFromFile(DISCNT_IN);
        double minSum = getMinSum(products, discount);
        int sum = getTotalSum(products);
        writeMinSumToFile(DISCNT_OUT, minSum);
        System.out.println(minSum);
        System.out.println(sum);
    }

    private static void writeMinSumToFile(String fileName, double minSum) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(BigDecimal.valueOf(minSum).setScale(2, RoundingMode.HALF_UP).toString());
        }
    }

    private static int getTotalSum(int[] products) {
        int sum = 0;
        for (int i = 0; i < products.length; i++) {
            sum += products[i];
        }
        return sum;
    }

    public static double getMinSum(int[] products, int discount) throws IOException {
        // sort to get products with the highest price
        HeapSort.sortAsc(products);

        int numOfProductsWithDiscount = products.length / 3;

        double minSum = 0;
        for (int i = products.length - 1; i >= products.length - numOfProductsWithDiscount; i--) {
            minSum += (1 - (double) discount / 100) * products[i];
        }
        for (int i = products.length - numOfProductsWithDiscount - 1; i >= 0; i--) {
            minSum += products[i];
        }
        return minSum;
    }

    private static void writeProductsAndDiscountToFile(String fileName, int numOfProducts) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (int i = 0; i < numOfProducts; i++) {
                fileWriter.write(RANDOM.nextInt(1000) + " ");
            }
            fileWriter.write("\n" + RANDOM.nextInt(100));
        }
    }

    private static int[] readProductsFromFile(String fileName) throws IOException {
        List<Integer> arrayList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line = bufferedReader.readLine();
            for (String number : line.split(" ")) {
                arrayList.add(Integer.parseInt(number));
            }
        }
        return arrayList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static int readDiscountFromFile(String fileName) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.readLine();
            return Integer.parseInt(bufferedReader.readLine());
        }
    }
}
