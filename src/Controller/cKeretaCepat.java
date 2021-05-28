/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOKeretaCepat;
import DAOInterface.IDAOKeretaCepat;
import Model.mPenumpang;
import Model.mTabelModelPenumpang;
import View.vFormPenumpang;
import java.awt.Color;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author Vincent Nathaniel
 */
public class cKeretaCepat {
    vFormPenumpang framePenumpang;
    IDAOKeretaCepat iKeretaCepat;
    List<mPenumpang> listPenumpang;
    
    public cKeretaCepat(vFormPenumpang framePenumpang)
    {
        this.framePenumpang = framePenumpang;
        iKeretaCepat = new DAOKeretaCepat();
    }
    
    public void startApp()
    {
        // Set Text Center di Text Field Status
        framePenumpang.getTfStatus().setHorizontalAlignment(JTextField.CENTER);
        
        // Membuat lblStatusSlot jadi blank
        framePenumpang.getLblStatusSlot10().setText("");
        framePenumpang.getLblStatusSlot12().setText("");
        framePenumpang.getLblStatusSlot14().setText("");
        
        // Menjalankan Method utama
        readData();
        updateSlot();
    }
    
    public void updateSlot()
    {
        // Update Slot Kursi
        int slot = iKeretaCepat.getSlotData("10");
//        int count10 = 40;
        int count10 = iKeretaCepat.getCountData("10");
        int count12 = iKeretaCepat.getCountData("12");
        int count14 = iKeretaCepat.getCountData("14");
        
        framePenumpang.getLblSisaJam10().setText(String.valueOf(slot - count10));
        framePenumpang.getLblSisaJam12().setText(String.valueOf(slot - count12));
        framePenumpang.getLblSisaJam14().setText(String.valueOf(slot - count14));
        
        if (slot - count10 == 0)
        {
            framePenumpang.getRbJam10().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam10().setEnabled(false);
            framePenumpang.getLblSisaJam10().setEnabled(false);
            framePenumpang.getLblStatusSlot10().setText("HABIS");
            framePenumpang.getLblStatusSlot10().setForeground(Color.RED);

        }
        if (slot - count12 == 0)
        {
            framePenumpang.getRbJam12().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam12().setEnabled(false);
            framePenumpang.getLblSisaJam12().setEnabled(false);
            framePenumpang.getLblStatusSlot12().setText("HABIS");
            framePenumpang.getLblStatusSlot12().setForeground(Color.RED);
        }
        if (slot - count14 == 0)
        {
            framePenumpang.getRbJam14().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam14().setEnabled(false);
            framePenumpang.getLblSisaJam14().setEnabled(false);
            framePenumpang.getLblStatusSlot14().setText("HABIS");
            framePenumpang.getLblStatusSlot14().setForeground(Color.RED);
        }
    }

    
    public void readData()//Menampilkan Data di Table
    {
        listPenumpang = iKeretaCepat.readData();
        mTabelModelPenumpang tabelPenumpang = new mTabelModelPenumpang(listPenumpang);
        framePenumpang.getTblPenumpang().setModel(tabelPenumpang);
 
    }
    
    public void insertData()
    {
        mPenumpang penumpang = new mPenumpang();
        penumpang.setNik(framePenumpang.getTfNik().getText());
        penumpang.setNama(framePenumpang.getTfNamaLengkap().getText());
        
        
        if(framePenumpang.getRbLakiLaki().isSelected())
        {
            penumpang.setJk("L");
        }else if(framePenumpang.getRbPerempuan().isSelected())
        {
            penumpang.setJk("P");
        }
        
        penumpang.setAlamat(framePenumpang.getTaAlamat().getText());
        
        if(framePenumpang.getRbJam10().isSelected())
        {         
            penumpang.setJamberangkat("10");
        }else if(framePenumpang.getRbJam12().isSelected())
        {
            penumpang.setJamberangkat("12");  
        }else if(framePenumpang.getRbJam14().isSelected())
        {
            penumpang.setJamberangkat("14"); 
        }
        
        boolean success = iKeretaCepat.insertData(penumpang);

        if (success)
        {    
            framePenumpang.getTfStatus().setText("Input Berhasil");
        }else{
            framePenumpang.getTfStatus().setText("Input Gagal");
        }
    }
    
    public void updateData()
    {
        
    }
    
    public void deleteData()
    {
        
    }
    
    
    
    public void resetData()
    {
        framePenumpang.getTfNik().setText("");
        framePenumpang.getTfNamaLengkap().setText("");
        framePenumpang.getBtnGroupJenisKelamin().clearSelection();
        framePenumpang.getTaAlamat().setText("");
        framePenumpang.getBtnGroupJamBerangkat().clearSelection();
    }
    
    public void searchData()
    {
        String jam = null;
        String atribut = null;
        
        int jamSearch = framePenumpang.getCbSearchJam().getSelectedIndex();
        int atributSearch = framePenumpang.getCbSearch().getSelectedIndex();
        String isiAtributSearch =  framePenumpang.getTfSearch().getText();
        switch(jamSearch)
        {
            case 0 :
                jam = "_";
                break;
            case 1 :
                jam = "10";
                break;
            case 2 :
                jam = "12";
                break;
            case 3 :
                jam = "14";
                break;
        }
        
        switch(atributSearch)
        {
            case 0 :
                atribut = "id_penumpang";
                isiAtributSearch = "_";
                break;
            case 1 :
                atribut = "id_penumpang";
                break;
            case 2 :
                atribut = "nik";
                break;
            case 3 :
                atribut = "nama";
                break;
        }
        
//        iKeretaCepat.searchData(jam, atribut , isiAtributSearch);
        listPenumpang = iKeretaCepat.searchData(jam, atribut , isiAtributSearch);
        mTabelModelPenumpang tabelPenumpang = new mTabelModelPenumpang(listPenumpang);
        framePenumpang.getTblPenumpang().setModel(tabelPenumpang);
        
        
    }
    

}
