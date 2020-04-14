<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>EID</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Font Awesome -->
<link rel="stylesheet" href="${contextPath }/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bbootstrap 4 -->
<link rel="stylesheet" href="${contextPath }/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="${contextPath }/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- JQVMap -->
<link rel="stylesheet" href="${contextPath }/plugins/jqvmap/jqvmap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${contextPath }/dist/css/adminlte.min.css">
<!-- overlayScrollbars -->
<link rel="stylesheet" href="${contextPath }/plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
<!-- Daterange picker -->
<link rel="stylesheet" href="${contextPath }/plugins/daterangepicker/daterangepicker.css">
<!-- summernote -->
<link rel="stylesheet" href="${contextPath }/plugins/summernote/summernote-bs4.css">
<!-- Google Font: Source Sans Pro -->
<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
<script src="${contentPath }/plugins/jquery/jquery.min.js"></script>

<link href="${contextPath}/css/jquery.growl.css" rel="stylesheet">
<script src="${contextPath}/js/jquery.growl.js"></script>

<link href="${contextPath}/css/sweetalert.css" rel="stylesheet">
<script src="${contextPath}/js/sweetalert.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#leftMenu .nav-treeview li").each(function() {
			var obj = $(this);
			var lo = window.location.pathname;
			$(this).find("a").each(function() {

				if (lo.startsWith($(this).attr("href"))) {
					$(this).addClass("active");
				}
			});

		});
	});
</script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<input type="hidden" id='success' value="${success }" />
	<input type="hidden" id='error' value="${error }" />

	<%@include file="../layout/js.jsp"%>

	<div class="wrapper">

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item">
					<a class="nav-link" data-widget="pushmenu" href="#">
						<i class="fas fa-bars"></i>
					</a>
				</li>
				<li class="nav-item d-none d-sm-inline-block">
					<a href="${contextPath }/" class="nav-link">Trang chủ</a>
				</li>
			</ul>

			<!-- SEARCH FORM -->
			<form class="form-inline ml-3">
				<div class="input-group input-group-sm">
					<input class="form-control form-control-navbar" type="search" placeholder="Search" aria-label="Search">
					<div class="input-group-append">
						<button class="btn btn-navbar" type="submit">
							<i class="fas fa-search"></i>
						</button>
					</div>
				</div>
			</form>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a class="nav-link"  href="/logout">
						<i class="fas fa-sign-out-alt"></i> Đăng xuất
					</a>
				</li>
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="${contentPath }/" class="brand-link">
				<img src="${contentPath }/dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
				<span class="brand-text font-weight-light"><b>Admin </b>EID</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">
					<div class="image">
						<img src="${contentPath }/image/default-user.png" class="img-circle elevation-2" alt="User Image">
					</div>
					<div class="info">
						<a href="#" class="d-block">${userName }</a>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false" id='leftMenu'>
						<li class="nav-item has-treeview menu-open">
							
							<ul class="nav nav-treeview">
								<li class="nav-item">
									<a href="${contextPath }/dang-ky-xac-thuc" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Đăng ký xác thực</p>
									</a>
								</li>
								<li class="nav-item">
									<a href="${contextPath }/muc-xac-thuc" class="nav-link">
										<i class="far fa-circle nav-icon"></i>
										<p>Xác thực giao dịch</p>
									</a>
								</li>
							</ul>
						</li>
					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>