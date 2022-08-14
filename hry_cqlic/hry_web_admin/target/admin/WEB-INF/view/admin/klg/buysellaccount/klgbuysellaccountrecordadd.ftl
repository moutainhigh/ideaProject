 <!-- Copyright:    -->
 <!-- KlgBuySellAccountRecordAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-22 17:02:55      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/buysellaccount/klgbuysellaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="accountName">账户名称</label>
			 <input type="text" class="form-control" name="accountName" id="accountName" />
		</div>
		<div class="form-group">
			 <label for="changeMoney">变动金额</label>
			 <input type="text" class="form-control" name="changeMoney" id="changeMoney" />
		</div>
		<div class="form-group">
			 <label for="changeType">变动类型：1加 2减</label>
			 <input type="text" class="form-control" name="changeType" id="changeType" />
		</div>
		<div class="form-group">
			 <label for="triggered">触发点</label>
			 <input type="text" class="form-control" name="triggered" id="triggered" />
		</div>
		<div class="form-group">
			 <label for="orderNum">触发点订单号</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" />
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
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
seajs.use("js/admin/klg/buysellaccount/KlgBuySellAccountRecord",function(o){
	o.add();
});
</script>




