<%@include file="../layout/header.jsp"%>
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
						<li class="breadcrumb-item"><a href="${contextPath }/">Trang
								chủ</a></li>
						<li class="breadcrumb-item active">Chọn hình thức xác thực</li>
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
					<h3 class="card-title">Chọn mức xác thực</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="get"
					action="${contextPath }/xac-thuc">
					<div class="card-body">
						<ul class="list-group">
							<li class="list-group-item"><a href="${contextPath }/xac-thuc?type=1">Mức xác thực loại 1</a></li>
							<li class="list-group-item"><a href="${contextPath }/xac-thuc?type=2">Mức xác thực loại 2</a></li>
							<li class="list-group-item"><a href="${contextPath }/xac-thuc?type=3">Mức xác thực loại 3</a></li>
						</ul>
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
	$(document).ready(function() {
		$("#quickForm").submit(function(e) {
			if ($("#types").val() == "") {
				alertER("Chọn 1 hình thức xác thực.");
				e.preventDefault();
			}
		});
	});
</script>
<%@include file="../layout/footer.jsp"%>