<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<head>
	<title>智能导览</title>
	<link rel="stylesheet" type="text/css" href="css/Home.css" media="only screen and (max-width:800px)"></link>
	<link rel="stylesheet" type="text/css" href="css/HomePC.css" media="only screen and (min-width:800px)"></link>
	<meta name="viewport" content="width=device-width, initial-scale=0.6" />
	<meta charset="utf-8">  



<script>
function setCookie(cname,cvalue,exdays){
	var d = new Date();
	d.setTime(d.getTime()+(exdays*24*60*60*1000));
	var expires = "expires="+d.toGMTString();
	document.cookie = cname+"="+cvalue+"; "+expires;
}
function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i].trim();
		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
	}
	return "";
}
function checkCookie(){
	var user=getCookie("username");
	if (user!=""){
		alert("Welcome again " + user);
	}
	else {
		user = prompt("Please enter your name:","");
  		if (user!="" && user!=null){
    		setCookie("username",user,30);
    	}
	}
}
</script>
</head>
	
<body onload="checkCookie()"></body>
  
  <body>
    This is my JSP page. <br>
  </body>
</html>
