<%--
  Created by IntelliJ IDEA.
  User: parkheejun
  Date: 4/23/24
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Objects" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%!
    private List<String> getClasses(Class<?> clazz) {
        if(Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        List<String> classes = new ArrayList<>();
        while(clazz != null) {
            classes.add(clazz.getName());
            clazz = clazz.getSuperclass();
        }

        return classes;
    }
%>
<%
    response.setContentType("text/plain");

    out.println("Hello, " + request.getParameter("name"));
    out.println("servlet name = " + config.getServletName());
    out.println("context path = " + application.getContextPath());

    out.println("this == page? " + (this == page));

    List<String> classes = getClasses(getClass());
    out.println("page classes = " + String.join(" > ", classes));

    classes = getClasses(pageContext.getClass());
    out.println("pageContext classes = " + String.join(" > ", classes));
%>
