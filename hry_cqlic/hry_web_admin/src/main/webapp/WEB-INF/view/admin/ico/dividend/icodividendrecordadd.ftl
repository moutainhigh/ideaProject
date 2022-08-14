 <!-- Copyright:    -->
 <!-- IcoDividendRecordAdd.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 20:56:02      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/dividend/icodividendrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">账户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" />
		</div>
		<div class="form-group">
			 <label for="customer_email">账户邮箱</label>
			 <input type="text" class="form-control" name="customer_email" id="customer_email" />
		</div>
		<div class="form-group">
			 <label for="customer_mobile">账户手机</label>
			 <input type="text" class="form-control" name="customer_mobile" id="customer_mobile" />
		</div>
		<div class="form-group">
			 <label for="customer_level">账户等级</label>
			 <input type="text" class="form-control" name="customer_level" id="customer_level" />
		</div>
		<div class="form-group">
			 <label for="hedgeQuantity">锁仓量</label>
			 <input type="text" class="form-control" name="hedgeQuantity" id="hedgeQuantity" />
		</div>
		<div class="form-group">
			 <label for="grossCommission">手续费总量</label>
			 <input type="text" class="form-control" name="grossCommission" id="grossCommission" />
		</div>
		<div class="form-group">
			 <label for="coid_id">币id</label>
			 <input type="text" class="form-control" name="coid_id" id="coid_id" />
		</div>
		<div class="form-group">
			 <label for="coinCode">币种(CNY)</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="coinName">虚拟币种汉语名称(比特币 莱特币..)</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" />
		</div>
		<div class="form-group">
			 <label for="dividend_radix">分红数量</label>
			 <input type="text" class="form-control" name="dividend_radix" id="dividend_radix" />
		</div>
		<div class="form-group">
			 <label for="dividend_num">分红流水号</label>
			 <input type="text" class="form-control" name="dividend_num" id="dividend_num" />
		</div>
		<div class="form-group">
			 <label for="status">状态(1 分红发放中，2 成功 ，3 失败)</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/ico/dividend/IcoDividendRecord",function(o){
	o.add();
});
</script>




