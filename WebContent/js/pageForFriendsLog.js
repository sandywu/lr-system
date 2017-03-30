


var thisPage = $('#thisPage').text();
var pageSize = $('#pageSize').text();
var url = $('#first a').attr("href").split('=')[0]+"=";
var suf = "&whoCreate=" + $('#first a').attr("href").split('=')[2];
/**
 * 初始化页码
 */
if(pageSize > 5) {	//总页数 > 5
	if(thisPage < 4) {	//当前页为前三页
		for(var i=5;i>1;i--) {
			var txt = "<li><a href="+ (url+i) +suf+">"+ i +"</a></li>";
			$('#first').after(txt);
		}
	} else {	//当前页大于三页
		if(parseInt(thisPage)+2 <= pageSize) {	//当前页+2 <= 总页数
			for(var i=parseInt(thisPage)+2;i>parseInt(thisPage)-3;i--) {
				var txt = "<li><a href="+ (url+i) +suf+">"+ i +"</a></li>";
				$('#first').after(txt);
			} 
		} else {	//当前页+2 > 总页数
			for(var i=pageSize;i>parseInt(thisPage)-3;i--) {
				var txt = "<li><a href="+ (url+i) +suf+">"+ i +"</a></li>";
				$('#first').after(txt);
			}
		}
		
	}
}else{		//总页数 < 5
	for(var i=pageSize;i>1;i--) {
		var txt = "<li><a href="+ (url+i) +suf+">"+ i +"</a></li>";
		$('#first').after(txt);
	}
}

/**
 * 当前为第一页
 */
if(thisPage == 1) {			//当前为第一页a_Previous
	$('#Previous').addClass("disabled");
	$('#a_Previous').attr("href",url+(parseInt(thisPage))+suf);
	$('#first').addClass("active");
	$('#a_Next').attr("href",url+(parseInt(thisPage)+1)+suf);
}

/**
 * 当前页为最后一个
 */
if(thisPage == pageSize) {	//当前页为最后一个
	$('#Next').addClass("disabled");
	$('#a_Previous').attr("href",url+(parseInt(thisPage)-1)+suf);
	$('#a_Next').attr("href",url+(parseInt(thisPage))+suf);
	activePage();
}

if(pageSize == 1) { //总页数为1
	$('#a_Previous').attr("href",url+(parseInt(thisPage))+suf);
	$('#a_Next').attr("href",url+(parseInt(thisPage))+suf);
}

/**
 * 当前页既不是第一页也不是最后一页
 */
if(thisPage != 1 && thisPage != pageSize) {
	$('#a_Previous').attr("href",url+(parseInt(thisPage)-1)+suf);
	$('#a_Next').attr("href",url+(parseInt(thisPage)+1)+suf);
	activePage();
}

/**
 * 设置当前活动页
 */
function activePage() {
	for(var i=0;i<$('#pagination a').length;i++) {
		var val = $('#pagination a')[i].innerHTML;
		if(val == thisPage) {
			$('#pagination a')[i].parentNode.setAttribute("class", "active");
		}
	}
}