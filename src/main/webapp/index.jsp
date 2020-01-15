

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- co to takiego ??-->
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" type="text/css" >
    </head>

    <body>
        <div class="container">
            <div class="createForm" >
                <h3>Create Account</h3>
                <form action="UserController" method="POST" >
                    <input type="hidden" name="action" value="CREATE_USER" />
                    <table>
                        <tr>
                            <td><label>First name </label></td>
                            <td><label><input type="text" name="userFirstName" ></label></td>
                        </tr>
                        <tr>
                            <td><label>Last name </label></td>
                            <td><label><input type="text" name="userLastName" ></label></td>
                        </tr>
                        <tr>
                            <td><label>Email </label></td>
                            <td><label><input type="text" name="userEmail" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="characters@characters.domain"></label></td>
                        </tr>
                        <tr>
                            <td><label>Password </label></td>
                            <td><label><input type="password" name="userPassword"  pattern=".{6,}" title="Six or more characters"></label></td>
                        </tr>
                        <tr>
                            <td><label>Repeat </label></td>
                            <td><label><input type="password" name="userPasswordRepeat"  pattern=".{6,}" title="Six or more characters"></label></td>
                        </tr>
                    </table>
                    <input id="submitCreate" type="submit" value="Create" />
                </form>
            </div>
            <div class="vl"></div>
            <div class="loginForm">
                <h3>Log in</h3>
                <form action="UserController" method="POST" >
                    <input type="hidden" name="action" value="LOGIN" />
                    <table>
                        <tr>
                            <td><label>Email</label></td>
                            <td><label><input type="text" name="login"></label></td>
                        </tr>
                        <tr>
                            <td><label>Password</label></td>
                            <td><label><input type="password" name="loginPassword" ></label></td>
                        </tr>
                    </table>
                    <input id="submitLogin" type="submit" value="Log in" />
                </form>
            </div>
            <div class="information" style="clear: both; text-align: center; color: white; "> ${information} </div>
        </div>


    </body>
</html>


<%--information--%>