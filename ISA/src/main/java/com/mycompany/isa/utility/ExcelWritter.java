/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import com.mycompany.isa.ISA;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import com.mycompany.isa.model.CategoriaIndicadores;
import com.mycompany.isa.model.Lote;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.ss.util.RegionUtil;

/**
 *
 * @author naoki
 */
public class ExcelWritter {
    private static final String DEFAULT_PATH_LOTE = String.join(File.separator, "planilhas", "lotes");
    private static final String DEFAULT_PATH_ASSENTAMENTO = String.join(File.separator, "planilhas", "assentamentos");
    private final Workbook workbook = new XSSFWorkbook();
    private Lote lote;
    private CellStyle styleBody;
    private CellStyle styleHeader;

    public ExcelWritter() {
        initStyle();
    }
    
    public void createLoteWorkbook(Lote lote) {
        clearWorkbook();
        this.lote = lote;
        loteInfoSheet();
        DataTransfer.importIndicadores().forEach(cat->categoriaToSheet(cat));
        String name = lote.getResponsavel()+"-"+lote.getNumParcela()+".xlsx";
        File file = new File(DEFAULT_PATH_LOTE, name);
        saveToFile(file);
    }
    
    public void createLoteWorkbook(Lote lote, File file) {
        clearWorkbook();
        this.lote = lote;
        loteInfoSheet();
        DataTransfer.importIndicadores().forEach(cat->categoriaToSheet(cat));
        saveToFile(file);
    }
    
    public void createAssentamentoWorkbook(String assentamento) {
        clearWorkbook();
        List<Lote> lotes = new ArrayList<>();
        for (Lote l : ISA.loteList) {
            if (l.getAssentamento().equals(assentamento)) {
                lotes.add(l);
            }
        }
        
        assentamentoToSheet(assentamento, lotes);
        String name = assentamento+".xlsx";
        saveToFile(new File(DEFAULT_PATH_ASSENTAMENTO, name));
    }
    
    public void createAssentamentoWorkbook(String assentamento, File file) {
        clearWorkbook();
        List<Lote> lotes = new ArrayList<>();
        for (Lote l : ISA.loteList) {
            if (l.getAssentamento().equals(assentamento)) {
                lotes.add(l);
            }
        }
        
        assentamentoToSheet(assentamento, lotes);
        saveToFile(file);
    }
    
    private void clearWorkbook() {
        while (workbook.getNumberOfSheets() > 0) {
            workbook.removeSheetAt(0);
        }
    }
    
    private void initStyle() {
        styleBody = workbook.createCellStyle();
        styleHeader = workbook.createCellStyle();
        // Borders
        styleBody.setBorderBottom(BorderStyle.THIN);
        styleBody.setBorderLeft(BorderStyle.THIN);
        styleBody.setBorderRight(BorderStyle.THIN);
        styleBody.setBorderTop(BorderStyle.THIN);
        styleHeader.cloneStyleFrom(styleBody);
        
        // Font - styleBody
        XSSFFont fontBody = ((XSSFWorkbook) workbook).createFont();
        fontBody.setFontName("Arial");
        fontBody.setFontHeightInPoints((short) 13);
        fontBody.setBold(false);
        styleBody.setFont(fontBody);
        styleBody.setAlignment(HorizontalAlignment.CENTER);
        styleBody.setVerticalAlignment(VerticalAlignment.CENTER);
        styleBody.setWrapText(true);
        
        // Font - styleHeader
        XSSFFont fontHeader = ((XSSFWorkbook) workbook).createFont();
        fontHeader.setFontName("Arial");
        fontHeader.setFontHeightInPoints((short) 13); 
        fontHeader.setBold(true);
        styleHeader.setFont(fontHeader);
        styleHeader.setAlignment(HorizontalAlignment.CENTER);
        styleHeader.setVerticalAlignment(VerticalAlignment.CENTER);
    }
    private void loteInfoSheet() {
        String[] headerValues = new String[]{
            "Assentamento", "Responsavel", "Número da Parcela",
            "Contato", "Coordenadas"};
        Sheet sheet = workbook.createSheet("Informações do lote");
        Row row = sheet.createRow(1);
        Cell cell;
        for (int i = 0; i < headerValues.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(headerValues[i]);
            cell.setCellStyle(styleHeader);
        }
        
        row = sheet.createRow(2);
        String[] data = new String[] {
            lote.getAssentamento(), lote.getResponsavel(),
            lote.getNumParcela(), lote.getContato(), Arrays.toString(lote.getCoordenada())
        };
        for (int i = 0; i < data.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(data[i]);
            cell.setCellStyle(styleBody);
            sheet.autoSizeColumn(i+1);
        }
        
        row = sheet.createRow(4);
        cell = row.createCell(1);
        cell.setCellValue("Índice do Lote:");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(2);
        cell.setCellValue(lote.calcularIndiceGeral());
        cell.setCellStyle(styleBody);
    }
    
