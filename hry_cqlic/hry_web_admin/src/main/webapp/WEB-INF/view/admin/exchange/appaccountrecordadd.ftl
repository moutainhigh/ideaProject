 <!-- Copyright:    -->
 <!-- AppAccountRecordAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-20 09:55:15      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/appaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="appAccountId">appAccountId</label>
			 <input type="text" class="form-control" name="appAccountId" id="appAccountId" />
		</div>
		<div class="form-group">
			 <label for="appAccountNum">appAccountNum</label>
			 <input type="text" class="form-control" name="appAccountNum" id="appAccountNum" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="customerName">customerName</label>
			 <input type="text" class="form-control" name="customerName" id="customerName" />
		</div>
		<div class="form-group">
			 <label for="recordType">recordType</label>
			 <input type="text" class="form-control" name="recordType" id="recordType" />
		</div>
		<div class="form-group">
			 <label for="source">source</label>
			 <input type="text" class="form-control" name="source" id="source" />
		</div>
		<div class="form-group">
			 <label for="transactionMoney">transactionMoney</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" />
		</div>
		<div class="form-group">
			 <label for="transactionNum">transactionNum</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" />
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="currencyName">currencyName</label>
			 <input type="text" class="form-control" name="currencyName" id="currencyName" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="auditor">auditor</label>
			 <input type="text" class="form-control" name="auditor" id="auditor" />
		</div>
		<div class="form-group">
			 <label for="operationTime">operationTime</label>
			 <input type="text" class="form-control" name="operationTime" id="operationTime" />
		</div>
		<div class="form-group">
			 <label for="customerAccount">customerAccount</label>
			 <input type="text" class="form-control" name="customerAccount" id="customerAccount" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="factorage">factorage</label>
			 <input type="text" class="form-control" name="factorage" id="factorage" />
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/AppAccountRecord",function(o){
	o.add();
});
</script>




