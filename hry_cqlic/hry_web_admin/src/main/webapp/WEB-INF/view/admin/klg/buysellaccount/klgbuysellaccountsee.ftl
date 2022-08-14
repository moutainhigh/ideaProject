 <!-- Copyright:    -->
 <!-- KlgBuySellAccountSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-22 17:02:32      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgBuySellAccount--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/buysellaccount/klgbuysellaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="accountName">账户名称</label>
			 <input type="text" class="form-control" name="accountName" id="accountName" value="${model.accountName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">账户币种</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="money">剩余金额</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}" disabled/>
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
 seajs.use("js/admin/klg/buysellaccount/KlgBuySellAccount",function(o){
 	o.see();
 });
 </script>




