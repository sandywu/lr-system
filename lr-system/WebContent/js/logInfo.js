/**
 * 给日记点赞
 */
var praise = document.getElementById('praise');
praise.onclick = function checkEmail() {
	var whoPraiseId = (praise.name).split(",")[0];
	var praiseLogId = (praise.name).split(",")[1];
	ajax({
		method : 'get',
		url : 'praiseLog',
		data : {
			'whoPraiseId' : whoPraiseId,
			'praiseLogId' : praiseLogId
		},
		success : function(text) {
			if(text == 0) {	//点赞失败
				alert("发生未知错误，点赞失败！");
			}
			if(text == 1) {	//点赞成功
				var praiseNumber = parseInt(document.getElementById('praiseNumber').innerHTML);
				document.getElementById('praiseNumber').innerHTML = praiseNumber + 1;
			}
			if(text == 2) {	//重复点赞
				var praiseNumber = parseInt(document.getElementById('praiseNumber').innerHTML);
				document.getElementById('praiseNumber').innerHTML = praiseNumber - 1;
			}
		},
		async : true
	});
}

/**
 * 举报日记
 */
var sub = document.getElementById('sub');
sub.onclick = function reportLog() {
	var logId = document.getElementById('logId').value;
	var reason = document.getElementById('reportContent').value;
	ajax({
		method : 'get',
		url : 'checkReport',
		data : {
			'logId' : logId
		},
		success : function(text) {
			if(text == 0) {	//可以举报
				ajax({
					method : 'get',
					url : 'addReport',
					data : {
						'logId' : logId ,
						'reason' : reason
					},
					success : function(text) {
						if(text == 0) {	//举报失败
							alert("发生未知错误，举报该日记失败，请重新举报！");
						}
						if(text == 1) {	//举报成功
							alert("感谢您对本网站良好环境的维护，我们将尽快处理被举报的日记！");
						}
					},
					async : true
				});
			}
			if(text == 1) {	//已经举报过了
				alert("一个人不可对同一篇日记举报多次！");
			}
		},
		async : true
	});
}

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
 * 删除前提醒
 * @returns {Boolean}
 */
function del() {
	if (!confirm("您确认要删除该条记录吗！删除的记录将无法找回！！！")) {
		return false;
	}
}