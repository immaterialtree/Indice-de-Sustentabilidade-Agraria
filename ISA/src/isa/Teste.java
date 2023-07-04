/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package isa;

import javax.swing.JFrame;
import model.ModeloIndicadores;
import view.NovoModelo;

/**
 *
 * @author naoki
 */
public class Teste {
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
        JFrame frame = new JFrame();
        frame.setBounds(0, 0, 800, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new NovoModelo(new ModeloIndicadores("Indice de sustentbilidade", grupos, items)));
        frame.setVisible(true);
    }
}
