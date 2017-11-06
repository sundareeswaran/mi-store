<%@ page language="java" contentType="text/html" import="com.model.Product"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>

	<tr>
		<td rowspan="5">
			<img src="<c:url value="/resources/images/${product.productId}.jpg"/>"  width="500px" height="500px" />
		</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td>Product ID :${product.productId}</td>
	</tr>
	<tr>
		<td>Product Name :${product.productName}</td>
	</tr>
	<tr>
		<td>Product Desc :${product.productDesc}</td>
	</tr>
	


</table>
</body>
</html>