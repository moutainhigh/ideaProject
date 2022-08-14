 <!-- Copyright:    -->
 <!-- ExOrderInfoSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:26:42      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExOrderInfo--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exorderinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="orderNum">orderNum</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" value="${model.orderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionPrice">transactionPrice</label>
			 <input type="text" class="form-control" name="transactionPrice" id="transactionPrice" value="${model.transactionPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionCount">transactionCount</label>
			 <input type="text" class="form-control" name="transactionCount" id="transactionCount" value="${model.transactionCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" value="${model.transactionTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionSum">transactionSum</label>
			 <input type="text" class="form-control" name="transactionSum" id="transactionSum" value="${model.transactionSum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionBuyFee">transactionBuyFee</label>
			 <input type="text" class="form-control" name="transactionBuyFee" id="transactionBuyFee" value="${model.transactionBuyFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionSellFee">transactionSellFee</label>
			 <input type="text" class="form-control" name="transactionSellFee" id="transactionSellFee" value="${model.transactionSellFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyEntrustNum">buyEntrustNum</label>
			 <input type="text" class="form-control" name="buyEntrustNum" id="buyEntrustNum" value="${model.buyEntrustNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellEntrustNum">sellEntrustNum</label>
			 <input type="text" class="form-control" name="sellEntrustNum" id="sellEntrustNum" value="${model.sellEntrustNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyCustomId">buyCustomId</label>
			 <input type="text" class="form-control" name="buyCustomId" id="buyCustomId" value="${model.buyCustomId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellCustomId">sellCustomId</label>
			 <input type="text" class="form-control" name="sellCustomId" id="sellCustomId" value="${model.sellCustomId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionBuyFeeRate">transactionBuyFeeRate</label>
			 <input type="text" class="form-control" name="transactionBuyFeeRate" id="transactionBuyFeeRate" value="${model.transactionBuyFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionSellFeeRate">transactionSellFeeRate</label>
			 <input type="text" class="form-control" name="transactionSellFeeRate" id="transactionSellFeeRate" value="${model.transactionSellFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="productName">productName</label>
			 <input type="text" class="form-control" name="productName" id="productName" value="${model.productName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" value="${model.currencyType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" value="${model.website}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderTimestapm">orderTimestapm</label>
			 <input type="text" class="form-control" name="orderTimestapm" id="orderTimestapm" value="${model.orderTimestapm}" disabled/>
		</div>
		<div class="form-group">
			 <label for="inOrOutTransaction">inOrOutTransaction</label>
			 <input type="text" class="form-control" name="inOrOutTransaction" id="inOrOutTransaction" value="${model.inOrOutTransaction}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyUserCode">buyUserCode</label>
			 <input type="text" class="form-control" name="buyUserCode" id="buyUserCode" value="${model.buyUserCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellUserCode">sellUserCode</label>
			 <input type="text" class="form-control" name="sellUserCode" id="sellUserCode" value="${model.sellUserCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellUserName">sellUserName</label>
			 <input type="text" class="form-control" name="sellUserName" id="sellUserName" value="${model.sellUserName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyUserName">buyUserName</label>
			 <input type="text" class="form-control" name="buyUserName" id="buyUserName" value="${model.buyUserName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" value="${model.userCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionFee">transactionFee</label>
			 <input type="text" class="form-control" name="transactionFee" id="transactionFee" value="${model.transactionFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionFeeRate">transactionFeeRate</label>
			 <input type="text" class="form-control" name="transactionFeeRate" id="transactionFeeRate" value="${model.transactionFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustNum">entrustNum</label>
			 <input type="text" class="form-control" name="entrustNum" id="entrustNum" value="${model.entrustNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="profitandlossMoney">profitandlossMoney</label>
			 <input type="text" class="form-control" name="profitandlossMoney" id="profitandlossMoney" value="${model.profitandlossMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyTrueName">buyTrueName</label>
			 <input type="text" class="form-control" name="buyTrueName" id="buyTrueName" value="${model.buyTrueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellTrueName">sellTrueName</label>
			 <input type="text" class="form-control" name="sellTrueName" id="sellTrueName" value="${model.sellTrueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fixPriceCoinCode">fixPriceCoinCode</label>
			 <input type="text" class="form-control" name="fixPriceCoinCode" id="fixPriceCoinCode" value="${model.fixPriceCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" value="${model.fixPriceType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isCulAtferMoney">isCulAtferMoney</label>
			 <input type="text" class="form-control" name="isCulAtferMoney" id="isCulAtferMoney" value="${model.isCulAtferMoney}" disabled/>
		</div>
	</div>
 </div>

 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExOrderInfo",function(o){
 	o.see();
 });
 </script>




