 <!-- Copyright:    -->
 <!-- AppBankCardModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 10:42:58      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppBankCard--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/customer/appbankcardlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}"/>
		</div>
		<div class="form-group">
			 <label for="cardName">cardName</label>
			 <input type="text" class="form-control" name="cardName" id="cardName" value="${model.cardName}"/>
		</div>
		<div class="form-group">
			 <label for="cardNumber">cardNumber</label>
			 <input type="text" class="form-control" name="cardNumber" id="cardNumber" value="${model.cardNumber}"/>
		</div>
		<div class="form-group">
			 <label for="cardBank">cardBank</label>
			 <input type="text" class="form-control" name="cardBank" id="cardBank" value="${model.cardBank}"/>
		</div>
		<div class="form-group">
			 <label for="bankProvince">bankProvince</label>
			 <input type="text" class="form-control" name="bankProvince" id="bankProvince" value="${model.bankProvince}"/>
		</div>
		<div class="form-group">
			 <label for="bankAddress">bankAddress</label>
			 <input type="text" class="form-control" name="bankAddress" id="bankAddress" value="${model.bankAddress}"/>
		</div>
		<div class="form-group">
			 <label for="subBank">subBank</label>
			 <input type="text" class="form-control" name="subBank" id="subBank" value="${model.subBank}"/>
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
			 <label for="signBank">signBank</label>
			 <input type="text" class="form-control" name="signBank" id="signBank" value="${model.signBank}"/>
		</div>
		<div class="form-group">
			 <label for="subBankNum">subBankNum</label>
			 <input type="text" class="form-control" name="subBankNum" id="subBankNum" value="${model.subBankNum}"/>
		</div>
		<div class="form-group">
			 <label for="isDelete">isDelete</label>
			 <input type="text" class="form-control" name="isDelete" id="isDelete" value="${model.isDelete}"/>
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
 seajs.use("js/admin/customer/AppBankCard",function(o){
 	o.modify();
 });
 </script>






