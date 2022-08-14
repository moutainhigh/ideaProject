 <!-- Copyright:    -->
 <!-- AppAccountRecordSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-20 09:55:15      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppAccountRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/appaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="appAccountId">appAccountId</label>
			 <input type="text" class="form-control" name="appAccountId" id="appAccountId" value="${model.appAccountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="appAccountNum">appAccountNum</label>
			 <input type="text" class="form-control" name="appAccountNum" id="appAccountNum" value="${model.appAccountNum}" disabled/>
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
			 <label for="recordType">recordType</label>
			 <input type="text" class="form-control" name="recordType" id="recordType" value="${model.recordType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="source">source</label>
			 <input type="text" class="form-control" name="source" id="source" value="${model.source}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionMoney">transactionMoney</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${model.transactionMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionNum">transactionNum</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyName">currencyName</label>
			 <input type="text" class="form-control" name="currencyName" id="currencyName" value="${model.currencyName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="auditor">auditor</label>
			 <input type="text" class="form-control" name="auditor" id="auditor" value="${model.auditor}" disabled/>
		</div>
		<div class="form-group">
			 <label for="operationTime">operationTime</label>
			 <input type="text" class="form-control" name="operationTime" id="operationTime" value="${model.operationTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerAccount">customerAccount</label>
			 <input type="text" class="form-control" name="customerAccount" id="customerAccount" value="${model.customerAccount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="factorage">factorage</label>
			 <input type="text" class="form-control" name="factorage" id="factorage" value="${model.factorage}" disabled/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppAccountRecord",function(o){
 	o.see();
 });
 </script>




