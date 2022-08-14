 <!-- Copyright:    -->
 <!-- ExCointoCoinList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:52:02      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">委托交易-交易对回收站</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">

 	    <div id="toolbar">

             <button id="close" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/open">
 	            <i class="glyphicon glyphicon-retweet"></i>  还原
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
 	           data-page-list="[ 50, 100]"
 	           data-show-footer="false"  
 	           data-side-pagination="server"
               data-page-size="50"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>
</div>
 <script type="text/javascript">
 seajs.use(["js/admin/exchange/ExCointoCoin","js/base/HrySelect"],function(o,HrySelect){
     HrySelect.init();
 	o.trashlist();
 });

 </script>

