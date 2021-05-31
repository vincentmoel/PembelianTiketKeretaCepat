/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOAdminLogin;
import DAOInterface.IDAOAdminLogin;
import Model.mAdminLogin;
import View.vAdminLogin;
import View.vFormPenumpang;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Vincent Nathaniel
 */
public class cAdminLogin {
    vAdminLogin frameAdminLogin;
    IDAOAdminLogin iAdminLogin;
    List<mAdminLogin> listAdminLogin;
    
    public cAdminLogin(vAdminLogin fraAdminLogin)
    {
        this.frameAdminLogin = fraAdminLogin;
        iAdminLogin = new DAOAdminLogin();
    }
    
    public void checkLogin()
    {
//        mAdminLogin userAdmin = new mAdminLogin();
//        userAdmin
        
        String usernameInput = frameAdminLogin.getTfUsername().getText();
        String passwordInput = new String(frameAdminLogin.getTfPassword().getPassword());
        listAdminLogin = iAdminLogin.checkLogin(usernameInput, passwordInput);
        
        if(listAdminLogin.size()>0)
        {
            String usernameDatabase = listAdminLogin.get(0).getUsername();
            String passwordDatabase = listAdminLogin.get(0).getPassword();
            int roleDatabase = listAdminLogin.get(0).getRole();
            if(usernameInput.equals(usernameDatabase) && passwordInput.equals(passwordDatabase))
            {
                new vFormPenumpang().setVisible(true);
                if(roleDatabase == 1)
                {
//                    jalankan method admin
                }else if(roleDatabase == 2)
                {
//                    jalankan method user
                }
                        
                
//                return roleDatabase;
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Username atau Password Salah","Gagal Login",JOptionPane.ERROR_MESSAGE);
        }
//        return 0;

        
        
    }
}
