 <!-- Copyright:    -->
 <!-- ExRobotSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExRobot--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="exCointoCoinId">exCointoCoinId</label>
			 <input type="text" class="form-control" name="exCointoCoinId" id="exCointoCoinId" value="${model.exCointoCoinId}" disabled/>
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
		<div class="form-group">
			 <label for="isRobotSelf">isRobotSelf</label>
			 <input type="text" class="form-control" name="isRobotSelf" id="isRobotSelf" value="${model.isRobotSelf}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isStartThirdPrice">isStartThirdPrice</label>
			 <input type="text" class="form-control" name="isStartThirdPrice" id="isStartThirdPrice" value="${model.isStartThirdPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="priceSource">priceSource</label>
			 <input type="text" class="form-control" name="priceSource" id="priceSource" value="${model.priceSource}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>
</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExRobot",function(o){
 	o.see();
 });
 </script>




