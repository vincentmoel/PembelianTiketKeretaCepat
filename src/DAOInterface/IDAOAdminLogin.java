package DAOInterface;

import Model.mAdminLogin;
import java.util.List;


public interface IDAOAdminLogin {
    public List<mAdminLogin> checkLogin(String username,String password);
    
}
