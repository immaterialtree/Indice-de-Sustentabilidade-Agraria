/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import model.ModeloIndicadores;

import csv.CSV;

/**
 *
 * @author naoki
 */
public class Dados {
    String root = "csv"+File.separator;
    String loteNome;
    List<List<String[]>> dados;
    
    ArrayList<ModeloIndicadores> modelos;

    public Dados(String loteNome, List<List<String[]>> tabelas, ArrayList<ModeloIndicadores> modelos) throws IOException {
        this.loteNome = loteNome;
        this.dados = tabelas;
        this.modelos = modelos;
        Files.createDirectories(Paths.get(root+loteNome));
    }
    
    
    
    
    public void exportar() throws IOException {
        
        for (int i = 0; i < dados.size(); i++) {
            CSV.dataArrayToCSV(dados.get(i), root+loteNome+modelos.get(i).getNome());
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String[]> data = CSV.readCSV("teste.csv");
        for (String[] line : data) {
            System.out.println(Arrays.toString(line));
        }
    }
}
