/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.components;

import com.mycompany.isa.ISA;
import com.mycompany.isa.view.CrudIndicadores;
import com.mycompany.isa.view.CrudLote;
import com.mycompany.isa.view.MainFrame;
import com.mycompany.isa.view.VisualizarLotes;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author naoki
 */
public class StandartMenuBar extends JMenuBar{
    private JMenu menuInicio = new JMenu();
    private JMenu menuCalcularIndice = new JMenu();
    private JMenu menuGerenciarLotes = new JMenu();
    private JMenu menuGerenciarIndicadores = new JMenu();
    public StandartMenuBar() {
        
        
        menuInicio.setText("Início");
        menuInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuInicioMousePressed(evt);
            }
        });
        this.add(menuInicio);

        menuCalcularIndice.setText("Índice de Sustentabilidade");
        menuCalcularIndice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuCalcularIndiceMousePressed(evt);
            }
        });
        this.add(menuCalcularIndice);
        
        menuGerenciarLotes.setText("Gerenciar lotes");
        menuGerenciarLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuGerenciarLotesMousePressed(evt);
            }
        });
        this.add(menuGerenciarLotes);
        
        menuGerenciarIndicadores.setText("Gerenciar indicadores");
        menuGerenciarIndicadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuGerenciarIndicadoresMousePressed(evt);
            }
        });
        this.add(menuGerenciarIndicadores);
    }
    private void disposeAllWindows() {
        for (var frame : java.awt.Frame.getFrames()) {
            frame.dispose();
        }

    }
    private void menuInicioMousePressed(java.awt.event.MouseEvent evt) {
        disposeAllWindows();
        new MainFrame().setVisible(true);
    }
    
    private void menuCalcularIndiceMousePressed(java.awt.event.MouseEvent evt) {                                                
        disposeAllWindows();
        new VisualizarLotes().setVisible(true);
    }                                                                                             

    private void menuGerenciarLotesMousePressed(java.awt.event.MouseEvent evt) {                                                
        disposeAllWindows();
        new CrudLote().setVisible(true);
    }                                                                                              

    private void menuGerenciarIndicadoresMousePressed(java.awt.event.MouseEvent evt) {                                                      
        disposeAllWindows();
        new CrudIndicadores(ISA.categoriaList).setVisible(true);
    }                                                     
}
