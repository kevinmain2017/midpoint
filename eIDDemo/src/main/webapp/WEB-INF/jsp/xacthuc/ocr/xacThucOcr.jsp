<%@include file="../../layout/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Xác thực</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="${contextPath }/">Trang chủ</a>
						</li>
						<li class="breadcrumb-item active">Xác thực OCR</li>
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
					<h3 class="card-title">Xác thực OCR</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="post" action="${contextPath }/xac-thuc/xac-thuc-ocr" enctype="multipart/form-data">
					<div class="card-body">
						<div class="form-group">
							<label for="exampleInputFile">Tải căn cước công dân</label>
							<div class="input-group">
								<div class="custom-file" style="display: block;">
									<input type="file" class="custom-file-input" id="exampleInputFile" name="fileMattruoc">
									<label class="custom-file-label" for="exampleInputFile">Ảnh mặt trước</label>
								</div>
								<div class="input-group-append">
									<span class="input-group-text" id="">Tải lên</span>
								</div>
							</div>
							<div class="input-group" style="margin-top: 10px;">
								<div class="custom-file" style="display: block;">
									<input type="file" class="custom-file-input" id="exampleInputFile1" name="fileMatSau">
									<label class="custom-file-label" for="exampleInputFile1">Ảnh mặt sau</label>
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
						<button type="submit" class="btn btn-primary">Đọc dữ liệu căn cước</button>
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
  			alertER("Tải lên ảnh mặt trước căn cước công dân");
  			e.preventDefault();
  		}
  		if($("#exampleInputFile1").val() == "") {
  			alertER("Tải lên ảnh mặt sau căn cước công dân");
  			e.preventDefault();
  		}
  	});
});
</script>
<%@include file="../../layout/footer.jsp"%>