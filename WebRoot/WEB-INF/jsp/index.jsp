<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
</head>
<body>
<h2>${basePath}</h2>
<a href="${basePath}login/logout">logout</a>
<h3>单文件上传</h3>
<form action="${basePath}file/upload" method="post" enctype="multipart/form-data">
    <label>book_id：</label><input type="text" name="bookId"/><br/>
    <label>书名：</label><input type="text" name="name"/><br/>
    <label>封面</label><input type="file" name="file" accept="image/gif, image/jpg, image/png"/><br/>
    <input type="submit" value="提  交"/>
</form>
<h3>多文件上传</h3>
<form action="${basePath}file/multiupload" method="post" enctype="multipart/form-data">
    <label>book_id：</label><input type="text" name="bookId"/><br/>
    <label>书名：</label><input type="text" name="name"/><br/>
    <label>封面1</label><input type="file" name="file" accept="image/gif, image/jpg, image/png"/><br/>
    <label>封面2</label><input type="file" name="file" accept="image/gif, image/jpg, image/png"/><br/>
    <input type="submit" value="提  交"/>
</form>
</body>
</html>