<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--<%@ taglib tagdir="/WEB-INF/tags/utils" prefix="utils"%>  --%>
<%@ taglib prefix="render" tagdir="/WEB-INF/tags/render" %> 

<c:import url="/header.jsp">
	<c:param name="pagetitle" value="STO - Manager's Tools"/>
</c:import>

<center>
Manager's Tools
<render:managerbuttons></render:managerbuttons>
<br/>
<div id="content">
	<div id="extra">Here is where the main content will go, changed depending on which button is pushed.</div>
	<div id="ct_content" style="display: none;"><render:completedtasks></render:completedtasks></div>
	<div id="it_content" style="display: none;"><render:incompletetasks></render:incompletetasks></div>
	<div id="at_content" style="display: none;"><render:assigntasks></render:assigntasks></div>
	<div id="mu_content" style="display: none;"><render:manageusers></render:manageusers></div>
</div>
<br>
<script>
	$('#ct').click(function(){
		$('#extra').hide();
		$('#it_content').hide();
		$('#at_content').hide();
		$('#mu_content').hide();
		$('#ct_content').show();
	});
	$('#it').click(function(){
		$('#extra').hide();
		$('#ct_content').hide();
		$('#at_content').hide();
		$('#mu_content').hide();
		$('#it_content').show();
	});
	$('#at').click(function(){
		$('#extra').hide();
		$('#it_content').hide();
		$('#ct_content').hide();
		$('#mu_content').hide();
		$('#at_content').show();
	});
	$('#mu').click(function(){
		$('#extra').hide();
		$('#it_content').hide();
		$('#at_content').hide();
		$('#ct_content').hide();
		$('#mu_content').show();
	});
</script>
<c:import url="footer.jsp"/>