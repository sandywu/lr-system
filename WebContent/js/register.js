/**
 * 邮箱校验
 */
var reg_email = document.getElementById('reg_email');
reg_email.onblur = function checkEmail() {
	ajax({
		method : 'get',
		url : 'checkEmail',
		data : {
			'email' : reg_email.value
		},
		success : function(text) {
			var reg_error = document.getElementById('reg_error');
			if (text == 1) { //邮箱已被注册
				var errorInfo = document.createTextNode("error：该邮箱已被注册，请换个邮箱试试！");//创建文本节点
				if(reg_error.innerHTML=='') {
					reg_error.appendChild(errorInfo);
				}
				reg_error.style.display = "block"; //显示错误提示信息
				document.getElementById('reg_sub').disabled = "disabled"; //注册按钮不可用
				//document.getElementById('reg_email').focus(); //邮箱输入框获取焦点
			}
			if (text == 0) { //邮箱未注册
				reg_error.innerHTML = ""; //将错误提示信息移除
				document.getElementById('reg_sub').disabled = ""; //注册按钮变为可编辑状态
				reg_error.style.display = "none"; //错误提示div隐藏
			}
		},
		async : true
	});
}

/**
 * 昵称校验
 */
var reg_nickName = document.getElementById('reg_nickName');
reg_nickName.onblur = function checkNickName() {
	ajax({
		method : 'get',
		url : 'checkNickName',
		data : {
			'nickName' : reg_nickName.value
		},
		success : function(text) {
			var reg_error = document.getElementById('reg_error');
			if (text == 1) { //邮箱已被注册
				var errorInfo = document
				.createTextNode("error：该昵称已被占用，请换个昵称试试！");//创建文本节点
				if(reg_error.innerHTML=='') {
					reg_error.appendChild(errorInfo);
				}
				reg_error.style.display = "block"; //显示错误提示信息
				document.getElementById('reg_sub').disabled = "disabled"; //注册按钮不可用
				//document.getElementById('reg_nickName').focus(); //昵称输入框获取焦点
			}
			if (text == 0) { //邮箱未注册
				reg_error.innerHTML = ""; //将错误提示信息移除
				document.getElementById('reg_sub').disabled = ""; //注册按钮变为可编辑状态
				reg_error.style.display = "none"; //错误提示div隐藏
			}
		},
		async : true
	});
}

/**
 * 邮箱，内容长度判断
 */
$('#reg_email').bind('propertychange input', function() {
	if($('#reg_email').val().length >= 20) {	//长度大于等于10
		//长度超出规定长度，截取规定长度内的字符串
		$('#reg_email').val($('#reg_email').val().substring(0,20));	
	}  
});

/**
 * 昵称，内容长度判断
 */
$('#reg_nickName').bind('propertychange input', function() {
	if($('#reg_nickName').val().length >= 10) {	//长度大于等于10
		//长度超出规定长度，截取规定长度内的字符串
		$('#reg_nickName').val($('#reg_nickName').val().substring(0,10));	
	}  
});

/**
 * 问题，内容长度判断
 */
$('#qeustion').bind('propertychange input', function() {
	if($('#qeustion').val().length >= 24) {	//长度大于等于10
		//长度超出规定长度，截取规定长度内的字符串
		$('#qeustion').val($('#qeustion').val().substring(0,24));	
	}  
});

/**
 * 答案，内容长度判断
 */
$('#answer').bind('propertychange input', function() {
	if($('#answer').val().length >= 24) {	//长度大于等于10
		//长度超出规定长度，截取规定长度内的字符串
		$('#answer').val($('#answer').val().substring(0,24));	
	}  
});

/**
 * 显示后台返回的提示信息
 */
var messge = document.getElementById('reg_message').innerHTML;
if(messge != "") {
	alert(messge);
	document.getElementById('reg_message').innerHTML = "";
}