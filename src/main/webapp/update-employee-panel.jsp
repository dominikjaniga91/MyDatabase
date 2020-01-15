
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style-update.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" type="text/css" >
    </head>

    <body>
        <div class="container">
            <div class="userForm" style="float: left;">
                <h3>Update employee</h3>
                <form action="ServletController" method="POST" >
                    <input type="hidden" name="action" value="UPDATE" />
                    <input type="hidden" name="employeeId" value="${the_employee.id}" />
                    <table>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><label><input type="text" name="firstName" value="${the_employee.firstName}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><label><input type="text" name="lastName" value="${the_employee.lastName}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><label><input type="text" name="email" value="${the_employee.email}" class="textInput" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="characters@characters.domain"></label></td>
                        </tr>
                        <tr>
                            <td><label>Phone number:</label></td>
                            <td><label><input type="text" name="phoneNumber" value="${the_employee.phoneNumber}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Street:</label></td>
                            <td><label><input type="text" name="street" value="${the_employee.address.street}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>House number:</label></td>
                            <td><label><input type="text" name="houseNumber" value="${the_employee.address.houseNumber}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Zip code:</label></td>
                            <td><label><input type="text" name="zipCode" value="${the_employee.address.zipCode}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>City:</label></td>
                            <td><label><input type="text" name="city" value="${the_employee.address.city}" class="textInput"></label></td>
                        </tr>
                    </table>
                    <div style="margin-left:auto; margin-right:auto; width:300px; height: 50px;">
                        <input  type="submit" value="Back to panel" onclick="window.location.href='ServletController'; return false;" class="saveButton" />
                        <input class="saveButton" type="submit" value="Save changes" />
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
