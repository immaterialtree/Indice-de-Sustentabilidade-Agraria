/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.model;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"responsavel",
"numParcela",
"contato",
"coordenada",
"scoresMap"
})
/**
 *
 * @author naoki
 */
public class Lote {
    @JsonProperty("responsavel")
    private String responsavel;
    @JsonProperty("numParcela")
    private String numParcela;
    @JsonProperty("contato")
    private String contato;
    @JsonProperty("coordenada")
    private double[] coordenada;
    private Map<Integer, Double[]> scoresMap; // Map<modelo.hashCode, scores>
 
    public Lote() {
        scoresMap = new LinkedHashMap<>();
    }
    
    public Lote(String responsavel, String numParcela, String numero, double[] coordenada) {
        this.responsavel = responsavel;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
        scoresMap = new LinkedHashMap<>();
    }
    
    public Lote(String responsavel, String numParcela, String numero, double[] coordenada, Integer[] keys, Double[][] scores) {
        this.responsavel = responsavel;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
        this.scoresMap = new LinkedHashMap<>();
        for (int i = 0; i < scores.length; i++) {
            scoresMap.put(keys[i], scores[i]);
        }
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
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

    public Map<Integer, Double[]> getScoresMap() {
        return scoresMap;
    }
    
    
    public void addScores(Integer key, Double[] scores) {
        scoresMap.put(key, scores);
    }
    
    public void addScores(CategoriaIndicadores modelo, Double[] scores) {
        scoresMap.put(modelo.hashCode(), scores);
    }
    
    public void initScoreSheet(CategoriaIndicadores modelo) {
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
    
    public void setScore(CategoriaIndicadores modelo, int pos, Double score) {
        scoresMap.get(modelo.hashCode())[pos] = score;
    }
    
    @JsonIgnore
    public Double getScore(Integer key, int pos) {
        return scoresMap.get(key)[pos];
    }
    @JsonIgnore
    public Double[] getScoresOf(Integer key) {
        return scoresMap.get(key);
    }
    
    @JsonIgnore
    public Double[][] getAllScores() {
        return scoresMap.values().toArray(Double[][]::new);
    }

    
    @Override
    public String toString() {
        return "Responsavel:" + responsavel + 
                "\nN° de parcelas:" + numParcela + 
                "\nNumero=" + contato + 
                "\nCoordenada=" + coordenada;
    }
}
