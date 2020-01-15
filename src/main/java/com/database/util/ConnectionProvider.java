package com.database.util;

import com.database.controller.DatabaseException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionProvider {
    private static DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getDSInstance().getConnection();
    }

    private static DataSource getDSInstance() {
        if (dataSource == null) {
            try {

                InitialContext context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:comp/env/jdbc/employees");

            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return dataSource;
    }

    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if(resultSet != null){
                resultSet.close();
            }
            if(statement != null){
                statement.close();
            }
            if(connection != null){
                connection.close();
            }

        }catch(SQLException ex){
            throw new DatabaseException(ex);
        }
    }
}