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

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
"nome",
"itemMap"
})

/**
 *
 * @author naoki
 */

public class CategoriaIndicadores {
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("itemMap")
    private Map<String, List<String>> itemMap; // map with <key, value> as <grupo, item>
    
    // Constructors
    public CategoriaIndicadores() {
        itemMap = new LinkedHashMap<>();
    }
    
    public CategoriaIndicadores(String nome, String[] grupos, String[][] itens) {
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
    
    // itemMap - getter/setter
    public Map<String, List<String>> getItemMap() {
        return itemMap;
    }
    
    
    // grupos - get/add/remove
    @JsonIgnore
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
    @JsonIgnore
    public List<List<String>> getAllItems() {
        return List.copyOf(itemMap.values());
    }
    
    @JsonIgnore
    public String[][] getAllItemsArr() {
        List<List<String>> itemList = getAllItems();
        String[][] itemArr = new String[itemList.size()][];
        for (int i = 0; i < itemList.size(); i++) {
            itemArr[i] = itemList.get(i).toArray(String[]::new);
        }
        return itemArr;
    }
    
    @JsonIgnore
    public List<String> getItens(String grupo) {
        return itemMap.get(grupo);
    }
    
    public void addItem(String grupo, String item) {
        itemMap.get(grupo).add(item);
    }
    
    
    public void replaceItem(String grupo, String oldItem, String newItem) {
        if (!newItem.isBlank()) {
            int index = itemMap.get(grupo).indexOf(oldItem);
            itemMap.get(grupo).remove(oldItem);
            itemMap.get(grupo).add(index, newItem);
        }
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
