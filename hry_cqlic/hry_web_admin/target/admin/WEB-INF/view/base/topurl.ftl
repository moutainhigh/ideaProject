<#include "/base/base.ftl">
<!--左侧start-->
  	   <ul class="pull-left navbar-top-logo nav">
				<li>
				   <a class="navbar-logo-pic" href="${ctx}">
				   ${cache_appconfig.extraParamConfig_managerName!"后台管理系统"}<#--<span>V6.0</span>-->
				   </a>
				</li>
				<!--消息下拉框-->
				<!--<li class="dropdown" style="padding-left:76px;">
				    <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" style="padding: 14px 0px 6px;font-size: 18px;"> 
				    <i class="fa-ico-new fa-ico-new-bell" style="display: inline-block;width: 18px;height: 20px;"></i>
                     <span class="badge" style="position:relative;font-size: 12px;font-weight: 700;line-height: 1;color: #fff;top: -14px;left: -14px;text-align: center;display: inline-block;background:#fa4040;border-radius: 50%!important;padding: 2px 5px;">
                     5
                     </span>
					</a>
					<ul class="dropdown-menu dropdown-messages">
						<li>
						  <a class="text-center" href="javascript:;" onclick="loadUrl('${ctx}/v.do?u=/admin/cas/presslist')" >催收任务</a>
						</li>
						<li>
						  <a class="text-center" href="javascript:;" onclick="loadUrl('${ctx}/v.do?u=/admin/cas/casecheckaccountlist')" >财务查账</a>
						</li>
						<li>
						  <a class="text-center" href="javascript:;" onclick="loadUrl('${ctx}/v.do?u=/admin/pro/income/probalanceaccount')" >财务对账</a>
						</li>
					</ul>
				</li>-->
				
				<!--问题列表-->
               <!---<li>
				  <a href="javascript:;" onclick="loadUrl('${ctx}/v.do?u=/admin/sys/sysquestionlist')" class="dropdown-toggle" style="padding: 14px 0px 6px;font-size: 18px;">
                        <i class="fa-ico-new fa-ico-new-feedback" style="display: inline-block;width: 22px;height:20px;"></i>
                        <span class="badge" id="sysquestUnreadNum" style="position:relative;font-size: 12px;font-weight: 700;line-height: 1;color: #fff;top: -14px;left: -14px;text-align: center;display: inline-block;background:#ffb55c;border-radius: 50%!important;padding: 2px 5px;"></span>
                    </a>
				</li>--->
				<li id="fullScreen_con"><a id="toggle-full-screen"></a></li>
			</ul>
	  	   <!--右侧start-->
	  	   <div class="pull-right" id="">
	  	   <ul class="nav navbar-top-links navbar-right">
			
			<li class="dropdown">
			  <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="padding-left: 34px;position: relative;"> 
			    <i class="fa-ico-new fa-ico-new-defaultman"></i>&nbsp;${user.username}&nbsp;&nbsp;<i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">


                    <li><a href="javascript:void(0);" onclick="loadUrl('${ctx}/v.do?u=oauth/user/resetpw')";  ><i class="fa fa-user fa-fw"></i>修改密码</a></li>
					<li class="divider" ></li>

			        <#--<li><a onclick="loadUrl('${ctx}/v.do?u=admin/dic/appdictree')";  permissions="/superadmin" href="javascript:void(0);"><i class="fa fa-gear fa-fw"></i>数据字典</a></li>-->
					<#--<li class="divider" permissions="/superadmin"></li>-->
			        <#--<li><a onclick="loadUrl('${ctx}/v.do?u=oauth/menu/appmenulist')";  permissions="/superadmin" href="javascript:void(0);"><i class="fa fa-gear fa-fw"></i>菜单设置</a></li>-->
					<#--<li class="divider" permissions="/superadmin" ></li>-->

                    <li><a href="javascript:void(0);" onclick="loadUrl('${ctx}/customer/appcustomer/toCustomer.do?isReal=0')";  ><i class="fa fa-user fa-fw"></i>桌面首页</a></li>
                    <li class="divider" ></li>
			
					<li><a href="${ctx}/logout"><i class="fa fa-sign-out fa-fw"></i>退出</a></li>
				</ul> <!-- /.dropdown-user --></li>
		</ul>
		<ul class="nav" id="top_app" style="float:right;">
			<#--
            <li >
                <a appname="entrust" permissions="/entrust" href="javascript:void(0);">
                    <i class="glyphicon glyphicon-lock"></i>&nbsp;委托交易
                </a>
            </li>
            -->
    <#--        <li >
                <a appname="c2ctrade" permissions="/c2ctrade" href="javascript:void(0);">
                    <i class="glyphicon glyphicon-subtitles"></i>&nbsp;C2C
                </a>
            </li>
            <li >
                <a appname="lend" permissions="/lend" href="javascript:void(0);">
                    <i class="glyphicon glyphicon-subtitles"></i>&nbsp;杠杆
                </a>
            </li>

			<li >
				<a appname="otc" permissions="/otc" href="javascript:void(0);">
					<i class="glyphicon glyphicon-subtitles"></i>&nbsp;OTC
				</a>
			</li>-->
			<li >
				<a appname="wallet" permissions="/wallet" href="javascript:void(0);">
					<i class="glyphicon glyphicon-gift"></i>&nbsp;钱包
				</a>
			</li>

            <#--<li >
                <a appname="happyGo" permissions="/happyGo" href="javascript:void(0);">
                    <i class="glyphicon glyphicon-gift"></i>&nbsp;快乐购
                </a>
            </li>-->

			<li class="active">
				<a appname="admin" permissions="/admin" href="javascript:void(0);">
					<i class="glyphicon glyphicon-home"></i>&nbsp;客服
				</a>
			</li>

            <#--<li >
                <a appname="rule" permissions="/rule" href="javascript:void(0);">
                    <i class="glyphicon glyphicon-list-alt"></i>&nbsp;业务规则
                </a>
            </li>-->
			
			<li >
				<a appname="oauth" permissions="/oauth" href="javascript:void(0);">
					<i class="glyphicon glyphicon-globe   fa-fw"></i>&nbsp;全球化
				</a>
			</li>
			<li >
				<a appname="website" permissions="/website" href="javascript:void(0);">
					<i class="glyphicon glyphicon-flag"></i>&nbsp;站点
				</a>
			</li>
		</ul>
	   </div>
