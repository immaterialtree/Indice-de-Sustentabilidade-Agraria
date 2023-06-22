/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Arrays;

/**
 *
 * @author naoki
 */
public class Lote {
    private int id;
    private String nome;
    private String numParcela;
    private String contato;
    private double[] coordenada;

    public Lote() {
    }

    public Lote(String nome, String numParcela, String numero, double[] coordenada) {
        this.nome = nome;
        this.numParcela = numParcela;
        this.contato = numero;
        this.coordenada = coordenada;
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

    @Override
    public String toString() {
        return "Nome:" + nome + 
                "\nN° de parcelas:" + numParcela + 
                "\nNumero=" + contato + 
                "\nCoordenada=" + Arrays.toString(coordenada);
    }
    
    
    
}
