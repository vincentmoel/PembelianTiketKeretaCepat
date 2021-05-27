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
    
    public void tampilData()//Menampilkan Data di Table
    {
        listPenumpang = iKeretaCepat.readData();
        mTabelModelPenumpang tabelPenumpang = new mTabelModelPenumpang(listPenumpang);
        framePenumpang.getTblPenumpang().setModel(tabelPenumpang);
    }
}
