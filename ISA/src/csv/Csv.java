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
public class CSV {

    private static String lineToCSV(String[] data) {
        return Stream.of(data)
          .map(CSV::escapeSpecialCharacters)
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
        File csvOutputFile = new File(path);
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
              .map(CSV::lineToCSV)
              .forEach(pw::println);
        }
    }
    

    public static ArrayList<String[]> readCSV(String path) throws FileNotFoundException {
        Scanner scan = new Scanner(path);
        ArrayList<String[]> records = new ArrayList<>();
        String[] record;
        while(scan.hasNext()) {
            record = scan.nextLine().split(",");
            records.add(record);
        }
        return records;
    }
}
