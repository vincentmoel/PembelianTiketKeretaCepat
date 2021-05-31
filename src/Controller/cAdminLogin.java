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
            if(usernameInput.equals(usernameDatabase) && passwordInput.equals(passwordDatabase))
            {
                System.out.println("Berhasil Login");
            }else
            {
                System.out.println("Username Pass Salah");
            }
        }else
        {
            JOptionPane.showMessageDialog(null, "Username dan Password Salah");
        }

        
        
    }
}
