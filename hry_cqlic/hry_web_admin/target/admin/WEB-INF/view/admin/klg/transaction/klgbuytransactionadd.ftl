 <!-- Copyright:    -->
 <!-- KlgBuyTransactionAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 11:40:59      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/transaction/klgbuytransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="transactionNum">流水号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" />
		</div>
		<div class="form-group">
			 <label for="accountId">数字货币账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="coinCode">币种</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="smeMoney">买入平台币金额</label>
			 <input type="text" class="form-control" name="smeMoney" id="smeMoney" />
		</div>
		<div class="form-group">
			 <label for="usdtMoney">需要支付USDT金额</label>
			 <input type="text" class="form-control" name="usdtMoney" id="usdtMoney" />
		</div>
		<div class="form-group">
			 <label for="firstMoney">保证金</label>
			 <input type="text" class="form-control" name="firstMoney" id="firstMoney" />
		</div>
		<div class="form-group">
			 <label for="lastMoney">尾款</label>
			 <input type="text" class="form-control" name="lastMoney" id="lastMoney" />
		</div>
		<div class="form-group">
			 <label for="interestMoney">利息</label>
			 <input type="text" class="form-control" name="interestMoney" id="interestMoney" />
		</div>
		<div class="form-group">
			 <label for="trusteeshipStatus">托管状态：1否 2是</label>
			 <input type="text" class="form-control" name="trusteeshipStatus" id="trusteeshipStatus" />
		</div>
		<div class="form-group">
			 <label for="rushOrders">是否抢单：1否 2是</label>
			 <input type="text" class="form-control" name="rushOrders" id="rushOrders" />
		</div>
		<div class="form-group">
			 <label for="status">订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 </label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="userId">操作人id</label>
			 <input type="text" class="form-control" name="userId" id="userId" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="timeStamp">排队开始时间戳</label>
			 <input type="text" class="form-control" name="timeStamp" id="timeStamp" />
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
seajs.use("js/admin/klg/transaction/KlgBuyTransaction",function(o){
	o.add();
});
</script>




