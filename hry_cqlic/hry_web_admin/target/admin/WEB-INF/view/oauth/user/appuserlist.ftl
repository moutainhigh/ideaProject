 <!-- Copyright:   互融云 -->
 <!-- AppUserList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header"><span id="organizationName"></span>用户管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 	    <div id="toolbar">
             <button id="add" class="btn  btn-info-blue" onclick="loadUrl2Div(_ctx+'/v.do?u=/oauth/user/appuseradd','tree_right')" permissions="/oauth/user/appuser/add" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="see" class="btn btn-info-blue"  permissions="/oauth/user/appuser/see">
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>
             <button id="modify" class="btn  btn-info-blue" permissions="/oauth/user/appuser/modify" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
			<button id="modifyOrganization" class="btn  btn-info-blue" permissions="/oauth/user/appuser/modifyOrganization" >
 	            <i class="glyphicon glyphicon-edit"></i>部门调动
 	        </button>
			<button id="resetpw" class="btn  btn-info-blue" permissions="/oauth/user/appuser/resetpw" >
 	            <i class="glyphicon glyphicon-edit"></i>重置密码
 	        </button>
             <button id="remove" class="btn  btn-info-blue" permissions="/oauth/user/appuser/remove" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
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

 <script type="text/javascript">
 seajs.use("js/oauth/user/AppUser",function(o){
	//获得组织机构id
 	var organizationid = $("#tree_selected_id").val();
 	$("#organizationName").html("【"+$("#tree_selected_name").val()+"】");
 	o.list(organizationid);
 });

 </script>

