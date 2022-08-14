 <!-- Copyright:    -->
 <!-- KlgRewardSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-05-24 18:13:05      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgReward--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/reward/klgrewardlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="sellTransactionNum">卖单流水号</label>
			 <input type="text" class="form-control" name="sellTransactionNum" id="sellTransactionNum" value="${model.sellTransactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountId">数字货币账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">奖励币种</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rewardMoney">奖励数量</label>
			 <input type="text" class="form-control" name="rewardMoney" id="rewardMoney" value="${model.rewardMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rewardType">奖励类型 1见点奖 2级差奖</label>
			 <input type="text" class="form-control" name="rewardType" id="rewardType" value="${model.rewardType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/reward/KlgReward",function(o){
 	o.see();
 });
 </script>




