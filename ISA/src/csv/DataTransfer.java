/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        for (String f : files) {
            try {
                String nome = f.substring(0, f.indexOf('.'));
                modelos.add(dataArrayToModelo(nome, CSV.readCSV(pathModelos+f)));
            } catch (FileNotFoundException e) {
            }
        }
        return modelos;
    }
    
    private static ModeloIndicadores dataArrayToModelo(String nome, ArrayList<String[]> dataArray) {
        String[] grupos = dataArray.remove(0);
        String[][] itens = dataArray.toArray(String[][]::new);
        return new ModeloIndicadores(nome, grupos, itens);
    }
}
