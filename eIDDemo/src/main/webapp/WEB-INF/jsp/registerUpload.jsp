<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>EID | Registration Page</title>
<!-- Tell the browser to be responsive to screen width -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="${contextPath }/plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- icheck bootstrap -->
<link rel="stylesheet"
	href="${contextPath }/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${contextPath }/dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<!-- jQuery -->
	<script src="${contextPath }/plugins/jquery/jquery.min.js"></script>
	

<link href="${contextPath}/css/jquery.growl.css" rel="stylesheet">
<script src="${contextPath}/js/jquery.growl.js"></script>

<link href="${contextPath}/css/sweetalert.css" rel="stylesheet">
<script src="${contextPath}/js/sweetalert.min.js"></script>
<script src="${contentPath }/plugins/bs-custom-file-input/bs-custom-file-input.min.js"></script>	
</head>
<body class="hold-transition register-page">
	<div class="register-box">
		<div class="register-logo">
			<a href="/login"><b>Admin</b>EID</a>
		</div>
	
		<div class="card">
			<div class="card-body register-card-body">
				<form action="${contentPath }/register/upload-training" method="post" id="quickForm" enctype="multipart/form-data">
					
					<i><b>Yêu cầu:</b> Tải lên 3 ảnh cá nhân rõ mặt</i>
					<div class="input-group mb-3" style="margin-top: 10px;">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="exampleInputFile" name="file1">
							<label class="custom-file-label" for="exampleInputFile">Ảnh 1</label>
						</div>
						<div class="input-group-append">
							<span class="input-group-text" id="">Tải lên</span>
						</div>
					</div>
					<div class="input-group mb-3">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="exampleInputFile2" name="file2">
							<label class="custom-file-label" for="exampleInputFile2">Ảnh 2</label>
						</div>
						<div class="input-group-append">
							<span class="input-group-text" id="">Tải lên</span>
						</div>
					</div>
					<div class="input-group mb-3">
						<div class="custom-file">
							<input type="file" class="custom-file-input" id="exampleInputFile3" name="file3">
							<label class="custom-file-label" for="exampleInputFile3">Ảnh 3</label>
						</div>
						<div class="input-group-append">
							<span class="input-group-text" id="">Tải lên</span>
						</div>
					</div>
					
					<div class="row">
						<div class="col-12">
							<button type="submit" class="btn btn-primary btn-block">Tiếp tục</button>
						</div>
						<!-- /.col -->
					</div>
				</form>

				<a href="/login" class="text-center">Đăng nhập</a>
			</div>
			<!-- /.form-box -->
		</div>
		<!-- /.card -->
	</div>
	<!-- /.register-box -->
	<%@include file="layout/js.jsp"%>
	<script type="text/javascript">
	$(document).ready(function () {
		bsCustomFileInput.init();
	  	$("#quickForm").submit(function(e){
	  		if($("#exampleInputFile").val() == "" || $("#exampleInputFile3").val() == ""|| $("#exampleInputFile2").val() == "") {
	  			alertER("Tải lên 3 ảnh cá nhân rõ mặt");
	  			e.preventDefault();
	  		}
	  	});
	});
	</script>
	
	<!-- Bootstrap 4 -->
	<script src="${contextPath }/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script src="${contextPath }/dist/js/adminlte.min.js"></script>
</body>
</html>
