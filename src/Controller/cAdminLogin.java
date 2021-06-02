package Controller;

import DAO.DAOAdminLogin;
import DAOInterface.IDAOAdminLogin;
import Model.mAdminLogin;
import View.vAdminLogin;
import View.vFormPenumpang;
import java.util.List;
import javax.swing.JOptionPane;


public class cAdminLogin {
    vAdminLogin frameAdminLogin;
    
    IDAOAdminLogin iAdminLogin;
    List<mAdminLogin> listAdminLogin;
    
    public cAdminLogin(vAdminLogin frameAdminLogin)
    {
        this.frameAdminLogin = frameAdminLogin;
        iAdminLogin = new DAOAdminLogin();
    }
    
    public void checkLogin()
    {
        String usernameInput = frameAdminLogin.getTfUsername().getText();
        String passwordInput = new String(frameAdminLogin.getTfPassword().getPassword());
        listAdminLogin = iAdminLogin.checkLogin(usernameInput, passwordInput); // username, pass, role
        
        if(listAdminLogin.size()>0)
        {
            String usernameDatabase = listAdminLogin.get(0).getUsername();
            String passwordDatabase = listAdminLogin.get(0).getPassword();
            int roleDatabase = listAdminLogin.get(0).getRole();
            if(usernameInput.equals(usernameDatabase) && passwordInput.equals(passwordDatabase))
            {
                frameAdminLogin.dispose();
                new vFormPenumpang(roleDatabase).setVisible(true);
                System.out.println("Berhasil Login");            
            }
        }else
        {
           
            JOptionPane.showMessageDialog(null, "Username atau Password Salah","Login Gagal",JOptionPane.ERROR_MESSAGE);
            frameAdminLogin.getTfUsername().requestFocus();
            
        }
     
    }
    
  
}
