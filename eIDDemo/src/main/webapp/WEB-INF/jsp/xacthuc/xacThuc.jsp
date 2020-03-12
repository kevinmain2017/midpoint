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
						<li class="breadcrumb-item">
							<a href="${contextPath }/">Trang chủ</a>
						</li>
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
					<h3 class="card-title">
						Chọn hình thức xác thực
					</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="get" action="${contextPath }/xac-thuc/dieu-huong">
					<div class="card-body">
						<div class="form-group">
							<select class="form-control" name="types" multiple="multiple" id="types">
								<c:forEach items="${mTypes }" var="item">
									<option value="${item.code }">${item.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<button type="submit" class="btn btn-primary" id="xacthuc">Xác thực</button>
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
$(document).ready(function(){
	$("#quickForm").submit(function(e){
		if($("#types").val() == ""){
			alertER("Chọn 1 hình thức xác thực.");
			e.preventDefault();
		}
	});
});
</script>
<%@include file="../layout/footer.jsp"%>