package com.database.dao;

import com.database.model.Employee;

import java.util.List;

public interface EmployeeDao {

    List<Employee> getEmployeeList();

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(int employeeId);

    Employee loadEmployee(int employeeId);

    List<Employee> searchEmployee(String employeeLastName);

}
