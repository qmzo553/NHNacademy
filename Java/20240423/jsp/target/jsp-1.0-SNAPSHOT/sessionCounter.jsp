<%--
  Created by IntelliJ IDEA.
  User: parkheejun
  Date: 4/23/24
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.Objects" %>
<%@ page import="java.nio.file.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sessionCounter</title>
</head>
<body>
    <%
        Long counter = 0l;
        if(Objects.nonNull(session.getAttribute("counter"))) {
            counter = (Long) session.getAttribute("counter");
        }
        session.setAttribute("counter", counter++);
    %>
<h1>
    counter : <%=counter%>
</h1>
</body>
</html>
