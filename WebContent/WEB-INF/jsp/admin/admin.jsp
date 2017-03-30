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
						<li><a id="manageUser" href="mannageUser?thisPage=1" target="manageUser">用户管理</a></li>
						<li><a id="manageLog" href="manageLog?thisPage=1" target="manageLog">日记管理</a></li>
						<li><a id="manageTODO" href="manageTODO?thisPage=1" target="manageTODO">TODO管理</a></li>
						<li><a id="manageSaid" href="manageSaid?thisPage=1" target="manageSaid">说说管理</a></li>
						<li><a id="manageStmMsg" href="manageStmMsg?thisPage=1" target="manageStmMsg">系统消息</a></li>
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
				欢迎来到左右微博客后台管理系统！
			</div>
		</div>
	</div> 

<script type="text/javascript" src="./../easyui/jquery.min.js"></script>
<script type="text/javascript" src="./../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="./../easyui/locale/easyui-lang-zh_CN.js" ></script>
<script type="text/javascript" src="./../js/admin_js/admin.js"></script>
</body>
</html>