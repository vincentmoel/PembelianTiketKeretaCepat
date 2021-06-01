package DAO;

import DAOInterface.IDAOUpdateSlot;
import Helper.KoneksiDB;
import java.sql.Connection;


public class DAOUpdateSlot implements IDAOUpdateSlot{
    
    Connection con;
    
    public DAOUpdateSlot()
    {
        con = KoneksiDB.getConnection();
    }

    @Override
    public boolean updateSlotDatabase(String jam) 
    {
        return false;
    }
    
}
