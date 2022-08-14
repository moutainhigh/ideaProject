 <!-- Copyright:    -->
 <!-- ExProductParameterSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:49:05      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExProductParameter--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductparameterlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
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
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}" disabled/>
		</div>
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
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
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExProductParameter",function(o){
 	o.see();
 });
 </script>




