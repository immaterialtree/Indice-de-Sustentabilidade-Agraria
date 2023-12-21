/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.isa.view;

import com.mycompany.isa.ISA;
import com.mycompany.isa.components.RefreshableJanela;
import com.mycompany.isa.model.Lote;
import com.mycompany.isa.utility.CalcularIndice;
import com.mycompany.isa.utility.ExcelWritter;
import com.mycompany.isa.view.indicadores.IndicadoresFrame;
import java.awt.Cursor;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Dialog;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author naoki
 */
public class VizualizarIndices extends RefreshableJanela {
    private Map<String, List<Lote>> assentamentosMap;
    private final JDialog diag = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);
    /**
     * Creates new form VisualizarLotes
     */
    public VizualizarIndices() {
        initComponents();
        scrollAssentamento.getVerticalScrollBar().setUnitIncrement(10);
        if (ISA.loteList.isEmpty()) {
            btnPropriedades.setEnabled(false);
            btnVisualizar.setEnabled(false);
        } else 
            jListLotes.setSelectedIndex(0);
        refreshJanela();
    }
    
    private void gerarMapAssentamento() {
        assentamentosMap = new HashMap<>();
        for (Lote lote : ISA.loteList) {
            if (!assentamentosMap.containsKey(lote.getAssentamento())) {
                assentamentosMap.put(lote.getAssentamento(), new ArrayList<>());
            }
            assentamentosMap.get(lote.getAssentamento()).add(lote);
        }
    }
    
    private void preencherLista(String assentamento) {
        DefaultListModel resultList = new DefaultListModel();
        List<Lote> lotes = assentamentosMap.containsKey(assentamento) ? 
                            assentamentosMap.get(assentamento) : 
                            ISA.loteList;
        for (Lote lote : lotes) {
            String loteString = String.format("<html>"
                    + "<b>Assentamento:</b> %s <br>"
                    + "<b>Responsável:</b>  %s <br>"
                    + "<b>Parcela:</b>      %s</html>",
                    lote.getAssentamento(), lote.getResponsavel(), lote.getNumParcela());
            resultList.addElement(loteString);
        }
        jListLotes.setModel(resultList);
    }
    
    private void atualizarCbox() {
        cboxAssentamento.removeAllItems();
        cboxAssentamento.addItem(" - Mostrar todos - ");
        for (String assentamento : assentamentosMap.keySet())
            cboxAssentamento.addItem(assentamento);
    }
    
    @Override
    public void refreshJanela() {
        gerarMapAssentamento();
        lblIndice.setVisible(false);
        preencherLista(null);
        atualizarCbox();
        jListLotes.updateUI();
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

        dialogPropriedades = new javax.swing.JDialog();
        panelPropriedades = new javax.swing.JPanel();
        txtContato = new javax.swing.JFormattedTextField();
        txtNome = new javax.swing.JTextField();
        txtNumParcela = new javax.swing.JTextField();
        panelCoordenada = new javax.swing.JPanel();
        lblCoordenadaX = new javax.swing.JLabel();
        lblCoordenadaY = new javax.swing.JLabel();
        txtCoordenadaX = new javax.swing.JFormattedTextField();
        txtCoordenadaY = new javax.swing.JFormattedTextField();
        txtAssentamento = new javax.swing.JTextField();
        txtIndiceDeSustentabilidade = new javax.swing.JTextField();
        dialogAssentamentos = new javax.swing.JDialog();
        scrollAssentamento = new javax.swing.JScrollPane();
        panelAssentamentos = new javax.swing.JPanel();
        lblAssentamentosTable = new javax.swing.JLabel();
        jOptionPane1 = new javax.swing.JOptionPane();
        panelExportar = new javax.swing.JPanel();
        lblExportar = new javax.swing.JLabel();
        cboxExportar = new javax.swing.JComboBox<>();
        btnExportarConfirma = new javax.swing.JButton();
        lblExportarLoteSelecionado = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListLotes = new javax.swing.JList<>();
        btnVisualizar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnPropriedades = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();
        btnIndiceAssentamentos = new javax.swing.JToggleButton();
        cboxAssentamento = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        lblIndice = new javax.swing.JLabel();
        btnExportar = new javax.swing.JButton();

        dialogPropriedades.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dialogPropriedades.setTitle("Propriedades do lote");
        dialogPropriedades.setResizable(false);

        txtContato.setEditable(false);
        txtContato.setBorder(javax.swing.BorderFactory.createTitledBorder("Contato"));
        try {
            txtContato.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        txtNome.setEditable(false);
        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder("Responsável"));

        txtNumParcela.setEditable(false);
        txtNumParcela.setBorder(javax.swing.BorderFactory.createTitledBorder("N° da parcela"));

        panelCoordenada.setBorder(javax.swing.BorderFactory.createTitledBorder("Coordenadas"));

        lblCoordenadaX.setText("Latitude");

        lblCoordenadaY.setText("Longitude");

        txtCoordenadaX.setEditable(false);
        txtCoordenadaX.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        txtCoordenadaY.setEditable(false);
        txtCoordenadaY.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        javax.swing.GroupLayout panelCoordenadaLayout = new javax.swing.GroupLayout(panelCoordenada);
        panelCoordenada.setLayout(panelCoordenadaLayout);
        panelCoordenadaLayout.setHorizontalGroup(
            panelCoordenadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoordenadaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCoordenadaX)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCoordenadaX, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCoordenadaY)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCoordenadaY, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelCoordenadaLayout.setVerticalGroup(
            panelCoordenadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCoordenadaLayout.createSequentialGroup()
                .addGroup(panelCoordenadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCoordenadaX)
                    .addComponent(lblCoordenadaY)
                    .addComponent(txtCoordenadaX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCoordenadaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtAssentamento.setEditable(false);
        txtAssentamento.setBorder(javax.swing.BorderFactory.createTitledBorder("Assentamento"));

        txtIndiceDeSustentabilidade.setEditable(false);
        txtIndiceDeSustentabilidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Índice de Sustentabilidade"));

        javax.swing.GroupLayout panelPropriedadesLayout = new javax.swing.GroupLayout(panelPropriedades);
        panelPropriedades.setLayout(panelPropriedadesLayout);
        panelPropriedadesLayout.setHorizontalGroup(
            panelPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPropriedadesLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNumParcela, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtContato)
                    .addComponent(panelCoordenada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAssentamento, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIndiceDeSustentabilidade))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelPropriedadesLayout.setVerticalGroup(
            panelPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPropriedadesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtAssentamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtNumParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCoordenada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIndiceDeSustentabilidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout dialogPropriedadesLayout = new javax.swing.GroupLayout(dialogPropriedades.getContentPane());
        dialogPropriedades.getContentPane().setLayout(dialogPropriedadesLayout);
        dialogPropriedadesLayout.setHorizontalGroup(
            dialogPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPropriedades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        dialogPropriedadesLayout.setVerticalGroup(
            dialogPropriedadesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPropriedades, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelAssentamentos.setLayout(new java.awt.GridBagLayout());

        lblAssentamentosTable.setText("tabela de assentamentos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 20, 0);
        panelAssentamentos.add(lblAssentamentosTable, gridBagConstraints);

        scrollAssentamento.setViewportView(panelAssentamentos);

        javax.swing.GroupLayout dialogAssentamentosLayout = new javax.swing.GroupLayout(dialogAssentamentos.getContentPane());
        dialogAssentamentos.getContentPane().setLayout(dialogAssentamentosLayout);
        dialogAssentamentosLayout.setHorizontalGroup(
            dialogAssentamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollAssentamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
        );
        dialogAssentamentosLayout.setVerticalGroup(
            dialogAssentamentosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollAssentamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
        );

        jOptionPane1.setWantsInput(true);

        lblExportar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblExportar.setText("<html>\n<p>\n<strong>LOTE SELECIONADO:<strong>\n</p>");

        cboxExportar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cboxExportar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exportar lote selecionado", "Exportar um assentamento...", "Exportar todos os lotes de um assentamento..." }));

        btnExportarConfirma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportarConfirma.setText("Exportar");
        btnExportarConfirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarConfirmaActionPerformed(evt);
            }
        });

        lblExportarLoteSelecionado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblExportarLoteSelecionado.setText("<html>\n<b>\nAssentamento:<br>\nResponsável:<br>\nParcela:\n</b>");
        lblExportarLoteSelecionado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelExportarLayout = new javax.swing.GroupLayout(panelExportar);
        panelExportar.setLayout(panelExportarLayout);
        panelExportarLayout.setHorizontalGroup(
            panelExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelExportarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cboxExportar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExportar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(btnExportarConfirma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblExportarLoteSelecionado, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(20, 20, 20))
        );
        panelExportarLayout.setVerticalGroup(
            panelExportarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExportarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(lblExportarLoteSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxExportar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportarConfirma)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ISA-RURAL");
        setResizable(false);

        jListLotes.setBorder(null);
        jListLotes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jListLotes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListLotesMouseClicked(evt);
            }
        });
        jListLotes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListLotesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jListLotes);

        btnVisualizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVisualizar.setText("Preencher pontuação de sustentabilidade do lote");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Escolha um Lote");

        btnPropriedades.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnPropriedades.setText("Ver informações do lote");
        btnPropriedades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPropriedadesActionPerformed(evt);
            }
        });

        btnVoltar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        btnIndiceAssentamentos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIndiceAssentamentos.setText("Visualizar índice dos assentamentos");
        btnIndiceAssentamentos.setMaximumSize(new java.awt.Dimension(111, 27));
        btnIndiceAssentamentos.setMinimumSize(new java.awt.Dimension(111, 27));
        btnIndiceAssentamentos.setPreferredSize(new java.awt.Dimension(111, 27));
        btnIndiceAssentamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIndiceAssentamentosActionPerformed(evt);
            }
        });

        cboxAssentamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboxAssentamentoItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-filter-16.png"))); // NOI18N
        jLabel1.setText("Filtrar assentamento");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel1.setIconTextGap(15);

        lblIndice.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblIndice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIndice.setText("Índice do assentamento: ");

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVoltar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExportar)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblIndice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxAssentamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnIndiceAssentamentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPropriedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(btnVisualizar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(66, 66, 66))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVoltar)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitulo)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboxAssentamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPropriedades, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIndiceAssentamentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblIndice)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void abrirAssentamento() {
        String assentamento = (String) cboxAssentamento.getSelectedItem();
        int selected = jListLotes.getSelectedIndex();
        if (selected==-1) return;
        Lote lote;
        if (assentamentosMap.containsKey(assentamento)){
            lote = assentamentosMap.get(assentamento).get(selected);
        } else {
            lote = ISA.loteList.get(selected);
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        IndicadoresFrame.lote = lote;
        ISA.trocarJanela(ISA.Janela.INDICADORES);
        this.setCursor(Cursor.getDefaultCursor());
    }
    
    private void exportarAssentamento(String assentamento) {
        JFileChooser fileChooser = new JFileChooser(); 
        fileChooser.setSelectedFile(new File(assentamento+".xlsx"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("Planilha excel","xlsx"));
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            ExcelWritter ew = new ExcelWritter();
            ew.createAssentamentoWorkbook(assentamento, fileChooser.getSelectedFile());
        }
    }
    private void exportarLote(Lote lote) {
        String name = lote.getResponsavel()+"-"+lote.getNumParcela();
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivo do Microsoft Excel (*.xlsx)","xlsx"));
        fileChooser.setSelectedFile(new File(name+".xlsx"));
        
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            ExcelWritter ew = new ExcelWritter();
            ew.createLoteWorkbook(lote, fileChooser.getSelectedFile());
        }
    }
    
    private void exportarLotes(Lote[]  lotes) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int result = fileChooser.showSaveDialog(null);
        String name;
        if (result == JFileChooser.APPROVE_OPTION) {
            ExcelWritter ew = new ExcelWritter();
            for (Lote lote : lotes) {
                name = lote.getResponsavel()+"-"+lote.getNumParcela()+".xlsx";
                ew.createLoteWorkbook(lote, new File(fileChooser.getSelectedFile().getAbsolutePath(), name));
            }
        }
    }
    
    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        abrirAssentamento();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnPropriedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPropriedadesActionPerformed
        int selected = jListLotes.getSelectedIndex();
        if (selected==-1) return;
        Lote lote = ISA.loteList.get(selected);
        
        txtAssentamento.setText(lote.getAssentamento());
        txtNome.setText(lote.getResponsavel());
        txtNumParcela.setText(lote.getNumParcela());
        txtContato.setText(lote.getContato());
        txtCoordenadaX.setText(String.valueOf(lote.getCoordenada()[0]));
        txtCoordenadaY.setText(String.valueOf(lote.getCoordenada()[1])); 
        txtIndiceDeSustentabilidade.setText(String.format("%.3f",lote.calcularIndiceGeral()));
        dialogPropriedades.setSize(390, 370);
        dialogPropriedades.setLocationRelativeTo(null);
        dialogPropriedades.setVisible(true);
    }//GEN-LAST:event_btnPropriedadesActionPerformed

    private void jListLotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListLotesMouseClicked
        if (evt.getClickCount()>1 && evt.getButton()==MouseEvent.BUTTON1)
            abrirAssentamento();
    }//GEN-LAST:event_jListLotesMouseClicked

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        ISA.trocarJanela(ISA.Janela.MAIN);
    }//GEN-LAST:event_btnVoltarActionPerformed

    private void jListLotesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListLotesValueChanged
        btnPropriedades.setEnabled(jListLotes.getSelectedIndex() != -1);
        btnVisualizar.setEnabled(jListLotes.getSelectedIndex() != -1);
    }//GEN-LAST:event_jListLotesValueChanged

    private void btnIndiceAssentamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIndiceAssentamentosActionPerformed
        String htmlTable = """
                           <html>
                                <style>
                                table {
                                    width: 300px;
                                    font-family: segoe, sans-serif;
                                    font-size:10px;
                                    border-collapse: collapse;
                                    border-spacing: 0px;
                                }
                                td, th {
                                        border: 1px solid black;
                                        padding: 6px;
                                    }
                                </style>
                           <table>
                           <tr> <th>ASSENTAMENTO</th> <th>ÍNDICE</th> <th>DESVIO PADRÃO</th></tr>
                           %s
                           </table>
                           </html>
                           """;
        String htmlItem = "<tr> <td>%s</td> <td>%.3f</td> <td>%.3f</td></tr>";
        
        StringBuilder htmlItens = new StringBuilder();
        for (String key : assentamentosMap.keySet()) {
            if (assentamentosMap.get(key).isEmpty()) continue;
            double indice = CalcularIndice.averageAssentamento(assentamentosMap.get(key));
            double dp = CalcularIndice.desvioPadraoAssentamento(assentamentosMap.get(key));
            htmlItens.append(String.format(htmlItem, key, indice, dp));
        }
        lblAssentamentosTable.setText(String.format(htmlTable, htmlItens.toString()));
        
        dialogAssentamentos.setSize(420, 400);
        dialogAssentamentos.setLocationRelativeTo(null);
        dialogAssentamentos.setVisible(true);
    }//GEN-LAST:event_btnIndiceAssentamentosActionPerformed

    private void cboxAssentamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboxAssentamentoItemStateChanged
        if (cboxAssentamento.getSelectedIndex()==-1) return;
        if (cboxAssentamento.getSelectedIndex()==0) {
            preencherLista(null);
            lblIndice.setVisible(false);
            //btnIndiceAssentamentos.setVisible(true);
        } else {
            String assentamento = cboxAssentamento.getSelectedItem().toString();
            preencherLista(assentamento);
            lblIndice.setVisible(true);
            //btnIndiceAssentamentos.setVisible(false);
            double indice = CalcularIndice.averageAssentamento(assentamentosMap.get(assentamento));
            double dp = CalcularIndice.desvioPadraoAssentamento(assentamentosMap.get(assentamento));
            lblIndice.setText(String.format("Índice do assentamento: %.3f ± %.3f", indice, dp));
        }
        if (cboxAssentamento.getItemCount()>0){
            jListLotes.setSelectedIndex(0);
        }
    }//GEN-LAST:event_cboxAssentamentoItemStateChanged

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        String selection = jListLotes.getSelectedValue();
        lblExportarLoteSelecionado.setText(selection);
        diag.setTitle("Exportar");
        diag.getContentPane().add(panelExportar);
        diag.pack();
        diag.setLocationRelativeTo(null);
        diag.setVisible(true);
    }//GEN-LAST:event_btnExportarActionPerformed

    private void btnExportarConfirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarConfirmaActionPerformed
        diag.dispose();
        int op = cboxExportar.getSelectedIndex();
        switch (op) {
            case 0 -> {
                Lote lote = ISA.loteList.get(jListLotes.getSelectedIndex());
                exportarLote(lote);
            }
            case 1 -> {
                Object[] selectionValues = assentamentosMap.keySet().toArray();
                String assentamento = (String) JOptionPane.showInputDialog(this, "Escolha o assentamento que deseja exportar", 
                        "Exportar assentamento", JOptionPane.PLAIN_MESSAGE, null,
                        selectionValues, DISPOSE_ON_CLOSE);
                if (assentamento!=null) exportarAssentamento(assentamento);
            }
            case 2 -> {
                Object[] selectionValues = assentamentosMap.keySet().toArray();
                String assentamento = (String) JOptionPane.showInputDialog(this, "Escolha o assentamento cujos lotes deseja exportar",
                        "Exportar lotes do assentamento", JOptionPane.PLAIN_MESSAGE, null,
                        selectionValues, DISPOSE_ON_CLOSE);
                if (assentamento!=null)exportarLotes(assentamentosMap.get(assentamento).toArray(Lote[]::new));
            }
            default -> {}
        }
        
    }//GEN-LAST:event_btnExportarConfirmaActionPerformed

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
            java.util.logging.Logger.getLogger(VizualizarIndices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VizualizarIndices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VizualizarIndices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VizualizarIndices.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new VizualizarIndices().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnExportarConfirma;
    private javax.swing.JToggleButton btnIndiceAssentamentos;
    private javax.swing.JButton btnPropriedades;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cboxAssentamento;
    private javax.swing.JComboBox<String> cboxExportar;
    private javax.swing.JDialog dialogAssentamentos;
    private javax.swing.JDialog dialogPropriedades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListLotes;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAssentamentosTable;
    private javax.swing.JLabel lblCoordenadaX;
    private javax.swing.JLabel lblCoordenadaY;
    private javax.swing.JLabel lblExportar;
    private javax.swing.JLabel lblExportarLoteSelecionado;
    private javax.swing.JLabel lblIndice;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelAssentamentos;
    private javax.swing.JPanel panelCoordenada;
    private javax.swing.JPanel panelExportar;
    private javax.swing.JPanel panelPropriedades;
    private javax.swing.JScrollPane scrollAssentamento;
    private javax.swing.JTextField txtAssentamento;
    private javax.swing.JFormattedTextField txtContato;
    private javax.swing.JFormattedTextField txtCoordenadaX;
    private javax.swing.JFormattedTextField txtCoordenadaY;
    private javax.swing.JTextField txtIndiceDeSustentabilidade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumParcela;
    // End of variables declaration//GEN-END:variables
}