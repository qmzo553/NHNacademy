<%--
  Created by IntelliJ IDEA.
  User: parkheejun
  Date: 4/23/24
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>구구단</title>
</head>
<body>
<%
    for (int i = 2; i <= 9; i++) {
%>
<!-- <%= i %>단 시작 - html comment -->
<%-- <%= i %>단 시작 - Scriptlet comment --%>
<h1><%=i%>단</h1>
<%
    for (int j = 1; j <= 9; j++) {
%>
<p>
    <%=i%>*<%=j%>=<%=i * j%>
</p>
<%
        }
    }
%>
</body>
</html>
