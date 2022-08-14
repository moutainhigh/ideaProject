 <!-- Copyright:    -->
 <!-- ExCoinExchangeRateModify.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-26 17:05:34      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">修改<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/excoinexchangeratelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="baseCoinCode">基础币code</label>
			 <input type="text" class="form-control" name="baseCoinCode" id="baseCoinCode" value="${model.baseCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="baseCoinName">基础币名称</label>
			 <input type="text" class="form-control" name="baseCoinName" id="baseCoinName" value="${model.baseCoinName}"/>
		</div>
		<div class="form-group">
			 <label for="targetCoinCode">目标币code</label>
			 <input type="text" class="form-control" name="targetCoinCode" id="targetCoinCode" value="${model.targetCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="targetCoinName">目标币名称</label>
			 <input type="text" class="form-control" name="targetCoinName" id="targetCoinName" value="${model.targetCoinName}"/>
		</div>
		<div class="form-group">
			 <label for="exchangeRate">目标币对基础币汇率</label>
			 <input type="text" class="form-control" name="exchangeRate" id="exchangeRate" value="${model.exchangeRate}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExCoinExchangeRate",function(o){
 	o.modify();
 });
 </script>






