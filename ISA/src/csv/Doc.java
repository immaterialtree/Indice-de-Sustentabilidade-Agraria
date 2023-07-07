/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csv;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author naoki
 */
public class Doc {

    private static String lineToCSV(String[] data) {
        return Stream.of(data)
          .map(Doc::escapeSpecialCharacters)
          .collect(Collectors.joining(","));
    }
    
    private static String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }
    
    public static void dataArrayToCSV(List<String[]> dataLines, String path) throws IOException {
        File outputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            dataLines.stream()
              .map(Doc::lineToCSV)
              .forEach(pw::println);
        }
    }
    

    public static ArrayList<String[]> readCSV(File sourceFile) throws FileNotFoundException {
        Scanner scan = new Scanner(sourceFile);
        ArrayList<String[]> records = new ArrayList<>();
        String[] record;
        while(scan.hasNext()) {
            record = scan.nextLine().split(",");
            records.add(record);
        }
        return records;
    }
}