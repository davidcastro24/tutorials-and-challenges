<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/userFormStyle.css"/>
</head>
<body>
<br><br>
<br><br>
<br><br>
    <table id="personTable">
        <tr>
            <th>First Name</th>
            <td>${person.firstName}</td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td>${person.lastName}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${person.age}</td>
        </tr>
        <tr>
            <th>Country</th>
            <td>${person.getCountryName()}</td>
        </tr>
        <tr>
            <th>Language</th>
                <td>${person.getLanguageName()}</td>
        </tr>
        <tr>
            <th>Preferred OS</th>
                <c:forEach var = "temp" items="${person.preferredOS}">
                    <td>${temp}</td>
                </c:forEach>
        </tr>
        <tr>
            <th>Description</th>
            <td>${person.description}</td>
        </tr>
    </table>
</body>
</html>
