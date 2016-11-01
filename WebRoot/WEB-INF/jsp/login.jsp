<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-login.css">
<style type="text/css">
#main {
	background: url(../img/bg.jpg) no-repeat center;
	height: 500px;
	display: -webkit-flex;
	display: flex;
	-webkit-align-items: center;
	align-items: center;
	-webkit-justify-content: center;
	justify-content: center;
}
</style>
<title>登陆</title>
</head>
<body>
	<div class="page-header">
		<center><h2 style="align:center" id="examples">MYSSM SYSTEM</h2></center>
	</div>
	<div id="main">
		<div id="login" data-toggle="login"></div>
		<input style="display:none" id="infos" value = '<%=(session.getAttribute("info") == null)?"":session.getAttribute("info")%>'>
	</div>

<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="../js/bootstrap.js"></script>
<script type="text/javascript" src="../js/bootstrap-login.js"></script>
<script type="text/javascript" src="../js/analytics.js"></script>
</body>
</html>