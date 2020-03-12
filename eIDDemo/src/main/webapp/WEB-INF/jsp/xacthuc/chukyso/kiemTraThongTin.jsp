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
						<li class="breadcrumb-item active">Kiểm tra thông tin OCR</li>
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
					<h3 class="card-title">Kiểm tra thông tin OCR</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="post" action="${contextPath }/dang-ky-xac-thuc/doi-chieu-ocr">
					<div class="card-body">
						<div class="form-group">
							<label for="hoTen">Họ và tên</label>
							<input type="text" class="form-control" id="hoTen" value="${oCRField.hoTen }" name="hoTen">
						</div>
						<div class="form-group">
							<label for="soCMT">Số CMT</label>
							<input type="text" class="form-control" id="soCMT" value="${oCRField.soCMT }" name="soCMT">
						</div>
						<div class="form-group">
							<label for="ngayThang">Ngày tháng năm sinh</label>
							<input type="text" class="form-control" id="ngayThang" value="${oCRField.ngayThang }" name="ngayThang">
						</div>
						<div class="form-group">
							<label for="gioiTinh">Giới tính</label>
							<input type="text" class="form-control" id="gioiTinh" value="${oCRField.gioiTinh }" name="gioiTinh">
						</div>
						<div class="form-group">
							<label for="danToc">Dân tộc</label>
							<input type="text" class="form-control" id="danToc" value="${oCRField.danToc }" name="danToc">
						</div>
						<div class="form-group">
							<label for="queQuan">Quê quán</label>
							<input type="text" class="form-control" id="queQuan" value="${oCRField.queQuan }" name="queQuan">
						</div>
						<div class="form-group">
							<label for="noiTru">Nơi trú</label>
							<input type="text" class="form-control" id="noiTru" value="${oCRField.noiTru }" name="noiTru">
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<a href="/dang-ky-xac-thuc/xac-thuc-ocr" class="btn btn-default">Quay lại</a>
						<button type="submit" class="btn btn-primary">Đối chiếu thông tin</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@include file="../../layout/footer.jsp"%>