package DAOInterface;

import Model.mUpdateSlot;
import java.util.List;


public interface IDAOUpdateSlot {
    public boolean updateSlotDatabase(int slot, String jam);
    
    public int getSlotDatabase(String jam);
}
