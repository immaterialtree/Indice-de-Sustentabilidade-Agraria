/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.mycompany.isa.model.Lote;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author naoki
 */
public class CalcularIndice {
    public static double avarageAssentamento(ArrayList<Lote> lotes) {
        lotes.stream().mapToDouble(lote -> avarageLote(lote)).average();
        return 0;
    }
    public static double avarageLote(Lote lote) {
        return Arrays.stream(Arrays.stream(lote.getAllScores()).
                mapToDouble(row -> Arrays.stream(row).average().orElse(0)).
                toArray()).average().orElse(0);
    }
}