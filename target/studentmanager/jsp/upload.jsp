<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,javax.servlet.http.*,src.dbutil.*,src.entity.*,src.model.*,java.io.*,java.sql.*,javax.servlet.*,org.apache.poi.ss.usermodel.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>通过excel文件批量添加</title>
	<style>
		/* 样式从这里开始 */
		body {
			font-family: Arial, sans-serif;
			padding: 20px;
		}

		form {
			border: 1px solid #ccc;
			padding: 20px;
			border-radius: 5px;
			box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
		}

		input[type="file"], input[type="submit"] {
			padding: 5px 10px;
			font-size: 16px;
			border-radius: 3px;
			border: 1px solid #ccc;
		}

		input[type="submit"] {
			background-color: #4CAF50; /* Green */
			border: none;
			color: white;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 16px;
			margin: 4px 2px;
			cursor: pointer;
		}

		input[type="submit"]:hover {
			background-color: #45a049; /* Darker green */
		}
		/* 样式在这里结束 */
	</style>
</head>
<body>

	<form action="${pageContext.request.contextPath }/uploadServlet"
		method="post" enctype="multipart/form-data">
		<input type="file" name="excelFile"
			accept=".xls, .xlsx"/>
		<input type="submit" value="上传">
	</form>
</body>
</html>