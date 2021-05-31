/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterface.IDAOAdminLogin;
import Helper.KoneksiDB;
import Model.mAdminLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent Nathaniel
 */
public class DAOAdminLogin implements IDAOAdminLogin{

    Connection con;
    public DAOAdminLogin()
    {
        con = KoneksiDB.getConnection();
    }
    
    String queryLogin = "SELECT * FROM tbl_useradmin WHERE username = ? AND password = ?;";
    
    @Override
    public List<mAdminLogin> checkLogin(String username,String password) 
    {
        List<mAdminLogin> listAdminLogin = null;
        try 
        {
            listAdminLogin = new ArrayList<mAdminLogin>();
            PreparedStatement st = con.prepareStatement(queryLogin);
            st.setString(1, username);
            st.setString(2, password);

            ResultSet rs = st.executeQuery();
            System.out.println(st);
            rs.next();
            
            mAdminLogin adminLogin = new mAdminLogin();
            adminLogin.setUsername(rs.getString("username"));
            adminLogin.setPassword(rs.getString("password"));
            listAdminLogin.add(adminLogin);

            System.out.println(listAdminLogin.get(0).getUsername());
            System.out.println(listAdminLogin.get(0).getPassword());

                    
        } catch (Exception e) 
        {
            System.out.println("Gagal Get Username Password / Tidak ada di database");
        }
        return listAdminLogin;
    }
    
}
