/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author naoki
 */
public class IndicadoresModelo {
    private String nome;
    private Map<String, List<String>> indicadores; // map with <key, value> as <grupo, item>
    
    // Constructors
    public IndicadoresModelo() {
        indicadores = new HashMap<>();
    }
    
    public IndicadoresModelo(String nome) {
        this.nome = nome;
        indicadores = new HashMap<>();
    }
    
    // nome - getter/setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) { 
        this.nome = nome;
    }
    
    // indicadores - getter
    public Map<String, List<String>> getIndicadores() {
        return indicadores;
    }
    
    // grupos - get/add/remove
    public java.util.Set<String> getGrupos() {
        return indicadores.keySet();
    }
    
    public void addGrupo(String grupo) {
        indicadores.put(grupo, new ArrayList<>());
    }
    
    public void removeGrupo(String grupo) {
        indicadores.remove(grupo);
    }
    
    // itens - get/add/remove
    public List<String> getItens(String grupo) {
        return indicadores.get(grupo);
    }
    
    public void addItem(String grupo, String item) {
        indicadores.get(grupo).add(item);
    }
    public void removeItem(String grupo, String item){
        indicadores.get(grupo).remove(item);
    }
}