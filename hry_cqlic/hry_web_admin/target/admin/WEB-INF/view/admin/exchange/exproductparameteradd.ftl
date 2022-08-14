 <!-- Copyright:    -->
 <!-- ExProductParameterAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:49:05      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductparameterlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="buyFeeRate">buyFeeRate</label>
			 <input type="text" class="form-control" name="buyFeeRate" id="buyFeeRate" />
		</div>
		<div class="form-group">
			 <label for="sellFeeRate">sellFeeRate</label>
			 <input type="text" class="form-control" name="sellFeeRate" id="sellFeeRate" />
		</div>
		<div class="form-group">
			 <label for="buyMinMoney">buyMinMoney</label>
			 <input type="text" class="form-control" name="buyMinMoney" id="buyMinMoney" />
		</div>
		<div class="form-group">
			 <label for="sellMinCoin">sellMinCoin</label>
			 <input type="text" class="form-control" name="sellMinCoin" id="sellMinCoin" />
		</div>
		<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" />
		</div>
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" />
		</div>
		<div class="form-group">
			 <label for="prepaidFeeRate">prepaidFeeRate</label>
			 <input type="text" class="form-control" name="prepaidFeeRate" id="prepaidFeeRate" />
		</div>
		<div class="form-group">
			 <label for="paceFeeRate">paceFeeRate</label>
			 <input type="text" class="form-control" name="paceFeeRate" id="paceFeeRate" />
		</div>
		<div class="form-group">
			 <label for="oneTimePaceNum">oneTimePaceNum</label>
			 <input type="text" class="form-control" name="oneTimePaceNum" id="oneTimePaceNum" />
		</div>
		<div class="form-group">
			 <label for="oneDayPaceNum">oneDayPaceNum</label>
			 <input type="text" class="form-control" name="oneDayPaceNum" id="oneDayPaceNum" />
		</div>
		<div class="form-group">
			 <label for="leastPaceNum">leastPaceNum</label>
			 <input type="text" class="form-control" name="leastPaceNum" id="leastPaceNum" />
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

</div>
<script type="text/javascript">
seajs.use("js/admin/exchange/ExProductParameter",function(o){
	o.add();
});
</script>




