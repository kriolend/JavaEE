package practic1;

import org.demo.practic1.dao.AudioDao;
import org.demo.practic1.models.Audio;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by u0100 on 03.06.2016.
 */
public class TestDao {

    public final java.lang.String DB_URL = "jdbc:mysql://localhost/pr1_db";
    public final String USER = "root";
    public final String PASS = "toor";
    Connection conn = null;


    @Test
    public void testConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(conn);
        conn.close();
    }



}
