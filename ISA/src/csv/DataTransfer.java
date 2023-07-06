/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
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
                    modelos.add(dataArrayToModelo(CSV.readCSV(pathModelos+f)));
                } catch (FileNotFoundException e) {
                }
            }
        }
        return modelos;
    }
    
    private static ModeloIndicadores dataArrayToModelo(ArrayList<String[]> dataArray) {
        String nome = dataArray.remove(0)[0];
        String[] grupos = dataArray.remove(0);
        String[][] itens = dataArray.toArray(String[][]::new);
        return new ModeloIndicadores(nome, grupos, itens);
    }
    
    public static ArrayList<String[]> modeloToDataArray(ModeloIndicadores modelo) {
        // TODO
        return null;
    }
    
    private static Lote dataArrayToLote(ArrayList<String[]> dataArray) {
        String[] firstLine = dataArray.remove(0);
        double[] coordenada = Arrays.stream(new String[] {firstLine[3], firstLine[4]}).mapToDouble(Double::parseDouble).toArray();
        Lote lote;
        if (dataArray.isEmpty()) {
            lote = new Lote(firstLine[0], firstLine[1], firstLine[2], coordenada);
        } else {
            for (String[] line : dataArray) {
                
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
            Arrays.stream(lote.getScores().get(key)).map(String::valueOf).toArray(String[]::new);
        }
        return dataArray;
    }
}
