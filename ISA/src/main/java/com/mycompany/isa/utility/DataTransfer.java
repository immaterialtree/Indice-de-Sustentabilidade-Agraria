/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.Indicador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author naoki
 */
public class DataTransfer {
    private static final String ROOT = "json";
    private static final String PATH_INDICADOR = String.join(File.separator, ROOT, "indicadores");
    private static final String PATH_LOTE = String.join(File.separator, ROOT, "lotes");
    
    
    public static ArrayList<Lote> importLotes() throws IOException {
        ArrayList<Lote> lotes = new ArrayList<>();
        File loteDir = new File(PATH_LOTE);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : loteDir.listFiles()) {
            Lote l = mapper.readValue(f, new TypeReference<Lote>(){});
            lotes.add(l);
        }
        return lotes;
    }
    
    public static ArrayList<Indicador> importIndicadores() throws IOException {
        ArrayList<Indicador> indicadores = new ArrayList<>();
        File indicadorDir = new File(PATH_INDICADOR);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : indicadorDir.listFiles()) {
            Indicador i = mapper.readValue(f, new TypeReference<Indicador>(){});
            indicadores.add(i);
        }
        return indicadores;
    }
    
    public static void exportLotes(List<Lote> loteList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        for (Lote lote : loteList) {
            File resultFile = new File(PATH_LOTE, lote.getNome());
            mapper.writeValue(resultFile, lote);
        }
        
    }
    
    public static void exportIndicadores(List<Indicador> indicadorList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        for (Indicador indicador : indicadorList) {
            File resultFile = new File(PATH_INDICADOR, indicador.getNome());
            mapper.writeValue(resultFile, indicador);
        }
    }
}
