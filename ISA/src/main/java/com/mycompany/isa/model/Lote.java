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
"scoresMap",
"assentamento"
})
/**
 *
 * @author naoki
 */
public class Lote {
    @JsonProperty("assentamento")
    private String assentamento;
    @JsonProperty("responsavel")
    private String responsavel;
    @JsonProperty("numParcela")
    private String numParcela;
    @JsonProperty("contato")
    private String contato;
    @JsonProperty("coordenada")
    private double[] coordenada;
    private Map<Integer, double[]> scoresMap; // Map<modelo.hashCode, scores>
 
    public Lote() {
        scoresMap = new LinkedHashMap<>();
    }

    public Lote(String assentamento, String responsavel, String numParcela, String contato, double[] coordenada) {
        this.assentamento = assentamento;
        this.responsavel = responsavel;
        this.numParcela = numParcela;
        this.contato = contato;
        this.coordenada = coordenada;
        scoresMap = new LinkedHashMap<>();
    }

    public Lote(String assentamento, String responsavel, String numParcela, String contato, double[] coordenada, Map<Integer, double[]> scoresMap) {
        this.assentamento = assentamento;
        this.responsavel = responsavel;
        this.numParcela = numParcela;
        this.contato = contato;
        this.coordenada = coordenada;
        this.scoresMap = scoresMap;
    }
    
    
    public Lote(String assentamento, String responsavel, String numParcela, String numero, double[] coordenada, Integer[] keys, double[][] scores) {
        this.assentamento = assentamento;
        this.responsavel = responsavel;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
        this.scoresMap = new LinkedHashMap<>();
        for (int i = 0; i < scores.length; i++) {
            scoresMap.put(keys[i], scores[i]);
        }
    }

    public String getAssentamento() {
        return assentamento;
    }

    public void setAssentamento(String assentamento) {
        this.assentamento = assentamento;
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

    public Map<Integer, double[]> getScoresMap() {
        return scoresMap;
    }
    
    
    public void addScores(Integer key, double[] scores) {
        scoresMap.put(key, scores);
    }
    
    public void addScores(CategoriaIndicadores modelo, double[] scores) {
        scoresMap.put(modelo.getNome().hashCode(), scores);
    }
    
    public void initScoreSheet(CategoriaIndicadores modelo) {
        int size = 0;
        for (List itemList : modelo.getAllItems()) {
            size += itemList.size();
        }
        double[] values = new double[size];
        scoresMap.putIfAbsent(modelo.getNome().hashCode(), values);
    }
    
    public void setScore(Integer key, int pos, Double score) {
        scoresMap.get(key)[pos] = score;
    }
    
    public void setScore(CategoriaIndicadores modelo, int pos, Double score) {
        scoresMap.get(modelo.getNome().hashCode())[pos] = score;
    }
    
    @JsonIgnore
    public double getScore(Integer key, int pos) {
        return scoresMap.get(key)[pos];
    }
    @JsonIgnore
    public double[] getScoresOf(Integer key) {
        return scoresMap.get(key);
    }
    
    @JsonIgnore
    public double[][] getAllScores() {
        return scoresMap.values().toArray(double[][]::new);
    }
    
    public double calcularIndiceCategoria(int categoria) {
        return Arrays.stream(scoresMap.get(categoria)).average().orElse(Double.NaN);
    }
    
    public double calcularIndiceGeral() {
        double sum=0;
        for (Integer key : scoresMap.keySet()) {
            sum += calcularIndiceCategoria(key);
        }
        return sum/scoresMap.size();
    }
    
    @Override
    public String toString() {
        return "Responsavel:" + responsavel + 
                "\nN° de parcelas:" + numParcela + 
                "\nNumero=" + contato + 
                "\nCoordenada=" + coordenada;
    }
}
