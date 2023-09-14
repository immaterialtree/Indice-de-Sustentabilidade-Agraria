/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.utility;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import com.mycompany.isa.model.CategoriaIndicadores;
import com.mycompany.isa.model.Lote;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.util.RegionUtil;

/**
 *
 * @author naoki
 */
public class ExcelWritter {
    private Lote lote; 
    private Workbook workbook;
    private CellStyle styleBody;
    private CellStyle styleHeader;

    public ExcelWritter(Lote lote) {
        this.lote = lote;        
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }
    
    public void newWorkbook() {
        workbook = new XSSFWorkbook();
        initStyle();
    }
    
    public void createLoteWorkbook() {
        workbook = new XSSFWorkbook();
        initStyle();
        loteInfoSheet();
        DataTransfer.importIndicadores().forEach(cat->categoriaToSheet(cat));
        saveToFile();
    }
    
    public void createAssentamentoWorkbook(String assentamento) {
        workbook = new XSSFWorkbook();
        initStyle();
        
    }
    
    private void assentamentoToSheet(String assentamento, List<Lote> lotes) {        
        workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row row = sheet.createRow(0);
        String[] values = new String[]{"Assentamento", "Indice de Sustentabilidade"};
        Cell cell;
        for (int i = 0; i < values.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(values[i]);
            cell.setCellStyle(styleHeader);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        
        row = sheet.createRow(1);
        values = new String[]{"Índice", "Desvio Padrão"};
        for (int i = 0; i < values.length; i++) {
            cell = row.createCell(i+1);
            cell.setCellValue(values[i]);
            cell.setCellStyle(styleHeader);
        }
        
        row = sheet.createRow(2);
        values = new String[]{assentamento, String.valueOf(CalcularIndice.averageAssentamento(lotes)), String.valueOf(CalcularIndice.desvioPadraoAssentamento(lotes))};
        for (int i = 0; i < values.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(values[i]);
            cell.setCellStyle(styleBody);
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
        Row row = sheet.createRow(0);
        Cell cell;
        for (int i = 0; i < headerValues.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerValues[i]);
            cell.setCellStyle(styleHeader);
        }
        
        row = sheet.createRow(1);
        String[] data = new String[] {
            lote.getAssentamento(), lote.getResponsavel(),
            lote.getNumParcela(), lote.getContato(), Arrays.toString(lote.getCoordenada())
        };
        for (int i = 0; i < data.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(data[i]);
            cell.setCellStyle(styleBody);
            sheet.autoSizeColumn(i);
        }
        
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellValue("Índice do Lote:");
        cell.setCellStyle(styleHeader);
        cell = row.createCell(1);
        cell.setCellValue(lote.calcularIndiceGeral());
        cell.setCellStyle(styleBody);
    }
    private void categoriaToSheet(CategoriaIndicadores categoria) {
        // Dicionário com os dados
        Map<String, List<String>> dados = categoria.getItemMap();
        // Lista com scores
        double[] scores = lote.getScoresOf(categoria.getNome().hashCode());

        // Crie uma nova planilha
        Sheet sheet = workbook.createSheet(categoria.getNome());
        
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        Cell cell = row.createCell(0);
        cell.setCellValue(categoria.getNome());
        cell.setCellStyle(styleHeader);
        
        CellRangeAddress mergedCell = new CellRangeAddress(rowNum-1, rowNum-1, 0, 3);
        sheet.addMergedRegion(mergedCell);
        regionSetAllBorders(BorderStyle.MEDIUM, mergedCell, sheet);
        
        row = sheet.createRow(rowNum++);
        String[] headers = new String[]{"Indicadores", null, "Parâmetros", "Índice"};
        for (int i = 0; i < headers.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(styleHeader);
        }
        
        mergedCell = new CellRangeAddress(rowNum-1, rowNum-1, 0, 1);
        sheet.addMergedRegion(mergedCell);
        
        // Itere sobre as entradas do dicionário
        for (Map.Entry<String, List<String>> entry : dados.entrySet()) {
            row = sheet.createRow(rowNum);
            String chave = entry.getKey();
            List<String> valores = entry.getValue();

            // Defina o valor da célula na coluna 1 (mesclando n linhas)
            cell = row.createCell(0);
            cell.setCellValue(chave);
            cell.setCellStyle(styleBody);

            mergedCell = new CellRangeAddress(rowNum, rowNum+valores.size()-1, 0, 0);
//            regionSetAllBorders(BorderStyle.THIN, mergedCell, sheet);
            sheet.addMergedRegion(mergedCell);
            
            // Valor da célula na coluna 4 (mesclando n linhas)
            cell = row.createCell(3);
            cell.setCellStyle(styleBody);
            mergedCell = new CellRangeAddress(rowNum, rowNum+valores.size()-1, 3, 3);
            sheet.addMergedRegion(mergedCell);
            cell.setCellFormula(String.format("AVERAGE(C%d:C%d)", mergedCell.getFirstRow()+1, mergedCell.getLastRow()+1));
            
            // Borda dos grupos
            
            // Preencha os valores da coluna 2            
            int scorePos = 0;
            for (int i=0; i<valores.size(); i++) {
                
                cell = row.createCell(1);
                cell.setCellValue(valores.get(i));
                cell.setCellStyle(styleBody);
                
                cell = row.createCell(2);
                cell.setCellValue(scores[scorePos++]);
                cell.setCellStyle(styleBody);
                
                row = sheet.createRow(++rowNum);
            }
            regionSetAllBorders(BorderStyle.MEDIUM, new CellRangeAddress(rowNum-valores.size(), rowNum-1, 0, 3) , sheet);

        }
        // Estilizar tabela
        for (int i = 0; i < 3; i++) {
            sheet.autoSizeColumn(i);
            if (sheet.getColumnWidth(i)>256*60) { // 60 chars
                sheet.setColumnWidth(i, 256*60);
            }
        }
        regionSetAllBorders(BorderStyle.MEDIUM, new CellRangeAddress(0, scores.length+1, 0, 3) , sheet);
    }
    
    private void regionSetAllBorders(BorderStyle border, CellRangeAddress region, Sheet sheet) {
        RegionUtil.setBorderTop(border, region, sheet);
        RegionUtil.setBorderLeft(border, region, sheet);
        RegionUtil.setBorderBottom(border, region, sheet);
        RegionUtil.setBorderRight(border, region, sheet);
    }
    
    public void saveToFile() {
         // Salve o arquivo Excel
        try (FileOutputStream outputStream = new FileOutputStream(new File("spreadsheet", 
                lote.getResponsavel()+"-"+lote.getNumParcela()+".xlsx"))) {
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelWritter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
     
    public static void main(String[] args) throws IOException{
        Lote lote = DataTransfer.importLotes().get(0);
        var categoria = DataTransfer.importIndicadores().get(0);
        ExcelWritter excel = new ExcelWritter(lote);
        excel.createLoteWorkbook();
    }
}