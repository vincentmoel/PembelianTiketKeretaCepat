/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterface.IDAOKeretaCepat;
import Helper.KoneksiDB;
import Model.mPenumpang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vincent Nathaniel
 */
public class DAOKeretaCepat implements IDAOKeretaCepat
{
    Connection con;
    public DAOKeretaCepat()
    {
        con = KoneksiDB.getConnection();
    }
    
    
    String queryRead = "SELECT * FROM tbl_penumpang;";
    String queryInsert = "INSERT INTO tbl_penumpang(nik,nama,jk,alamat,jamberangkat) values(?,?,?,?,?);";
    
    @Override
    public List<mPenumpang> readData() 
    {
        List<mPenumpang> listPenumpang = null;
        try
        {
            listPenumpang = new ArrayList<mPenumpang>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(queryRead);
            while(rs.next())
            {
                mPenumpang penumpang = new mPenumpang();
                penumpang.setId(rs.getInt("id_penumpang")); // id_penumpang dari atribut di database
                penumpang.setNik(rs.getString("nik"));
                penumpang.setNama(rs.getString("nama"));
                penumpang.setJk(rs.getString("jk"));
                penumpang.setAlamat(rs.getString("alamat"));
                penumpang.setJamberangkat(rs.getString("jamberangkat"));
                listPenumpang.add(penumpang);
            }
        }
        catch(SQLException e)
        {
            System.out.println("Error "+e);
        }
        return listPenumpang;
    }

    @Override
    public void insertData(mPenumpang penumpang) 
    {
        PreparedStatement statement = null;
        try
        {
            statement = con.prepareStatement(queryInsert);
            statement.setString(1,penumpang.getNik());
            statement.setString(2,penumpang.getNama());
            statement.setString(3,penumpang.getJk());
            statement.setString(4,penumpang.getAlamat());
            statement.setString(5,penumpang.getJamberangkat());
            
            statement.execute();
        }catch(SQLException e)
        {

        }
        finally
        {
            try 
            {
                statement.close();
            } catch (SQLException ex) 
            {
                System.out.println("Gagal Input");
            }
        }
    }

    @Override
    public void updateData(mPenumpang penumpang) 
    {
        
    }

    @Override
    public void deleteData(mPenumpang penumpang) 
    {
        
    }

}
