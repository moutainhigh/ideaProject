 <!-- Copyright:    -->
 <!-- ExDmTransfColdDetailAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-27 18:01:50      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exdmtransfcolddetaillist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="fromAddress">fromAddress</label>
			 <input type="text" class="form-control" name="fromAddress" id="fromAddress" />
		</div>
		<div class="form-group">
			 <label for="toAddress">toAddress</label>
			 <input type="text" class="form-control" name="toAddress" id="toAddress" />
		</div>
		<div class="form-group">
			 <label for="amount">amount</label>
			 <input type="text" class="form-control" name="amount" id="amount" />
		</div>
		<div class="form-group">
			 <label for="operator">operator</label>
			 <input type="text" class="form-control" name="operator" id="operator" />
		</div>
		<div class="form-group">
			 <label for="tx">tx</label>
			 <input type="text" class="form-control" name="tx" id="tx" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/exchange/ExDmTransfColdDetail",function(o){
	o.add();
});
</script>




