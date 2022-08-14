 <!-- Copyright:    -->
 <!-- DealRecordAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-14 15:22:23      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/record/dealrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="accountId">数币账户Id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="transactionNum">流水号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" />
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="dealType">交易类型 1：静态收益 2：见点奖 3：管理奖 4：级别奖 5：共建社区奖励 6：出局 7：兑换</label>
			 <input type="text" class="form-control" name="dealType" id="dealType" />
		</div>
		<div class="form-group">
			 <label for="ratio">比例</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" />
		</div>
		<div class="form-group">
			 <label for="dealMoney">交易金额</label>
			 <input type="text" class="form-control" name="dealMoney" id="dealMoney" />
		</div>
		<div class="form-group">
			 <label for="codeValue">当前币价值（USDT）</label>
			 <input type="text" class="form-control" name="codeValue" id="codeValue" />
		</div>
		<div class="form-group">
			 <label for="predictExpendlimit">预计消耗额度</label>
			 <input type="text" class="form-control" name="predictExpendlimit" id="predictExpendlimit" />
		</div>
		<div class="form-group">
			 <label for="actualExpendlimit">实际消耗额度</label>
			 <input type="text" class="form-control" name="actualExpendlimit" id="actualExpendlimit" />
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
seajs.use("js/admin/licqb/record/DealRecord",function(o){
	o.add();
});
</script>




