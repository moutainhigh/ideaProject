 <!-- Copyright:    -->
 <!-- KlgAmountLimitationModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 16:55:42      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">新人总额度限制  </h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
     <input type="hidden" class="form-control" name="type" id="type" value="${model.type}"/>
     <div class="row">
	<div class="col-md-4 column">
	    <div class="form-group">
			 <label for="state">是否限额</label>
             <@HrySelect key="yesno"  name="state"  id="state"   value="${model.state}"> </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="money">新人总额度</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}"/>
		</div>
		</div>
 </div>

 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
  <div class="row">
 	<div class="col-md-6">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
            </div>
        </div>
        </form>
 	    <div id="toolbar">
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="false"
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
 seajs.use("js/admin/klg/limit/KlgAmountLimitation",function(o){
 	o.modify();
 	o.listCustomer();
 });
 </script>






