/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 *
 * @author naoki
 */
public class ModeloIndicadores {
    private String nome;
    private Map<String, List<String>> indicadoresMap; // map with <key, value> as <grupo, item>
    
    // Constructors
    public ModeloIndicadores() {
        indicadoresMap = new LinkedHashMap<>();
    }
    
    public ModeloIndicadores(String nome, String[] grupos, String[][] itens) {
        this.nome = nome;
        indicadoresMap = new LinkedHashMap<>();
        for (int i = 0; i < grupos.length; i++) {
            List<String> list = Arrays.asList(itens[i]);
            indicadoresMap.put(grupos[i], list);
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
        return indicadoresMap;
    }
    // grupos - get/add/remove
    public java.util.ArrayList<String> getGrupos() {
        return new ArrayList<>(indicadoresMap.keySet());
    }
    
    public void addGrupo(String grupo) {
        indicadoresMap.put(grupo, new ArrayList<>());
    }
    
    public void addGrupo(String grupo, ArrayList itens) {
        indicadoresMap.put(grupo, itens);
    }
    
    public void replaceGrupo(String oldGrupo, String newGrupo) {
        indicadoresMap.put(newGrupo, indicadoresMap.get(oldGrupo));
        indicadoresMap.remove(oldGrupo);
    }
    
    public void removeGrupo(String grupo) {
        indicadoresMap.remove(grupo);
    }
    
    // itens - get/add/remove
    public List<List<String>> getAllItems() {
        return List.copyOf(indicadoresMap.values());
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
        return indicadoresMap.get(grupo);
    }
    
    public void addItem(String grupo, String item) {
        indicadoresMap.get(grupo).add(item);
    }
    
    
    public void replaceItem(String grupo, String oldItem, String newItem) {
        indicadoresMap.get(grupo).remove(oldItem);
        indicadoresMap.get(grupo).add(newItem);
    }
    
    public void addAllItems(String grupo, String... items) {
        indicadoresMap.get(grupo).addAll(Arrays.asList(items));
    }
    
    public void removeItem(String grupo, String item){
        indicadoresMap.get(grupo).remove(item);
    }
    
    public void removeAllItems(String grupo, String... items) {
        indicadoresMap.get(grupo).removeAll(Arrays.asList(items));
    }
}
