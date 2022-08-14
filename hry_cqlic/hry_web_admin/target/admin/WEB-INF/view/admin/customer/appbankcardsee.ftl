 <!-- Copyright:    -->
 <!-- AppBankCardSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 10:42:58      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppBankCard--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appbankcardlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardName">cardName</label>
			 <input type="text" class="form-control" name="cardName" id="cardName" value="${model.cardName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardNumber">cardNumber</label>
			 <input type="text" class="form-control" name="cardNumber" id="cardNumber" value="${model.cardNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="cardBank">cardBank</label>
			 <input type="text" class="form-control" name="cardBank" id="cardBank" value="${model.cardBank}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankProvince">bankProvince</label>
			 <input type="text" class="form-control" name="bankProvince" id="bankProvince" value="${model.bankProvince}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankAddress">bankAddress</label>
			 <input type="text" class="form-control" name="bankAddress" id="bankAddress" value="${model.bankAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="subBank">subBank</label>
			 <input type="text" class="form-control" name="subBank" id="subBank" value="${model.subBank}" disabled/>
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
			 <label for="signBank">signBank</label>
			 <input type="text" class="form-control" name="signBank" id="signBank" value="${model.signBank}" disabled/>
		</div>
		<div class="form-group">
			 <label for="subBankNum">subBankNum</label>
			 <input type="text" class="form-control" name="subBankNum" id="subBankNum" value="${model.subBankNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isDelete">isDelete</label>
			 <input type="text" class="form-control" name="isDelete" id="isDelete" value="${model.isDelete}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surname">surname</label>
			 <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/customer/AppBankCard",function(o){
 	o.see();
 });
 </script>




