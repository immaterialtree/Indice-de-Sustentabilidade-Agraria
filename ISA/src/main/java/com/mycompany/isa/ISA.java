/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.isa;

import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.CategoriaIndicadores;
import com.mycompany.isa.utility.DataTransfer;
import com.mycompany.isa.view.MainFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;

/**
 *
 * @author Admin
 */
public class ISA {
    public static List<Lote> loteList = new ArrayList<>();
    public static List<CategoriaIndicadores> indicadoresList = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // Setting look and feel
        try {
  
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Look and Feel não configurado");
        }
        
        // load objects
        loteList = DataTransfer.importLotes();
        indicadoresList = DataTransfer.importIndicadores();
        
        // start aplication
        MainFrame telaPrincipal = new MainFrame();
        telaPrincipal.setVisible(true);
    }
}
