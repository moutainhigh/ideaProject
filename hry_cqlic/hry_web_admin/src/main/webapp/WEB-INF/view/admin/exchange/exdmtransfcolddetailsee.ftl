 <!-- Copyright:    -->
 <!-- ExDmTransfColdDetailSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-27 18:01:50      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExDmTransfColdDetail--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exdmtransfcolddetaillist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fromAddress">fromAddress</label>
			 <input type="text" class="form-control" name="fromAddress" id="fromAddress" value="${model.fromAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="toAddress">toAddress</label>
			 <input type="text" class="form-control" name="toAddress" id="toAddress" value="${model.toAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="amount">amount</label>
			 <input type="text" class="form-control" name="amount" id="amount" value="${model.amount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="operator">operator</label>
			 <input type="text" class="form-control" name="operator" id="operator" value="${model.operator}" disabled/>
		</div>
		<div class="form-group">
			 <label for="tx">tx</label>
			 <input type="text" class="form-control" name="tx" id="tx" value="${model.tx}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExDmTransfColdDetail",function(o){
 	o.see();
 });
 </script>




