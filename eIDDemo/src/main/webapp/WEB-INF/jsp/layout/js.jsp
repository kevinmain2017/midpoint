<%@ page contentType="text/html; charset=UTF-8"%>
<input type="hidden" value="${success}" id="success"/>
<input type="hidden" value="${error}" id="error"/>
<script type="text/javascript">
$(document).ready(function(){
	$('ul.pagination').on('click', 'a', function () {
        // Create paging action
        $('#action').val('paging');
        var value = $(this).attr('data-value');
        var $page = $('#page');
        switch (value) {
            case 'next':
                $page.val(parseInt($page.val()) + 1);
                break;
            case 'end':
                $page.val(totalPage);
                break;
            case 'prev':
                if((parseInt($page.val()) - 1) >= 1)
                    $page.val(parseInt($page.val()) - 1);
                break;
            case 'begin':
                $page.val(1);
                break;
            default:
                $page.val(value);
        }

        $("#submitForm").attr("method", "GET");
        $("#submitForm").submit();
    });
	if($("#success").val() != "") {
        $.growl.notice({
            title: "Thông báo",
            message: $("#success").val()
        });
    }
    if($("#error").val() != "") {
        $.growl.error({
            title: "Có lỗi xẩy ra",
            message: $("#error").val()
        });
    }
});
function loadAdd(urlAdd) {
	loadUrl(urlAdd);
}
function loadEdit(urlEdit) {
	loadUrl(urlEdit);
}
function loadUrl(url) {
	resetInfo();
	$.ajax({
		url : url,
		success : function(result) {
			$("#modal-content").html(result);
		}
	});
}
function resetInfo() {
	$("#error").val("");
	$("#success").val("");
	showWait();
}
function showWait() {
	$("#modal-content").html('Đang tải ...');
}
function alertSC(msg) {
	$.growl.notice({
        title: "Thông báo",
        message: msg
    });
}
function alertER(msg) {
	$.growl.error({
        title: "Thông báo",
        message: msg
    });
}
function deleteRC(url) {
	alertRC(url, "Bạn có chắc muốn xóa?");
}
function change(url) {
	alertRC(url, "Bạn có chắc muốn thay đổi?");
}
function alertRC(url, title) {
	swal({
		title: title,
        text: "",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "Đồng ý",
        cancelButtonText: "Thoát",
        closeOnConfirm: false
    }, function () {
    	location.href=url;
    });
}
function goBack() {
  	window.history.back();
}
</script>