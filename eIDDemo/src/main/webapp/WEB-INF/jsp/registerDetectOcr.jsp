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
				<form action="${contextPath}/register/upload" method="post" id="quickForm" enctype="multipart/form-data">
					<div class="row">
						<div class="col-12">
							<img src="/view" style="width: 100%; height: 250px;"/>
						</div>
					</div>
					<i>Xác nhận thông tin</i>
					<div class="form-group">
						<label for="hoTen">Họ và tên</label>
						<input type="text" class="form-control" id="hoTen" value="${oCRField.name }" name="name">
					</div>
					<div class="form-group">
						<label for="soCMT">Số CMT</label>
						<input type="text" class="form-control" id="soCMT" value="${oCRField.id }" name="id">
					</div>
					<div class="form-group">
						<label for="ngayThang">Ngày tháng năm sinh</label>
						<input type="text" class="form-control" id="ngayThang" value="${oCRField.dob }" name="dob">
					</div>
					<div class="form-group">
						<label for="gioiTinh">Giới tính</label>
						<input type="text" class="form-control" id="gioiTinh" value="${oCRField.sex }" name="sex">
					</div>
					<div class="form-group">
						<label for="danToc">Dân tộc</label>
						<input type="text" class="form-control" id="danToc" value="${oCRField.ethnicity }" name="ethnicity">
					</div>
					<div class="form-group">
						<label for="queQuan">Quê quán</label>
						<input type="text" class="form-control" id="queQuan" value="${oCRField.home }" name="home">
					</div>
					<div class="form-group">
						<label for="noiTru">Nơi trú</label>
						<input type="text" class="form-control" id="noiTru" value="${oCRField.address }" name="address">
					</div>
					<div class="form-group">
						<label for="tonGiao">Tôn giáo</label>
						<input type="text" class="form-control" id="tonGiao" value="${oCRField.religion }" name="religion">
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
	  		if($("#exampleInputFile").val() == "" || $("#exampleInputFile3").val() == "") {
	  			alertER("Tải lên ảnh mặt trước, mặt sau căn cước công dân");
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
