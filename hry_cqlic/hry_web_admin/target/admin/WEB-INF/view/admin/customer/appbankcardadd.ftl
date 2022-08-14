 <!-- Copyright:    -->
 <!-- AppBankCardAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 10:42:58      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appbankcardlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" />
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="cardName">cardName</label>
			 <input type="text" class="form-control" name="cardName" id="cardName" />
		</div>
		<div class="form-group">
			 <label for="cardNumber">cardNumber</label>
			 <input type="text" class="form-control" name="cardNumber" id="cardNumber" />
		</div>
		<div class="form-group">
			 <label for="cardBank">cardBank</label>
			 <input type="text" class="form-control" name="cardBank" id="cardBank" />
		</div>
		<div class="form-group">
			 <label for="bankProvince">bankProvince</label>
			 <input type="text" class="form-control" name="bankProvince" id="bankProvince" />
		</div>
		<div class="form-group">
			 <label for="bankAddress">bankAddress</label>
			 <input type="text" class="form-control" name="bankAddress" id="bankAddress" />
		</div>
		<div class="form-group">
			 <label for="subBank">subBank</label>
			 <input type="text" class="form-control" name="subBank" id="subBank" />
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
		<div class="form-group">
			 <label for="signBank">signBank</label>
			 <input type="text" class="form-control" name="signBank" id="signBank" />
		</div>
		<div class="form-group">
			 <label for="subBankNum">subBankNum</label>
			 <input type="text" class="form-control" name="subBankNum" id="subBankNum" />
		</div>
		<div class="form-group">
			 <label for="isDelete">isDelete</label>
			 <input type="text" class="form-control" name="isDelete" id="isDelete" />
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
seajs.use("js/admin/customer/AppBankCard",function(o){
	o.add();
});
</script>




