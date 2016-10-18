<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>图片识别结果</title>

		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	 <link href="css/bootstrap.min.css" rel="stylesheet">
<style type="text/css">
.size{
	width:80%;
	margin:auto;
}
</style>	
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript">

             $.ajax({
               type:"post",//请求方式
               url:"ImageRecognition2",//发送请求地址
               timeout:30000,//超时时间：30秒
               dataType:"json",//设置返回数据的格式
               //请求成功后的回调函数 data为json格式
               success:function(data){
               	 $("#resultJsonText2").text("url："+data.url);
                  $("#resultJsonText").append("<br></br>"
                  							  +"<img  class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url0+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url1+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url2+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url3+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url4+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url5+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url6+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url7+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url8+ ">"
											  +"<br></br>"
											  +"<img class=\"img-rounded center-block img-responsive\" width=\"80%\" src="+ data.url9+ ">"
																);
              },
              //请求出错的处理
              error:function(){
                        alert("请求出错");
              }
           });
          
	</script>
	</head>
	<body class="bg-success">
	<div class="text-center btn-info size">
		<h2>${message} 识别结果为</h2>
	</div>
	<div>
		
		<p id="resultJsonText"></p>
	</div>
	<div id="resText">
	</div>
	</body>
</html>