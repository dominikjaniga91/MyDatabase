<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 2020-01-05
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> <!-- co to takiego ??-->
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style-update.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <div class="userForm" style="float: left;">
                <h3>Create employee</h3>
                <form action="ServletController" method="POST" >
                    <input type="hidden" name="action" value="CREATE_EMPLOYEE" />
                    <table>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><label><input type="text" name="firstName" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><label><input type="text" name="lastName" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><label><input type="text" name="email" class="textInput" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="characters@characters.domain"></label></td>
                        </tr>
                        <tr>
                            <td><label>Phone number:</label></td>
                            <td><label><input type="text" name="phoneNumber" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Street:</label></td>
                            <td><label><input type="text" name="street" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>House number:</label></td>
                            <td><label><input type="text" name="houseNumber" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Zip code:</label></td>
                            <td><label><input type="text" name="zipCode" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>City:</label></td>
                            <td><label><input type="text" name="city" class="textInput"></label></td>
                        </tr>
                    </table>
                    <div  style="margin-left:auto; margin-right:auto; width:300px; height: 50px;">
                        <input class="saveButton"  type="submit" value="Back to panel" onclick="window.location.href='ServletController'; return false;"  />
                        <input  class="saveButton" type="submit" value="Add" />
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
