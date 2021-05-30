/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Model.mAdminLogin;
import java.util.List;

/**
 *
 * @author Vincent Nathaniel
 */
public interface IDAOAdminLogin {
    public List<mAdminLogin> checkLogin(String username,String password);
    
}
