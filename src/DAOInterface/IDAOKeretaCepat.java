/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterface;

import Model.mPenumpang;
import java.util.List;

/**
 *
 * @author Vincent Nathaniel
 */
public interface IDAOKeretaCepat {
    // Read Data
    public List<mPenumpang> readData();
    
    // Insert Data
    public boolean insertData(mPenumpang penumpang);
    
    // Update Data
    public void updateData(mPenumpang penumpang);
    
    // Delete Data
    public void deleteData(mPenumpang penumpang);
    
    // Count Data
    public int getCountData(String jam);
    
    // Get Slot Data
    public int getSlotData(String jam);



}
