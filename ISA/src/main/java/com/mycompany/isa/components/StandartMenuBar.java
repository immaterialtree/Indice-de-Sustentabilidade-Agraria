/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.components;

import com.mycompany.isa.ISA;
import com.mycompany.isa.view.AjudaFrame;
import com.mycompany.isa.view.SobreDialog;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author naoki
 */
public class StandartMenuBar extends JMenuBar{
    final private JMenu menuInicio = new JMenu();
    final private JMenu menuCalcularIndice = new JMenu();
    final private JMenu menuGerenciar = new JMenu();
    final private JMenu menuAjuda = new JMenu();
    final private JMenuItem itemGerenciarLotes = new JMenuItem();
    final private JMenuItem itemGerenciarIndicadores = new JMenuItem();
    final private JMenuItem itemManual = new JMenuItem();
    final private JMenuItem itemSobre = new JMenuItem();
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
        menuGerenciar.setText("Gerenciar");
        
        itemGerenciarLotes.setText("Gerenciar lotes");
        itemGerenciarLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuGerenciarLotesMousePressed(evt);
            }
        });
        menuGerenciar.add(itemGerenciarLotes);
        
        itemGerenciarIndicadores.setText("Gerenciar indicadores");
        itemGerenciarIndicadores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuGerenciarIndicadoresMousePressed(evt);
            }
        });
        menuGerenciar.add(itemGerenciarIndicadores);
        
        this.add(menuGerenciar);
        
        menuAjuda.setText("Ajuda");
        
        itemManual.setText("Manual do usuário");
        itemManual.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Arrays.stream(java.awt.Frame.getFrames()).forEach(a->
                    System.out.println(a.getName() + ", " +a.getClass().getName()+", "+ a.getTitle())
                );
                // Verifica se essa janela já foi criada
                for (var frame : java.awt.Frame.getFrames()) {
                    if (frame.getName().equals("ajuda")){
                        if (frame.isVisible()) {
                            frame.requestFocus();
                        } else {
                            ((AjudaFrame)frame).refresh();
                            frame.setLocationRelativeTo(menuAjuda);
                            frame.setVisible(true);
                        }
                        return;
                    }
                }
                // Cria a janela na primeira vez que o método é invocado
                AjudaFrame ajuda = new AjudaFrame();
                ajuda.setLocationRelativeTo(menuAjuda);                
                ajuda.setVisible(true);
            }
        });
        menuAjuda.add(itemManual);
        
        itemSobre.setText("Sobre");
        itemSobre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {  
                SobreDialog dialog = new SobreDialog(JFrame.getFrames()[0]);
                dialog.setVisible(true);
            }
        });
        menuAjuda.add(itemSobre);
        
        this.add(menuAjuda);
        
    }
    
    private void disposeAllWindows() {
        for (var frame : java.awt.Frame.getFrames()) {
            frame.dispose();
        }

    }
    private void menuInicioMousePressed(java.awt.event.MouseEvent evt) {
        ISA.trocarJanela(ISA.Janela.MAIN);
    }
    
    private void menuCalcularIndiceMousePressed(java.awt.event.MouseEvent evt) {                                                
        ISA.trocarJanela(ISA.Janela.VER_INDICE);
    }

    private void menuGerenciarLotesMousePressed(java.awt.event.MouseEvent evt) {                                                
        ISA.trocarJanela(ISA.Janela.CRUD_LOTE);
    }

    private void menuGerenciarIndicadoresMousePressed(java.awt.event.MouseEvent evt) {                                                      
        ISA.trocarJanela(ISA.Janela.CRUD_INDICADOR);
    }
}
