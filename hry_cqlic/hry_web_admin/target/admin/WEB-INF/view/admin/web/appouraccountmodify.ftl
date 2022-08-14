 <!-- Copyright:    -->
 <!-- AppOurAccountModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-13 18:38:16      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppOurAccount--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appouraccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}"/>
		</div>
		<div class="form-group">
			 <label for="bankLogo">bankLogo</label>
			 <input type="text" class="form-control" name="bankLogo" id="bankLogo" value="${model.bankLogo}"/>
		</div>
		<div class="form-group">
			 <label for="isShow">isShow</label>
			 <input type="text" class="form-control" name="isShow" id="isShow" value="${model.isShow}"/>
		</div>
		<div class="form-group">
			 <label for="accountType">accountType</label>
			 <input type="text" class="form-control" name="accountType" id="accountType" value="${model.accountType}"/>
		</div>
		<div class="form-group">
			 <label for="openAccountType">openAccountType</label>
			 <input type="text" class="form-control" name="openAccountType" id="openAccountType" value="${model.openAccountType}"/>
		</div>
		<div class="form-group">
			 <label for="bankName">bankName</label>
			 <input type="text" class="form-control" name="bankName" id="bankName" value="${model.bankName}"/>
		</div>
		<div class="form-group">
			 <label for="accountName">accountName</label>
			 <input type="text" class="form-control" name="accountName" id="accountName" value="${model.accountName}"/>
		</div>
		<div class="form-group">
			 <label for="accountNumber">accountNumber</label>
			 <input type="text" class="form-control" name="accountNumber" id="accountNumber" value="${model.accountNumber}"/>
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" value="${model.openTime}"/>
		</div>
		<div class="form-group">
			 <label for="accountMoney">accountMoney</label>
			 <input type="text" class="form-control" name="accountMoney" id="accountMoney" value="${model.accountMoney}"/>
		</div>
		<div class="form-group">
			 <label for="bankAddress">bankAddress</label>
			 <input type="text" class="form-control" name="bankAddress" id="bankAddress" value="${model.bankAddress}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}"/>
		</div>
		<div class="form-group">
			 <label for="retainsMoney">retainsMoney</label>
			 <input type="text" class="form-control" name="retainsMoney" id="retainsMoney" value="${model.retainsMoney}"/>
		</div>
		<div class="form-group">
			 <label for="accountMoneyNew">accountMoneyNew</label>
			 <input type="text" class="form-control" name="accountMoneyNew" id="accountMoneyNew" value="${model.accountMoneyNew}"/>
		</div>
		<div class="form-group">
			 <label for="todayAddedMoney">todayAddedMoney</label>
			 <input type="text" class="form-control" name="todayAddedMoney" id="todayAddedMoney" value="${model.todayAddedMoney}"/>
		</div>
		<div class="form-group">
			 <label for="accountFee">accountFee</label>
			 <input type="text" class="form-control" name="accountFee" id="accountFee" value="${model.accountFee}"/>
		</div>
		<div class="form-group">
			 <label for="hasOutFee">hasOutFee</label>
			 <input type="text" class="form-control" name="hasOutFee" id="hasOutFee" value="${model.hasOutFee}"/>
		</div>
		<div class="form-group">
			 <label for="todayAddedFee">todayAddedFee</label>
			 <input type="text" class="form-control" name="todayAddedFee" id="todayAddedFee" value="${model.todayAddedFee}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppOurAccount",function(o){
 	o.modify();
 });
 </script>






