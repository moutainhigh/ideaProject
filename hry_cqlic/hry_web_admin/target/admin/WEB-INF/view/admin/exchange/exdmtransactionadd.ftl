 <!-- Copyright:    -->
 <!-- ExDmTransactionAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:59:41      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exdmtransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="transactionNum">transactionNum</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" />
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
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="transactionType">transactionType</label>
			 <input type="text" class="form-control" name="transactionType" id="transactionType" />
		</div>
		<div class="form-group">
			 <label for="transactionMoney">transactionMoney</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" />
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" />
		</div>
		<div class="form-group">
			 <label for="fee">fee</label>
			 <input type="text" class="form-control" name="fee" id="fee" />
		</div>
		<div class="form-group">
			 <label for="inAddress">inAddress</label>
			 <input type="text" class="form-control" name="inAddress" id="inAddress" />
		</div>
		<div class="form-group">
			 <label for="outAddress">outAddress</label>
			 <input type="text" class="form-control" name="outAddress" id="outAddress" />
		</div>
		<div class="form-group">
			 <label for="time">time</label>
			 <input type="text" class="form-control" name="time" id="time" />
		</div>
		<div class="form-group">
			 <label for="confirmations">confirmations</label>
			 <input type="text" class="form-control" name="confirmations" id="confirmations" />
		</div>
		<div class="form-group">
			 <label for="timereceived">timereceived</label>
			 <input type="text" class="form-control" name="timereceived" id="timereceived" />
		</div>
		<div class="form-group">
			 <label for="blocktime">blocktime</label>
			 <input type="text" class="form-control" name="blocktime" id="blocktime" />
		</div>
		<div class="form-group">
			 <label for="rejectionReason">rejectionReason</label>
			 <input type="text" class="form-control" name="rejectionReason" id="rejectionReason" />
		</div>
		<div class="form-group">
			 <label for="ourAccountNumber">ourAccountNumber</label>
			 <input type="text" class="form-control" name="ourAccountNumber" id="ourAccountNumber" />
		</div>
		<div class="form-group">
			 <label for="orderNo">orderNo</label>
			 <input type="text" class="form-control" name="orderNo" id="orderNo" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="btcDate">btcDate</label>
			 <input type="text" class="form-control" name="btcDate" id="btcDate" />
		</div>
		<div class="form-group">
			 <label for="btcCount">btcCount</label>
			 <input type="text" class="form-control" name="btcCount" id="btcCount" />
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" />
		</div>
		<div class="form-group">
			 <label for="optType">optType</label>
			 <input type="text" class="form-control" name="optType" id="optType" />
		</div>
		<div class="form-group">
			 <label for="isOpenCheck">isOpenCheck</label>
			 <input type="text" class="form-control" name="isOpenCheck" id="isOpenCheck" />
		</div>
		<div class="form-group">
			 <label for="memo">memo</label>
			 <input type="text" class="form-control" name="memo" id="memo" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/ExDmTransaction",function(o){
	o.add();
});
</script>




