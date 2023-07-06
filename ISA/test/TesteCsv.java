
import csv.DataTransfer;
import java.util.Arrays;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author naoki
 */
public class TesteCsv {
    public static void main(String[] args) {
        Double[] dArray = {24d, 2., 8d};
        Arrays.stream(dArray).map(String::valueOf).toArray(String[]::new);
    }
}
