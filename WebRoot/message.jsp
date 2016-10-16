<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>图片识别结果</title>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript">
	    
	         var userNameObj=$("#username").val();
	         var contentObj=$("#content").val();
             $.ajax({
               type:"post",//请求方式
               url:"jsonAjaxAction2",//发送请求地址
               timeout:30000,//超时时间：30秒
               dataType:"json",//设置返回数据的格式
               //请求成功后的回调函数 data为json格式
               success:function(data){
               	 $("#resultJsonText2").text("url："+data.url);
                  $("#resultJsonText").append("<img src="+ data.url0+ ">"
											  +"<img src="+ data.url1+ ">"
											  +"<img src="+ data.url2+ ">"
											  +"<img src="+ data.url3+ ">"
											  +"<img src="+ data.url4+ ">"
											  +"<img src="+ data.url5+ ">"
											  +"<img src="+ data.url6+ ">"
											  +"<img src="+ data.url7+ ">"
											  +"<img src="+ data.url8+ ">"
											  +"<img src="+ data.url9+ ">"
																);
              },
              //请求出错的处理
              error:function(){
                        alert("请求出错");
              }
           });
          
	</script>
	</head>
	<body>
	
	<h2>${message}</h2>
	
	<div >
		<h3>识别结果为</h3>
		<p id="resultJsonText"></p>
	</div>
	<div id="resText">
	</div>
	</body>
</html>