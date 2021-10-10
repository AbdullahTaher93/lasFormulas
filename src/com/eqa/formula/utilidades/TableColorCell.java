package com.eqa.formula.utilidades;


import com.eqa.formula.screen.Alta;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abdullah_PC
 */
public class TableColorCell implements TableCellRenderer{
    private static final TableCellRenderer cellRenderer=new DefaultTableCellRenderer();
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
       
        Component c=cellRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
            Object result=table.getModel().getValueAt(row, 3);
            Object result2=table.getModel().getValueAt(row, 4);
            Object result3=table.getModel().getValueAt(row, 9);
            boolean f=Boolean.parseBoolean(result.toString());
             boolean f2=Boolean.parseBoolean(result2.toString());
             boolean ouctar=Boolean.parseBoolean(result3.toString());
            Color color=null;
            Color color2=null;
             
            System.out.println(f);
            if(column==2||column==1&&!ouctar){
                if(column==1&&!ouctar)
                if(f){
                    color=Color.GREEN;
                     color2=Color.BLACK;
                }else{
                    color=Color.RED;
                     color2=Color.BLACK;
                }else
                    if(f2&&!ouctar){
                    color=Color.green;
                     color2=Color.BLACK;
                }else if(!f2&&!ouctar){
                    color=Color.red;
                     color2=Color.BLACK;
                }
                else {
                    color=Color.gray;
                    color2=Color.lightGray;
                }
            }
            else if (ouctar){
                color=Color.gray;
                color2=Color.lightGray;
            }
            else{
                 color=Color.WHITE;
                 color2=Color.BLACK;
            }
            
            
            c.setBackground(color);
            c.setForeground(color2);
        
        return c;
    }
    
}
