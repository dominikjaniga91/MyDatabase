package com.database.dao;

import com.database.controller.DatabaseException;
import com.database.model.Address;
import com.database.model.History;
import com.database.util.ConnectionProvider;
import com.database.model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoDatabase implements EmployeeDao{

    @Override
    public List<Employee> getEmployeeList() {

        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionProvider.getConnection();
            statement = connection.createStatement();

            String sqlGetEmployees = "SELECT * FROM myemployee";

            resultSet = statement.executeQuery(sqlGetEmployees);

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String zipCcode = resultSet.getString("zip_code");
                String houserNumber = resultSet.getString("house_number");

                Employee tmpEmployee = new Employee(id, firstName, lastName, email, phoneNumber, new Address(street, houserNumber, zipCcode, city));

                employees.add(tmpEmployee);
            }

        }catch(SQLException ex){

            throw new DatabaseException(ex);

        } finally {
            ConnectionProvider.closeConnection(connection,statement,resultSet);
        }
        return employees;
    }

    @Override
    public void addEmployee(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();


            String sqlGetEmployees = "INSERT INTO myemployee" +
                    "(first_name, last_name, email, phone_number, street, city, zip_code, house_number)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sqlGetEmployees);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getEmail());
            preparedStatement.setString(4, employee.getPhoneNumber());
            preparedStatement.setString(5, employee.getAddress().getStreet());
            preparedStatement.setString(6, employee.getAddress().getCity());
            preparedStatement.setString(7, employee.getAddress().getZipCode());
            preparedStatement.setString(8, employee.getAddress().getHouseNumber());
            preparedStatement.executeUpdate();

        } catch (SQLException ex){

            throw new DatabaseException(ex);
        }finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }

    }

    @Override
    public void updateEmployee(Employee employee) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlUpdateEmployee = "UPDATE myemployee " +
                    " SET first_name = ?," +
                    " last_name = ?," +
                    " email = ?," +
                    " phone_number = ?," +
                    " street = ?," +
                    " city = ?," +
                    " zip_code = ?," +
                    " house_number = ?" +
                    " WHERE id = ?";

            preparedStatement = connection.prepareStatement(sqlUpdateEmployee);

            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setString(3,employee.getEmail());
            preparedStatement.setString(4,employee.getPhoneNumber());
            preparedStatement.setString(5,employee.getAddress().getStreet());
            preparedStatement.setString(6,employee.getAddress().getCity());
            preparedStatement.setString(7,employee.getAddress().getZipCode());
            preparedStatement.setString(8,employee.getAddress().getHouseNumber());
            preparedStatement.setInt(9,employee.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex){

            throw new DatabaseException(ex);
        }
        finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlUpdateEmployee = "DELETE FROM myemployee " +
                                        " WHERE id = ? ";

            preparedStatement = connection.prepareStatement(sqlUpdateEmployee);
            preparedStatement.setInt(1,employeeId);
            preparedStatement.executeUpdate();

        } catch (SQLException ex){

            throw new DatabaseException(ex);
        }
        finally {
            ConnectionProvider.closeConnection(connection, preparedStatement, null);
        }
    }

    @Override
    public Employee loadEmployee(int employeeId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee tmpEmployee = null;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlLoadEmployee = "SELECT * FROM myemployee WHERE id = ?";

           preparedStatement = connection.prepareStatement(sqlLoadEmployee);
           preparedStatement.setInt(1,employeeId);

           resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String zipCcode = resultSet.getString("zip_code");
                String houserNumber = resultSet.getString("house_number");

                tmpEmployee = new Employee(employeeId,firstName, lastName, email, phoneNumber, new Address(street, houserNumber, zipCcode, city));

            }

        }catch(SQLException ex){

            throw new DatabaseException(ex);

        } finally {
            ConnectionProvider.closeConnection(connection,preparedStatement,resultSet);
        }
        return tmpEmployee;

    }

    @Override
    public List<Employee> searchEmployee(String employeeLastName){

        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Employee tmpEmployee;

        try {
            connection = ConnectionProvider.getConnection();

            String sqlLoadEmployee = "SELECT * FROM myemployee WHERE last_name = ?";

            preparedStatement = connection.prepareStatement(sqlLoadEmployee);
            preparedStatement.setString(1,employeeLastName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                String zipCcode = resultSet.getString("zip_code");
                String houserNumber = resultSet.getString("house_number");

                tmpEmployee = new Employee(id,firstName, lastName, email, phoneNumber, new Address(street, houserNumber, zipCcode, city));
                employees.add(tmpEmployee);
            }

        }catch(SQLException ex){

            throw new DatabaseException(ex);

        } finally {
            ConnectionProvider.closeConnection(connection,preparedStatement,resultSet);
        }
        return employees;

    }

}
