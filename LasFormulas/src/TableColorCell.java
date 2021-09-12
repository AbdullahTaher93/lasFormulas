
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
            boolean f=Boolean.parseBoolean(result.toString());
             boolean f2=Boolean.parseBoolean(result2.toString());
            Color color=null;
             
            System.out.println(f);
            if(f&&f2){
                color=Color.GREEN;
            }else{
                color=Color.red;
            }
              
            
            
            c.setBackground(color);
        
        return c;
    }
    
}
