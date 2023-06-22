/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isa;

import model.IndicadoresModelo;
import view.MainFrame;
import view.indicadores.IndicadoresFrame;

/**
 *
 * @author Admin
 */
public class ISA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] grupos = {"Paisagem", "Qualidade Ambiental"};
        String[][] items = {
            {
                "Fisionomia e conservação de habitats naturais", 
                "Cumprimento com requerimento da reserva legal",
                "Cumprimento com requerimento de áreas de PP"
            },
            {
                "Atmosfera", "Água", "Solo"
            },
        };
        
        IndicadoresModelo ambientalTabela = new IndicadoresModelo("Indicadores Ambientais");
        for (int i=0; i<grupos.length; i++) {
            ambientalTabela.addGrupo(grupos[i]);
            ambientalTabela.addAllItems(grupos[i], items[i]);
        }
        IndicadoresFrame tela = new IndicadoresFrame(ambientalTabela);
        tela.setVisible(true);
    }
    
}
