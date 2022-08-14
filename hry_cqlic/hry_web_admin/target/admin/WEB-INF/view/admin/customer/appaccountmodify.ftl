 <!-- Copyright:    -->
 <!-- AppAccountModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-15 13:08:06      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppAccount--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="hotMoney">hotMoney</label>
			 <input type="text" class="form-control" name="hotMoney" id="hotMoney" value="${model.hotMoney}"/>
		</div>
		<div class="form-group">
			 <label for="coldMoney">coldMoney</label>
			 <input type="text" class="form-control" name="coldMoney" id="coldMoney" value="${model.coldMoney}"/>
		</div>
		<div class="form-group">
			 <label for="version">version</label>
			 <input type="text" class="form-control" name="version" id="version" value="${model.version}"/>
		</div>
		<div class="form-group">
			 <label for="accountNum">accountNum</label>
			 <input type="text" class="form-control" name="accountNum" id="accountNum" value="${model.accountNum}"/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}"/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="lendMoney">lendMoney</label>
			 <input type="text" class="form-control" name="lendMoney" id="lendMoney" value="${model.lendMoney}"/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}"/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}"/>
		</div>
		<div class="form-group">
			 <label for="rewardMoney">rewardMoney</label>
			 <input type="text" class="form-control" name="rewardMoney" id="rewardMoney" value="${model.rewardMoney}"/>
		</div>
		<div class="form-group">
			 <label for="hasRewardMoney">hasRewardMoney</label>
			 <input type="text" class="form-control" name="hasRewardMoney" id="hasRewardMoney" value="${model.hasRewardMoney}"/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/customer/AppAccount",function(o){
 	o.modify();
 });
 </script>






