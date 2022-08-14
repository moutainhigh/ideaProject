 <!-- Copyright:    -->
 <!-- IcoRuleDetailedList.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-12 18:38:38      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">平台发币管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                
            </div>
        </div>
        </form>
 	    <div id="toolbar">
             <button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/ico/rulesconfig/icoruledetailedadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="issue" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>发行
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


 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/rulesconfig/IcoRuleDetailed",function(o){
 	o.list();
 });

 </script>

