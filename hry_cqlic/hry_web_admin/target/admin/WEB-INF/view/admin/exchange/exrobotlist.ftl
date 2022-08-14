 <!-- Copyright:    -->
 <!-- ExRobotList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">K线机器人</h3>
     </div>
 </div>
 <div class="row">
			<div class="_params">
	 		</div>
 </div>
 <div class="row">
 	<div class="col-md-12">
 		<div class="_params">
 			
 		</div>
 	    <div id="toolbar">
             <button id="add" class="btn  btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotadd')" permissions="/admin/exchange/exrobot/add">
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>

             <button id="modify" class="btn  btn-info-blue" permissions="/admin/exchange/exrobot/modify">
 	            <i class="glyphicon glyphicon-edit"></i>修改
 	        </button>
             <button id="remove" class="btn  btn-info-blue" permissions="/admin/exchange/exrobot/remove">
 	            <i class="glyphicon glyphicon-remove"></i>删除
 	        </button>
            <button id="open" class="btn  btn-info-blue" permissions="/admin/exchange/exrobot/open">
                <i class="glyphicon glyphicon-edit"></i>开启交易
            </button>
            <button id="close" class="btn  btn-info-blue" permissions="/admin/exchange/exrobot/close">
                <i class="glyphicon glyphicon-remove"></i>关闭交易
            </button>
            <button id="test" class="btn  btn-info-blue" permissions="/admin/exchange/exrobot/test">
                <i class="glyphicon glyphicon-edit"></i>测试接口
            </button>
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="true"
 	           data-show-columns="false"
 	           data-show-export="false"
 	           data-search="false"
 	           data-detail-view="false"
 	           data-minimum-count-columns="2"
 	           data-pagination="true"
 	           data-id-field="id"
 	           data-page-list="[10, 25, 50, 100]"
 	           data-show-footer="false"  
 	           data-side-pagination="server"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>

	<#--开启交易时输入密码-->
    <div id="opentrade" style="display:none; top:20px;">
        <form style="text-align: center;">
            <input type="hidden" id="ids">
            <input type="password" id="password" placeholder="请输入交易账号的登录密码" style="text-align: center;margin-top: 20px;"/>
            <div class="col-md-2 column" style="margin: 0px 135px -100px;">
                <button type="button" id="confirmOpen" class="btn  btn-info-blue btn-warning"
                        style="margin-top: 18px;margin-left: 8px;">提交
                </button>
            </div>
        </form>
    </div>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExRobot",function(o){
 	o.list();
 });

 </script>

