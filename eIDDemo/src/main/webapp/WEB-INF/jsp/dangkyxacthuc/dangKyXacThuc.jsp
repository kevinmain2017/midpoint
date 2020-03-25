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
						<table class="table">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Tên loại hình xác thực</th>
									<th scope="col">Đăng ký</th>
									<th scope="col" style="width: 100px;"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${mTypes }" var="item" varStatus="status">
									<tr>
										<th scope="row">${status.index+1 }</th>
										<td>${item.name }</td>
										<td>
											<c:set var="check" value="0"></c:set>
											<c:forEach items="${mTypeRegisters }" var="it">
												<c:if test="${item.code eq it.code }">
													<c:set var="check" value="1"></c:set>
												</c:if>
											</c:forEach>
											<input type="checkbox" value="${item.code }" <c:if test="${check eq 1 }">checked="checked"</c:if> disabled="disabled"/>
										</td>
										<td>
											<a href="javascript:void(0)" onclick="register('/dang-ky-xac-thuc/dk?code=${item.code }', '${item.code }')"><i class="fas fa-registered"></i></a>
											<a href="javascript:void(0)" onclick="deleteRC('/dang-ky-xac-thuc/huy?code=${item.code }')" style="margin-left: 10px;"><i class="fas fa-trash"></i></a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
					</div>
				</form>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>
	<!-- /.content -->
</div>
<script type="text/javascript">
function register(url, code) {
	if(code == 'faceid' || code=='ocr') {
		alertRC(url, "Bạn có muốn đăng ký loại hình xác thực này?");
	} else if(code=='ca'){
		swal({
			  title: "",
			  text: "Nhập vào seria chữ ký số",
			  type: "input",
			  showCancelButton: true,
			  closeOnConfirm: false,
			  inputPlaceholder: "Nhập vào seria chữ ký số"
			}, function (inputValue) {
			  if (inputValue === false) return false;
			  if (inputValue === "") {
			    swal.showInputError("Nhập vào seria chữ ký số!");
			    return false
			  }
			  location.href=url+"&info="+inputValue;
			});
	} else if(code='otp'){
		swal({
		  title: "",
		  text: "Nhập vào số điện thoại cần xác thực",
		  type: "input",
		  showCancelButton: true,
		  closeOnConfirm: false,
		  inputPlaceholder: "Nhập vào số điện thoại cần xác thực"
		}, function (inputValue) {
		  if (inputValue === false) return false;
		  if (inputValue === "") {
		    swal.showInputError("Nhập vào số điện thoại cần xác thực!");
		    return false
		  }
		  location.href=url+"&info="+inputValue;
		});
	}
}
</script>
<!-- /.content-wrapper -->
<%@include file="../layout/footer.jsp"%>