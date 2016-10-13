<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/myssm/js/jquery-3.1.1.min.js"></script>
<title>Book list</title>
<script type="text/javascript">
$(document).ready(function () {
	$("#ajaxTest").click(function () {
		$.ajax({
            url: "/myssm/book/test?bookId=测试",
            dataType : "json",
            type: "get",
            data: {
            },
            success:function(response){
            	alert(response.message);
            }
		});
    });
});
</script>
</head>
<body>
<h1>Book list</h1>
<input type="button" id="ajaxTest" value="查询"></input>
</body>
</html>