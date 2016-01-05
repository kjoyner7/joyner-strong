<%@page contentType="application/json; charset=UTF-8"%>
<%@page import="com.tmd.lib.Users" %>


<%
String msg="message";
Users user = new Users();

String secPwd = user.getMD5(request.getParameter("password"));
msg = "[{\"pwd\":\""+secPwd+"\"}]";

response.setHeader("Cache-Control", "no-cache");
response.getWriter().write(msg);
%>