    private void assentamentoToSheet(String assentamento, List<Lote> lotes) {        
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(1);
        String[] values = new String[]{"Assentamento", "Índice de Sustentabilidade"};
        for (int i = 0; i < values.length; i++) {
            createCell(row, i+1, values[i], styleHeader);
        }
        CellRangeAddress mergeRegion = new CellRangeAddress(1, 2, 1, 1);
        sheet.addMergedRegion(mergeRegion);
        RegionUtil.setBorderLeft(BorderStyle.THIN, mergeRegion, sheet);
        regionSetAllBorders(BorderStyle.THIN, mergeRegion, sheet);
        mergeRegion = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(mergeRegion);
        regionSetAllBorders(BorderStyle.THIN, mergeRegion, sheet);
        
        row = sheet.createRow(2);
        values = new String[]{"Índice", "Desvio Padrão"};
        for (int i = 0; i < values.length; i++) {
            createCell(row, i+2, values[i], styleHeader);
        }
        
        row = sheet.createRow(3);
        Object[] objs = new Object[]{assentamento, CalcularIndice.averageAssentamento(lotes), CalcularIndice.desvioPadraoAssentamento(lotes)};
        System.out.println(Arrays.toString(objs));
        for (int i = 0; i < objs.length; i++) {
            if (i==0) {
                createCell(row, i+1, String.valueOf(objs[i]), styleBody);
            } else {
                createCell(row, i+1, (double) objs[i], styleBody);
            }
        }
        for (int i = 1; i <= 3; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Lotes do assentamento
        //cabeçalho
        row = sheet.createRow(5);
        values = new String[]{"Responsável", "Parcela", "Índice do lote"};
        for (int i = 0; i < values.length; i++) {
            createCell(row, i+1, values[i], styleHeader);
        }
        // dados
        for (int i = 0; i < lotes.size(); i++) {
            row = sheet.createRow(i+6); // começa da linha 6
            Lote l = lotes.get(i);
            createCell(row, 1, l.getResponsavel(), styleBody);
            createCell(row, 2, l.getNumParcela(), styleBody);
            createCell(row, 3, l.calcularIndiceGeral(), styleBody);
        }
    }
    
    private Cell createCell(Row row, int i, String value, CellStyle style) {
        Cell cell = row.createCell(i);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        return cell;        
    }
    private Cell createCell(Row row, int i, double value, CellStyle style) {
        Cell cell = row.createCell(i);
        cell.setCellValue(value);
        cell.setCellStyle(style);
        return cell;        
    }
    
    private void categoriaToSheet(CategoriaIndicadores categoria) {
        // Dicionário com os dados
        Map<String, List<String>> dados = categoria.getItemMap();
        // Lista com scores
        double[] scores = lote.getScoresOf(categoria.getNome().hashCode());

        // Crie uma nova planilha
        Sheet sheet = workbook.createSheet(categoria.getNome());
        
        int rowNum = 1;
        
        Row row = sheet.createRow(rowNum);
        createCell(row, 1, categoria.getNome(), styleHeader);
        CellRangeAddress mergedCell = new CellRangeAddress(rowNum, rowNum, 1, 4);
        sheet.addMergedRegion(mergedCell);
        regionSetAllBorders(BorderStyle.MEDIUM, mergedCell, sheet);
        
        row = sheet.createRow(++rowNum); //2
        String[] headers = new String[]{"Indicadores", null, "Parâmetros", "Índice"};
        for (int i = 0; i < headers.length; i++) {
            createCell(row, i+1, headers[i], styleHeader);
        }
        
        mergedCell = new CellRangeAddress(rowNum, rowNum, 1, 2);
        sheet.addMergedRegion(mergedCell);
        
        // Itere sobre as entradas do dicionário
        rowNum++; //3
        for (Map.Entry<String, List<String>> entry : dados.entrySet()) {
            row = sheet.createRow(rowNum);
            String chave = entry.getKey();
            List<String> valores = entry.getValue();

            // Defina o valor da célula na coluna 1 (mesclando n linhas)
            createCell(row, 1, chave, styleBody);
            mergedCell = new CellRangeAddress(rowNum, rowNum+valores.size()-1, 1, 1);
//            regionSetAllBorders(BorderStyle.THIN, mergedCell, sheet);
            sheet.addMergedRegion(mergedCell);
            
            // Valor da célula na coluna 4 (mesclando n linhas)
            Cell cell = row.createCell(4);
            cell.setCellStyle(styleBody);
            mergedCell = new CellRangeAddress(rowNum, rowNum+valores.size()-1, 4, 4);
            sheet.addMergedRegion(mergedCell);
            cell.setCellFormula(String.format("AVERAGE(D%d:D%d)", mergedCell.getFirstRow()+2, mergedCell.getLastRow()+2));
            
            
            // Preencha os valores da coluna 2 e 3          
            int scorePos = 0;
            for (int i=0; i<valores.size(); i++) {
                createCell(row, 2, valores.get(i), styleBody);
                createCell(row, 3, scores[scorePos++], styleBody);
                row = sheet.createRow(++rowNum);
            }
            // Borda dos grupos
            regionSetAllBorders(BorderStyle.MEDIUM, new CellRangeAddress(rowNum-valores.size(), rowNum-1, 1, 4) , sheet);

        }
        
        // Estilizar tabela
        for (int i = 0; i < 4; i++) {
            sheet.autoSizeColumn(i, true);
            if (sheet.getColumnWidth(i)>256*50) { // char length = 1/256
                sheet.setColumnWidth(i, 256*50);
            }
        }
        regionSetAllBorders(BorderStyle.MEDIUM, new CellRangeAddress(1, scores.length+2, 1, 4) , sheet);
    }
    
    private void regionSetAllBorders(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        RegionUtil.setBorderTop(border, region, sheet);
        RegionUtil.setBorderLeft(border, region, sheet);
        RegionUtil.setBorderBottom(border, region, sheet);
        RegionUtil.setBorderRight(border, region, sheet);
    }
    
    public void saveToFile(File file) {
         // Salve o arquivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(file)) {
            Files.createDirectories(Paths.get(file.getParent()));
            workbook.write(outputStream);
            outputStream.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, 
                    String.format("O arquivo \"%s\" está sendo usado em outro processo. Feche o aplicativo e tente novamente", file.getName()), 
                    "Erro ao salvar arquivo", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(ExcelWritter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelWritter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}