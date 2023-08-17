/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.mycompany.isa.model.Lote;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author naoki
 */
public class CalcularIndice {
    public static double averageAssentamento(List<Lote> lotes) {
        return lotes.stream().mapToDouble(lote -> averageLote(lote)).average().orElse(0);
    }
    public static double averageLote(Lote lote) {
        return Arrays.stream(Arrays.stream(lote.getAllScores()).
                mapToDouble(row -> Arrays.stream(row).average().orElse(0)).
                toArray()).average().orElse(0);
    }
    
    public static double desvioPadraoAssentamento(List<Lote> lotes) {
        return desvioPadrao(lotes.stream().mapToDouble(lote -> averageLote(lote)).toArray());
    }
    
    public static double desvioPadrao(double[] data) {
        int n = data.length;
        
        if (n <= 1) {
            return 0;
        }
        
        double mean = Arrays.stream(data).average().getAsDouble();
        double sumOfSquaredDifferences = 0.0;
        
        for (double value : data) {
            double difference = value - mean;
            sumOfSquaredDifferences += difference * difference;
        }
        
        double variance = sumOfSquaredDifferences / (n - 1);
        
        return Math.sqrt(variance);
    }
}