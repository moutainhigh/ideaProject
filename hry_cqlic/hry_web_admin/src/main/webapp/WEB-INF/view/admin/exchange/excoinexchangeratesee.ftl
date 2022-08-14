 <!-- Copyright:    -->
 <!-- ExCoinExchangeRateSee.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-26 17:05:34      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExCoinExchangeRate--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/excoinexchangeratelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="baseCoinCode">基础币code</label>
			 <input type="text" class="form-control" name="baseCoinCode" id="baseCoinCode" value="${model.baseCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="baseCoinName">基础币名称</label>
			 <input type="text" class="form-control" name="baseCoinName" id="baseCoinName" value="${model.baseCoinName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="targetCoinCode">目标币code</label>
			 <input type="text" class="form-control" name="targetCoinCode" id="targetCoinCode" value="${model.targetCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="targetCoinName">目标币名称</label>
			 <input type="text" class="form-control" name="targetCoinName" id="targetCoinName" value="${model.targetCoinName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="exchangeRate">目标币对基础币汇率</label>
			 <input type="text" class="form-control" name="exchangeRate" id="exchangeRate" value="${model.exchangeRate}" disabled/>
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
 seajs.use("js/admin/exchange/ExCoinExchangeRate",function(o){
 	o.see();
 });
 </script>




