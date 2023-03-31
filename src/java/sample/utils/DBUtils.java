/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Duy
 */
public class DBUtils {

    public static Connection makeConnection() throws Exception {
        Connection connection = null;
        
        String IP = "localhost";
        String instanceName = "LAPTOP-RJI4HEUN\\DUYDUY";
        String port = "1433";
        String userName = "sa";
        String passWord = "12345";
        String dataBase = "PlantShop";

        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + dataBase + ";user=" + userName + ";password=" + passWord;

        Class.forName(driver);
        connection = DriverManager.getConnection(url);
        return connection;
    }
}
