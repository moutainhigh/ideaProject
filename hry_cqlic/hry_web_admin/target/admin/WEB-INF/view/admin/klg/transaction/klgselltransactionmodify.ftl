 <!-- Copyright:    -->
 <!-- KlgSellTransactionModify.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 14:25:18      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgSellTransaction--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/transaction/klgselltransactionlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="customerGrade">排单时用户等级</label>
			 <input type="text" class="form-control" name="customerGrade" id="customerGrade" value="${model.customerGrade}"/>
		</div>
		<div class="form-group">
			 <label for="transactionNum">流水号</label>
			 <input type="text" class="form-control" name="transactionNum" id="transactionNum" value="${model.transactionNum}"/>
		</div>
		<div class="form-group">
			 <label for="accountId">数字货币账户id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="usdtMoney">卖出后获取瓜分后总USDT</label>
			 <input type="text" class="form-control" name="usdtMoney" id="usdtMoney" value="${model.usdtMoney}"/>
		</div>
		<div class="form-group">
			 <label for="smeMoney">卖出平台币金额</label>
			 <input type="text" class="form-control" name="smeMoney" id="smeMoney" value="${model.smeMoney}"/>
		</div>
		<div class="form-group">
			 <label for="candySmeMoney">当前应获糖果总金额(SME）</label>
			 <input type="text" class="form-control" name="candySmeMoney" id="candySmeMoney" value="${model.candySmeMoney}"/>
		</div>
		<div class="form-group">
			 <label for="smeusdtRate">排队时SME-USDT汇率</label>
			 <input type="text" class="form-control" name="smeusdtRate" id="smeusdtRate" value="${model.smeusdtRate}"/>
		</div>
		<div class="form-group">
			 <label for="status">1排队中 2排队成功待收款 3已完成</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		<div class="form-group">
			 <label for="userId">操作人id</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="timeStamp">排队开始时间戳</label>
			 <input type="text" class="form-control" name="timeStamp" id="timeStamp" value="${model.timeStamp}"/>
		</div>
		<div class="form-group">
			 <label for="oneselfRate">本人获取糖果比例</label>
			 <input type="text" class="form-control" name="oneselfRate" id="oneselfRate" value="${model.oneselfRate}"/>
		</div>
		<div class="form-group">
			 <label for="platformRate">平台获取糖果比例</label>
			 <input type="text" class="form-control" name="platformRate" id="platformRate" value="${model.platformRate}"/>
		</div>
		<div class="form-group">
			 <label for="seePointRate">见点糖果比例</label>
			 <input type="text" class="form-control" name="seePointRate" id="seePointRate" value="${model.seePointRate}"/>
		</div>
		<div class="form-group">
			 <label for="gradationRate">级差糖果比例</label>
			 <input type="text" class="form-control" name="gradationRate" id="gradationRate" value="${model.gradationRate}"/>
		</div>
		<div class="form-group">
			 <label for="prizeRate">周奖糖果比例</label>
			 <input type="text" class="form-control" name="prizeRate" id="prizeRate" value="${model.prizeRate}"/>
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
 seajs.use("js/admin/klg/transaction/KlgSellTransaction",function(o){
 	o.modify();
 });
 </script>






