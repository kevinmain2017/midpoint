<%@include file="../../layout/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Đăng ký xác thực</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="${contextPath }/">Trang chủ</a>
						</li>
						<li class="breadcrumb-item active">Xác thực chữ ký số</li>
					</ol>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /.container-fluid -->
	</div>
	<!-- /.content-header -->

	<!-- Main content -->
	<section class="content">
		<div class="container-fluid">
			<div class="card card-primary">
				<div class="card-header">
					<h3 class="card-title">Xác thực chữ ký số</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="post" action="${contextPath }/dang-ky-xac-thuc/xac-thuc-ca" enctype="multipart/form-data">
					<div class="card-body">
						<div class="form-group">
							<label for="exampleInputFile">Nhập Serial của chữ ký số</label>
							<input type="text" class="form-control" id="exampleInputFile" name="serial">
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<a href="/dang-ky-xac-thuc" class="btn btn-default">Quay lại</a>
						<button type="submit" class="btn btn-primary">Lưu</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<script type="text/javascript">
$(document).ready(function () {
  	bsCustomFileInput.init();
  	$("#quickForm").submit(function(e){
  		if($("#exampleInputFile").val() == "") {
  			alertER("Chọn file cần tải");
  			e.preventDefault();
  		}
  	});
});
</script>
<%@include file="../../layout/footer.jsp"%>