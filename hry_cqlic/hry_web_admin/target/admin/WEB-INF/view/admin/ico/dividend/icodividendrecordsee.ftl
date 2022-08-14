 <!-- Copyright:    -->
 <!-- IcoDividendRecordSee.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 20:56:02      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoDividendRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/dividend/icodividendrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">账户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" value="${model.customer_id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customer_email">账户邮箱</label>
			 <input type="text" class="form-control" name="customer_email" id="customer_email" value="${model.customer_email}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customer_mobile">账户手机</label>
			 <input type="text" class="form-control" name="customer_mobile" id="customer_mobile" value="${model.customer_mobile}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customer_level">账户等级</label>
			 <input type="text" class="form-control" name="customer_level" id="customer_level" value="${model.customer_level}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hedgeQuantity">锁仓量</label>
			 <input type="text" class="form-control" name="hedgeQuantity" id="hedgeQuantity" value="${model.hedgeQuantity}" disabled/>
		</div>
		<div class="form-group">
			 <label for="grossCommission">手续费总量</label>
			 <input type="text" class="form-control" name="grossCommission" id="grossCommission" value="${model.grossCommission}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coid_id">币id</label>
			 <input type="text" class="form-control" name="coid_id" id="coid_id" value="${model.coid_id}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种(CNY)</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinName">虚拟币种汉语名称(比特币 莱特币..)</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" value="${model.coinName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="dividend_radix">分红数量</label>
			 <input type="text" class="form-control" name="dividend_radix" id="dividend_radix" value="${model.dividend_radix}" disabled/>
		</div>
		<div class="form-group">
			 <label for="dividend_num">分红流水号</label>
			 <input type="text" class="form-control" name="dividend_num" id="dividend_num" value="${model.dividend_num}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">状态(1 分红发放中，2 成功 ，3 失败)</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/dividend/IcoDividendRecord",function(o){
 	o.see();
 });
 </script>




