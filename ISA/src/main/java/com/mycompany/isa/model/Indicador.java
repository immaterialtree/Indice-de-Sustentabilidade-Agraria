/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 *
 * @author naoki
 */
public class Indicador {
    private String nome;
    private Map<String, List<String>> itemMap; // map with <key, value> as <grupo, item>
    
    // Constructors
    public Indicador() {
        itemMap = new LinkedHashMap<>();
    }
    
    public Indicador(String nome, String[] grupos, String[][] itens) {
        this.nome = nome;
        itemMap = new LinkedHashMap<>();
        for (int i = 0; i < grupos.length; i++) {
            List<String> list = Arrays.asList(itens[i]);
            itemMap.put(grupos[i], list);
        }
    }
    
    // nome - getter/setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) { 
        this.nome = nome;
    }
    
    // item - getter
    public Map<String, List<String>> getItemMap() {
        return itemMap;
    }
    // grupos - get/add/remove
    public java.util.ArrayList<String> getGrupos() {
        return new ArrayList<>(itemMap.keySet());
    }
    
    public void addGrupo(String grupo) {
        itemMap.put(grupo, new ArrayList<>());
    }
    
    public void addGrupo(String grupo, ArrayList itens) {
        itemMap.put(grupo, itens);
    }
    
    public void replaceGrupo(String oldGrupo, String newGrupo) {
        itemMap.put(newGrupo, itemMap.get(oldGrupo));
        itemMap.remove(oldGrupo);
    }
    
    public void removeGrupo(String grupo) {
        itemMap.remove(grupo);
    }
    
    // itens - get/add/remove
    public List<List<String>> getAllItems() {
        return List.copyOf(itemMap.values());
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
        return itemMap.get(grupo);
    }
    
    public void addItem(String grupo, String item) {
        itemMap.get(grupo).add(item);
    }
    
    
    public void replaceItem(String grupo, String oldItem, String newItem) {
        itemMap.get(grupo).remove(oldItem);
        itemMap.get(grupo).add(newItem);
    }
    
    public void addAllItems(String grupo, String... items) {
        itemMap.get(grupo).addAll(Arrays.asList(items));
    }
    
    public void removeItem(String grupo, String item){
        itemMap.get(grupo).remove(item);
    }
    
    public void removeAllItems(String grupo, String... items) {
        itemMap.get(grupo).removeAll(Arrays.asList(items));
    }
}
