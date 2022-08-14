 <!-- Copyright:    -->
 <!-- AppAccountAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-15 13:08:06      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="hotMoney">hotMoney</label>
			 <input type="text" class="form-control" name="hotMoney" id="hotMoney" />
		</div>
		<div class="form-group">
			 <label for="coldMoney">coldMoney</label>
			 <input type="text" class="form-control" name="coldMoney" id="coldMoney" />
		</div>
		<div class="form-group">
			 <label for="version">version</label>
			 <input type="text" class="form-control" name="version" id="version" />
		</div>
		<div class="form-group">
			 <label for="accountNum">accountNum</label>
			 <input type="text" class="form-control" name="accountNum" id="accountNum" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="lendMoney">lendMoney</label>
			 <input type="text" class="form-control" name="lendMoney" id="lendMoney" />
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
			 <label for="rewardMoney">rewardMoney</label>
			 <input type="text" class="form-control" name="rewardMoney" id="rewardMoney" />
		</div>
		<div class="form-group">
			 <label for="hasRewardMoney">hasRewardMoney</label>
			 <input type="text" class="form-control" name="hasRewardMoney" id="hasRewardMoney" />
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
</div>
<script type="text/javascript">
seajs.use("js/admin/customer/AppAccount",function(o){
	o.add();
});
</script>




