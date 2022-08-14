 <!-- Copyright:    -->
 <!-- ExDmTransactionSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:59:41      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExDmTransaction--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exdmtransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="transactionNum">transactionNum</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerName">customerName</label>
			 <input type="text" class="form-control" name="customerName" id="customerName" value="${model.customerName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionType">transactionType</label>
			 <input type="text" class="form-control" name="transactionType" id="transactionType" value="${model.transactionType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionMoney">transactionMoney</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${model.transactionMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fee">fee</label>
			 <input type="text" class="form-control" name="fee" id="fee" value="${model.fee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="inAddress">inAddress</label>
			 <input type="text" class="form-control" name="inAddress" id="inAddress" value="${model.inAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="outAddress">outAddress</label>
			 <input type="text" class="form-control" name="outAddress" id="outAddress" value="${model.outAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="time">time</label>
			 <input type="text" class="form-control" name="time" id="time" value="${model.time}" disabled/>
		</div>
		<div class="form-group">
			 <label for="confirmations">confirmations</label>
			 <input type="text" class="form-control" name="confirmations" id="confirmations" value="${model.confirmations}" disabled/>
		</div>
		<div class="form-group">
			 <label for="timereceived">timereceived</label>
			 <input type="text" class="form-control" name="timereceived" id="timereceived" value="${model.timereceived}" disabled/>
		</div>
		<div class="form-group">
			 <label for="blocktime">blocktime</label>
			 <input type="text" class="form-control" name="blocktime" id="blocktime" value="${model.blocktime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rejectionReason">rejectionReason</label>
			 <input type="text" class="form-control" name="rejectionReason" id="rejectionReason" value="${model.rejectionReason}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ourAccountNumber">ourAccountNumber</label>
			 <input type="text" class="form-control" name="ourAccountNumber" id="ourAccountNumber" value="${model.ourAccountNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderNo">orderNo</label>
			 <input type="text" class="form-control" name="orderNo" id="orderNo" value="${model.orderNo}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="btcDate">btcDate</label>
			 <input type="text" class="form-control" name="btcDate" id="btcDate" value="${model.btcDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="btcCount">btcCount</label>
			 <input type="text" class="form-control" name="btcCount" id="btcCount" value="${model.btcCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}" disabled/>
		</div>
		<div class="form-group">
			 <label for="optType">optType</label>
			 <input type="text" class="form-control" name="optType" id="optType" value="${model.optType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isOpenCheck">isOpenCheck</label>
			 <input type="text" class="form-control" name="isOpenCheck" id="isOpenCheck" value="${model.isOpenCheck}" disabled/>
		</div>
		<div class="form-group">
			 <label for="memo">memo</label>
			 <input type="text" class="form-control" name="memo" id="memo" value="${model.memo}" disabled/>
		</div>
	</div>
 </div>

 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExDmTransaction",function(o){
 	o.see();
 });
 </script>




