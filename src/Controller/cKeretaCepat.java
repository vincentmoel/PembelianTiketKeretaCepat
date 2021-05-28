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
import java.util.List;

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
    
    public void readData()//Menampilkan Data di Table
    {
        listPenumpang = iKeretaCepat.readData();
        mTabelModelPenumpang tabelPenumpang = new mTabelModelPenumpang(listPenumpang);
        framePenumpang.getTblPenumpang().setModel(tabelPenumpang);
        
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
        }
        if (slot - count12 == 0)
        {
            framePenumpang.getRbJam12().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam12().setEnabled(false);
            framePenumpang.getLblSisaJam12().setEnabled(false);
        }
        if (slot - count14 == 0)
        {
            framePenumpang.getRbJam14().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam14().setEnabled(false);
            framePenumpang.getLblSisaJam14().setEnabled(false);
        }
 
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
            framePenumpang.getTfStatus().setText("Slot Kursi Habis");
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
    

}
