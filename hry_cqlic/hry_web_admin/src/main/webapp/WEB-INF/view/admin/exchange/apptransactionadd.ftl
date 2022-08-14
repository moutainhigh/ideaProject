 <!-- Copyright:    -->
 <!-- AppTransactionAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-06 14:36:50      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-success pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/apptransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
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
			 <label for="bankNum">bankNum</label>
			 <input type="text" class="form-control" name="bankNum" id="bankNum" />
		</div>
		<div class="form-group">
			 <label for="style">style</label>
			 <input type="text" class="form-control" name="style" id="style" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="custromerAccountNumber">custromerAccountNumber</label>
			 <input type="text" class="form-control" name="custromerAccountNumber" id="custromerAccountNumber" />
		</div>
		<div class="form-group">
			 <label for="ourAccountNumber">ourAccountNumber</label>
			 <input type="text" class="form-control" name="ourAccountNumber" id="ourAccountNumber" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="cardHolder">cardHolder</label>
			 <input type="text" class="form-control" name="cardHolder" id="cardHolder" />
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
			 <label for="readyStates">readyStates</label>
			 <input type="text" class="form-control" name="readyStates" id="readyStates" />
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
			 <label for="rejectionReason">rejectionReason</label>
			 <input type="text" class="form-control" name="rejectionReason" id="rejectionReason" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
		<div class="form-group">
			 <label for="thirdPayName">thirdPayName</label>
			 <input type="text" class="form-control" name="thirdPayName" id="thirdPayName" />
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" />
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
seajs.use("js/admin/exchange/AppTransaction",function(o){
	o.add();
});
</script>




