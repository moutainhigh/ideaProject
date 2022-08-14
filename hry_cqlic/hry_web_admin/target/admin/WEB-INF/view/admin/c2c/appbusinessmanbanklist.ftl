 <!-- Copyright:    -->
 <!-- appBusinessmanBankList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:09      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">交易商银行卡管理</h3>
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
             <button id="add" class="btn  btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/appbusinessmanbankadd')" permissions="/admin/c2c/appbusinessmanbank/add" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>

             <button id="modify" class="btn  btn-info-blue" permissions="/admin/c2c/appbusinessmanbank/modify"  >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn  btn-info-blue"   permissions="/admin/c2c/appbusinessmanbank/delete" >
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
 seajs.use("js/admin/c2c/appBusinessmanBank",function(o){
 	o.list();
 });

 </script>

