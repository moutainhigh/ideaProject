 <!-- Copyright:    -->
 <!-- IcoAccountList.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 12:02:43      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">平台支出台账</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
		<div class="_params">
			<form id="table_query_form">
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="coinCode">币种名称</label>
						<input type="text" class="form-control" tablesearch name="coinCode"/>
					</div>
				</div>

				<div class="col-md-2 column">
					<div style="margin-top: 26px;">
						<button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
						<button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
					</div>
				</div>
			</form>
		</div>

 	    <div id="toolbar">

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


 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/account/IcoAccount",function(o){
 	o.expenditureAccount();
 });

 </script>

