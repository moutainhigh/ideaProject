 <!-- Copyright:    -->
 <!-- AppTransactionModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-06 14:36:51      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppTransaction--Modify  <button type="button"  class="btn btn-warning pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/apptransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="transactionNum">transactionNum</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}"/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}"/>
		</div>
		<div class="form-group">
			 <label for="transactionType">transactionType</label>
			 <input type="text" class="form-control" name="transactionType" id="transactionType" value="${model.transactionType}"/>
		</div>
		<div class="form-group">
			 <label for="transactionMoney">transactionMoney</label>
			 <input type="text" class="form-control" name="transactionMoney" id="transactionMoney" value="${model.transactionMoney}"/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}"/>
		</div>
		<div class="form-group">
			 <label for="bankNum">bankNum</label>
			 <input type="text" class="form-control" name="bankNum" id="bankNum" value="${model.bankNum}"/>
		</div>
		<div class="form-group">
			 <label for="style">style</label>
			 <input type="text" class="form-control" name="style" id="style" value="${model.style}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="custromerAccountNumber">custromerAccountNumber</label>
			 <input type="text" class="form-control" name="custromerAccountNumber" id="custromerAccountNumber" value="${model.custromerAccountNumber}"/>
		</div>
		<div class="form-group">
			 <label for="ourAccountNumber">ourAccountNumber</label>
			 <input type="text" class="form-control" name="ourAccountNumber" id="ourAccountNumber" value="${model.ourAccountNumber}"/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}"/>
		</div>
		<div class="form-group">
			 <label for="cardHolder">cardHolder</label>
			 <input type="text" class="form-control" name="cardHolder" id="cardHolder" value="${model.cardHolder}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="factorage">factorage</label>
			 <input type="text" class="form-control" name="factorage" id="factorage" value="${model.factorage}"/>
		</div>
		<div class="form-group">
			 <label for="readyStates">readyStates</label>
			 <input type="text" class="form-control" name="readyStates" id="readyStates" value="${model.readyStates}"/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}"/>
		</div>
		<div class="form-group">
			 <label for="fee">fee</label>
			 <input type="text" class="form-control" name="fee" id="fee" value="${model.fee}"/>
		</div>
		<div class="form-group">
			 <label for="rejectionReason">rejectionReason</label>
			 <input type="text" class="form-control" name="rejectionReason" id="rejectionReason" value="${model.rejectionReason}"/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}"/>
		</div>
		<div class="form-group">
			 <label for="thirdPayName">thirdPayName</label>
			 <input type="text" class="form-control" name="thirdPayName" id="thirdPayName" value="${model.thirdPayName}"/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-success btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppTransaction",function(o){
 	o.modify();
 });
 </script>






