 <!-- Copyright:    -->
 <!-- ExLendDetailSee.html     -->
 <!-- @author:      HeC  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-18 17:58:04      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">借款明细<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/lend/exlenddetaillist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">交易对</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lendCode">借款币种</label>
			 <input type="text" class="form-control" name="lendCode" id="lendCode" value="${model.lendCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lendCount">申请的数量</label>
			 <input type="text" class="form-control" name="lendCount" id="lendCount" value="${model.lendCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ratio">利率</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" value="${model.ratio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="dayCount">计算天数</label>
			 <input type="text" class="form-control" name="dayCount" id="dayCount" value="${model.dayCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="repayInerest">已还利息</label>
			 <input type="text" class="form-control" name="repayInerest" id="repayInerest" value="${model.repayInerest}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nopayInerest">未偿还利息</label>
			 <input type="text" class="form-control" name="nopayInerest" id="nopayInerest" value="${model.nopayInerest}" disabled/>
		</div>
		<div class="form-group">
			 <label for="repayMoney">已偿还本金</label>
			 <input type="text" class="form-control" name="repayMoney" id="repayMoney" value="${model.repayMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">还款状态</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/lend/ExLendDetail",function(o){
 	o.see();
 });
 </script>




