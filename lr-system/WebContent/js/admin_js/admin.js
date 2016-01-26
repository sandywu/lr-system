$(function () {
	
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
	
	$('a[name="manageUser"]').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true,
				content:'<iframe name="manageUser" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('a[title="shangp"]').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true,
				content:'<iframe src="" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('a[title="quanxian"]').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true, 
				content:'<iframe src="" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
});