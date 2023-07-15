/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author naoki
 */
public class Lote {
    private String nome;
    private String numParcela;
    private String contato;
    private double[] coordenada;
    private Map<Integer, Double[]> scoresMap; // Map<modelo.hashCode, scores>
 
    public Lote() {
    }

    public Lote(String nome, String numParcela, String numero, double[] coordenada) {
        this.nome = nome;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
        scoresMap = new LinkedHashMap<>();
    }
    
    public Lote(String nome, String numParcela, String numero, double[] coordenada, Integer[] keys, Double[][] scores) {
        this.nome = nome;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
        this.scoresMap = new LinkedHashMap<>();
        for (int i = 0; i < scores.length; i++) {
            scoresMap.put(keys[i], scores[i]);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumParcela() {
        return numParcela;
    }

    public void setNumParcela(String numParcela) {
        this.numParcela = numParcela;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public double[] getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(double[] coordenada) {
        this.coordenada = coordenada;
    }

    public Map<Integer, Double[]> getScores() {
        return scoresMap;
    }
    
    
    public void addScores(Integer key, Double[] scores) {
        scoresMap.put(key, scores);
    }
    
    public void addScores(IndicadorTabela modelo, Double[] scores) {
        scoresMap.put(modelo.hashCode(), scores);
    }
    
    public void initScoreSheet(IndicadorTabela modelo) {
        int size = 0;
        for (List itemList : modelo.getAllItems()) {
            size += itemList.size();
        }
        Double[] values = new Double[size];
        Arrays.fill(values, 0d);
        scoresMap.putIfAbsent(modelo.hashCode(), values);
    }
    
    public void setScore(Integer key, int pos, Double score) {
        scoresMap.get(key)[pos] = score;
    }
    
    public void setScore(IndicadorTabela modelo, int pos, Double score) {
        scoresMap.get(modelo.hashCode())[pos] = score;
    }
    
    public Double getScore(Integer key, int pos) {
        return scoresMap.get(key)[pos];
    }
    
    public Double[] getScores(Integer key) {
        return scoresMap.get(key);
    }
    
    public Double[][] getAllScores() {
        return scoresMap.values().toArray(Double[][]::new);
    }

    
    @Override
    public String toString() {
        return "Nome:" + nome + 
                "\nN° de parcelas:" + numParcela + 
                "\nNumero=" + contato + 
                "\nCoordenada=" + Arrays.toString(coordenada);
    }
}
