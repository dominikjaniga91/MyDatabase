package com.database.dao;

import com.database.controller.DatabaseException;
import com.database.model.History;
import com.database.util.ConnectionProvider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDatabase implements HistoryDao{

    @Override
    public void addToHistory(String user, String employee, String action) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlAddHistory = "INSERT INTO history" +
                    "(user, employee, action, date, time)" +
                    "VALUES (?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sqlAddHistory);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, employee);
            preparedStatement.setString(3, action);
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            preparedStatement.executeUpdate();

        } catch (SQLException ex){

            throw new DatabaseException(ex);
        }finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public List<History> getFromHistory(){

        List<History> historyList = new ArrayList<>();
        History history;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlGetHistory = " SELECT * FROM history ";
            preparedStatement = connection.prepareStatement(sqlGetHistory);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){

                String user = resultSet.getString("user");
                String employee = resultSet.getString("employee");
                String action = resultSet.getString("action");
                Date date = resultSet.getDate("date");
                Time time = resultSet.getTime("time");

                history = new History(user, employee, action, date, time);

                historyList.add(history);

            }

        } catch (SQLException ex){

            throw new DatabaseException(ex);
        }finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, resultSet);
        }

        return  historyList;
    }
}
