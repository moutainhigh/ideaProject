<!DOCTYPE html>
<html lang="en">

<head type="402880e3604021992ac3a50005index">
<#include "/base/base.ftl">
<link rel="icon" type="image/x-icon" href="${fileUrl}${cache_appconfig.extraParamConfig_managerSysLogo}" />
<input type="hidden" id="hiddenpermissions" value="${HRY_permissions}"></input>
<input type="hidden" id="menukey" value="${menukey}"></input>
<script type="text/javascript">
    var HRY_permissions = document.getElementById("hiddenpermissions").getAttribute("value");
    var HRY_menukey = document.getElementById("menukey").getAttribute("value");
</script>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>${cache_appconfig.extraParamConfig_managerName!"后台管理系统"}</title>



</head>

<style>

.testdiv{
	top: 50px; 
	height: 834px;
	width: 800px;
	background-color: #fffffe;
	position: absolute;/*这里使用了相对定位,left意思是在原来的位置向左移动多少*/  
    left: 100%;/*左侧坐标变大，向右移动*/   
    z-index:999999;
    display:none;
}

</style>

<style type="text/css">

    .hryUpload{
        text-align: center;
        width:500px;
        padding: 0px;
        border: 0px solid #666;
        margin: auto;
        position: relative;
        border-radius: 10px;
    }
    .hry_inputBox{
        width: 100%;
        height: 40px;
        border: 0px solid cornflowerblue;
        color: cornflowerblue;
        border-radius: 20px;
        position: relative;
        text-align: center;
        line-height: 40px;
        overflow: hidden;
        font-size: 16px;
    }
    .hry_inputBox input{
        width: 114%;
        height: 40px;
        opacity: 0;
        cursor: pointer;
        position: absolute;
        top: 0;
        left: -14%;
    }
    .hry_imgBox{
        text-align: left;
    }
    .imgContainer{
        display: inline-block;
        width: 100%;
        height: 150px;
        margin-left: 0%;
        border: 1px solid #666666;
        position: relative;
        margin-top: 5px;
        box-sizing: border-box;
    }
    .imgContainer img{
        width: 100%;
        height: 150px;
        cursor: pointer;
    }
    .imgContainer p{
        position: absolute;
        bottom: -1px;
        left: 0;
        width: 100%;
        height: 30px;
        background: black;
        text-align: center;
        line-height: 30px;
        color: white;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        display: none;
    }
    .imgContainer:hover p{
        display: block;
    }

</style>

<body class="theme-Blue">

	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top navbar-version1" role="navigation" style="margin-bottom: 0;width: 100%;position: fixed;top: 0;left: 0;right: 0;">

			<!-- /.navbar-header -->
			<#include "/base/top.ftl">
			<!-- /.navbar-top-links -->
			<!-- /.navbar-static-side -->
		</nav>
		<#include "/base/left.ftl">
		<!-- 菜单点击渲染区 -->
        <div id="page-wrapperbox" style="width: auto;position: absolute;top: 54px;left: 212px;bottom: 0px;right: 0px;overflow: visible;border-radius: 4px;    padding: 0 20px 0 0;">
			<div id="newsMenu">

			</div>
			<div id="page-wrapper"  style="" data-show-pageNum=""></div>
        </div>
		<!-- /#page-wrapper -->
		
		<div id="test_div" class="testdiv " >
		</div>
		
		
	</div>
	<!-- /#wrapper -->
	

<script type="text/javascript"  src="${ctx}/static/${version}/js/base/utils/permissions.js"></script>
<script type="text/javascript"  src="${ctx}/static/${version}/lib/seajs/sea.js"></script>
<script type="text/javascript">
 seajs.config({
    base: "${ctx}/static/${version}",
    alias: {
      <!-- 基础框架JS -->
      "jquery": "lib/jquery/jquery.js",
      "jqueryForm": "lib/jqueryForm/jquery.form.js",
        "ajaxfileupload": "lib/jquery/ajaxfileupload.js",
      "bootstrap": "lib/bootstrap/bootstrap.js",
      "metisMenu": "lib/sb_admin2/metisMenu.js",
      "sbadmin2": "lib/sb_admin2/sb-admin-2.js",
      <!-- layer -->
      "layer" : "lib/layer/layer.js",
      <!-- ztree -->
      "ztree" : "lib/ztree/jquery.ztree.all-3.5.min.js",
      
      <!-- 自定义JS -->
      "base": "js/base/base.js",
      "menu": "js/base/menu.js",
      "menuTop": "js/base/menuTop.js"
    },
    preload: ['jquery','jqueryForm','ajaxfileupload','layer'],
    map:[
		['.js','.js?v='+new Date().getTime()]//映射规则
	]
  });




 seajs.use(["js/main"],function(m){
	 m.indexInit();
 });
</script>
	
	
</body>

</html>



