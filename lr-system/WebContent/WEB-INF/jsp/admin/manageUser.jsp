<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./../easyui/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="./../easyui/themes/icon.css" />
</head>
<style>
body{
	margin: 0;
	padding: 0;
}
.textbox {
	height: 20px;
	margin: 0;
	padding: 0 2px;
	box-sizing: content-box;
}
</style>
<body>
	<table id="box"></table>

	<div id="tb" style="padding: 5px;">
		<div style="margin-bottom: 5px;">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
				onclick="obj.add();">添加</a> <a href="#" class="easyui-linkbutton"
				iconCls="icon-edit" plain="true" onclick="obj.edit();">修改</a> <a
				href="#" class="easyui-linkbutton" iconCls="icon-remove"
				plain="true" onclick="obj.remove();">删除</a> <a href="#"
				class="easyui-linkbutton" iconCls="icon-save" plain="true"
				style="display: none;" id="save" onclick="obj.save();">保存</a> <a
				href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true"
				style="display: none;" id="redo" onclick="obj.redo();">取消编辑</a>
		</div>
		<div style="padding: 0 0 0 7px; color: #333;">
			查询帐号：<input type="text" class="textbox" name="user"
				style="width: 110px"> 创建时间从：<input type="text"
				name="date_from" class="easyui-datebox" editable="false"
				style="width: 110px"> 到：<input type="text" name="date_to"
				class="easyui-datebox" editable="false" style="width: 110px">
			<a href="#" class="easyui-linkbutton" iconCls="icon-search"
				onclick="obj.search();">查询</a>
		</div>
	</div>

	<div id="menu" class="easyui-menu" style="width: 120px; display: none;">
		<div onclick="" iconCls="icon-add">增加</div>
		<div onclick="" iconCls="icon-remove">删除</div>
		<div onclick="" iconCls="icon-edit">修改</div>
	</div>




	<script type="text/javascript" src="./../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="./../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="./../easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="./../js/admin_js/manageUser.js"></script>
</body>
</html>
