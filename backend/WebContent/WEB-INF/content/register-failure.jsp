<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>コース登録失敗</title>
    <link href="assets/css/bootstrap-united.css" rel="stylesheet" />
</head>
<body>
<script src="jquery-1.8.3.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>

<div class="navbar navbar-default">
    <div class="navbar-collapse collapse navbar-responsive-collapse">
        <ul class="nav navbar-nav navbar-right">
            <li><a href="/">Home</a></li>
            <li><a href="signup-input">Signup</a></li>
            <li class="active"><a href="login-input">Login</a></li>
            <li class="dropdown"><a href="#" class="dropdown-toggle"
                                    data-toggle="dropdown">Explore<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="courses-show-all">All Courses</a></li>
                    <li class="divider"></li>
                    <li><a href="#">Further Actions</a></li>
                </ul></li>
        </ul>
    </div>
    <!-- /.nav-collapse -->
</div>

<div class="container">
    <div class="jumbotron">
        <div class="alert alert-danger">
            <h1>登録失敗</h1>
            <p><s:property value="message"/></p>
        </div>
        <p>
            <a href="courses-add" class="btn btn-primary btn-lg">コース登録に戻る</a>
            <a href="login-success.jsp" class="btn btn-default btn-lg">ダッシュボードに戻る</a>
        </p>
    </div>
</div>
</body>
</html> 