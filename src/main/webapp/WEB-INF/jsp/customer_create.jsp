<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户管理  - 创建客户</title>
</head>
<body>
<h1>创建客户界面</h1>

<form id = "customer_form" enctype = "multipart/form-data">
	<table>
		<tr>
			<td>客户名称 ：</td>
			<td>
				<input type = "text" name = "name" value = "${customer.name}">
			</td>
		</tr>
	</table>
</form>
</body>
</html>