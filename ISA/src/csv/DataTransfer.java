/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csv;

import isa.ISA;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Lote;
import model.ModeloIndicadores;

/**
 *
 * @author naoki
 */
public class DataTransfer {
    final static String root = "csv"+File.separator;
    final static String pathModelos = root+"modelos";
    final static String pathLotes = root+"lotes";
    
    
    public static ArrayList<ModeloIndicadores> importAllModelos(){
        ArrayList<ModeloIndicadores> modelos = new ArrayList<>();
        String[] files = new File(pathModelos).list();
        if (files!=null) {
//            System.out.println(Arrays.toString(files));
            for (String f : files) {
                try {
                    modelos.add(dataArrayToModelo(Doc.readCSV(pathModelos+f)));
                } catch (FileNotFoundException e) {
                }
            }
        }
        return modelos;
    }
    
    public static void exportAllLotes(List<Lote> loteList)  {
        for (Lote lote : loteList) {
            try {
                String file = pathLotes + lote.getNome();
                Doc.dataArrayToCSV(loteToDataArray(lote), file);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static ArrayList<Lote> importAllLotes() {
        ArrayList<Lote> lotes = new ArrayList<>();
        String[] files = new File(pathLotes).list();
        if (files!=null) {
//            System.out.println(Arrays.toString(files));
            for (String f : files) {
                try {
                    
                    lotes.add(dataArrayToLote(Doc.readCSV(pathModelos+File.separator+f)));
                } catch (FileNotFoundException e) {
                }
            }
        }
        return lotes;
    }
    
    
    
    private static ModeloIndicadores dataArrayToModelo(ArrayList<String[]> dataArray) {
        String nome = dataArray.remove(0)[0];
        String[] grupos = dataArray.remove(0);
        String[][] itens = dataArray.toArray(String[][]::new);
        return new ModeloIndicadores(nome, grupos, itens);
    }
    
    public static ArrayList<String[]> modeloToDataArray(ModeloIndicadores modelo) {
        ArrayList<String[]>  dataArray = new ArrayList<>();
        dataArray.add(new String[] {modelo.getNome()});
        
        return dataArray;
    }
    
    private static Lote dataArrayToLote(ArrayList<String[]> dataArray) {;
        String[] firstLine = dataArray.remove(0);
        double[] coordenada = Arrays.stream(new String[] {firstLine[3], firstLine[4]}).mapToDouble(Double::parseDouble).toArray();
        Lote lote = new Lote(firstLine[0], firstLine[1], firstLine[2], coordenada);
        if (!dataArray.isEmpty()) {
            while (!dataArray.isEmpty()) {
                Integer key = Integer.valueOf(dataArray.remove(0)[0]);
                Double[] scores = Arrays.stream(dataArray.remove(0)).
                        map(Double::valueOf).toArray(Double[]::new);
                lote.addScores(key, scores);
            }
        }
        return lote;
    }
    
    public static ArrayList<String[]> loteToDataArray(Lote lote) {
        ArrayList<String[]> dataArray = new ArrayList<>();
        String[] line = new String[5];
        line[0] = lote.getNome();
        line[1] = lote.getNumParcela();
        line[2] = lote.getContato();
        line[3] = String.valueOf(lote.getCoordenada()[0]);
        line[4] = String.valueOf(lote.getCoordenada()[1]);
        dataArray.add(line);
        
        for (Integer key : lote.getScores().keySet()) {
            dataArray.add(new String[]{key.toString()});
            dataArray.add(Arrays.stream(
                    lote.getScores().get(key)).
                    map(String::valueOf).toArray(String[]::new)
            );
        }
        return dataArray;
    }
}
