
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style-update.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <div class="userForm" style="float: left;">
                <h3>Update employee</h3>
                <form action="UserController" method="POST" >
                    <input type="hidden" name="action" value="UPDATE_USER" />
                    <input type="hidden" name="userId" value="${the_user.id}" />
                    <table>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><label><input type="text" name="firstName" value="${the_user.firstName}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><label><input type="text" name="lastName" value="${the_user.lastName}" class="textInput"></label></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><label><input type="text" name="email" value="${the_user.email}" class="textInput" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="characters@characters.domain"></label></td>
                        </tr>
                        <tr>
                            <td><label>Password</label></td>
                            <td><label><input type="text" name="password" value="${the_user.password}" class="textInput" pattern=".{6,}" title="Six or more characters"></label></td>
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
