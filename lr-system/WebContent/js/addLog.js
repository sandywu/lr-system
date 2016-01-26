

$(function(){
	var editor = $('#textarea1').wangEditor();
});

/**
 * 提示信息，内容长度判断
 */
var moodLength = 10;
$('#addlog_mood').bind('propertychange input', function() {
	if($('#addlog_mood').val().length >= moodLength) {	//长度大于等于规定长度
		//长度超出规定长度，截取规定长度内的字符串
		$('#addlog_mood').val($('#addlog_mood').val().substring(0,moodLength));	
	}  
});

var weatherLength = 10;
$('#addlog_weather').bind('propertychange input', function() {
	if($('#addlog_weather').val().length >= weatherLength) {	//长度大于等于规定长度
		//长度超出规定长度，截取规定长度内的字符串
		$('#addlog_weather').val($('#addlog_weather').val().substring(0,weatherLength));	
	}  
});

var placeLength = 10;
$('#addlog_place').bind('propertychange input', function() {
	if($('#addlog_place').val().length >= placeLength) {	//长度大于等于规定长度
		//长度超出规定长度，截取规定长度内的字符串
		$('#addlog_place').val($('#addlog_place').val().substring(0,placeLength));	
	}  
});

var titleLength = 20;
$('#addlog_title').bind('propertychange input', function() {
	if($('#addlog_title').val().length >= titleLength) {	//长度大于等于规定长度
		//长度超出规定长度，截取规定长度内的字符串
		$('#addlog_title').val($('#addlog_title').val().substring(0,titleLength));	
	}  
});


/**
 * 显示后台返回的提示信息
 */
var messge = document.getElementById('addLog_message').innerHTML;
if(messge == "") {
	document.getElementById('addLog_message').style.display = "none";
}
