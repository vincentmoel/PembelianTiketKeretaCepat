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
}
