package DAOInterface;

import Model.mUpdateSlot;
import java.util.List;


public interface IDAOUpdateSlot {
    
    // Melakukan Update
    public boolean updateSlotDatabase(int slot, String jam);
    
    // Mendapatkan Slot Terkini
    public int getSlotDatabase(String jam);
}
