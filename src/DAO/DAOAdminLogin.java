package DAO;

import DAOInterface.IDAOAdminLogin;
import Helper.KoneksiDB;
import Model.mAdminLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


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
            rs.next();
            
            mAdminLogin adminLogin = new mAdminLogin();
            adminLogin.setUsername(rs.getString("username"));
            adminLogin.setPassword(rs.getString("password"));
            adminLogin.setRole(rs.getInt(("role")));
            listAdminLogin.add(adminLogin);
      
        } catch (Exception e) 
        {
            System.out.println("Gagal Get Username Password / Tidak ada di database");
        }
        return listAdminLogin;
    }
    
}
