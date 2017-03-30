/**
 * 邮箱校验
 */
var reg_email = document.getElementById('email');
reg_email.onblur = function checkEmail() {
	ajax({
		method : 'get',
		url : 'checkEmail',
		data : {
			'email' : reg_email.value
		},
		success : function(text) {
			if (text == 1) { //邮箱已被注册
				ajax({
					method : 'get',
					url : 'getQuestion',
					data : {
						'email' : reg_email.value
					},
					success : function(text) {
						$('#email').val(reg_email.value);
						$('#email_box').css("display","none");//邮箱隐藏
						$('#qeustion_box').css("display","block");//问题出现
						$('#qeustion').val(text);
						$('#answer_box').css("display","block");//回答出现
					},
					async : true
				});
				
			}
			if (text == 0) { //邮箱未注册
				alert("该邮箱未注册，请重新填写！");
			}
		},
		async : true
	});
}

/**
 * 答案校验
 */
var answer = document.getElementById('answer');
answer.onblur = function checkEmail() {
	ajax({
		method : 'get',
		url : 'checkAnswer',
		data : {
			'email' : reg_email.value ,
			'answer' : answer.value
		},
		success : function(text) {
			if (text == 1) { //答案正确
				$('#qeustion_box').css("display","none");//问题隐藏
				$('#answer_box').css("display","none");//回答隐藏
				
				$('#password_box').css("display","block");//新密码框出现
				$('#password2_box').css("display","block");//确认新密码框出现
				$('#but_box').css("display","block");//提交按钮出现
			}
			if (text == 0) { //答案错误
				alert("答案错误！请重新填写！");
			}
		},
		async : true
	});
}

/**
 * 邮箱，内容长度判断
 */
$('#email').bind('propertychange input', function() {
	if($('#email').val().length >= 20) {	//长度大于等于10
		//长度超出规定长度，截取规定长度内的字符串
		$('#email').val($('#reg_email').val().substring(0,20));	
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

function checkPwd() {
	if($('#password').val() != $('#password2').val()) {
		alert("新密码与确认密码不一致，请重新填写！");
		return false;
	}
	return true;
}

/**
 * 显示后台返回的提示信息
 */
var messge = document.getElementById('reg_message').innerHTML;
if(messge != "") {
	alert(messge);
	document.getElementById('reg_message').innerHTML = "";
}