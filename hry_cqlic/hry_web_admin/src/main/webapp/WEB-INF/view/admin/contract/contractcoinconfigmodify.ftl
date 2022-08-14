 <!-- Copyright:    -->
 <!-- contractCoinConfigModify.html     -->
 <!-- @author:      hec  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-12-27 15:00:04      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">币种信息修改<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/contract/contractcoinconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="faceValue">合约面值</label>
			 <input type="text" class="form-control" name="faceValue" id="faceValue" value="${model.faceValue}"/>
		</div>
		<div class="form-group">
			 <label for="maxBuyIn">最大买入</label>
			 <input type="text" class="form-control" name="maxBuyIn" id="maxBuyIn" value="${model.maxBuyIn}"/>
		</div>
		<div class="form-group">
			 <label for="buyFeeRate">买入手续费</label>
			 <input type="text" class="form-control" name="buyFeeRate" id="buyFeeRate" value="${model.buyFeeRate}"/>
		</div>
		<div class="form-group">
			 <label for="sellFeeRate">卖出手续费</label>
			 <input type="text" class="form-control" name="sellFeeRate" id="sellFeeRate" value="${model.sellFeeRate}"/>
		</div>
		<div class="form-group">
			 <label for="keepDecimal">保留小数位</label>
			 <input type="text" class="form-control" name="keepDecimal" id="keepDecimal" value="${model.keepDecimal}"/>
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
 seajs.use("js/admin/contract/contractCoinConfig",function(o){
 	o.modify();
 });
 </script>






