	/**
	 * 登录邮箱校验
	 */
	var log_img = document.getElementById('log_img');
	var log_email = document.getElementById('log_email');
	var log_password = document.getElementById('password');
	log_email.onfocus = function inpEmail() {	//邮箱获取焦点
		log_img.src = "./img/username.gif";
	}
	log_password.onfocus = function inpEmail() {	//密码框获取焦点
		log_img.src = "./img/password.gif";
	}
	log_password.onblur = function remvPassword() {	//密码框失去焦点
		log_img.src = "./img/beforelogin.gif";
	}
	log_email.onblur = function checkEmail() {	//邮箱失去焦点
		log_img.src = "./img/beforelogin.gif";
		//log_email.onfocus = null;	//释放内存
		ajax({
			method : 'get',
			url : 'checkEmail',
			data : {
				'email' : log_email.value
			},
			success : function(text) {
				var log_message = document.getElementById('log_message');
				if (text == 0) { //邮箱未注册
					if(log_message.innerHTML == ""){
						var errorInfo = document.createTextNode("该邮箱未注册");//创建文本节点
						log_message.appendChild(errorInfo);
						log_message.style.display = "block"; //显示错误提示信息
						document.getElementById('log_sub').disabled = "disabled"; //注册按钮不可用
						document.getElementById('log_email').focus(); //邮箱输入框获取焦点
					}
					
				}
				if (text == 1) { //邮箱已被注册
					log_message.innerHTML = ""; //将错误提示信息移除
					document.getElementById('log_sub').disabled = ""; //注册按钮变为可编辑状态
					log_message.style.display = "none"; //错误提示div隐藏
				}
			},
			async : true
		});
	}

	/**
	 * 显示后台返回的提示信息
	 */
	var messge = document.getElementById('log_error').innerHTML;
	if(messge != "") {
		alert(messge);
		document.getElementById('log_error').innerHTML = "";
	}
	
	
	