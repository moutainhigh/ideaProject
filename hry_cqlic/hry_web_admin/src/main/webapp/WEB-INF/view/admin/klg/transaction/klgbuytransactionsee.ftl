 <!-- Copyright:    -->
 <!-- KlgBuyTransactionSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 11:40:59      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgBuyTransaction--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/transaction/klgbuytransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionNum">流水号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountId">数字货币账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="smeMoney">买入平台币金额</label>
			 <input type="text" class="form-control" name="smeMoney" id="smeMoney" value="${model.smeMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="usdtMoney">需要支付USDT金额</label>
			 <input type="text" class="form-control" name="usdtMoney" id="usdtMoney" value="${model.usdtMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="firstMoney">保证金</label>
			 <input type="text" class="form-control" name="firstMoney" id="firstMoney" value="${model.firstMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lastMoney">尾款</label>
			 <input type="text" class="form-control" name="lastMoney" id="lastMoney" value="${model.lastMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="interestMoney">利息</label>
			 <input type="text" class="form-control" name="interestMoney" id="interestMoney" value="${model.interestMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="trusteeshipStatus">托管状态：1否 2是</label>
			 <input type="text" class="form-control" name="trusteeshipStatus" id="trusteeshipStatus" value="${model.trusteeshipStatus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rushOrders">是否抢单：1否 2是</label>
			 <input type="text" class="form-control" name="rushOrders" id="rushOrders" value="${model.rushOrders}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">订单状态：1排队中 2排队成功待支付 3待收款 4已成交 5已作废 </label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userId">操作人id</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="timeStamp">排队开始时间戳</label>
			 <input type="text" class="form-control" name="timeStamp" id="timeStamp" value="${model.timeStamp}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/transaction/KlgBuyTransaction",function(o){
 	o.see();
 });
 </script>




