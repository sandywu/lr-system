$(function () {
	
	$('#tabs').tabs({
		fit : true,
		border : false,
	});
	
	$('#manageUser').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true,
				content:'<iframe name="manageUser" frameborder="0" width="100%" height="100%"/>'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('#manageLog').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true,
				content:'<iframe name="manageLog" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('#manageTODO').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true, 
				content:'<iframe name="manageTODO" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('#manageSaid').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true, 
				content:'<iframe name="manageSaid" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	$('#manageStmMsg').click(function(){
		var text=$(this).text();
		var href=$(this).attr('title');
		if(!$('#tabs').tabs('exists',text)){
			$('#tabs').tabs('add',{
				title:text,
				closable:true, 
				content:'<iframe name="manageStmMsg" frameborder="0" width="100%" height="100%" />'
			});
		}else{
			$('#tabs').tabs('select',text);
		}
	});
	
	
});