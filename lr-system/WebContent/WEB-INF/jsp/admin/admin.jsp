<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>管理页</title>
	<meta charset="UTF-8"/>
	<link rel="shortcut icon" href="./img/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="./../easyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="./../easyui/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="./../css/admin.css" />

</head>
<body class="easyui-layout">

	<div data-options="region:'north',title:'header',split:true,noheader:true" class="top">
		<div class="logo">左右微博客后台管理</div>
		<div class="logout">您好，${sessionScope.user.nickName} | <a href="quit">退出</a></div>
	</div>   

	<div data-options="region:'south',title:'footer',split:true,noheader:true" class="footer">
		Copyright &copy; 2016 <a href="#">SHANKS</a>
	</div>

	<div data-options="region:'west',title:'导航',split:true,iconCls:'icon-world'" class="left">
		<div id="aa" class="easyui-accordion" data-options="fit:true">   
			<div title="管理列表" data-options="iconCls:'icon-save'">   
				<div id="menu">
					<ul>
						<li><a name="manageUser" href="manageUser" target="manageUser">用户管理</a></li>
						<li><a title="shangp" href="#">商品管理</a></li>
						<li><a title="quanxian" href="#">权限管理</a></li>
					</ul>
				</div>
			</div>   
			<div title="其他">   
			content2
			</div>
		</div> 
	</div>   
	<div data-options="region:'center'" class="right">
		<div id="tabs">
			<div title="起始页" iconCls="icon-house" class="ind">
				欢迎来到后台管理系统！
			</div>
		</div>
	</div> 

<script type="text/javascript" src="./../easyui/jquery.min.js"></script>
<script type="text/javascript" src="./../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./../easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="./../js/admin_js/admin.js"></script>
</body>
</html>