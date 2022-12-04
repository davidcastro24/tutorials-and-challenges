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
            <td>${personName}</td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td>${personLastName}</td>
        </tr>
        <tr>
            <th>Age</th>
            <td>${personAge}</td>
        </tr>
        <tr>
            <th>Country</th>
            <td>${personCountry}</td>
        </tr>

        <tr>
            <th>Description</th>
            <td>${personSubject}</td>
        </tr>
    </table>
</body>
</html>
