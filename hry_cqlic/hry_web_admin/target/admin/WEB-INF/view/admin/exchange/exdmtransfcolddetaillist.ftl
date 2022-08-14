 <!-- Copyright:    -->
 <!-- ExDmTransfColdDetailList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-27 18:01:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">归集币种冷账户明细</h3>
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
             <#--<button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exdmtransfcolddetailadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>转入线下
 	        </button>-->

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
 seajs.use("js/admin/exchange/ExDmTransfColdDetail",function(o){
 	o.list();
 });

 </script>

