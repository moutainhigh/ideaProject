 <!-- Copyright:    -->
 <!-- C2cTransactionAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:46      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/c2ctransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>
<#---->

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
			 <label for="transactionCount">transactionCount</label>
			 <input type="text" class="form-control" name="transactionCount" id="transactionCount" />
		</div>
		<div class="form-group">
			 <label for="transactionPrice">transactionPrice</label>
			 <input type="text" class="form-control" name="transactionPrice" id="transactionPrice" />
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
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="fee">fee</label>
			 <input type="text" class="form-control" name="fee" id="fee" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="businessmanId">businessmanId</label>
			 <input type="text" class="form-control" name="businessmanId" id="businessmanId" />
		</div>
		<div class="form-group">
			 <label for="businessmanBankId">businessmanBankId</label>
			 <input type="text" class="form-control" name="businessmanBankId" id="businessmanBankId" />
		</div>
		<div class="form-group">
			 <label for="randomNum">randomNum</label>
			 <input type="text" class="form-control" name="randomNum" id="randomNum" />
		</div>
		<div class="form-group">
			 <label for="customerBankId">customerBankId</label>
			 <input type="text" class="form-control" name="customerBankId" id="customerBankId" />
		</div>
		<div class="form-group">
			 <label for="transactionId">transactionId</label>
			 <input type="text" class="form-control" name="transactionId" id="transactionId" />
		</div>
		<div class="form-group">
			 <label for="status2">status2</label>
			 <input type="text" class="form-control" name="status2" id="status2" />
		</div>
		<div class="form-group">
			 <label for="businessman">businessman</label>
			 <input type="text" class="form-control" name="businessman" id="businessman" />
		</div>
		<div class="form-group">
			 <label for="checkUser">checkUser</label>
			 <input type="text" class="form-control" name="checkUser" id="checkUser" />
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
seajs.use("js/admin/c2c/C2cTransaction",function(o){
	o.add();
});
</script>




