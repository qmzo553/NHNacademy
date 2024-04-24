<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="style.css" />
</head>
<body>

<table>
    <tbody>
    <tr>
        <th>status_code</th>
        <td><%= request.getAttribute("status_code") %></td>
    </tr>
    <tr>
        <th>exception_type</th>
        <td><%= request.getAttribute("exception_type") %></td>
    </tr>
    <tr>
        <th>message</th>
        <td><%= request.getAttribute("message") %></td>
    </tr>
    <tr>
        <th>exception</th>
        <td><%= request.getAttribute("exception") %></td>
    </tr>
    <tr>
        <th>request_uri</th>
        <td><%= request.getAttribute("request_uri")%></td>
    </tr>
    </tbody>

</table>
</body>
</html>