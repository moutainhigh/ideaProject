 <!-- Copyright:    -->
 <!-- KlgBuySellAccountRecordSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-22 17:02:55      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgBuySellAccountRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/buysellaccount/klgbuysellaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="changeMoney">变动金额</label>
			 <input type="text" class="form-control" name="changeMoney" id="changeMoney" value="${model.changeMoney}" disabled/>
		</div>
		<div class="form-group">
			 <label for="changeType">变动类型：1加 2减</label>
			 <input type="text" class="form-control" name="changeType" id="changeType" value="${model.changeType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="triggered">触发点</label>
			 <input type="text" class="form-control" name="triggered" id="triggered" value="${model.triggered}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderNum">触发点订单号</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" value="${model.orderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
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
 seajs.use("js/admin/klg/buysellaccount/KlgBuySellAccountRecord",function(o){
 	o.see();
 });
 </script>




