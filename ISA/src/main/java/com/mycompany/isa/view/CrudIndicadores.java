/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.isa.view;

import com.mycompany.isa.ISA;
import com.mycompany.isa.components.RefreshableJanela;
import com.mycompany.isa.model.CategoriaIndicadores;
import com.mycompany.isa.utility.JsonExporter;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author naoki
 */
public class CrudIndicadores extends RefreshableJanela{
    NovaCategoriaPanel novoModeloPanel;
    CardLayout cl;
    
    public CrudIndicadores() {
        initAll();
    }
    
    private void initAll() {
        initComponents();
        scrollTablePanel.getVerticalScrollBar().setUnitIncrement(10);
        cl = (CardLayout) cardPanel.getLayout();
        refreshJanela();
        
        novoModeloPanel = new NovaCategoriaPanel();
        cardPanel.add(novoModeloPanel, "new");
        novoModeloPanel.getBtnVoltar().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preencherLista();
                int reply = JOptionPane.showConfirmDialog(null, 
                        "Tem certeza que deseja sair sem salvar?", "Sair sem salvar", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (reply==JOptionPane.YES_OPTION)
                    cl.show(cardPanel, "home");
            }
        });
    }
    
    void preencherLista() {
        DefaultListModel resultList = new DefaultListModel();
        for (CategoriaIndicadores i : ISA.categoriaList) {
            resultList.addElement(i.getNome());
        }
        jListModelos.setModel(resultList);
        if (jListModelos.getSelectedIndex()==-1 && jListModelos.getModel().getSize() > 0) {
            jListModelos.setSelectedIndex(0);
        }
        preencherTabela(jListModelos.getSelectedIndex());
    }
    
    private StringBuilder htmlTable;
    void preencherTabela(int indice) {
        tablePanel.removeAll();
        tablePanel.updateUI();
        scrollTablePanel.getVerticalScrollBar().setValue(0);
        if (indice < 0) return;
        if (ISA.categoriaList.isEmpty()) {
            lblNomeModelo.setText("");
            return;
        }
        lblNomeModelo.setText(ISA.categoriaList.get(indice).getNome());
        int i=0;
        for (Map.Entry grupo: ISA.categoriaList.get(indice).getItemMap().entrySet()) {
            JLabel lblTable = new JLabel();
            String htmlStyle = """
                               <style>
                               table {
                                 width: %dpx;
                                 font-family: segoe, sans-serif;
                                 font-size:10px;
                                 border-collapse: collapse;
                                 border-spacing: 0px;
                               }
                               
                               td, th {
                                 border: 1px solid black;
                                 padding: 8px;
                               }
                               
                               td {border-top: 0px;}
                               
                               th {
                                 font-size: 12px;
                                 text-align: center;
                               }
                               
                               tr:nth-child(even) {
                                 background-color: #dddddd;
                               }
                               </style>
                               """;
            String htmlHeader = "<table> <tr> <th>%s</th> </tr>";
            htmlHeader = String.format(htmlHeader, grupo.getKey());
            String htmlItem = "<tr> <td>%s</td> </tr>";
            htmlTable= new StringBuilder("<html>"+htmlStyle+htmlHeader);
            for (String item : (List<String>) grupo.getValue()) {
                htmlTable.append(String.format(htmlItem, item));
            }
            htmlTable.append("</table></html>");
            int width = (int) (scrollTablePanel.getWidth()*0.70);
            String strTable = String.format(htmlTable.toString(), width);
            lblTable.setText(strTable);
            tablePanel.add(lblTable);
            GridBagLayout layout = (GridBagLayout) tablePanel.getLayout();
            GridBagConstraints c = layout.getConstraints(lblTable);
            c.insets = new Insets(0, 0, 10, 0);
            c.gridy = i++;
            layout.setConstraints(lblTable, c);
            tablePanel.updateUI();
        }
    }
    
    @Override
    public void refreshJanela() {
        preencherLista();
    }
    
    /**
     * Creates new form CadastrarModelo
     */

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListModelos = new javax.swing.JList<>();
        lblNomeModelo = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnResetDefault = new javax.swing.JButton();
        scrollTablePanel = new javax.swing.JScrollPane();
        tablePanel = new javax.swing.JPanel();
        standartMenuBar1 = new com.mycompany.isa.components.StandartMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ISA-Rural - Gerenciar Indicadores");

        cardPanel.setPreferredSize(new java.awt.Dimension(1317, 470));
        cardPanel.setLayout(new java.awt.CardLayout());

        jListModelos.setBorder(null);
        jListModelos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListModelosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListModelos);

        lblNomeModelo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNomeModelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNomeModelo.setText("Nome da Categoria");
        lblNomeModelo.setToolTipText("");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-edit-16.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-trash-16.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-new-file-16.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Categorias");

        btnResetDefault.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-reset-16.png"))); // NOI18N
        btnResetDefault.setText("Restaurar padrão");
        btnResetDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetDefaultActionPerformed(evt);
            }
        });

        scrollTablePanel.setBorder(null);

        tablePanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                tablePanelComponentResized(evt);
            }
        });
        tablePanel.setLayout(new java.awt.GridBagLayout());
        scrollTablePanel.setViewportView(tablePanel);

        javax.swing.GroupLayout homePanelLayout = new javax.swing.GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(homePanelLayout.createSequentialGroup()
                                .addComponent(btnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE))
                            .addComponent(btnResetDefault, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNomeModelo, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                    .addComponent(scrollTablePanel))
                .addGap(30, 30, 30))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeModelo)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(scrollTablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExcluir)
                    .addComponent(btnEditar)
                    .addComponent(btnNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnResetDefault)
                .addGap(46, 46, 46))
        );

        cardPanel.add(homePanel, "home");
        homePanel.getAccessibleContext().setAccessibleName("homePanel");

        setJMenuBar(standartMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(cardPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 476, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
  
    
    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        cl.show(cardPanel, "new");
        novoModeloPanel.resetar();
        novoModeloPanel.editing = false;
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (0!=JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir?", "Confirmar exclusão", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION)) {
            return;
        }
        ISA.categoriaList.remove(jListModelos.getSelectedIndex());
        preencherLista();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int selected = jListModelos.getSelectedIndex();
        if (selected < 0) {
            return;
        }
        novoModeloPanel.carregarCategoria(ISA.categoriaList.get(selected), selected);
        cl.show(cardPanel, "new");
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jListModelosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListModelosValueChanged
        preencherTabela(jListModelos.getSelectedIndex());
    }//GEN-LAST:event_jListModelosValueChanged

    private void btnResetDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetDefaultActionPerformed
        JsonExporter.restaurarIndicadoresPadrao();
        ISA.categoriaList = JsonExporter.importIndicadores();
        preencherLista();
    }//GEN-LAST:event_btnResetDefaultActionPerformed

    private void tablePanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tablePanelComponentResized
        preencherTabela(jListModelos.getSelectedIndex());
    }//GEN-LAST:event_tablePanelComponentResized
    
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrudIndicadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudIndicadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudIndicadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudIndicadores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudIndicadores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnResetDefault;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListModelos;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblNomeModelo;
    private javax.swing.JScrollPane scrollTablePanel;
    private com.mycompany.isa.components.StandartMenuBar standartMenuBar1;
    private javax.swing.JPanel tablePanel;
    // End of variables declaration//GEN-END:variables
}
