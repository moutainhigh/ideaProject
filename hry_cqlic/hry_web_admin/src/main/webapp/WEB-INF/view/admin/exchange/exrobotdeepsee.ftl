 <!-- Copyright:    -->
 <!-- exRobotDeepSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-12 20:31:39      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">exRobotDeep--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotdeeplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
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
			 <label for="robotType">robotType</label>
			 <input type="text" class="form-control" name="robotType" id="robotType" value="${model.robotType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isSratAuto">isSratAuto</label>
			 <input type="text" class="form-control" name="isSratAuto" id="isSratAuto" value="${model.isSratAuto}" disabled/>
		</div>
		<div class="form-group">
			 <label for="autoUsername">autoUsername</label>
			 <input type="text" class="form-control" name="autoUsername" id="autoUsername" value="${model.autoUsername}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyDeep">buyDeep</label>
			 <input type="text" class="form-control" name="buyDeep" id="buyDeep" value="${model.buyDeep}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellDeep">sellDeep</label>
			 <input type="text" class="form-control" name="sellDeep" id="sellDeep" value="${model.sellDeep}" disabled/>
		</div>
		<div class="form-group">
			 <label for="everyEntrustCount">everyEntrustCount</label>
			 <input type="text" class="form-control" name="everyEntrustCount" id="everyEntrustCount" value="${model.everyEntrustCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="stopLine">stopLine</label>
			 <input type="text" class="form-control" name="stopLine" id="stopLine" value="${model.stopLine}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyOneDiffRate">buyOneDiffRate</label>
			 <input type="text" class="form-control" name="buyOneDiffRate" id="buyOneDiffRate" value="${model.buyOneDiffRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellOneDiffRate">sellOneDiffRate</label>
			 <input type="text" class="form-control" name="sellOneDiffRate" id="sellOneDiffRate" value="${model.sellOneDiffRate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" value="${model.openTime}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/exRobotDeep",function(o){
 	o.see();
 });
 </script>




