 <!-- Copyright:    -->
 <!-- AppAccountSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-15 13:08:06      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppAccount--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hotMoney">hotMoney</label>
			 <input type="text" class="form-control" name="hotMoney" id="hotMoney" value="${model.hotMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coldMoney">coldMoney</label>
			 <input type="text" class="form-control" name="coldMoney" id="coldMoney" value="${model.coldMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="version">version</label>
			 <input type="text" class="form-control" name="version" id="version" value="${model.version}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountNum">accountNum</label>
			 <input type="text" class="form-control" name="accountNum" id="accountNum" value="${model.accountNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lendMoney">lendMoney</label>
			 <input type="text" class="form-control" name="lendMoney" id="lendMoney" value="${model.lendMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rewardMoney">rewardMoney</label>
			 <input type="text" class="form-control" name="rewardMoney" id="rewardMoney" value="${model.rewardMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hasRewardMoney">hasRewardMoney</label>
			 <input type="text" class="form-control" name="hasRewardMoney" id="hasRewardMoney" value="${model.hasRewardMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/customer/AppAccount",function(o){
 	o.see();
 });
 </script>




