package com.database.dao;

import com.database.controller.DatabaseException;
import com.database.util.ConnectionProvider;
import com.database.model.User;
import java.sql.*;

public class UserDaoDatabase implements UserDao {

    @Override
    public void createUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = ConnectionProvider.getConnection();

            String sqlCreateUser = "INSERT INTO users" +
                    "(first_name, last_name, email, password)" +
                    "VALUES (?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sqlCreateUser);

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();

        }catch (SQLException ex){

            throw new DatabaseException(ex);

        } finally {

            ConnectionProvider.closeConnection(connection, preparedStatement,null);
        }
    }

    @Override
    public User getUser(String userEmail) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlCreateUser = "SELECT * FROM users" +
                    " WHERE email = ? ";

            preparedStatement = connection.prepareStatement(sqlCreateUser);
            preparedStatement.setString(1, userEmail);
            resultSet = preparedStatement.executeQuery();

            if(resultSet != null) {
                while (resultSet.next()) {

                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");

                    user = new User(id, firstName, lastName, email, password);
                }
            }
        } catch (SQLException ex) {
            throw new DatabaseException(ex);
        } finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, resultSet);
        }
        return user;
    }


    public void updateUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlUpdateEmployee = "UPDATE users " +
                    " SET first_name = ?," +
                    " last_name = ?," +
                    " email = ?," +
                    " password = ?" +
                    " WHERE id = ? ";

            preparedStatement = connection.prepareStatement(sqlUpdateEmployee);

            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setInt(5,user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex){
            throw new DatabaseException(ex);
        }
        finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }
    }
}
