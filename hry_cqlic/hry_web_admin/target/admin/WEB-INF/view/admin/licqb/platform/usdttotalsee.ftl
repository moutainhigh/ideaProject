 <!-- Copyright:    -->
 <!-- UsdtTotalSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-11 12:01:59      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">UsdtTotal--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/usdttotallist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="payMoney">充值总数</label>
			 <input type="text" class="form-control" name="payMoney" id="payMoney" value="${model.payMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="extractMoney">提取总数</label>
			 <input type="text" class="form-control" name="extractMoney" id="extractMoney" value="${model.extractMoney}" disabled/>
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
 seajs.use("js/admin/licqb/platform/UsdtTotal",function(o){
 	o.see();
 });
 </script>




