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
						<li class="breadcrumb-item active">Xác thực Face ID</li>
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
					<h3 class="card-title">Xác thực Face ID</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="post" action="${contextPath }/dang-ky-xac-thuc/xac-thuc-face-id" enctype="multipart/form-data">
					<div class="card-body">
						<div class="form-group">
							<label for="exampleInputFile">Tải ảnh xác thực</label>
							<div class="input-group">
								<div class="custom-file">
									<input type="file" class="custom-file-input" id="exampleInputFile" name="file">
									<label class="custom-file-label" for="exampleInputFile">Chọn file</label>
								</div>
								<div class="input-group-append">
									<span class="input-group-text" id="">Tải lên</span>
								</div>
							</div>
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<a href="/dang-ky-xac-thuc" class="btn btn-default">Quay lại</a>
						<button type="submit" class="btn btn-primary">Tải ảnh</button>
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