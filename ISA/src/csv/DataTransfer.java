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
    final static String ROOT = "csv"+File.separator;
    final static String PATH_MODELOS = ROOT+"modelos";
    final static String PATH_LOTES = ROOT+"lotes";
    
    
    public static ArrayList<ModeloIndicadores> importAllModelos(){
        ArrayList<ModeloIndicadores> modelos = new ArrayList<>();
        String[] files = new File(PATH_MODELOS).list();
        if (files!=null) {
            for (String f : files) {
                try {
                    modelos.add(dataArrayToModelo(Doc.readCSV(PATH_MODELOS+f)));
                } catch (FileNotFoundException e) {
                }
            }
        }
        return modelos;
    }
    
    public static void exportAllModelos(List<ModeloIndicadores> modeloList)  {
        for (ModeloIndicadores modelo : modeloList) {
            try {
                String filePath = PATH_MODELOS + File.separator + modelo.getNome();
                Doc.dataArrayToCSV(modeloToDataArray(modelo), filePath);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void exportAllLotes(List<Lote> loteList)  {
        for (Lote lote : loteList) {
            try {
                String filePath = PATH_LOTES + File.separator + lote.getNome();
                Doc.dataArrayToCSV(loteToDataArray(lote), filePath);
            } catch (IOException ex) {
                Logger.getLogger(DataTransfer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static ArrayList<Lote> importAllLotes() {
        ArrayList<Lote> lotes = new ArrayList<>();
        String[] files = new File(PATH_LOTES).list();
        if (files!=null) {
//            System.out.println(Arrays.toString(files));
            for (String f : files) {
                try {
                    String filePath = PATH_LOTES+File.separator+f;
                    lotes.add(dataArrayToLote(Doc.readCSV(filePath)));
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
        dataArray.add(modelo.getGrupos().toArray(String[]::new));
        for (String[] itemList : modelo.getAllItemsArr())
            dataArray.add(itemList);
        return dataArray;
    }
    
    private static Lote dataArrayToLote(ArrayList<String[]> dataArray) {
        if (dataArray.isEmpty())
            return null;
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
    
    public static boolean deleteLote(String nome) {
        File lote = new File(PATH_LOTES, nome);
        return lote.delete();
    }
    
    public static boolean renameLote(String oldName, String newName) {
        File oldFile = new File(PATH_LOTES, oldName);
        File newFile = new File(PATH_LOTES, newName);
        boolean success = false;
        if (!newFile.exists()) {
            success = oldFile.renameTo(newFile);
        }
        return success;
    }
    public static void deleteAllLotes() {
        File file = new File(PATH_LOTES);
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
    }
    
    public static void deleteAllModelos() {
        File file = new File(PATH_MODELOS);
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                f.delete();
            }
        }
    }
}
