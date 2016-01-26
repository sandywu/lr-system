/**
 * 提示信息，内容长度判断
 */
var length = $('#todoLength').text(); //计划规定的最大长度
$('#todoContent').bind('propertychange input', function() {
	$('#todoLength').css('color','green'); 	//提示信息变为绿色
	$('#todoLength').html(length - $('#todoContent').val().length);	//提示个数
	if($('#todoContent').val().length >= length) {	//长度大于等于规定长度
		$('#todoLength').css('color','red'); 	//提示信息变为红色
		$('#todoLength').html(0);	//提示信息变为0
		//长度超出规定长度，截取规定长度内的字符串
		$('#todoContent').val($('#todoContent').val().substring(0,length));	
	}  
});

/**
 * 显示后台返回的提示信息
 */
var messge = document.getElementById('manageBlog_message').innerHTML;
document.getElementById('manageBlog_message').style.display =  messge == "" ? "none" : "block";
