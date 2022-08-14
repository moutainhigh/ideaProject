 <!-- Copyright:    -->
 <!-- AppCommendTradeAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:50:18      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommendtradelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="custromerId">custromerId</label>
			 <input type="text" class="form-control" name="custromerId" id="custromerId" />
		</div>
		<div class="form-group">
			 <label for="custromerName">custromerName</label>
			 <input type="text" class="form-control" name="custromerName" id="custromerName" />
		</div>
		<div class="form-group">
			 <label for="ordertNum">ordertNum</label>
			 <input type="text" class="form-control" name="ordertNum" id="ordertNum" />
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
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
			 <label for="rewardmoney">rewardmoney</label>
			 <input type="text" class="form-control" name="rewardmoney" id="rewardmoney" />
		</div>
		<div class="form-group">
			 <label for="deliveryName">deliveryName</label>
			 <input type="text" class="form-control" name="deliveryName" id="deliveryName" />
		</div>
		<div class="form-group">
			 <label for="deliveryId">deliveryId</label>
			 <input type="text" class="form-control" name="deliveryId" id="deliveryId" />
		</div>
		<div class="form-group">
			 <label for="hierarchy">hierarchy</label>
			 <input type="text" class="form-control" name="hierarchy" id="hierarchy" />
		</div>
		<div class="form-group">
			 <label for="userMoney">userMoney</label>
			 <input type="text" class="form-control" name="userMoney" id="userMoney" />
		</div>
		<div class="form-group">
			 <label for="ratetype">ratetype</label>
			 <input type="text" class="form-control" name="ratetype" id="ratetype" />
		</div>
		<div class="form-group">
			 <label for="personType">personType</label>
			 <input type="text" class="form-control" name="personType" id="personType" />
		</div>
		<div class="form-group">
			 <label for="basemoney">basemoney</label>
			 <input type="text" class="form-control" name="basemoney" id="basemoney" />
		</div>
		<div class="form-group">
			 <label for="changeMoney">changeMoney</label>
			 <input type="text" class="form-control" name="changeMoney" id="changeMoney" />
		</div>
		<div class="form-group">
			 <label for="currentMoney">currentMoney</label>
			 <input type="text" class="form-control" name="currentMoney" id="currentMoney" />
		</div>
		<div class="form-group">
			 <label for="feeamout">feeamout</label>
			 <input type="text" class="form-control" name="feeamout" id="feeamout" />
		</div>
		<div class="form-group">
			 <label for="transactionTime">transactionTime</label>
			 <input type="text" class="form-control" name="transactionTime" id="transactionTime" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/commend/AppCommendTrade",function(o){
	o.add();
});
</script>




