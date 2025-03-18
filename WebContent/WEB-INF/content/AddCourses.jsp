<%--
  Created by IntelliJ IDEA.
  User: aliabbasjaffri
  Date: 25/11/2015
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>コース登録</title>
    <link href="assets/css/bootstrap-united.css" rel="stylesheet" />
    <style>
        .error {
            color: #ff0000;
        }
        
        .courseTable {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        
        .courseTable th, .courseTable td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        
        .courseTable tr:hover {
            background-color: #f5f5f5;
        }
        
        .submitButton {
            margin: 20px;
        }
    </style>
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
        <h1>コース登録</h1>
        <p>登録したいコースを選択してください</p>
    </div>
    
    <s:form action="courses-register" method="post">
        <table class="courseTable">
            <thead>
                <tr>
                    <th>選択</th>
                    <th>コース名</th>
                    <th>コースID</th>
                    <th>講師名</th>
                    <th>時間</th>
                    <th>曜日</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="allCourses">
                    <tr>
                        <td><s:checkbox name="selectedCourseIds" fieldValue="%{courseId}" /></td>
                        <td><s:property value="name"/></td>
                        <td><s:property value="courseId"/></td>
                        <td><s:property value="instructorName"/></td>
                        <td><s:property value="time"/></td>
                        <td><s:property value="day"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
        
        <div class="submitButton">
            <s:submit value="登録" cssClass="btn btn-primary"/>
        </div>
    </s:form>
</div>
</body>
</html>
