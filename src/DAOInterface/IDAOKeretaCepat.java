package DAOInterface;

import Model.mPenumpang;
import java.util.List;


public interface IDAOKeretaCepat {
    // Read Data
    public List<mPenumpang> readData();
    
    // Insert Data
    public boolean insertData(mPenumpang penumpang);
    
    // Update Data
    public boolean updateData(mPenumpang penumpang);
    
    // Delete Data
    public void deleteData(mPenumpang penumpang);
    
    // Count Data
    public int getCountData(String jam);
    
    // Get Slot Data
    public int getSlotData(String jam);

    // Cari Data
    public List<mPenumpang> searchData(String jam, String attribut, String isiAtribut);


}
