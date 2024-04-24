<%--
  Created by IntelliJ IDEA.
  User: parkheejun
  Date: 4/23/24
  Time: 1:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> forward vs include </title>
</head>
<body>
<%
    String id = "marco";
%>
    <h1>THIS IS pageContext.jsp.</h1>
    <%
        String type = request.getParameter("type");
        if("include".equals(type)) {
            // pageContext.include("sub.jsp");
    %>
        <jsp:include page="sub.jsp">
            <jsp:param name="id" value="<%=id%>" />
        </jsp:include>
    <%
        } else if ("forward".equals(type)) {
            // pageContext.forward("sub.jsp");
    %>
        <jsp:forward page="sub.jsp">
            <jsp:param name="id" value="<%=id%>" />
        </jsp:forward>
    <%
        } else {
            out.println("type parameter is needed.");
        }
    %>

    <p>
        pageContext : ID is <%=request.getParameter("id")%>
    </p>
</body>
</html>
