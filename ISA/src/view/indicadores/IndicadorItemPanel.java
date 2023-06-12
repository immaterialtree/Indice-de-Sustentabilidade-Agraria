/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.indicadores;

/**
 *
 * @author naoki
 */
public class IndicadorItemPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form IndicadoresPanel
     */
    public IndicadorItemPanel() {
        indicadorNome = "Sem nome";
        initComponents();
    }
    
    String indicadorNome;
    public IndicadorItemPanel(String nome) {
        indicadorNome = nome;
        initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblNomeItem = new javax.swing.JLabel();
        lblValorItem = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        lblNomeItem.setFont(new java.awt.Font("Liberation Sans", 0, 14)); // NOI18N
        lblNomeItem.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomeItem.setText(indicadorNome);
        lblNomeItem.setIconTextGap(1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(lblNomeItem, gridBagConstraints);

        lblValorItem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblValorItem.setText("0");
        lblValorItem.setToolTipText("Digite a pontuação desta modalidade (número real)");
        lblValorItem.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                lblValorItemPropertyChange(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(lblValorItem, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void lblValorItemPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_lblValorItemPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_lblValorItemPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblNomeItem;
    private javax.swing.JTextField lblValorItem;
    // End of variables declaration//GEN-END:variables
}