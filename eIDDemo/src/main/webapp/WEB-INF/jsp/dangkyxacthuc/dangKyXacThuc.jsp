<%@include file="../layout/header.jsp"%>
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
						<li class="breadcrumb-item"><a href="${contextPath }/">Trang
								chủ</a></li>
						<li class="breadcrumb-item active">Chọn hình thức đăng ký</li>
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
					<h3 class="card-title">Chọn loại hình xác thực</h3>
				</div>
				<!-- /.card-header -->
				<!-- form start -->
				<form role="form" id="quickForm" method="get"
					action="${contextPath }/dang-ky-xac-thuc/dieu-huong">
					<div class="card-body">
						<c:if test="${fn:length(mTypes) ne fn:length(mTypeRegisters) }">
							<div class="form-group">
								<select class="form-control" name="type">
									<c:forEach items="${mTypes }" var="item">
										<c:set var="check" value="0"></c:set>
										<c:forEach items="${mTypeRegisters }" var="it">
											<c:if test="${item.code eq it.code }">
												<c:set var="check" value="1"></c:set>
											</c:if>
										</c:forEach>
										<c:if test="${check eq 0 }">
											<option value="${item.code }">${item.name }</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</c:if>
						<c:if test="${fn:length(mTypes) eq fn:length(mTypeRegisters) }">
							Không còn phương thức xác thực
						</c:if>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<button type="submit" class="btn btn-primary">Đăng ký</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<%@include file="../layout/footer.jsp"%>