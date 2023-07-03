/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.*;

import static csv.CSV.*;

/**
 *
 * @author naoki
 */
public class Dados {
    String root = "csv"+File.separator;
    String loteNome;
    List<List<String[]>> tabelas;
    
    ArrayList<IndicadoresModelo> modelos;

    public Dados(String loteNome, List<List<String[]>> tabelas, ArrayList<IndicadoresModelo> modelos) throws IOException {
        this.loteNome = loteNome;
        this.tabelas = tabelas;
        this.modelos = modelos;
        Files.createDirectories(Paths.get(root+loteNome));
    }
    
    
    
    public void importar(){
        String[] files = new File(root+loteNome).list();
        for (int i=0; i<modelos.size(); i++) {
            try {
                tabelas.add(readCSV(files[i]));
            } catch (FileNotFoundException e) {
                tabelas.add(new ArrayList<>());
            }
        }
    }
    public void exportar() throws IOException {
        
        for (int i = 0; i < tabelas.size(); i++) {
            dataArrayToCSV(tabelas.get(i), root+loteNome+modelos.get(i).getNome());
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String[]> data = readCSV("teste.csv");
        for (String[] line : data) {
            System.out.println(Arrays.toString(line));
        }
    }
}
