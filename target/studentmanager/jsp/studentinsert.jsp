<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK" pageEncoding="gbk"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>����ѧ����Ϣ</title>
	  <style>
		  /* insert.css */

		  body {
			  font-family: Arial, sans-serif;
			  margin: 0;
			  padding: 20px;
			  background-color: #f0f0f0;
		  }

		  h1 {
			  text-align: center;
			  color: #333;
		  }

		  form {
			  max-width: 400px;
			  margin: 0 auto;
			  padding: 20px;
			  background-color: #fff;
			  border: 1px solid #ccc;
			  border-radius: 5px;
		  }

		  form p {
			  margin: 10px 0;
		  }

		  form input[type="text"],
		  form input[type="submit"],
		  form input[type="reset"] {
			  width: 100%;
			  padding: 10px;
			  box-sizing: border-box;
			  border: 1px solid #ccc;
			  border-radius: 4px;
		  }

		  form input[type="submit"],
		  form input[type="reset"] {
			  width: auto;
			  margin-top: 10px;
			  padding: 8px 15px;
			  color: #fff;
			  background-color: #4CAF50; /* Green */
			  border: none;
			  cursor: pointer;
		  }

		  form input[type="reset"] {
			  background-color: #f44336; /* Red */
		  }

		  form input[type="submit"]:hover,
		  form input[type="reset"]:hover {
			  background-color: #45a049; /* Darker green */
			  /* Redder red for reset button */
		  }

		  /* Optional: For mobile devices */
		  @media screen and (max-width: 600px) {
			  form {
				  padding: 10px;
			  }
		  }
	  </style>
  </head>
  
  <body>

		<h1>����ѧ����Ϣ</h1>
			<form action="../InsertStudentservlet.do" method="post">

				<p>ѧ��: <input type="text" name="id"></p>
				<p>����:
				<input type="text" name="name" />
				<br></p>
				<p>�Ա�:
				<input type="text" name="sex" />
                <p> ����:
				<input type="text" name="age" />
				<br></p>
                <p>  �༶:
				<input type="text" name="grade" />
				<br></p>
                <p>  �ɼ�:
				<input type="text" name="score" />
				<br></p>
				<input type="submit" value="�ύ" />
				<input type="reset" value="����" />
			</form>
  </body>
</html>
