 <!-- Copyright:    -->
 <!-- exRobotDeepAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-12 20:31:39      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotdeeplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
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
			 <label for="robotType">robotType</label>
			 <input type="text" class="form-control" name="robotType" id="robotType" />
		</div>
		<div class="form-group">
			 <label for="isSratAuto">isSratAuto</label>
			 <input type="text" class="form-control" name="isSratAuto" id="isSratAuto" />
		</div>
		<div class="form-group">
			 <label for="autoUsername">autoUsername</label>
			 <input type="text" class="form-control" name="autoUsername" id="autoUsername" />
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="buyDeep">buyDeep</label>
			 <input type="text" class="form-control" name="buyDeep" id="buyDeep" />
		</div>
		<div class="form-group">
			 <label for="sellDeep">sellDeep</label>
			 <input type="text" class="form-control" name="sellDeep" id="sellDeep" />
		</div>
		<div class="form-group">
			 <label for="everyEntrustCount">everyEntrustCount</label>
			 <input type="text" class="form-control" name="everyEntrustCount" id="everyEntrustCount" />
		</div>
		<div class="form-group">
			 <label for="stopLine">stopLine</label>
			 <input type="text" class="form-control" name="stopLine" id="stopLine" />
		</div>
		<div class="form-group">
			 <label for="buyOneDiffRate">buyOneDiffRate</label>
			 <input type="text" class="form-control" name="buyOneDiffRate" id="buyOneDiffRate" />
		</div>
		<div class="form-group">
			 <label for="sellOneDiffRate">sellOneDiffRate</label>
			 <input type="text" class="form-control" name="sellOneDiffRate" id="sellOneDiffRate" />
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/exRobotDeep",function(o){
	o.add();
});
</script>




