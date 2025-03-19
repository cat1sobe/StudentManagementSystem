<%--
  Created by IntelliJ IDEA.
  User: aliabbasjaffri
  Date: 25/11/2015
  Time: 10:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登録済みコース一覧</title>
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
        
        .message {
            margin: 20px;
            padding: 10px;
            border-radius: 5px;
        }
        
        .info {
            background-color: #d9edf7;
            color: #31708f;
        }
        
        .warning {
            background-color: #fcf8e3;
            color: #8a6d3b;
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
        <h1>登録済みコース一覧</h1>
        <p>あなたが登録しているコースの一覧です</p>
    </div>
    
    <s:if test="message != null">
        <div class="message <s:if test="registeredCourses != null && !registeredCourses.isEmpty()">info</s:if><s:else>warning</s:else>">
            <s:property value="message"/>
        </div>
    </s:if>
    
    <s:if test="registeredCourses != null && !registeredCourses.isEmpty()">
        <table class="courseTable">
            <thead>
                <tr>
                    <th>コース名</th>
                    <th>コースID</th>
                    <th>講師名</th>
                    <th>時間</th>
                    <th>曜日</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="registeredCourses">
                    <tr>
                        <td><s:property value="name"/></td>
                        <td><s:property value="courseId"/></td>
                        <td><s:property value="instructorName"/></td>
                        <td><s:property value="time"/></td>
                        <td><s:property value="day"/></td>
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </s:if>
    
    <div style="margin-top: 20px; text-align: center;">
        <a href="courses-add" class="btn btn-primary">コース登録</a>
        <a href="login-success" class="btn btn-default">ダッシュボードに戻る</a>
    </div>
</div>
</body>
</html>
