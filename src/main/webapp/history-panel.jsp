<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Dominik Janiga" />
        <link rel="stylesheet" href="css/style-control.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" type="text/css" >
    </head>

    <body>
        <div class="firstContainer">

            <div class="headerContainer" style="width: 200px;">

                <input type="button" value="Back"
                       onclick="window.location.href='ServletController'; return false;"
                       class="controlButton"/>
            </div>
        </div>
        <div id="container" style="width: 800px;">
            <table>
                <tr id="tableHeader">
                    <th>Admin</th>
                    <th>Employee</th>
                    <th>Action</th>
                    <th>Date</th>
                    <th>Time</th>
                </tr>
                <c:forEach var="tmpHistory" items="${history_list}" >
                    <tr>
                        <td style="width: 20px; border-left: 1px dashed black;"> ${tmpHistory.user} </td>
                        <td> ${tmpHistory.employee}</td>
                        <td> ${tmpHistory.action}</td>
                        <td> ${tmpHistory.date}</td>
                        <td> ${tmpHistory.time}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
