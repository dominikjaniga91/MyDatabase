<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

    <head>
        <title>My Database</title>
        <meta   charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Dominik Janiga" />
        <script src="script.js" async></script>
        <link rel="stylesheet" href="css/style-control.css" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet" type="text/css" >
    </head>

    <body>
        <div class="firstContainer">

            <div class="innerDiv">
                Welcome ${firstName}  ${lastName}
            </div>

            <form  action="ServletController" style="position: absolute; top: 10px; left: 5%;">
                <input type="text" name="searchName" class="searchField" id="search"/>
                <input type="submit" value=" ${searchOperation} " class="controlButton" style="margin-top:65px; margin-left: -65px; width:70px;" />
                <input type="hidden" name="action" value="SEARCH" />
            </form>
            <div id="tooltip">Search by last name</div>
            <div class="headerContainer">
                <input type="button" value="Add employee"
                       onclick="window.location.href='add-employee-panel.jsp'; return false;"
                       class="controlButton"/>
                <form action="UserController" >
                    <input type="hidden" name="action" value="LOAD_USER" />
                    <input type="submit" value="Your account" class="controlButton"/>
                </form>
                <form action="ServletController" >
                    <input type="hidden" name="action" value="HISTORY" />
                    <input type="submit" value="History" class="controlButton"/>
                </form>
                <form action="UserController">
                    <input type="hidden" name="action" value="LOG_OUT" />
                    <input type="submit" value="Log out" class="controlButton"  />
                </form>
            </div>
        </div>
        <div id="container">
            <table>
                <tr id="tableHeader">
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone Number</th>
                    <th>Street</th>
                    <th>House number</th>
                    <th>Zip code</th>
                    <th>City</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="tmpHistory" items="${employee_list}" >

                    <c:url var="updateLink" value="ServletController"  >
                        <c:param name="action" value="LOAD" />
                        <c:param name="employeeId" value="${tmpHistory.id}" />
                    </c:url>
                    <c:url var="deleteLink" value="ServletController" >
                        <c:param name="action" value="DELETE" />
                        <c:param name="employeeId" value="${tmpHistory.id}" />
                    </c:url>
                    <tr>
                        <td style="width: 20px; border-left: 1px dashed black;"> ${tmpHistory.id} </td>
                        <td> ${tmpHistory.firstName}</td>
                        <td> ${tmpHistory.lastName}</td>
                        <td> ${tmpHistory.email}</td>
                        <td> ${tmpHistory.phoneNumber}</td>
                        <td> ${tmpHistory.address.street}</td>
                        <td> ${tmpHistory.address.houseNumber}</td>
                        <td> ${tmpHistory.address.zipCode}</td>
                        <td> ${tmpHistory.address.city}</td>
                        <td>
                            <a href="${updateLink}" > Update</a>
                            |
                            <a href="${deleteLink}" > Delete </a>
                        </td>
                    </tr>

                </c:forEach>

            </table>
        </div>
    </body>
</html>
