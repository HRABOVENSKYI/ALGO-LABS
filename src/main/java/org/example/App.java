package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

public class App {

    public static final Logger logger = Logger.getLogger("MyLogger");

    public static void main( String[] args ) {

        // take input from file
        int[] arr = new int[0];
        try {
            arr = readArrFromFile("arr.csv", ";");
        } catch (IOException e) {
            logger.severe(e.getMessage());
        }

        // sort arr
        System.out.println(HeapSort.sortAscReturnStat(new int[]{-9, 1, 2, 11, -9, 45, 56, 78}));
    }

    private static int[] readArrFromFile(String fileName, String separator) throws IOException {

        List<Integer> arrayList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                for (String number : line.split(separator)) {
                    arrayList.add(Integer.parseInt(number));
                }
            }
        }

        return arrayList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
