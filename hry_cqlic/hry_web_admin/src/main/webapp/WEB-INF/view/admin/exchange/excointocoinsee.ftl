 <!-- Copyright:    -->
 <!-- ExCointoCoinSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:52:02      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExCointoCoin--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/exchange/excointocoin/toList')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="toProductId">toProductId</label>
			 <input type="text" class="form-control" name="toProductId" id="toProductId" value="${model.toProductId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="fromProductId">fromProductId</label>
			 <input type="text" class="form-control" name="fromProductId" id="fromProductId" value="${model.fromProductId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
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
			 <label for="keepDecimalFixPrice">keepDecimalFixPrice</label>
			 <input type="text" class="form-control" name="keepDecimalFixPrice" id="keepDecimalFixPrice" value="${model.keepDecimalFixPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="decline">decline</label>
			 <input type="text" class="form-control" name="decline" id="decline" value="${model.decline}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rose">rose</label>
			 <input type="text" class="form-control" name="rose" id="rose" value="${model.rose}" disabled/>
		</div>
		<div class="form-group">
			 <label for="averagePrice">averagePrice</label>
			 <input type="text" class="form-control" name="averagePrice" id="averagePrice" value="${model.averagePrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyFeeRate">buyFeeRate</label>
			 <input type="text" class="form-control" name="buyFeeRate" id="buyFeeRate" value="${model.buyFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellFeeRate">sellFeeRate</label>
			 <input type="text" class="form-control" name="sellFeeRate" id="sellFeeRate" value="${model.sellFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyMinMoney">buyMinMoney</label>
			 <input type="text" class="form-control" name="buyMinMoney" id="buyMinMoney" value="${model.buyMinMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellMinCoin">sellMinCoin</label>
			 <input type="text" class="form-control" name="sellMinCoin" id="sellMinCoin" value="${model.sellMinCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="priceLimits">priceLimits</label>
			 <input type="text" class="form-control" name="priceLimits" id="priceLimits" value="${model.priceLimits}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oneTimeOrderNum">oneTimeOrderNum</label>
			 <input type="text" class="form-control" name="oneTimeOrderNum" id="oneTimeOrderNum" value="${model.oneTimeOrderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isSratAuto">isSratAuto</label>
			 <input type="text" class="form-control" name="isSratAuto" id="isSratAuto" value="${model.isSratAuto}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isFromChbtc">isFromChbtc</label>
			 <input type="text" class="form-control" name="isFromChbtc" id="isFromChbtc" value="${model.isFromChbtc}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoUsername">autoUsername</label>
			 <input type="text" class="form-control" name="autoUsername" id="autoUsername" value="${model.autoUsername}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoPrice">autoPrice</label>
			 <input type="text" class="form-control" name="autoPrice" id="autoPrice" value="${model.autoPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoPriceFloat">autoPriceFloat</label>
			 <input type="text" class="form-control" name="autoPriceFloat" id="autoPriceFloat" value="${model.autoPriceFloat}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoCount">autoCount</label>
			 <input type="text" class="form-control" name="autoCount" id="autoCount" value="${model.autoCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoCountFloat">autoCountFloat</label>
			 <input type="text" class="form-control" name="autoCountFloat" id="autoCountFloat" value="${model.autoCountFloat}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="atuoPriceType">atuoPriceType</label>
			 <input type="text" class="form-control" name="atuoPriceType" id="atuoPriceType" value="${model.atuoPriceType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="upFloatPer">upFloatPer</label>
			 <input type="text" class="form-control" name="upFloatPer" id="upFloatPer" value="${model.upFloatPer}" disabled/>
		</div>
	</div>
 </div>

 </form>
</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExCointoCoin",function(o){
 	o.see();
 });
 </script>




