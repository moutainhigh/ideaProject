 <!-- Copyright:    -->
 <!-- ExEntrustSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:12:40      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExEntrust--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exentrustlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="entrustNum">entrustNum</label>
			 <input type="text" class="form-control" name="entrustNum" id="entrustNum" value="${model.entrustNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustPrice">entrustPrice</label>
			 <input type="text" class="form-control" name="entrustPrice" id="entrustPrice" value="${model.entrustPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustCount">entrustCount</label>
			 <input type="text" class="form-control" name="entrustCount" id="entrustCount" value="${model.entrustCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustSum">entrustSum</label>
			 <input type="text" class="form-control" name="entrustSum" id="entrustSum" value="${model.entrustSum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustTime">entrustTime</label>
			 <input type="text" class="form-control" name="entrustTime" id="entrustTime" value="${model.entrustTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="entrustWay">entrustWay</label>
			 <input type="text" class="form-control" name="entrustWay" id="entrustWay" value="${model.entrustWay}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surplusEntrustCount">surplusEntrustCount</label>
			 <input type="text" class="form-control" name="surplusEntrustCount" id="surplusEntrustCount" value="${model.surplusEntrustCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="source">source</label>
			 <input type="text" class="form-control" name="source" id="source" value="${model.source}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionFee">transactionFee</label>
			 <input type="text" class="form-control" name="transactionFee" id="transactionFee" value="${model.transactionFee}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionSum">transactionSum</label>
			 <input type="text" class="form-control" name="transactionSum" id="transactionSum" value="${model.transactionSum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="processedPrice">processedPrice</label>
			 <input type="text" class="form-control" name="processedPrice" id="processedPrice" value="${model.processedPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionFeeRate">transactionFeeRate</label>
			 <input type="text" class="form-control" name="transactionFeeRate" id="transactionFeeRate" value="${model.transactionFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerType">customerType</label>
			 <input type="text" class="form-control" name="customerType" id="customerType" value="${model.customerType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="floatUpPrice">floatUpPrice</label>
			 <input type="text" class="form-control" name="floatUpPrice" id="floatUpPrice" value="${model.floatUpPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="floatDownPrice">floatDownPrice</label>
			 <input type="text" class="form-control" name="floatDownPrice" id="floatDownPrice" value="${model.floatDownPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="matchPriority">matchPriority</label>
			 <input type="text" class="form-control" name="matchPriority" id="matchPriority" value="${model.matchPriority}" disabled/>
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
			 <label for="userCode">userCode</label>
			 <input type="text" class="form-control" name="userCode" id="userCode" value="${model.userCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trueName">trueName</label>
			 <input type="text" class="form-control" name="trueName" id="trueName" value="${model.trueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="projectType">projectType</label>
			 <input type="text" class="form-control" name="projectType" id="projectType" value="${model.projectType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" value="${model.transactionTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerIp">customerIp</label>
			 <input type="text" class="form-control" name="customerIp" id="customerIp" value="${model.customerIp}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fixPriceType">fixPriceType</label>
			 <input type="text" class="form-control" name="fixPriceType" id="fixPriceType" value="${model.fixPriceType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fixPriceCoinCode">fixPriceCoinCode</label>
			 <input type="text" class="form-control" name="fixPriceCoinCode" id="fixPriceCoinCode" value="${model.fixPriceCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinAccountId">coinAccountId</label>
			 <input type="text" class="form-control" name="coinAccountId" id="coinAccountId" value="${model.coinAccountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="surName">surName</label>
			 <input type="text" class="form-control" name="surName" id="surName" value="${model.surName}" disabled/>
		</div>
	</div>
 </div>

 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExEntrust",function(o){
 	o.see();
 });
 </script>




