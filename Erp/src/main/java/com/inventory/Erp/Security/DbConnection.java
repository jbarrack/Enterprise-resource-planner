package com.inventory.Erp.Security;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnection {
    private final String db_url="jdbc:mysql://localhost:3306/inventory";
    private final String db_username="root";
    private final String db_password="root17";

    public boolean dbConnection(String username,String password) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(db_url, db_username, db_password);
            String query = "Select * from users where password=? AND password=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.print("login Successfull");
                return true;
            } else System.out.print("login Failed");
            return false;

        } catch (Exception e) {
            e.printStackTrace();
            e.fillInStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();

            System.out.print("Connection Closed");
        }

        return true;
    }

}