<script>
(function () {
    var viewFullScreen = document.getElementById("toggle-full-screen");
    if (viewFullScreen){
    	var toggleFullScreenNum = true;
        viewFullScreen.addEventListener("click", function () {
        	if(toggleFullScreenNum){
        		toggleFullScreenNum = false;
        		var docElm = document.documentElement;
	            if (docElm.requestFullscreen) {
	                docElm.requestFullscreen();
	            }
	            else if (docElm.msRequestFullscreen) {
	                docElm = document.body; //overwrite the element (for IE)
	                docElm.msRequestFullscreen();
	            }
	            else if (docElm.mozRequestFullScreen) {
	                docElm.mozRequestFullScreen();
	            }
	            else if (docElm.webkitRequestFullScreen) {
	                docElm.webkitRequestFullScreen();
	            }
	            
	            viewFullScreen.style.background = "url('" + _ctx + "/static/" + _version + "/lib/sb_admin2/images/toggleFullScreen02.png')center no-repeat"
	        }else{
	        	toggleFullScreenNum = true;
	        	var docElm = document.documentElement;
	            if (document.exitFullscreen) {
	                document.exitFullscreen();
	            }
	            else if (document.msExitFullscreen) {
	                document.msExitFullscreen();
	            }
	            else if (document.mozCancelFullScreen) {
	                document.mozCancelFullScreen();
	            }
	            else if (document.webkitCancelFullScreen) {
	                document.webkitCancelFullScreen();
	            }
	            viewFullScreen.style.background = "url('" + _ctx + "/static/" + _version + "/lib/sb_admin2/images/toggleFullScreen.png')center no-repeat"
	        };
        }, false);
    }
    
    //监听退出全屏事件
    window.onresize = function() {
        if (!checkFull()) {
        	toggleFullScreenNum = true;
        	viewFullScreen.style.background = "url('" + _ctx + "/static/" + _version + "/lib/sb_admin2/images/toggleFullScreen.png')center no-repeat"
        }
    }
    function checkFull() {
        var isFull = document.fullscreenEnabled || window.fullScreen || document.webkitIsFullScreen || document.msFullscreenEnabled;
        //to fix : false || undefined == undefined
        if (isFull === undefined) {isFull = false;}
        return isFull;
    }
})();

</script>





