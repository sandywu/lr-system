//扩展 dateTimeBox
$.extend($.fn.datagrid.defaults.editors, {
	datetimebox : {
		init: function(container, options){
			var input = $('<input type="text">').appendTo(container);
			options.editable = false;
			input.datetimebox(options);
			return input;
		},
		getValue: function(target){
			return $(target).datetimebox('getValue');
		},
		setValue: function(target, value){
			$(target).datetimebox('setValue', value);
		},
		resize: function(target, width){
			$(target).datetimebox('resize', width);
		},
		destroy : function (target) {
			$(target).datetimebox('destroy');
		},
	}
});

$(function () {

	obj = {
		editRow : undefined,
		search : function () {
			$('#box').datagrid('load', {
				user : $.trim($('input[name="user"]').val()),
				date_from : $('input[name="date_from"]').val(),
				date_to : $('input[name="date_to"]').val(),
			});
		},
		add : function () {
			$('#save,#redo').show();
			/*
			//当前页行结尾添加
			$('#box').datagrid('appendRow', {
				user : 'bnbbs',
				email : 'bnbbs@163.com',
				date : '2014-11-11',
			});
			*/
			
			if (this.editRow == undefined) {
				//添加一行 
				$('#box').datagrid('insertRow', {
					index : 0,
					row : {
						/*
						user : 'bnbbs',
						email : 'bnbbs@163.com',
						date : '2014-11-11',
						*/
					},
				});
				
				//将第一行设置为可编辑状态
				$('#box').datagrid('beginEdit', 0);
				
				this.editRow = 0;
			}
		},
		save : function () {
			//这两句不应该放这里，应该是保存成功后，再执行
			//$('#save,#redo').hide();
			//this.editRow = false;
			//将第一行设置为结束编辑状态
			$('#box').datagrid('endEdit', this.editRow);
		},
		redo : function () {
			$('#save,#redo').hide();
			this.editRow = undefined;
			$('#box').datagrid('rejectChanges');
		},
		edit : function () {
			var rows = $('#box').datagrid('getSelections');
			if (rows.length == 1) {
				if (this.editRow != undefined) {
					$('#box').datagrid('endEdit', this.editRow);
				}
			
				if (this.editRow == undefined) {
					var index = $('#box').datagrid('getRowIndex', rows[0]);
					$('#save,#redo').show();
					$('#box').datagrid('beginEdit', index);
					this.editRow = index;
					$('#box').datagrid('unselectRow', index);
				}
			} else {
				$.messager.alert('警告', '修改必须或只能选择一行！', 'warning');
			}
		},
		remove : function () {
			var rows = $('#box').datagrid('getSelections');
			if (rows.length > 0) {
				$.messager.confirm('确定操作', '您正在要删除所选的记录吗？', function (flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i ++) {
							ids.push(rows[i].id);
						}
						console.log(ids.join(','));
						$.ajax({
							type : 'POST',
							url : 'delete',
							data : {
								ids : ids.join(','),
							},
							beforeSend : function () {
								$('#box').datagrid('loading');
							},
							success : function (data) {
								if (data) {
									$('#box').datagrid('loaded');
									$('#box').datagrid('load');
									$('#box').datagrid('unselectAll');
									$.messager.show({
										title : '提示',
										msg : data + '个用户被删除成功！',
									});
								}
							},
						});
					}
				});
			} else {
				$.messager.alert('提示', '请选择要删除的记录！', 'info');
			}
		},
	};

	$('#box').datagrid({
		width : '100%',
		url : 'manageUser',
		title : '用户列表',
		iconCls : 'icon-search',
		striped : true,
		nowrap : true,
		rownumbers : true,
		//singleSelect : true,
		
		fitColumns : true,
		columns : [[
			{
				field : 'id',
				title : '编号',
				sortable : true,
				width : 100,
				checkbox : true,
			},
			{
				field : 'nickName',
				title : '昵称',
				sortable : true,
				width : 100,
				editor : {
					type : 'validatebox',
					options : {
						required : true,
					},
				},
				formatter : function (value, rowData, rowIndex) {
					return '[' + value + ']';
				},
			},
			{
				field : 'integral',
				title : '积分',
				sortable : true,
				width : 100,
				editor : {
					type : 'validatebox',
					options : {
						required : true,
					},
				},
				formatter : function (value, rowData, rowIndex) {
					return '[' + value + '分'+']';
				},
			},
			{
				field : 'email',
				title : '邮箱',
				sortable : true,
				width : 100,
				editor : {
					type : 'validatebox',
					options : {
						required : true,
						validType : 'email',
					},
				},
			},
			{
				field : 'createTime',
				title : '注册时间',
				sortable : true,
				width : 100,
				editor : {
					type : 'datetimebox',
					options : {
						required : true,
					},
				},
			},
			
		]],
		toolbar : '#tb',
		pagination : true,
		pageSize : 10,
		pageList : [10, 20, 30],
		pageNumber : 1,
		sortName : 'date',
		sortOrder : 'DESC',
		onDblClickRow : function (rowIndex, rowData) {
		
			if (obj.editRow != undefined) {
				$('#box').datagrid('endEdit', obj.editRow);
			}
		
			if (obj.editRow == undefined) {
				$('#save,#redo').show();
				$('#box').datagrid('beginEdit', rowIndex);
				obj.editRow = rowIndex;
			}
			
		},
		onAfterEdit : function (rowIndex, rowData, changes) {
			$('#save,#redo').hide();
			
			var inserted = $('#box').datagrid('getChanges', 'inserted');
			var updated = $('#box').datagrid('getChanges', 'updated');
			
			var url = info =  '';
			
			//新增用户
			if (inserted.length > 0) {
				url = 'add';
				info = '新增';
			}
			
			//修改用户
			if (updated.length > 0) {
				url = 'update';
				info = '修改';
			}
			
			$.ajax({
				type : 'POST',
				url : url,
				data : {
					row : rowData,
				},
				beforeSend : function () {
					$('#box').datagrid('loading');
				},
				success : function (data) {
					if (data) {
						$('#box').datagrid('loaded');
						$('#box').datagrid('load');
						$('#box').datagrid('unselectAll');
						$.messager.show({
							title : '提示',
							msg : data + '个用户被' + info + '成功！',
						});
						obj.editRow = undefined;
					}
				},
			});
			console.log(rowData);
		},
		
		onRowContextMenu : function (e, rowIndex, rowData) {
			e.preventDefault();
			//console.log(rowIndex);
			//console.log(rowData);
			$('#menu').menu('show', {
				left : e.pageX,
				top : e.pageY,
			});
		},
		
	});
	
	
	
});


	









