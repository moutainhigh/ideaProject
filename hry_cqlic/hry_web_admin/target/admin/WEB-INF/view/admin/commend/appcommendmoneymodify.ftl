 <!-- Copyright:    -->
 <!-- AppCommendMoneyModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:49:22      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendMoney--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommendmoneylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="custromerId">custromerId</label>
			 <input type="text" class="form-control" name="custromerId" id="custromerId" value="${model.custromerId}"/>
		</div>
		<div class="form-group">
			 <label for="custromerName">custromerName</label>
			 <input type="text" class="form-control" name="custromerName" id="custromerName" value="${model.custromerName}"/>
		</div>
		<div class="form-group">
			 <label for="refecode">refecode</label>
			 <input type="text" class="form-control" name="refecode" id="refecode" value="${model.refecode}"/>
		</div>
		<div class="form-group">
			 <label for="moneyNum">moneyNum</label>
			 <input type="text" class="form-control" name="moneyNum" id="moneyNum" value="${model.moneyNum}"/>
		</div>
		<div class="form-group">
			 <label for="paidMoney">paidMoney</label>
			 <input type="text" class="form-control" name="paidMoney" id="paidMoney" value="${model.paidMoney}"/>
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" value="${model.fixPriceType}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="dealType">dealType</label>
			 <input type="text" class="form-control" name="dealType" id="dealType" value="${model.dealType}"/>
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" value="${model.userCode}"/>
		</div>
		<div class="form-group">
			 <label for="commendOne">commendOne</label>
			 <input type="text" class="form-control" name="commendOne" id="commendOne" value="${model.commendOne}"/>
		</div>
		<div class="form-group">
			 <label for="commendTwo">commendTwo</label>
			 <input type="text" class="form-control" name="commendTwo" id="commendTwo" value="${model.commendTwo}"/>
		</div>
		<div class="form-group">
			 <label for="commendThree">commendThree</label>
			 <input type="text" class="form-control" name="commendThree" id="commendThree" value="${model.commendThree}"/>
		</div>
		<div class="form-group">
			 <label for="commendLater">commendLater</label>
			 <input type="text" class="form-control" name="commendLater" id="commendLater" value="${model.commendLater}"/>
		</div>
		<div class="form-group">
			 <label for="lastPaidTime">lastPaidTime</label>
			 <input type="text" class="form-control" name="lastPaidTime" id="lastPaidTime" value="${model.lastPaidTime}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendMoney",function(o){
 	o.modify();
 });
 </script>






