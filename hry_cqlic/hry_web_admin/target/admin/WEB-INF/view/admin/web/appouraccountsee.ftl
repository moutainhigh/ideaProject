 <!-- Copyright:    -->
 <!-- AppOurAccountSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-13 18:38:15      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppOurAccount--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appouraccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankLogo">bankLogo</label>
			 <input type="text" class="form-control" name="bankLogo" id="bankLogo" value="${model.bankLogo}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isShow">isShow</label>
			 <input type="text" class="form-control" name="isShow" id="isShow" value="${model.isShow}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountType">accountType</label>
			 <input type="text" class="form-control" name="accountType" id="accountType" value="${model.accountType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openAccountType">openAccountType</label>
			 <input type="text" class="form-control" name="openAccountType" id="openAccountType" value="${model.openAccountType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankName">bankName</label>
			 <input type="text" class="form-control" name="bankName" id="bankName" value="${model.bankName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountName">accountName</label>
			 <input type="text" class="form-control" name="accountName" id="accountName" value="${model.accountName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountNumber">accountNumber</label>
			 <input type="text" class="form-control" name="accountNumber" id="accountNumber" value="${model.accountNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" value="${model.openTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountMoney">accountMoney</label>
			 <input type="text" class="form-control" name="accountMoney" id="accountMoney" value="${model.accountMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bankAddress">bankAddress</label>
			 <input type="text" class="form-control" name="bankAddress" id="bankAddress" value="${model.bankAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}" disabled/>
		</div>
		<div class="form-group">
			 <label for="retainsMoney">retainsMoney</label>
			 <input type="text" class="form-control" name="retainsMoney" id="retainsMoney" value="${model.retainsMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountMoneyNew">accountMoneyNew</label>
			 <input type="text" class="form-control" name="accountMoneyNew" id="accountMoneyNew" value="${model.accountMoneyNew}" disabled/>
		</div>
		<div class="form-group">
			 <label for="todayAddedMoney">todayAddedMoney</label>
			 <input type="text" class="form-control" name="todayAddedMoney" id="todayAddedMoney" value="${model.todayAddedMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountFee">accountFee</label>
			 <input type="text" class="form-control" name="accountFee" id="accountFee" value="${model.accountFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hasOutFee">hasOutFee</label>
			 <input type="text" class="form-control" name="hasOutFee" id="hasOutFee" value="${model.hasOutFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="todayAddedFee">todayAddedFee</label>
			 <input type="text" class="form-control" name="todayAddedFee" id="todayAddedFee" value="${model.todayAddedFee}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppOurAccount",function(o){
 	o.see();
 });
 </script>




