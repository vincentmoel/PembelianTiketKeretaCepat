package Controller;

import DAO.DAOUpdateSlot;
import DAOInterface.IDAOUpdateSlot;
import View.vUpdateSlot;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;


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
    
//    public int convertToNumeric()
    
    public void updateSlot()
    {
        String tfAfter10 = frameUpdateSlot.getTfSlotAfter10().getText();
        String tfAfter12 = frameUpdateSlot.getTfSlotAfter12().getText();
        String tfAfter14 = frameUpdateSlot.getTfSlotAfter14().getText();
        
        boolean success10 = false;
        boolean success12 = false;
        boolean success14 = false;
        boolean isEmpty = this.isEmpty(true);
        
        if(!isEmpty)
        {
            success10 = iUpdateSlot.updateSlotDatabase(Integer.parseInt(tfAfter10), "10");
            success12 = iUpdateSlot.updateSlotDatabase(Integer.parseInt(tfAfter12), "12");
            success14 = iUpdateSlot.updateSlotDatabase(Integer.parseInt(tfAfter14), "14");
            
            
            if (success10 && success12 && success14)
            {    
                
                JOptionPane.showMessageDialog(null, "Slot berhasil diupdate!");

            }else
            {
                JOptionPane.showMessageDialog(null, "Slot gagal diupdate!", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        }else
        {
            
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
    
    public void resetData()
    {
        boolean success = iUpdateSlot.refreshSlotDatabase();
        
        if(success)
        {
            this.startApp();
            JOptionPane.showMessageDialog(null, "Slot Kembali ke 40", "Reset Slot", JOptionPane.INFORMATION_MESSAGE);
            
        } else 
        {
            JOptionPane.showMessageDialog(null, "GAGAL REFRESH SLOT");
        }
    }
    
    public boolean isEmpty(boolean show)
    {
        int tfAfter10 = frameUpdateSlot.getTfSlotAfter10().getText().trim().length();
        int tfAfter12 = frameUpdateSlot.getTfSlotAfter12().getText().trim().length();
        int tfAfter14 = frameUpdateSlot.getTfSlotAfter14().getText().trim().length();
        
        StringBuilder errorText = new StringBuilder();
        errorText.append("Update slot ");
        
        if(tfAfter10 == 0)
        {
            errorText.append("jam 10, ");
            frameUpdateSlot.getTfSlotAfter10().setBorder(new LineBorder(Color.red, 2));
        }
        
        if(tfAfter12 == 0)
        {
            errorText.append("jam 12, ");
            frameUpdateSlot.getTfSlotAfter12().setBorder(new LineBorder(Color.red, 2));
        }
        
        if(tfAfter14 == 0)
        {
            errorText.append("jam 14, ");
            frameUpdateSlot.getTfSlotAfter14().setBorder(new LineBorder(Color.red, 2));
        }
        
        if(tfAfter10 == 0 ||tfAfter12 == 0 ||tfAfter14 == 0)
        {
            errorText.append("\nTidak boleh kosong!");
            if(show)
            {
                JOptionPane.showMessageDialog(null, errorText, "Field Kosong", JOptionPane.ERROR_MESSAGE);
            }
            
            return true;
        }
        return false; 
    }
    
    
    
    public void resetBorder()
    {
        frameUpdateSlot.getTfSlotAfter10().setBorder(new JTextField().getBorder());
        frameUpdateSlot.getTfSlotAfter12().setBorder(new JTextField().getBorder());
        frameUpdateSlot.getTfSlotAfter14().setBorder(new JTextField().getBorder());
    }

    
}
