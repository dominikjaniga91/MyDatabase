package com.database.controller;

import com.database.dao.UserDaoDatabase;
import com.database.model.User;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserController")
public class UserController extends HttpServlet {

    private UserDaoDatabase userDaoDatabase = new UserDaoDatabase();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String action =  request.getParameter("action");
        try {
            switch (action) {

                case "LOG_OUT": {
                    logOutUser(request, response);
                    break;
                }
                case "LOAD_USER": {
                    loadUser(request, response);
                    break;
                }
            }
        }catch(ServletException | IOException ex){
            ex.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action =  request.getParameter("action");

        try {
            switch (action) {

                case "LOGIN": {
                    checkUserData(request, response);
                    break;
                }
                case "CREATE_USER": {
                    createUser(request, response);
                    break;
                }
                case "UPDATE_USER": {
                    updateUser(request, response);
                    break;
                }
            }
        }catch (ServletException | IOException ex){
            ex.printStackTrace();
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request,response);
        }
    }

    private void logOutUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if(request.getSession(false) != null){

            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
        } else{
            System.out.println("No session found");
        }
    }


    public void createUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userFirstName = request.getParameter("userFirstName");
        String userLastName = request.getParameter("userLastName");
        String userEmail = request.getParameter("userEmail");
        request.getSession().setAttribute("user_email",userEmail);
        String userPassword = request.getParameter("userPassword");
        String repeatPassword = request.getParameter("userPasswordRepeat");
        String theMessage;

        if(!userPassword.equals(repeatPassword)){
            theMessage = " the passwords are different";
            accessDeniedMessage(request, response, theMessage);
        }
        User user = new User(userFirstName, userLastName, userEmail, userPassword);

        userDaoDatabase.createUser(user);

        request.getSession().setAttribute("firstName", userFirstName);
        request.getSession().setAttribute("lastName", userLastName);
        request.getSession().setAttribute("user_email", userEmail);

        response.sendRedirect(request.getContextPath() + "/ServletController?action=LIST");

    }

    public void checkUserData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userLogin = request.getParameter("login");  // user login == user email
        String userPassword = request.getParameter("loginPassword");
        String theMessage;

        User tempUser = userDaoDatabase.getUser(userLogin);

        if( userLogin.equals("") || userPassword.equals("") ){

            theMessage = "Login or password is empty";
            accessDeniedMessage(request, response, theMessage);

        } else if(tempUser == null){

            theMessage = "There is no such user in database, please crate user";
            accessDeniedMessage(request, response, theMessage);

        } else if( !userPassword.equals(tempUser.getPassword()) ){

            theMessage = "Invalid password, please try again";
            accessDeniedMessage(request, response, theMessage);
        } else {

            request.getSession().setAttribute("firstName", tempUser.getFirstName());
            request.getSession().setAttribute("lastName", tempUser.getLastName());
            request.getSession().setAttribute("user_email", tempUser.getEmail());
            response.sendRedirect(request.getContextPath() + "/ServletController?action=LIST");
        }
    }



    private void accessDeniedMessage(HttpServletRequest request, HttpServletResponse response, String theMessage) throws ServletException, IOException {

        request.setAttribute("information", theMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);

    }

    public void loadUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userEmail = (String)request.getSession().getAttribute("user_email");

        User user = userDaoDatabase.getUser(userEmail);

        request.setAttribute("the_user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-panel.jsp");
        dispatcher.forward(request, response);

    }

    public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id =Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        request.getSession().setAttribute("firstName", firstName);
        request.getSession().setAttribute("lastName", lastName);
        request.getSession().setAttribute("user_email", email);

        User user = new User(id, firstName, lastName, email, password);

        userDaoDatabase.updateUser(user);
        response.sendRedirect(request.getContextPath() + "/UserController?action=LOAD_USER");

    }
}
