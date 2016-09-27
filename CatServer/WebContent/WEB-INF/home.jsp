<%@page import="main.ServerCommunication"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Cat</title>
</head>

<body>
	<h1>Hello Cat</h1>

		<%
			out.println("<table>");
			for (int i = 0; i < 4; i++) {
				out.println("<tr>");
				for (int j = 0; j < 4; j++) {
					out.println("<td>");
					int index = 4 * i + j;
					String css = "";

					out.println("<button name='btn' onclick='formSubmit(" + index + ")'>" + index + "</button>");

					out.println("</td>");
				}
				out.println("</tr>");
			}
			out.println("</table>");
		%>
	
	 <div id="messages">Message received will appear here:</div>
	
	<script type="text/javascript"  src="js/jquery2.2.4.js"> </script>
	<script>
	function formSubmit(i){
		document.getElementsByName('btn').forEach(function(button){ button.disabled = true; });
		document.getElementById('messages').innerHTML = "LOCKED";
		$.ajax({
		    type: "POST",
		    url: "http://192.168.2.5:8080/CatServer/home",
		    data: 'gpio='+ i,
		    success:function(response) {
		        document.getElementById('messages').innerHTML = response;
		        document.getElementsByName('btn').forEach(function(button){ button.disabled = false; });
		    }
		  });
	}
	</script>
</body>
</html>