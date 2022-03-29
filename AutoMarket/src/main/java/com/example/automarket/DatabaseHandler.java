package com.example.automarket;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler extends Configs {
    Connection con;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
            con = DriverManager.getConnection(url, dbUser, dbPass);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

     }


    public void signUpAuto(String stamp, String model, String category, String number, String type, String production) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.AUTO_TABLE + "(" + Const.AUTO_STAMP + "," +
                Const.AUTO_MODEL + "," + Const.AUTO_CATEGORY + "," +
                Const.AUTO_NUMBER + "," + Const.AUTO_TYPE + "," +
                Const.AUTO_PRODUCTION + ")" +
                "VALUE(?,?,?,?,?,?) ";


       try {
           PreparedStatement prSt = getDbConnection().prepareStatement(insert);
           prSt.setString(1, stamp);
           prSt.setString(2, model);
           prSt.setString(3, category);
           prSt.setString(4, number);
           prSt.setString(5, type);
           prSt.setString(6, production);

           prSt.executeUpdate();
       } catch (SQLException | ClassNotFoundException e) {
           e.printStackTrace();
       }

       if (getDbConnection() == null)
           System.err.println("NO");
       else
           System.out.println("Yes");


    }

}



