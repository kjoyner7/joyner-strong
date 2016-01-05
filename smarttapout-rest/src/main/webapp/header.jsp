<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="render" tagdir="/WEB-INF/tags/render" %> 

<!DOCTYPE html>
<html lang="en">
     <head>
          <title>${param.pagetitle}</title>
          <meta name="viewport" content="width=device-width, initial-scale=1.0" />
          <meta http-equiv="X-UA-Compatible" content="IE=edge" />
          <c:if test="${!empty param.canonical}"><link rel="canonical" href="${param.canonical}" /></c:if>
          <link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
          <!--Dev stylesheets-->
          <link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/> 
          <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>  
		  <script type="text/javascript" src="js/jquery-ui.min.js"></script>
  
     </head>
     <body>

