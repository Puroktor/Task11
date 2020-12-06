package ru.vsu.cs.skofenko;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InOutClass {

    public static List<String> readListFromFile(String inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(inputFile));
        List<String> list = new ArrayList<>();
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    public static void writeListToFile(String outputFile, List<String> list) throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter(outputFile)) {
            for (String date : list) {
                printWriter.println(date);
            }
        }
    }
}
