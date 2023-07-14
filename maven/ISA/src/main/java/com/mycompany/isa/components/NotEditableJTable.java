/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.isa.components;

import javax.swing.JTable;

/**
 *
 * @author naoki
 */
public class NotEditableJTable extends JTable {
    @Override
    public boolean isCellEditable(int row, int column) {                
                return false;               
    };
}
