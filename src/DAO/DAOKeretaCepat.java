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


public class DAOKeretaCepat implements IDAOKeretaCepat
{
    String a = null;
    Connection con;
    public DAOKeretaCepat()
    {
        con = KoneksiDB.getConnection();
    }
    
//    SQL
    String queryRead = "SELECT * FROM tbl_penumpang;";
    String queryInsert = "INSERT INTO tbl_penumpang(nik,nama,jk,alamat,jamberangkat) values(?,?,?,?,?);";
    String queryUpdate = "UPDATE tbl_penumpang set nik=?, nama=?, jk=?, alamat=?, jamberangkat=? WHERE id_penumpang=?;";
    String queryDelete = "DELETE FROM tbl_penumpang WHERE id_penumpang =?;";
    String queryCount = "SELECT COUNT(jamberangkat) AS jml FROM tbl_penumpang WHERE jamberangkat = ?;";
    String querySlot = "SELECT slot_berangkat FROM tbl_jadwal WHERE jam_berangkat = ?;";
    String querySearch;


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
    public boolean insertData(mPenumpang penumpang) 
    {
        boolean success = true;
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
            success = false;            
        }
        finally
        {
            try 
            {
                statement.close();
            } catch (SQLException ex) 
            {
                System.out.println("Gagal Input");
                success = false;            

            }
        }
        return success;
    }

    @Override
    public boolean updateData(mPenumpang penumpang) 
    {
        boolean success = true;
        PreparedStatement statement = null;
        
        try 
        {
            statement = con.prepareStatement(queryUpdate);
            statement.setString(1, penumpang.getNik());
            statement.setString(2, penumpang.getNama());
            statement.setString(3, penumpang.getJk());
            statement.setString(4, penumpang.getAlamat());
            statement.setString(5, penumpang.getJamberangkat());
            statement.setInt(6, penumpang.getId());
            
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
    public boolean deleteData(int id_penumpang) 
    {
        boolean success = true;
        PreparedStatement statement = null;
        
        try 
        {
            statement = con.prepareStatement(queryDelete);
            statement.setInt(1, id_penumpang);
            statement.execute();
            
        } catch (Exception e) 
        {
            System.out.println("DELETE GAGAL");
            success = false;
        }
        finally
        {
            try 
            {
                statement.close();
            } catch (SQLException ex) 
            {
                System.out.println("Gagal hapus");
                success = false;            

            }
        }
        return success;
    }

    @Override
    public int getCountData(String jam) 
    {
        
        int jml = 0;
        
        try 
        {
            PreparedStatement st = con.prepareStatement(queryCount);
            st.setString(1, jam);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                jml = rs.getInt("jml");
            }
                    
        } catch (Exception e) 
        {
            System.out.println("Gagal Count");
        }
        
        return jml;
    }

    @Override
    public int getSlotData(String jam) 
    {
        int slot = 0;
        
        try 
        {
            PreparedStatement st = con.prepareStatement(querySlot);
            st.setString(1, jam);
            ResultSet rs = st.executeQuery();
            
            while(rs.next())
            {
                slot = rs.getInt("slot_berangkat");
            }
            
        } catch (Exception e) {
            System.out.println("Gagal get Slot");
        }
        
        return slot;
    }

    @Override
    public List<mPenumpang> searchData(String jam, String atribut, String isiAtribut) 
    {
        querySearch = "SELECT * FROM tbl_penumpang WHERE jamberangkat LIKE ? AND "+atribut+" LIKE ?;";

        List<mPenumpang> listPenumpang = null;
        try
        {
            listPenumpang = new ArrayList<mPenumpang>();
            PreparedStatement st = con.prepareStatement(querySearch);
            st.setString(1,"%"+jam+"%");
            st.setString(2,"%"+isiAtribut+"%");
            ResultSet rs = st.executeQuery();
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
            System.out.println(querySearch);

            System.out.println("Error "+e);
        }
        return listPenumpang;
    }
}
