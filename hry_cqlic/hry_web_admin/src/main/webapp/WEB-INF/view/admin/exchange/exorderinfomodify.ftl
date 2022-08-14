 <!-- Copyright:    -->
 <!-- ExOrderInfoModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:26:42      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExOrderInfo--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exorderinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="orderNum">orderNum</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" value="${model.orderNum}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="transactionPrice">transactionPrice</label>
			 <input type="text" class="form-control" name="transactionPrice" id="transactionPrice" value="${model.transactionPrice}"/>
		</div>
		<div class="form-group">
			 <label for="transactionCount">transactionCount</label>
			 <input type="text" class="form-control" name="transactionCount" id="transactionCount" value="${model.transactionCount}"/>
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" value="${model.transactionTime}"/>
		</div>
		<div class="form-group">
			 <label for="transactionSum">transactionSum</label>
			 <input type="text" class="form-control" name="transactionSum" id="transactionSum" value="${model.transactionSum}"/>
		</div>
		<div class="form-group">
			 <label for="transactionBuyFee">transactionBuyFee</label>
			 <input type="text" class="form-control" name="transactionBuyFee" id="transactionBuyFee" value="${model.transactionBuyFee}"/>
		</div>
		<div class="form-group">
			 <label for="transactionSellFee">transactionSellFee</label>
			 <input type="text" class="form-control" name="transactionSellFee" id="transactionSellFee" value="${model.transactionSellFee}"/>
		</div>
		<div class="form-group">
			 <label for="buyEntrustNum">buyEntrustNum</label>
			 <input type="text" class="form-control" name="buyEntrustNum" id="buyEntrustNum" value="${model.buyEntrustNum}"/>
		</div>
		<div class="form-group">
			 <label for="sellEntrustNum">sellEntrustNum</label>
			 <input type="text" class="form-control" name="sellEntrustNum" id="sellEntrustNum" value="${model.sellEntrustNum}"/>
		</div>
		<div class="form-group">
			 <label for="buyCustomId">buyCustomId</label>
			 <input type="text" class="form-control" name="buyCustomId" id="buyCustomId" value="${model.buyCustomId}"/>
		</div>
		<div class="form-group">
			 <label for="sellCustomId">sellCustomId</label>
			 <input type="text" class="form-control" name="sellCustomId" id="sellCustomId" value="${model.sellCustomId}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="transactionBuyFeeRate">transactionBuyFeeRate</label>
			 <input type="text" class="form-control" name="transactionBuyFeeRate" id="transactionBuyFeeRate" value="${model.transactionBuyFeeRate}"/>
		</div>
		<div class="form-group">
			 <label for="transactionSellFeeRate">transactionSellFeeRate</label>
			 <input type="text" class="form-control" name="transactionSellFeeRate" id="transactionSellFeeRate" value="${model.transactionSellFeeRate}"/>
		</div>
		<div class="form-group">
			 <label for="productName">productName</label>
			 <input type="text" class="form-control" name="productName" id="productName" value="${model.productName}"/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}"/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}"/>
		</div>
		<div class="form-group">
			 <label for="orderTimestapm">orderTimestapm</label>
			 <input type="text" class="form-control" name="orderTimestapm" id="orderTimestapm" value="${model.orderTimestapm}"/>
		</div>
		<div class="form-group">
			 <label for="inOrOutTransaction">inOrOutTransaction</label>
			 <input type="text" class="form-control" name="inOrOutTransaction" id="inOrOutTransaction" value="${model.inOrOutTransaction}"/>
		</div>
		<div class="form-group">
			 <label for="buyUserCode">buyUserCode</label>
			 <input type="text" class="form-control" name="buyUserCode" id="buyUserCode" value="${model.buyUserCode}"/>
		</div>
		<div class="form-group">
			 <label for="sellUserCode">sellUserCode</label>
			 <input type="text" class="form-control" name="sellUserCode" id="sellUserCode" value="${model.sellUserCode}"/>
		</div>
		<div class="form-group">
			 <label for="sellUserName">sellUserName</label>
			 <input type="text" class="form-control" name="sellUserName" id="sellUserName" value="${model.sellUserName}"/>
		</div>
		<div class="form-group">
			 <label for="buyUserName">buyUserName</label>
			 <input type="text" class="form-control" name="buyUserName" id="buyUserName" value="${model.buyUserName}"/>
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}"/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" value="${model.userCode}"/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="transactionFee">transactionFee</label>
			 <input type="text" class="form-control" name="transactionFee" id="transactionFee" value="${model.transactionFee}"/>
		</div>
		<div class="form-group">
			 <label for="transactionFeeRate">transactionFeeRate</label>
			 <input type="text" class="form-control" name="transactionFeeRate" id="transactionFeeRate" value="${model.transactionFeeRate}"/>
		</div>
		<div class="form-group">
			 <label for="entrustNum">entrustNum</label>
			 <input type="text" class="form-control" name="entrustNum" id="entrustNum" value="${model.entrustNum}"/>
		</div>
		<div class="form-group">
			 <label for="profitandlossMoney">profitandlossMoney</label>
			 <input type="text" class="form-control" name="profitandlossMoney" id="profitandlossMoney" value="${model.profitandlossMoney}"/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}"/>
		</div>
		<div class="form-group">
			 <label for="buyTrueName">buyTrueName</label>
			 <input type="text" class="form-control" name="buyTrueName" id="buyTrueName" value="${model.buyTrueName}"/>
		</div>
		<div class="form-group">
			 <label for="sellTrueName">sellTrueName</label>
			 <input type="text" class="form-control" name="sellTrueName" id="sellTrueName" value="${model.sellTrueName}"/>
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
			 <label for="isCulAtferMoney">isCulAtferMoney</label>
			 <input type="text" class="form-control" name="isCulAtferMoney" id="isCulAtferMoney" value="${model.isCulAtferMoney}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExOrderInfo",function(o){
 	o.modify();
 });
 </script>






