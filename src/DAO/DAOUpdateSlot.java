package DAO;

import DAOInterface.IDAOUpdateSlot;
import Helper.KoneksiDB;
import Model.mUpdateSlot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DAOUpdateSlot implements IDAOUpdateSlot{
    
    Connection con;
    
    public DAOUpdateSlot()
    {
        con = KoneksiDB.getConnection();
    }
    
    // SQL Query
    String queryGetSlot = "SELECT * FROM tbl_jadwal WHERE jam_berangkat = ?;";
    String queryUpdate = "UPDATE tbl_jadwal SET slot_berangkat = ? WHERE jam_berangkat = ?;";
    String queryCoba = "SELECT * FROM tbl_jadwal;";

    @Override
    public boolean updateSlotDatabase(int slot, String jam) 
    {
        boolean success = true;
        PreparedStatement statement = null;
        
        try 
        {
            statement = con.prepareStatement(queryUpdate);
            statement.setInt(1, slot);
            statement.setString(2, jam);
            
            statement.execute();
        } catch(SQLException e)
        {
            success = false;            
        }
        finally
        {
            try 
            {
                statement.close();
            } catch (SQLException ex) 
            {

                success = false;            
            }
        }
        return success;
    }

    @Override
    public int getSlotDatabase(String jam) 
    {
        int slotDB = 0;
        try
        {
            PreparedStatement st = con.prepareStatement(queryGetSlot);
            st.setString(1, jam);
            ResultSet rs = st.executeQuery();
            rs.next();
            
            mUpdateSlot slotDatabase = new mUpdateSlot();
            slotDatabase.setId_berangkat(rs.getInt("id_berangkat"));
            slotDatabase.setJam_berangkat(rs.getString("jam_berangkat"));
            slotDatabase.setSlot_berangkat(rs.getInt("slot_berangkat"));
     
            slotDB = slotDatabase.getSlot_berangkat();
        }
        catch(SQLException e)
        {

        }
        return slotDB;
    }
    
}
