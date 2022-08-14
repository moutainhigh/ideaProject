 <!-- Copyright:    -->
 <!-- ExEntrustAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:12:40      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exentrustlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="entrustNum">entrustNum</label>
			 <input type="text" class="form-control" name="entrustNum" id="entrustNum" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" />
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="entrustPrice">entrustPrice</label>
			 <input type="text" class="form-control" name="entrustPrice" id="entrustPrice" />
		</div>
		<div class="form-group">
			 <label for="entrustCount">entrustCount</label>
			 <input type="text" class="form-control" name="entrustCount" id="entrustCount" />
		</div>
		<div class="form-group">
			 <label for="entrustSum">entrustSum</label>
			 <input type="text" class="form-control" name="entrustSum" id="entrustSum" />
		</div>
		<div class="form-group">
			 <label for="entrustTime">entrustTime</label>
			 <input type="text" class="form-control" name="entrustTime" id="entrustTime" />
		</div>
		<div class="form-group">
			 <label for="entrustWay">entrustWay</label>
			 <input type="text" class="form-control" name="entrustWay" id="entrustWay" />
		</div>
		<div class="form-group">
			 <label for="surplusEntrustCount">surplusEntrustCount</label>
			 <input type="text" class="form-control" name="surplusEntrustCount" id="surplusEntrustCount" />
		</div>
		<div class="form-group">
			 <label for="source">source</label>
			 <input type="text" class="form-control" name="source" id="source" />
		</div>
		<div class="form-group">
			 <label for="transactionFee">transactionFee</label>
			 <input type="text" class="form-control" name="transactionFee" id="transactionFee" />
		</div>
		<div class="form-group">
			 <label for="transactionSum">transactionSum</label>
			 <input type="text" class="form-control" name="transactionSum" id="transactionSum" />
		</div>
		<div class="form-group">
			 <label for="processedPrice">processedPrice</label>
			 <input type="text" class="form-control" name="processedPrice" id="processedPrice" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="transactionFeeRate">transactionFeeRate</label>
			 <input type="text" class="form-control" name="transactionFeeRate" id="transactionFeeRate" />
		</div>
		<div class="form-group">
			 <label for="customerType">customerType</label>
			 <input type="text" class="form-control" name="customerType" id="customerType" />
		</div>
		<div class="form-group">
			 <label for="floatUpPrice">floatUpPrice</label>
			 <input type="text" class="form-control" name="floatUpPrice" id="floatUpPrice" />
		</div>
		<div class="form-group">
			 <label for="floatDownPrice">floatDownPrice</label>
			 <input type="text" class="form-control" name="floatDownPrice" id="floatDownPrice" />
		</div>
		<div class="form-group">
			 <label for="matchPriority">matchPriority</label>
			 <input type="text" class="form-control" name="matchPriority" id="matchPriority" />
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
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" />
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" />
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" />
		</div>
		<div class="form-group">
			 <label for="projectType">projectType</label>
			 <input type="text" class="form-control" name="projectType" id="projectType" />
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" />
		</div>
		<div class="form-group">
			 <label for="customerIp">customerIp</label>
			 <input type="text" class="form-control" name="customerIp" id="customerIp" />
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" />
		</div>
		<div class="form-group">
			 <label for="fixPriceCoinCode">fixPriceCoinCode</label>
			 <input type="text" class="form-control" name="fixPriceCoinCode" id="fixPriceCoinCode" />
		</div>
		<div class="form-group">
			 <label for="coinAccountId">coinAccountId</label>
			 <input type="text" class="form-control" name="coinAccountId" id="coinAccountId" />
		</div>
		<div class="form-group">
			 <label for="surName">surName</label>
			 <input type="text" class="form-control" name="surName" id="surName" />
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
seajs.use("js/admin/exchange/ExEntrust",function(o){
	o.add();
});
</script>




