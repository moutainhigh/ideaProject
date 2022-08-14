 <!-- Copyright:    -->
 <!-- chatRecordList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-28 16:29:34      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
		 <div class="col-lg-12">
			 <h3 class="page-header">聊天记录</h3>
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
			  <#--   <button id="add" class="btn btn-success" onclick="loadUrl('${ctx}/v.do?u=/admin/web/chatrecordadd')" >
					<i class="glyphicon glyphicon-plus"></i>添加
				</button>
				 <button id="see" class="btn btn-info" >
					<i class="glyphicon glyphicon-share"></i>查看
				</button>
				 <button id="modify" class="btn btn-warning" >
					<i class="glyphicon glyphicon-edit"></i>编辑
				</button>-->
				 <button id="remove" class="btn btn-info-blue" >
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
 </div>
 <script type="text/javascript">
 seajs.use("js/admin/web/chatRecord",function(o){
 	o.list();
 });

 </script>

