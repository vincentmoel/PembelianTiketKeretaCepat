package Controller;

import DAO.DAOUpdateSlot;
import DAOInterface.IDAOUpdateSlot;
import View.vUpdateSlot;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class cUpdateSlot {
    vUpdateSlot frameUpdateSlot;
    IDAOUpdateSlot iUpdateSlot;
    
    public cUpdateSlot(vUpdateSlot frameUpdateSlot)
    {
        this.frameUpdateSlot = frameUpdateSlot;
        iUpdateSlot = new DAOUpdateSlot();
    }
    
    public int getSlot(String jam)
    {
        return iUpdateSlot.getSlotDatabase(jam);
    }
    
    public void updateSlot()
    {
        int tfAfter10 = Integer.parseInt(frameUpdateSlot.getTfSlotAfter10().getText());
        int tfAfter12 = Integer.parseInt(frameUpdateSlot.getTfSlotAfter12().getText());
        int tfAfter14 = Integer.parseInt(frameUpdateSlot.getTfSlotAfter14().getText());
        
        boolean success10 = false;
        boolean success12 = false;
        boolean success14 = false;
        boolean isEmpty = false;
        
        if(!isEmpty)
        {
            success10 = iUpdateSlot.updateSlotDatabase(tfAfter10, "10");
            success12 = iUpdateSlot.updateSlotDatabase(tfAfter12, "12");
            success14 = iUpdateSlot.updateSlotDatabase(tfAfter14, "14");
            if (success10 && success12 && success14)
            {    
//                framePenumpang.getTfStatus().setText("Update Berhasil");
//                framePenumpang.getTfStatus().setBackground(Color.green);
//                framePenumpang.getTfStatus().setForeground(Color.black);
                
                JOptionPane.showMessageDialog(null, "BERHASIL UPDATE SLOT");

            }else{
                JOptionPane.showMessageDialog(null, "GAGAL UPDATE SLOT");
            }
        }else{
//            framePenumpang.getTfStatus().setText("Update Gagal");
//            framePenumpang.getTfStatus().setBackground(Color.red);
//            framePenumpang.getTfStatus().setForeground(Color.white);

            JOptionPane.showMessageDialog(null, "EMPTY");
            
        }
    }
    
    public void startApp()
    {
        frameUpdateSlot.getTfSlotBefore10().setText(String.valueOf(this.getSlot("10")));
        frameUpdateSlot.getTfSlotBefore12().setText(String.valueOf(this.getSlot("12")));
        frameUpdateSlot.getTfSlotBefore14().setText(String.valueOf(this.getSlot("14")));
        
        frameUpdateSlot.getTfSlotAfter10().setText(String.valueOf(this.getSlot("10")));
        frameUpdateSlot.getTfSlotAfter12().setText(String.valueOf(this.getSlot("12")));
        frameUpdateSlot.getTfSlotAfter14().setText(String.valueOf(this.getSlot("14")));
        
        
        frameUpdateSlot.getTfSlotBefore10().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotBefore12().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotBefore14().setHorizontalAlignment(JTextField.CENTER);
        
        frameUpdateSlot.getTfSlotAfter10().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotAfter12().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotAfter14().setHorizontalAlignment(JTextField.CENTER);
        
        frameUpdateSlot.getTfSlotBefore10().setEditable(false);
        frameUpdateSlot.getTfSlotBefore12().setEditable(false);
        frameUpdateSlot.getTfSlotBefore14().setEditable(false);
    }
    
    public void refresh()
    {
        frameUpdateSlot.getTfSlotBefore10().setText(String.valueOf(this.getSlot("10")));
        frameUpdateSlot.getTfSlotBefore12().setText(String.valueOf(this.getSlot("12")));
        frameUpdateSlot.getTfSlotBefore14().setText(String.valueOf(this.getSlot("14")));
        
        frameUpdateSlot.getTfSlotBefore10().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotBefore12().setHorizontalAlignment(JTextField.CENTER);
        frameUpdateSlot.getTfSlotBefore14().setHorizontalAlignment(JTextField.CENTER);
    }

    
}
