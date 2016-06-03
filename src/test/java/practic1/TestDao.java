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

    private static final java.lang.String DB_URL = "jdbc:mysql://localhost/pr1_db";
    private static final String USER = "root";
    private static final String PASS = "toor";
    private static Connection conn = null;
    static AudioDao audioDao;


    //Тест соединения с БД
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
        //conn.close(); //т.к. буду использовать дальше
    }

    @Test
    public void TestSave() throws SQLException {
        testConnection();
        Audio audio = new Audio();
        audio.setTitle("musik12");
        audio.setYear(1900);
        audio.setDuration(60);
        audioDao.save(audio);
        Assert.assertNotNull(audio);
    }


}
