/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.isa;

import com.mycompany.isa.components.RefreshableJanela;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.CategoriaIndicadores;
import com.mycompany.isa.utility.JsonExporter;
import com.mycompany.isa.view.CrudIndicadores;
import com.mycompany.isa.view.CrudLote;
import com.mycompany.isa.view.MainFrame;
import com.mycompany.isa.view.VizualizarIndices;
import com.mycompany.isa.view.indicadores.IndicadoresFrame;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class ISA {
    public static List<Lote> loteList = new ArrayList<>();
    public static List<CategoriaIndicadores> categoriaList = new ArrayList<>();
    private static Map<Janela, RefreshableJanela> janelaMap = new HashMap<>();
    private static Janela janelaAtual;
    
    public enum Janela {
        MAIN, CRUD_LOTE, CRUD_INDICADOR, VER_INDICE, INDICADORES
    }
    
    public static void main(String[] args) {
        // Configurar look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Look and Feel do sistema não encontrado. Utilizando o padrão");
        }
        finally {
            System.out.println("O Look and Feel em uso é: "+UIManager.getLookAndFeel().getDescription());
        }
        
        // Importar objetos pelo JSON
        JsonExporter.createPaths();
        loteList = JsonExporter.importLotes();
        categoriaList = JsonExporter.importIndicadores();
        
        // Inicializar janelas
        janelaMap.put(Janela.MAIN, new MainFrame());
        janelaMap.put(Janela.CRUD_LOTE, new CrudLote());
        janelaMap.put(Janela.CRUD_INDICADOR, new CrudIndicadores());
        janelaMap.put(Janela.VER_INDICE, new VizualizarIndices());
        janelaMap.put(Janela.INDICADORES, new IndicadoresFrame());
        
        // inicializar aplicativo
        janelaAtual = Janela.MAIN;
        janelaMap.get(janelaAtual).setVisible(true);
    }
    
    public static void trocarJanela(Janela novaJanela) {
        if (janelaAtual==novaJanela) return;
        RefreshableJanela janelaFrame = janelaMap.get(novaJanela);
        janelaFrame.setLocationRelativeTo(janelaMap.get(janelaAtual));
        janelaFrame.refreshJanela();
        janelaFrame.setVisible(true);
        janelaMap.get(janelaAtual).dispose();
        janelaAtual = novaJanela;
    }
    
    public static javax.swing.JFrame getJanela(Janela janela) {
        return janelaMap.get(janela);
    }
}
