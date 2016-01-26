$(function() {
	var date = new Date();
	var hours = 23 - date.getHours();
	var minutes = 60 - date.getMinutes();
	var txt = "<span>" + hours + '小时' + minutes + '分钟' + " </span>";
	$('#time').after(txt);
	
	/**
	 * 提示信息，内容长度判断
	 */
	var length = $('#saidLength').text(); //说说规定的最大长度
	$('#saidContent').bind('propertychange input', function() {
		$('#saidLength').css('color','green'); 	//提示信息变为绿色
		$('#saidLength').html(length - $('#saidContent').val().length);	//提示个数
		if($('#saidContent').val().length >= length) {	//长度大于等于规定长度
			$('#saidLength').css('color','red'); 	//提示信息变为红色
			$('#saidLength').html(0);	//提示信息变为0
			//长度超出规定长度，截取规定长度内的字符串
			$('#saidContent').val($('#saidContent').val().substring(0,length));	
		}  
		
	});
	
	/**
	 * 后台返回的提示信息
	 */
	if($('#uindex_message').text() != "") {
		alert($('#uindex_message').text());
		$('#uindex_message').val("");
	}
	
	
});