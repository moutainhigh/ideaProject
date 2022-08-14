 <!-- Copyright:    -->
 <!-- ExOrderInfoAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:26:42      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exorderinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="orderNum">orderNum</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="transactionPrice">transactionPrice</label>
			 <input type="text" class="form-control" name="transactionPrice" id="transactionPrice" />
		</div>
		<div class="form-group">
			 <label for="transactionCount">transactionCount</label>
			 <input type="text" class="form-control" name="transactionCount" id="transactionCount" />
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" />
		</div>
		<div class="form-group">
			 <label for="transactionSum">transactionSum</label>
			 <input type="text" class="form-control" name="transactionSum" id="transactionSum" />
		</div>
		<div class="form-group">
			 <label for="transactionBuyFee">transactionBuyFee</label>
			 <input type="text" class="form-control" name="transactionBuyFee" id="transactionBuyFee" />
		</div>
		<div class="form-group">
			 <label for="transactionSellFee">transactionSellFee</label>
			 <input type="text" class="form-control" name="transactionSellFee" id="transactionSellFee" />
		</div>
		<div class="form-group">
			 <label for="buyEntrustNum">buyEntrustNum</label>
			 <input type="text" class="form-control" name="buyEntrustNum" id="buyEntrustNum" />
		</div>
		<div class="form-group">
			 <label for="sellEntrustNum">sellEntrustNum</label>
			 <input type="text" class="form-control" name="sellEntrustNum" id="sellEntrustNum" />
		</div>
		<div class="form-group">
			 <label for="buyCustomId">buyCustomId</label>
			 <input type="text" class="form-control" name="buyCustomId" id="buyCustomId" />
		</div>
		<div class="form-group">
			 <label for="sellCustomId">sellCustomId</label>
			 <input type="text" class="form-control" name="sellCustomId" id="sellCustomId" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="transactionBuyFeeRate">transactionBuyFeeRate</label>
			 <input type="text" class="form-control" name="transactionBuyFeeRate" id="transactionBuyFeeRate" />
		</div>
		<div class="form-group">
			 <label for="transactionSellFeeRate">transactionSellFeeRate</label>
			 <input type="text" class="form-control" name="transactionSellFeeRate" id="transactionSellFeeRate" />
		</div>
		<div class="form-group">
			 <label for="productName">productName</label>
			 <input type="text" class="form-control" name="productName" id="productName" />
		</div>
		<div class="form-group">
			 <label for="currencyType">currencyType</label>
			 <input type="text" class="form-control" name="currencyType" id="currencyType" />
		</div>
		<div class="form-group">
			 <label for="website">website</label>
			 <input type="text" class="form-control" name="website" id="website" />
		</div>
		<div class="form-group">
			 <label for="orderTimestapm">orderTimestapm</label>
			 <input type="text" class="form-control" name="orderTimestapm" id="orderTimestapm" />
		</div>
		<div class="form-group">
			 <label for="inOrOutTransaction">inOrOutTransaction</label>
			 <input type="text" class="form-control" name="inOrOutTransaction" id="inOrOutTransaction" />
		</div>
		<div class="form-group">
			 <label for="buyUserCode">buyUserCode</label>
			 <input type="text" class="form-control" name="buyUserCode" id="buyUserCode" />
		</div>
		<div class="form-group">
			 <label for="sellUserCode">sellUserCode</label>
			 <input type="text" class="form-control" name="sellUserCode" id="sellUserCode" />
		</div>
		<div class="form-group">
			 <label for="sellUserName">sellUserName</label>
			 <input type="text" class="form-control" name="sellUserName" id="sellUserName" />
		</div>
		<div class="form-group">
			 <label for="buyUserName">buyUserName</label>
			 <input type="text" class="form-control" name="buyUserName" id="buyUserName" />
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" />
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" />
		</div>
		<div class="form-group">
			 <label for="transactionFee">transactionFee</label>
			 <input type="text" class="form-control" name="transactionFee" id="transactionFee" />
		</div>
		<div class="form-group">
			 <label for="transactionFeeRate">transactionFeeRate</label>
			 <input type="text" class="form-control" name="transactionFeeRate" id="transactionFeeRate" />
		</div>
		<div class="form-group">
			 <label for="entrustNum">entrustNum</label>
			 <input type="text" class="form-control" name="entrustNum" id="entrustNum" />
		</div>
		<div class="form-group">
			 <label for="profitandlossMoney">profitandlossMoney</label>
			 <input type="text" class="form-control" name="profitandlossMoney" id="profitandlossMoney" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
		<div class="form-group">
			 <label for="buyTrueName">buyTrueName</label>
			 <input type="text" class="form-control" name="buyTrueName" id="buyTrueName" />
		</div>
		<div class="form-group">
			 <label for="sellTrueName">sellTrueName</label>
			 <input type="text" class="form-control" name="sellTrueName" id="sellTrueName" />
		</div>
		<div class="form-group">
			 <label for="fixPriceCoinCode">fixPriceCoinCode</label>
			 <input type="text" class="form-control" name="fixPriceCoinCode" id="fixPriceCoinCode" />
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" />
		</div>
		<div class="form-group">
			 <label for="isCulAtferMoney">isCulAtferMoney</label>
			 <input type="text" class="form-control" name="isCulAtferMoney" id="isCulAtferMoney" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/ExOrderInfo",function(o){
	o.add();
});
</script>




