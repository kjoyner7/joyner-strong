<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%--<%@ taglib tagdir="/WEB-INF/tags/utils" prefix="utils"%>  --%>
<%@ taglib prefix="render" tagdir="/WEB-INF/tags/render" %> 
<%
//Cookie myData = new Cookie("", "value");
//response.addCookie(myData);
%>

<c:import url="/header.jsp">
	<c:param name="pagetitle" value="Smart Tap Out"/>
</c:import>

<center>
<h2>Smart Tap Out</h2>
<div id="checkin">
<label>User Name:</label>
<input id="username" type="text" value="" placeholder="Username" name="username">
<label>Password:</label>
<input id="password" type="password" value="" placeholder="Password" name="password">
<button id="btnSubmit">Submit</button>
<%--action="index.php?module=Users&action=Login" --%>
<div id="messageDialog" title="Message Dialog">
	<h4 id="message"></h4>
</div>
</div>
<div id="mainContent">
</div>
<script type="text/javascript">
$('#btnSubmit').click(function(){
	if($('#username').val().length == 0){
		$('#message').html("<h4>You must enter a User Name</h4>");
		$('#messageDialog').dialog("open");
		return;
	}
	if($('#password').val() == ""){
		$('#message').html("<h4>You must enter a Password</h4>");
		$('#messageDialog').dialog("open");
		return;
	}
	$.ajax({
			url: "ajax/SecurePassword.jsp",
			type: "POST",
			data: {password: $("#password").val()},
			dataType: "json",
			success: function(msg){		
				$.ajax({
		      	//url: "ajax/CheckLogin.jsp",
		      		url: "rest/user",
		      		type: "GET",
		      		data: { username: $("#username").val(), password: msg[0].pwd},
		      		dataType: "json",
		      		success: function(msg){
		    	//  $("#load_div").hide();
		          // process the response		      	  
		          		if(msg[0].found.indexOf("yes") != -1){
			      			$('#checkin').hide();
			      			if(msg[0].role.indexOf("Manager") != -1){
			      				window.location = "manager.jsp";
			      			}else{
			      				showList();
			      			}
			      		//	window.location = ("rest/tasks/"+$('#username').val());
			      			//$('#mainContent').html("<h2>Made it back</h2>");
		          		}else if(msg[0].found.indexOf("no") != -1){
		        	//  alert(msg[0].message);
		        			$('#message').html(msg[0].message);
		        			$('#messageDialog').dialog("open");
		        	//  $('#username').focus();
		          		}
		    	  	},
		      		error:function(jqXHR, textStatus, errorThrown) {
		       //     alert(jqXHR);
		       //     alert(textStatus); // this comes back as "error"
		           		alert(errorThrown);
						alert(jqXHR.responseText);
						alert("There was a problem connectiing with the server.\nPlease try again later or contact your support.");	                   
		      		}   
				}); },
			error:function(jqXHR, textStatus, errorThrown) {
	       //     alert(jqXHR);
	       //     alert(textStatus); // this comes back as "error"
	        	    alert(errorThrown);
					alert(jqXHR.responseText);
					alert("There was a problem connectiing with the server.\nPlease try again later or contact your support.");	                   
	      	}
		});
});

function showList(){
	//$('#mainContent').html("<h2>Made it back function</h2>");
	$.ajax({
	//	url: "rest/getlistfor/"+$('#username').val(),
		url: "rest/tasks/"+$('#username').val(),
		type: "GET",
		//data:{username: $('#username').val()},
		//dataType: "json",
		success: function(msg){
			$('#mainContent').html(msg);
			},
		error:function(jqXHR, textStatus, errorThrown) {
		       //     alert(jqXHR);
		       //     alert(textStatus); // this comes back as "error"
		        	    alert(errorThrown);
						alert(jqXHR.responseText);
						alert("There was a problem the list from the server.\nPlease try again later or contact your support.");	                   
		      	}
		});
}

//updates tasks completed by user
function doUpdate(){
//	alert("updateing");
	var formvalues = $('#frmTasks').serialize();
	$.ajax({
		url: "rest/tasks/updateTasks",
		type: "POST",
		data: formvalues,
		success: function(msg){
			//$('#mainContent').html(msg);
			$('#message').html(msg[0].message);
			$('#messageDialog').dialog("open");
			},
		error:function(jqXHR, textStatus, errorThrown) {
		       //     alert(jqXHR);
		       //     alert(textStatus); // this comes back as "error"
		        	    alert(errorThrown);
						alert(jqXHR.responseText);
						alert("There was a problem the list from the server.\nPlease try again later or contact your support.");	                   
		      	}
	}); 

}

$('#messageDialog').dialog({
	autoOpen: false,
	width: 400,
	buttons: [
		{
			text: "Ok",
			click: function(){
				$(this).dialog("close");
				$('#username').focus();
			}
		}          
	]	 
});
</script>
<c:import url="footer.jsp"/>
