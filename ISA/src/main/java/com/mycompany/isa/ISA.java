/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.isa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.model.ModeloIndicadores;
import com.mycompany.isa.view.MainFrame;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ISA {
    public static List<Lote> loteList = new ArrayList<>();
    public static List<ModeloIndicadores> indicadoresList = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {

        MainFrame telaPrincipal = new MainFrame();
        telaPrincipal.setVisible(true);
    }
}
