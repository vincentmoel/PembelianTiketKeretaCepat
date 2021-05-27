/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Vincent Nathaniel
 */
public class mTabelModelPenumpang extends AbstractTableModel {
    List<mPenumpang> listPenumpang;
    
    public mTabelModelPenumpang(List<mPenumpang> listPenumpang)
    {
        this.listPenumpang = listPenumpang;
    }

    @Override
    public int getRowCount() 
    {
        return this.listPenumpang.size();
    }

    @Override
    public int getColumnCount() 
    {
        return 6;
    }

    @Override
    public String getColumnName(int column){
        switch(column)
        {
            case 0 :
                return "ID";
            case 1 :
                return "NIK";
            case 2 :
                return "Nama";
            case 3 :
                return "Jenis Kelamin";
            case 4 :
                return "Alamat";
            case 5 :
                return "Jam Berangkat";
            default:
                return null;
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        switch(columnIndex)
        {
            case 0 :
                return listPenumpang.get(rowIndex).getId();
            case 1 :
                return listPenumpang.get(rowIndex).getNik();
            case 2 :
                return listPenumpang.get(rowIndex).getNama();
            case 3 :
                return listPenumpang.get(rowIndex).getJk();
            case 4 :
                return listPenumpang.get(rowIndex).getAlamat();
            case 5 :
                return listPenumpang.get(rowIndex).getJamberangkat();
            default:
                return null;
        }
    }
    
    
}
