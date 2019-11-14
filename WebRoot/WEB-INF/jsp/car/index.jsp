<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="../js/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<script src="../js/ga.js"></script>
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/bootstrap-table.css">
<link rel="stylesheet" href="../css/bootstrap-editable.css">
<link rel="stylesheet" href="../css/index.css">
<title>Book list</title>
<script type="text/javascript">
var company = [{value:"长沙市出租车公司",text:"长沙市出租车公司"},{value:"2",text:"销售部2"},{value:"3",text:"行政部"}];
</script>
</head>
<body style="font-family: 'Microsoft YaHei';">
<div class="container" style="height:300px">
    <h1></h1>
    <div id="toolbar">
        <button id="remove" class="btn btn-success" disabled>
            <i class="glyphicon glyphicon-ok"></i> 欢迎使用系统！
        </button>
    </div>
</div>
<script>
	$(function () {
	    
	});
</script>
</body>
</html>