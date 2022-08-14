 <!-- Copyright:    -->
 <!-- AppCommendTradeModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:50:18      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendTrade--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommendtradelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="ordertNum">ordertNum</label>
			 <input type="text" class="form-control" name="ordertNum" id="ordertNum" value="${model.ordertNum}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="fixPriceCoinCode">fixPriceCoinCode</label>
			 <input type="text" class="form-control" name="fixPriceCoinCode" id="fixPriceCoinCode" value="${model.fixPriceCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" value="${model.fixPriceType}"/>
		</div>
		<div class="form-group">
			 <label for="rewardmoney">rewardmoney</label>
			 <input type="text" class="form-control" name="rewardmoney" id="rewardmoney" value="${model.rewardmoney}"/>
		</div>
		<div class="form-group">
			 <label for="deliveryName">deliveryName</label>
			 <input type="text" class="form-control" name="deliveryName" id="deliveryName" value="${model.deliveryName}"/>
		</div>
		<div class="form-group">
			 <label for="deliveryId">deliveryId</label>
			 <input type="text" class="form-control" name="deliveryId" id="deliveryId" value="${model.deliveryId}"/>
		</div>
		<div class="form-group">
			 <label for="hierarchy">hierarchy</label>
			 <input type="text" class="form-control" name="hierarchy" id="hierarchy" value="${model.hierarchy}"/>
		</div>
		<div class="form-group">
			 <label for="userMoney">userMoney</label>
			 <input type="text" class="form-control" name="userMoney" id="userMoney" value="${model.userMoney}"/>
		</div>
		<div class="form-group">
			 <label for="ratetype">ratetype</label>
			 <input type="text" class="form-control" name="ratetype" id="ratetype" value="${model.ratetype}"/>
		</div>
		<div class="form-group">
			 <label for="personType">personType</label>
			 <input type="text" class="form-control" name="personType" id="personType" value="${model.personType}"/>
		</div>
		<div class="form-group">
			 <label for="basemoney">basemoney</label>
			 <input type="text" class="form-control" name="basemoney" id="basemoney" value="${model.basemoney}"/>
		</div>
		<div class="form-group">
			 <label for="changeMoney">changeMoney</label>
			 <input type="text" class="form-control" name="changeMoney" id="changeMoney" value="${model.changeMoney}"/>
		</div>
		<div class="form-group">
			 <label for="currentMoney">currentMoney</label>
			 <input type="text" class="form-control" name="currentMoney" id="currentMoney" value="${model.currentMoney}"/>
		</div>
		<div class="form-group">
			 <label for="feeamout">feeamout</label>
			 <input type="text" class="form-control" name="feeamout" id="feeamout" value="${model.feeamout}"/>
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" value="${model.transactionTime}"/>
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
 seajs.use("js/admin/commend/AppCommendTrade",function(o){
 	o.modify();
 });
 </script>






