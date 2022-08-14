 <!-- Copyright:    -->
 <!-- DealRecordSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-14 15:22:23      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">DealRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/record/dealrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="accountId">数币账户Id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionNum">流水号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="dealType">交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑换</label>
			 <input type="text" class="form-control" name="dealType" id="dealType" value="${model.dealType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ratio">比例</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" value="${model.ratio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="dealMoney">交易金额</label>
			 <input type="text" class="form-control" name="dealMoney" id="dealMoney" value="${model.dealMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="codeValue">当前币价值（USDT）</label>
			 <input type="text" class="form-control" name="codeValue" id="codeValue" value="${model.codeValue}" disabled/>
		</div>
		<div class="form-group">
			 <label for="predictExpendlimit">预计消耗额度</label>
			 <input type="text" class="form-control" name="predictExpendlimit" id="predictExpendlimit" value="${model.predictExpendlimit}" disabled/>
		</div>
		<div class="form-group">
			 <label for="actualExpendlimit">实际消耗额度</label>
			 <input type="text" class="form-control" name="actualExpendlimit" id="actualExpendlimit" value="${model.actualExpendlimit}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/record/DealRecord",function(o){
 	o.see();
 });
 </script>




