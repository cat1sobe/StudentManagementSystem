<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生ダッシュボード</title>
<link href="assets/css/bootstrap-united.css" rel="stylesheet" />

</head>
<body>
	<script src="jquery-1.8.3.js">
		
	</script>

	<script src="bootstrap/js/bootstrap.js">
		
	</script>

	<div class="navbar navbar-default">

		<div class="navbar-collapse collapse navbar-responsive-collapse">
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
		</div>
		<!-- /.nav-collapse -->
	</div>

	<!-- 
	<legend>Student Enrollment Login Success</legend>
	 -->
	<div class="panel panel-success">
		<div class="panel-heading">
			<h3 align="center" class="panel-title">学生管理システム ダッシュボード</h3>
		</div>
		<div class="panel-body">
			<div class="alert alert-dismissable alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<p align="center">
					<strong>ログイン成功！</strong>
				</p>
			</div>
		</div>
	</div>

	<div align="center">
		<p>
			<a class="btn btn-large btn-block btn-primary" href="courses-add">コース登録</a>
			<a class="btn btn-large btn-block btn-primary" href="courses-show">登録済みコース一覧</a>
		</p>
	</div>

	<div align="center">
		<p>
			<a class="btn btn-primary" href="login-input">別のユーザーでログイン</a>
		</p>
	</div>

</body>
</html>