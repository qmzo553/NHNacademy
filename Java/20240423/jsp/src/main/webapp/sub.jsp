<%--
  Created by IntelliJ IDEA.
  User: parkheejun
  Date: 4/23/24
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<p>
    THIS IS SUB PAGE. TYPE IS <%= request.getParameter("type") %>.
</p>

<p>
    SUB : ID IS  <%=request.getParameter("id")%>
</p>
