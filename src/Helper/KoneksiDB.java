package Helper;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class KoneksiDB {
    static Connection con;
    
    public static Connection getConnection()
    {
        if (con == null)
        {
            MysqlDataSource data = new MysqlDataSource();
            data.setDatabaseName("db_keretacepat");
            data.setUser("root");
            data.setPassword("");
            try
            {
                con = data.getConnection();
                System.out.println("Koneksi Berhasil");
            }catch(SQLException e)
            {
                System.out.println("Tidak Konek");
            }
        }
        return con;
    }
}


