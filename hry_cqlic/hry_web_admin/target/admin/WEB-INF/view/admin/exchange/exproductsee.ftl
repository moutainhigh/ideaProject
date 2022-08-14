 <!-- Copyright:    -->
 <!-- ExProductSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:44:37      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExProduct--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issueTotalMoney">issueTotalMoney</label>
			 <input type="text" class="form-control" name="issueTotalMoney" id="issueTotalMoney" value="${model.issueTotalMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="totalNum">totalNum</label>
			 <input type="text" class="form-control" name="totalNum" id="totalNum" value="${model.totalNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issuePrice">issuePrice</label>
			 <input type="text" class="form-control" name="issuePrice" id="issuePrice" value="${model.issuePrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issueTime">issueTime</label>
			 <input type="text" class="form-control" name="issueTime" id="issueTime" value="${model.issueTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issueState">issueState</label>
			 <input type="text" class="form-control" name="issueState" id="issueState" value="${model.issueState}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="splitMinCoin">splitMinCoin</label>
			 <input type="text" class="form-control" name="splitMinCoin" id="splitMinCoin" value="${model.splitMinCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issueId">issueId</label>
			 <input type="text" class="form-control" name="issueId" id="issueId" value="${model.issueId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="issueName">issueName</label>
			 <input type="text" class="form-control" name="issueName" id="issueName" value="${model.issueName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="stock">stock</label>
			 <input type="text" class="form-control" name="stock" id="stock" value="${model.stock}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sort">sort</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
		</div>
		<div class="form-group">
			 <label for="arithmetic">arithmetic</label>
			 <input type="text" class="form-control" name="arithmetic" id="arithmetic" value="${model.arithmetic}" disabled/>
		</div>
		<div class="form-group">
			 <label for="proveMode">proveMode</label>
			 <input type="text" class="form-control" name="proveMode" id="proveMode" value="${model.proveMode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="productReferral">productReferral</label>
			 <input type="text" class="form-control" name="productReferral" id="productReferral" value="${model.productReferral}" disabled/>
		</div>
		<div class="form-group">
			 <label for="pamState">pamState</label>
			 <input type="text" class="form-control" name="pamState" id="pamState" value="${model.pamState}" disabled/>
		</div>
		<div class="form-group">
			 <label for="blockspeed">blockspeed</label>
			 <input type="text" class="form-control" name="blockspeed" id="blockspeed" value="${model.blockspeed}" disabled/>
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
			 <label for="minBlockSize">minBlockSize</label>
			 <input type="text" class="form-control" name="minBlockSize" id="minBlockSize" value="${model.minBlockSize}" disabled/>
		</div>
		<div class="form-group">
			 <label for="maxBlockSize">maxBlockSize</label>
			 <input type="text" class="form-control" name="maxBlockSize" id="maxBlockSize" value="${model.maxBlockSize}" disabled/>
		</div>
		<div class="form-group">
			 <label for="walletDownload">walletDownload</label>
			 <input type="text" class="form-control" name="walletDownload" id="walletDownload" value="${model.walletDownload}" disabled/>
		</div>
		<div class="form-group">
			 <label for="soundDownload">soundDownload</label>
			 <input type="text" class="form-control" name="soundDownload" id="soundDownload" value="${model.soundDownload}" disabled/>
		</div>
		<div class="form-group">
			 <label for="blockBrowser">blockBrowser</label>
			 <input type="text" class="form-control" name="blockBrowser" id="blockBrowser" value="${model.blockBrowser}" disabled/>
		</div>
		<div class="form-group">
			 <label for="officialWebsite">officialWebsite</label>
			 <input type="text" class="form-control" name="officialWebsite" id="officialWebsite" value="${model.officialWebsite}" disabled/>
		</div>
		<div class="form-group">
			 <label for="officialForum">officialForum</label>
			 <input type="text" class="form-control" name="officialForum" id="officialForum" value="${model.officialForum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="miningAddress">miningAddress</label>
			 <input type="text" class="form-control" name="miningAddress" id="miningAddress" value="${model.miningAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isRecommend">isRecommend</label>
			 <input type="text" class="form-control" name="isRecommend" id="isRecommend" value="${model.isRecommend}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openingTime">openingTime</label>
			 <input type="text" class="form-control" name="openingTime" id="openingTime" value="${model.openingTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="closeTime">closeTime</label>
			 <input type="text" class="form-control" name="closeTime" id="closeTime" value="${model.closeTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="picturePath">picturePath</label>
			 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${model.picturePath}" disabled/>
		</div>
		<div class="form-group">
			 <label for="closePlateTime">closePlateTime</label>
			 <input type="text" class="form-control" name="closePlateTime" id="closePlateTime" value="${model.closePlateTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openAndclosePlateTime">openAndclosePlateTime</label>
			 <input type="text" class="form-control" name="openAndclosePlateTime" id="openAndclosePlateTime" value="${model.openAndclosePlateTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionType">transactionType</label>
			 <input type="text" class="form-control" name="transactionType" id="transactionType" value="${model.transactionType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="prepaidFeeRate">prepaidFeeRate</label>
			 <input type="text" class="form-control" name="prepaidFeeRate" id="prepaidFeeRate" value="${model.prepaidFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="paceFeeRate">paceFeeRate</label>
			 <input type="text" class="form-control" name="paceFeeRate" id="paceFeeRate" value="${model.paceFeeRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oneTimePaceNum">oneTimePaceNum</label>
			 <input type="text" class="form-control" name="oneTimePaceNum" id="oneTimePaceNum" value="${model.oneTimePaceNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oneDayPaceNum">oneDayPaceNum</label>
			 <input type="text" class="form-control" name="oneDayPaceNum" id="oneDayPaceNum" value="${model.oneDayPaceNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="leastPaceNum">leastPaceNum</label>
			 <input type="text" class="form-control" name="leastPaceNum" id="leastPaceNum" value="${model.leastPaceNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="circulation">circulation</label>
			 <input type="text" class="form-control" name="circulation" id="circulation" value="${model.circulation}" disabled/>
		</div>
		<div class="form-group">
			 <label for="priceLimits">priceLimits</label>
			 <input type="text" class="form-control" name="priceLimits" id="priceLimits" value="${model.priceLimits}" disabled/>
		</div>
		<div class="form-group">
			 <label for="keepDecimalForCoin">keepDecimalForCoin</label>
			 <input type="text" class="form-control" name="keepDecimalForCoin" id="keepDecimalForCoin" value="${model.keepDecimalForCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="keepDecimalForCurrency">keepDecimalForCurrency</label>
			 <input type="text" class="form-control" name="keepDecimalForCurrency" id="keepDecimalForCurrency" value="${model.keepDecimalForCurrency}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openBell">openBell</label>
			 <input type="text" class="form-control" name="openBell" id="openBell" value="${model.openBell}" disabled/>
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
			 <label for="oneTimeOrderNum">oneTimeOrderNum</label>
			 <input type="text" class="form-control" name="oneTimeOrderNum" id="oneTimeOrderNum" value="${model.oneTimeOrderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="giveCoin">giveCoin</label>
			 <input type="text" class="form-control" name="giveCoin" id="giveCoin" value="${model.giveCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="paceCurrecy">paceCurrecy</label>
			 <input type="text" class="form-control" name="paceCurrecy" id="paceCurrecy" value="${model.paceCurrecy}" disabled/>
		</div>
		<div class="form-group">
			 <label for="paceType">paceType</label>
			 <input type="text" class="form-control" name="paceType" id="paceType" value="${model.paceType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="c2cBuyPrice">c2cBuyPrice</label>
			 <input type="text" class="form-control" name="c2cBuyPrice" id="c2cBuyPrice" value="${model.c2cBuyPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="c2cSellPrice">c2cSellPrice</label>
			 <input type="text" class="form-control" name="c2cSellPrice" id="c2cSellPrice" value="${model.c2cSellPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="open_c2c">open_c2c</label>
			 <input type="text" class="form-control" name="open_c2c" id="open_c2c" value="${model.open_c2c}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openTibi">openTibi</label>
			 <input type="text" class="form-control" name="openTibi" id="openTibi" value="${model.openTibi}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderNo">orderNo</label>
			 <input type="text" class="form-control" name="orderNo" id="orderNo" value="${model.orderNo}" disabled/>
		</div>
		<div class="form-group">
			 <label for="commendCoin">commendCoin</label>
			 <input type="text" class="form-control" name="commendCoin" id="commendCoin" value="${model.commendCoin}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isOpenCheck">isOpenCheck</label>
			 <input type="text" class="form-control" name="isOpenCheck" id="isOpenCheck" value="${model.isOpenCheck}" disabled/>
		</div>
	</div>
 </div>

 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExProduct",function(o){
 	o.see();
 });
 </script>




