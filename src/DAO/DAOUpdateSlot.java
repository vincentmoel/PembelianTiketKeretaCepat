package DAO;

import DAOInterface.IDAOUpdateSlot;
import Helper.KoneksiDB;
import Model.mUpdateSlot;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DAOUpdateSlot implements IDAOUpdateSlot{
    
    Connection con;
    
    public DAOUpdateSlot()
    {
        con = KoneksiDB.getConnection();
    }
    
    //SQL
    String queryGetSlot = "SELECT * FROM tbl_jadwal WHERE jam_berangkat = ?;";
    String queryUpdate = "UPDATE tbl_jadwal SET slot_berangkat = ? WHERE jam_berangkat = ?;";

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
            System.out.println("UPDATE GAGAL");
            success = false;            
        }
        finally
        {
            try 
            {
                statement.close();
            } catch (SQLException ex) 
            {
                System.out.println("Gagal Update");
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
//            listSlotDB = new ArrayList<mUpdateSlot>();
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

            System.out.println("Error "+e);
        }
        return slotDB;
    }
    
}
