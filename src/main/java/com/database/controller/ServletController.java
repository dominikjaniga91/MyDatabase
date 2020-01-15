package com.database.controller;

import com.database.dao.EmployeeDaoDatabase;
import com.database.dao.HistoryDatabase;
import com.database.dao.UserDaoDatabase;
import com.database.model.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ServletController")
public class ServletController extends HttpServlet {

    private EmployeeDaoDatabase employeeDaoDatabase = new EmployeeDaoDatabase();
    private UserDaoDatabase userDaoDatabase = new UserDaoDatabase();
    private HistoryDatabase historyDatabase = new HistoryDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action =  request.getParameter("action");

        if(action == null){
            action = "LIST";
        }

        try {
            switch (action) {
                case "LIST": {
                    getEmployeeList(request, response);
                    break;
                }
                case "LOAD": {
                    loadEmployee(request, response);
                    break;
                }
                case "DELETE": {
                    deleteEmployee(request, response);
                    break;
                }
                case "SEARCH": {
                    searchEmployee(request, response);
                    break;
                }
                case "HISTORY": {
                    getHistory(request, response);
                    break;
                }
                default:
                    getEmployeeList(request, response);
            }
        }catch(ServletException | IOException ex){
            ex.printStackTrace();
//            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
//            dispatcher.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String action =  request.getParameter("action");

        try {
            switch (action) {
                case "CREATE_EMPLOYEE": {
                    addEmployee(request, response);
                    break;
                }
                case "UPDATE": {
                    System.out.println("case update");
                    updateEmployee(request, response);
                    break;
                }
            }
        }catch(ServletException | IOException ex){
            ex.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request,response);
        }
    }

    private void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("searchOperation", "cancel");
        String employeeLastName = request.getParameter("searchName");

        List<Employee> employees = employeeDaoDatabase.searchEmployee(employeeLastName);

        if(!employeeLastName.equals("")){
            request.setAttribute("employee_list", employees);
            RequestDispatcher dispatcher = request.getRequestDispatcher("control-panel.jsp");
            dispatcher.forward(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/ServletController?action=LIST");
        }
    }

    public void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String street = request.getParameter("street");
        String houseNumber = request.getParameter("houseNumber");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");

        Employee employee = new Employee(firstName, lastName, email, phoneNumber, new Address(street, houseNumber, zipCode, city));

        // adding to history
        String userEmail = (String) request.getSession().getAttribute("user_email");
        User user = userDaoDatabase.getUser(userEmail);
        historyDatabase.addToHistory(user.getFirstName() + ' ' + user.getLastName(), firstName+ ' ' + lastName, "add");

        employeeDaoDatabase.addEmployee(employee);

        response.sendRedirect(request.getContextPath() + "/ServletController?action=LIST");
    }

    public void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("employeeId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String street = request.getParameter("street");
        String houseNumber = request.getParameter("houseNumber");
        String zipCode = request.getParameter("zipCode");
        String city = request.getParameter("city");

        Employee employee = new Employee(id,firstName, lastName, email, phoneNumber, new Address(street, houseNumber, zipCode, city));

        employeeDaoDatabase.updateEmployee(employee);

        // adding to history
        String userEmail = (String) request.getSession().getAttribute("user_email");
        User user = userDaoDatabase.getUser(userEmail);
        historyDatabase.addToHistory(user.getFirstName() + ' ' + user.getLastName(), firstName+ ' ' + lastName, "update");

        getEmployeeList(request, response);

    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userEmail = (String) request.getSession().getAttribute("user_email");
        User user = userDaoDatabase.getUser(userEmail);

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        Employee employee = employeeDaoDatabase.loadEmployee(employeeId);

        employeeDaoDatabase.deleteEmployee(employeeId);

        historyDatabase.addToHistory(user.getFirstName()+' '+user.getLastName(), employee.getFirstName()+' '+employee.getLastName() , "delete");

        getEmployeeList(request, response);
    }


    public void getEmployeeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("searchOperation", "search");

        List<Employee> employees = employeeDaoDatabase.getEmployeeList();

        request.setAttribute("employee_list", employees);

        RequestDispatcher dispatcher = request.getRequestDispatcher("control-panel.jsp");

        dispatcher.forward(request, response);

    }

    public void getHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<History> historyList = historyDatabase.getFromHistory();

        request.setAttribute("history_list", historyList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("history-panel.jsp");

        dispatcher.forward(request, response);

    }

    public void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int employeeId = Integer.parseInt(request.getParameter("employeeId"));

        Employee employee = employeeDaoDatabase.loadEmployee(employeeId);

        request.setAttribute("the_employee", employee);

        RequestDispatcher dispatcher = request.getRequestDispatcher("update-employee-panel.jsp");
        dispatcher.forward(request, response);

    }

}
