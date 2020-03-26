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
				<form role="form" id="quickForm" method="post" action="${contextPath }/xac-thuc/xac-thuc-ca" enctype="multipart/form-data">
					<div class="card-body">
						<c:if test="${typeFile eq 'pdf' }">
							<iframe src="/viewPdf" style="width: 100%;height: 400px;border: none;"></iframe>
						</c:if>
						<c:if test="${typeFile eq 'xml' }">
							<textarea readonly="readonly" style="width: 100%;height: 400px;border: none;">						
${xml }				
							</textarea>
						</c:if>
					</div>
					<!-- /.card-body -->
					<div class="card-footer">
						<a href="/xac-thuc/dieu-huong" class="btn btn-primary">Tiếp tục xác thực</a>
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
  		if(!checkExFile($("#exampleInputFile"))) {
  			e.preventDefault();
  		}
  	});
  	
  	$("#exampleInputFile").change(function () {
  		checkExFile($(this));
    });
  	
  	function checkExFile(obj) {
  		var fileExtension = ['xml', 'pdf'];
        if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1) {
        	alertER("Bạn chỉ có thể chọn file : "+fileExtension.join(', '));
        	return false;
        }
        return true;
  	}
});

</script>
<%@include file="../../layout/footer.jsp"%>