package Controller;

import DAO.DAOKeretaCepat;
import DAOInterface.IDAOKeretaCepat;
import Model.mPenumpang;
import Model.mTabelModelPenumpang;
import View.vAdminLogin;
import View.vFormPenumpang;
import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class cKeretaCepat {
    vFormPenumpang framePenumpang;
    IDAOKeretaCepat iKeretaCepat;
    List<mPenumpang> listPenumpang;
    private int roleDatabase;
    
    public cKeretaCepat(vFormPenumpang framePenumpang, int roleDatabase)
    {
        this.framePenumpang = framePenumpang;
        this.roleDatabase = roleDatabase;
        iKeretaCepat = new DAOKeretaCepat();
    }
    
    public void startApp()
    {
        // Membuat full screen
        framePenumpang.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        
        // Set Text Center di Text Field Status
        framePenumpang.getTfStatus().setHorizontalAlignment(JTextField.CENTER);
        
        // Membuat lblStatusSlot jadi blank
        framePenumpang.getLblStatusSlot10().setText("");
        framePenumpang.getLblStatusSlot12().setText("");
        framePenumpang.getLblStatusSlot14().setText("");
        

        
        // Menjalankan Method utama
        readData();
        updateSlot();
        resizeTable();
        roleSetting(); 
        
        
      

        // Menghilangkan Text Field Status
//        framePenumpang.getTfStatus().setVisible(false);
    }
    
    public void resizeTable()
    {
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(0).setPreferredWidth(1);
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(1).setPreferredWidth(70);        
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(2).setPreferredWidth(150);        
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(3).setPreferredWidth(1);
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(4).setPreferredWidth(150);
        framePenumpang.getTblPenumpang().getColumnModel().getColumn(5).setPreferredWidth(35);
        framePenumpang.getTblPenumpang().setRowHeight(40);

    }
    
    public void roleSetting()
    {
        if(roleDatabase == 2) // USER BIASA
        {
            framePenumpang.getBtnUpdate().setEnabled(false);
            framePenumpang.getBtnDelete().setEnabled(false);
            framePenumpang.getBtnUpdateSlot().setEnabled(false);
        }
    }
    
    public void updateSlot()
    {
        // Update Slot Kursi
        int slot10 = iKeretaCepat.getSlotData("10");
        int slot12 = iKeretaCepat.getSlotData("12");
        int slot14 = iKeretaCepat.getSlotData("14");

//        int count10 = 40;
        int count10 = iKeretaCepat.getCountData("10");
        int count12 = iKeretaCepat.getCountData("12");
        int count14 = iKeretaCepat.getCountData("14");
        
        framePenumpang.getLblSisaJam10().setText(String.valueOf(slot10 - count10));
        framePenumpang.getLblSisaJam12().setText(String.valueOf(slot12 - count12));
        framePenumpang.getLblSisaJam14().setText(String.valueOf(slot14 - count14));
        
        if (slot10 - count10 == 0)
        {
            framePenumpang.getRbJam10().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam10().setEnabled(false);
            framePenumpang.getLblSisaJam10().setEnabled(false);
            framePenumpang.getLblStatusSlot10().setText("HABIS");
            framePenumpang.getLblStatusSlot10().setForeground(Color.RED);

        }
        if (slot12 - count12 == 0)
        {
            framePenumpang.getRbJam12().setEnabled(false);
            framePenumpang.getLbLHurufSisaJam12().setEnabled(false);
            framePenumpang.getLblSisaJam12().setEnabled(false);
            framePenumpang.getLblStatusSlot12().setText("HABIS");
            framePenumpang.getLblStatusSlot12().setForeground(Color.RED);
        }
        if (slot14 - count14 == 0)
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
//        framePenumpang.getTfStatus().setVisible(true);

        if (success)
        {    
            framePenumpang.getTfStatus().setText("Input Berhasil");

            framePenumpang.getTfStatus().setBackground(Color.green);
        }else{
            framePenumpang.getTfStatus().setText("Input Gagal");

            framePenumpang.getTfStatus().setBackground(Color.red);
            framePenumpang.getTfStatus().setForeground(Color.white);
            

        }
    }
    
    public void updateData()
    {
        mPenumpang penumpang = new mPenumpang();
        penumpang.setId(Integer.parseInt(framePenumpang.getTfId().getText()));
        penumpang.setNik(framePenumpang.getTfNik().getText());
        penumpang.setNama(framePenumpang.getTfNamaLengkap().getText());
        
        // set Radiobutton JK
        if(framePenumpang.getRbLakiLaki().isSelected())
        {
            penumpang.setJk("L");
        }else if(framePenumpang.getRbPerempuan().isSelected())
        {
            penumpang.setJk("P");
        }
        
        penumpang.setAlamat(framePenumpang.getTaAlamat().getText());
        
        // set jam berangkat
        if(framePenumpang.getRbJam10().isSelected())
        {         
            penumpang.setJamberangkat("10");
        }else if(framePenumpang.getRbJam12().isSelected())
        {
            penumpang.setJamberangkat("12");  
        }else
        {
            penumpang.setJamberangkat("14"); 
        }
        
        boolean success = iKeretaCepat.updateData(penumpang);
        
        if (success)
        {    
            framePenumpang.getTfStatus().setText("Update Berhasil");

            framePenumpang.getTfStatus().setBackground(Color.green);
        }else{
            framePenumpang.getTfStatus().setText("Update Gagal");

            framePenumpang.getTfStatus().setBackground(Color.red);
            framePenumpang.getTfStatus().setForeground(Color.white);
            

        }
        
    }
    
    public void deleteData()
    {
        
    }
    
    
    
    public void resetData()
    {
        framePenumpang.getTfId().setText("");
        framePenumpang.getTfNik().setText("");
        framePenumpang.getTfNamaLengkap().setText("");
        framePenumpang.getBtnGroupJenisKelamin().clearSelection();
        framePenumpang.getTaAlamat().setText("");
        framePenumpang.getBtnGroupJamBerangkat().clearSelection();
        framePenumpang.getCbSearchJam().setSelectedIndex(0);
        framePenumpang.getCbSearch().setSelectedIndex(0);
        framePenumpang.getTfSearch().setText("");
        framePenumpang.getBtnInsert().setEnabled(true);

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
        
        listPenumpang = iKeretaCepat.searchData(jam, atribut , isiAtributSearch);
        mTabelModelPenumpang tabelPenumpang = new mTabelModelPenumpang(listPenumpang);
        framePenumpang.getTblPenumpang().setModel(tabelPenumpang);
        
        
    }
    
    public void isiField(int row)
    {
        framePenumpang.getBtnInsert().setEnabled(false);
        framePenumpang.getTfId().setText(String.valueOf(listPenumpang.get(row).getId()));
        framePenumpang.getTfNik().setText(listPenumpang.get(row).getNik());
        framePenumpang.getTfNamaLengkap().setText(listPenumpang.get(row).getNama());
        
        // Selected radiobutton JK
        if (listPenumpang.get(row).getJk().equals("L"))
        {
            framePenumpang.getRbLakiLaki().setSelected(true);
        } else 
        {
            framePenumpang.getRbPerempuan().setSelected(true);
        }
        
        
        framePenumpang.getTaAlamat().setText(listPenumpang.get(row).getAlamat());
        
        // Selected radiobutton jam berangkat
        if (listPenumpang.get(row).getJamberangkat().equals("10"))
        {
            framePenumpang.getRbJam10().setSelected(true);
        } else if (listPenumpang.get(row).getJamberangkat().equals("12"))
        {
            framePenumpang.getRbJam12().setSelected(true);
        } else
        {
            framePenumpang.getRbJam14().setSelected(true);
        }
        
    }
    

}
