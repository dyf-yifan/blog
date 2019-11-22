<%--
  Created by IntelliJ IDEA.
  User: 丁怡凡
  Date: 2019/11/21
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传页面</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data" multiple>
<input type="file" name="filename" multiple>
<input type="submit" value="上传">
</form>
<p>${msg}</p>
<p>${url}</p>
</body>
</html>
