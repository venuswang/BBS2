$(function(){
	var $form = $('#login-form'),
		$result = $('#login-result'),
		$btnClose =$('.btn-close');
	(function(){
		var winH = $(window).height(),
			formH = $form.height(),
			formW = $form.width();
		$form.css({
			marginLeft: -(formW / 2),
			top: ( winH / 2 - formH / 2 )
		});
	})();
	//表单验证 
	$form.validate({
		rules:{
			username: {
				required: true,
				rangelength: [6, 30]
			},
			password: {
				required: true,
				rangelength: [6, 16]
			}
		},
		messages: {
			username: {
				required: "请输入用户名",
				rangelength: "用户名为6-30位"
			},
			password: {
				required: "请输入密码",
				rangelength: "密码长度为6-16位"
			}
		},
		focusInvalid: true,
		errorClass: 'loginError',
		validClass: 'loginPass',
		errorElement: 'i',
		errorContainer: true
	});

	// 提交表单
	$('#submit').bind('click', function(event) {
		if ($('#login-form').valid()) {
			$('#login-form').submit();
		}
		return false;
	});

	// 关闭登录错误提示
	$btnClose.on("click", function(){
		$result.hide();
	});
	$result.on('click', function(){
		$(this).hide();
	});
});