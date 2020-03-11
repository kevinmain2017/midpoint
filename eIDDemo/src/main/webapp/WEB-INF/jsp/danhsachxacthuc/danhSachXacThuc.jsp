<%@include file="../layout/header.jsp"%>
<%@ page contentType="text/html; charset=UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<div class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1 class="m-0 text-dark">Danh sách loại hình xác thực</h1>
				</div>
				<!-- /.col -->
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">
							<a href="${contextPath }/">Trang chủ</a>
						</li>
						<li class="breadcrumb-item active">Danh sách loại hình xác thực</li>
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
					<h3 class="card-title">Danh sách loại hình xác thực</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<div class="card-body">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th style="width: 10px">#</th>
								<th>Tên phương thức</th>
								<th style="width: 200px"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${mTypes }" var="item" varStatus="status">
								<tr>
									<td>${status.index+1 }</td>
									<td>${item.name }</td>
									<td>
										<a href="${contextPath }/dang-ky-xac-thuc/dieu-huong?type=${item.code}"><i class="fas fa-edit"></i></a>
										<a href="javascript:void(0)" onclick="deleteRC('${contextPath }/danh-sach-xac-thuc/xoa?type_code=${item.code}')"><i class="fas fa-trash-alt"></i></a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../layout/footer.jsp"%>