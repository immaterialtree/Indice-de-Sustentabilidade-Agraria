/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package isa;

import java.util.ArrayList;
import java.util.List;
import model.IndicadoresModelo;
import view.MainFrame;
import view.indicadores.IndicadoresFrame;

/**
 *
 * @author Admin
 */
public class ISA {
    public static List<IndicadoresModelo> modelList = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        String[] grupos = {"Paisagem", "Qualidade Ambiental"};
        String[][] items = {
            {
                "Conservação de habitats", 
                "reserva legal",
                "áreas de PP"
            },
            {
                "Atmosfera", "Água", "Solo"
            },
        };
        
         
        IndicadoresModelo ambientalTabela = new IndicadoresModelo("Indicadores Ambientais", grupos, items);
        modelList.add(ambientalTabela);
        MainFrame telaPrincipal = new MainFrame();
        telaPrincipal.setVisible(true);
        
//        IndicadoresFrame tela = new IndicadoresFrame(ambientalTabela);
//        tela.setVisible(true);
    }
    
}
