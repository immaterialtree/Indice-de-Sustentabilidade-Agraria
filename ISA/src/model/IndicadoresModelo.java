/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
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
    
    public IndicadoresModelo(String nome, String[] grupos, String[][] itens) {
        this.nome = nome;
        indicadores = new HashMap<>();
        for (int i = 0; i < grupos.length; i++) {
            List<String> list = Arrays.asList(itens[i]);
            indicadores.put(grupos[i], list);
        }
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
    public java.util.ArrayList<String> getGrupos() {
        return new ArrayList<>(indicadores.keySet());
    }
    
    public void addGrupo(String grupo) {
        indicadores.put(grupo, new ArrayList<>());
    }
    
    public void removeGrupo(String grupo) {
        indicadores.remove(grupo);
    }
    
    // itens - get/add/remove
    public List<List<String>> getAllItems() {
        return List.copyOf(indicadores.values());
    }
    public String[][] getAllItemsArr() {
        List<List<String>> itemList = getAllItems();
        String[][] itemArr = new String[itemList.size()][];
        for (int i = 0; i < itemList.size(); i++) {
            itemArr[i] = itemList.get(i).toArray(String[]::new);
        }
        return itemArr;
    }
    public List<String> getItens(String grupo) {
        return indicadores.get(grupo);
    }
    
    public void addItem(String grupo, String item) {
        indicadores.get(grupo).add(item);
    }
    
    public void addAllItems(String grupo, String... items) {
        indicadores.get(grupo).addAll(Arrays.asList(items));
    }
    
    public void removeItem(String grupo, String items){
        indicadores.get(grupo).remove(items);
    }
    
    public void removeAllItems(String grupo, String... items) {
        indicadores.get(grupo).removeAll(Arrays.asList(items));
    }
}
