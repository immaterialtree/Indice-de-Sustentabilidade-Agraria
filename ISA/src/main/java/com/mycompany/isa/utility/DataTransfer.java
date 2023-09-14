/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.CategoriaIndicadores;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author naoki
 */
public class DataTransfer {
    private static final String ROOT = "json";
    private static final String PATH_LOTE = String.join(File.separator, ROOT, "lotes");
    private static final String PATH_INDICADOR = String.join(File.separator, ROOT, "indicadores");
    private static final String PATH_DEFAULT_INDICADOR = String.join(File.separator, ROOT, "indicadores padrao");
    
    
    public static ArrayList<Lote> importLotes()  {
        ArrayList<Lote> lotes = new ArrayList<>();
        File loteDir = new File(PATH_LOTE);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : loteDir.listFiles()) {
            try {
                Lote l = mapper.readValue(f, new TypeReference<Lote>(){});
                lotes.add(l);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lotes;
    }
    
    public static ArrayList<CategoriaIndicadores> importIndicadores()  {
        ArrayList<CategoriaIndicadores> indicadores = new ArrayList<>();
        File indicadorDir = new File(PATH_INDICADOR);
        ObjectMapper mapper = new ObjectMapper();
        for (File f : indicadorDir.listFiles()) {
            CategoriaIndicadores i;
            try {
                i = mapper.readValue(f, new TypeReference<CategoriaIndicadores>(){});
                indicadores.add(i);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return indicadores;
    }
    
    public static void exportAllLotes(List<Lote> loteList) {
        ObjectMapper mapper = new ObjectMapper();
        
        for (Lote lote : loteList) {
            File resultFile = new File(PATH_LOTE, lote.getResponsavel()+"-"+lote.getNumParcela());
            try {
                mapper.writeValue(resultFile, lote);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void exportLote(Lote lote) {
        ObjectMapper mapper = new ObjectMapper();
        File resultFile = new File(PATH_LOTE, lote.getResponsavel()+"-"+lote.getNumParcela());
        resultFile.delete(); // delete file if exists
        try {
            mapper.writeValue(resultFile, lote);
        } catch (IOException ex) {
            Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void exportIndicadores(List<CategoriaIndicadores> indicadorList) {
        ObjectMapper mapper = new ObjectMapper();
        
        for (CategoriaIndicadores indicador : indicadorList) {
            File resultFile = new File(PATH_INDICADOR, indicador.getNome());
            try {
                mapper.writeValue(resultFile, indicador);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private static void deleteFileContent(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
    }
    
    public static void deleteLotes() {
        deleteFileContent(new File(PATH_LOTE));
    }
    
    public static void deleteIndicadores() {
        deleteFileContent(new File(PATH_INDICADOR));
    }
    
    public static void resetIndicadores() {
        deleteIndicadores();
        File[] contents = new File(PATH_DEFAULT_INDICADOR).listFiles();
        if (contents != null) {
            for (File f : contents) {
                try {
                    File target = new File(PATH_INDICADOR, f.getName());
                    Files.copy(f.toPath(), target.toPath());
                } catch (IOException ex) {
                    Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
