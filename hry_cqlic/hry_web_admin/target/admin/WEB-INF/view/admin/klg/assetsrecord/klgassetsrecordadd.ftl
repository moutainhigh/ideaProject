 <!-- Copyright:    -->
 <!-- KlgAssetsRecordAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-29 15:54:03      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/assetsrecord/klgassetsrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="accountId">币账户Id</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" />
		</div>
		<div class="form-group">
			 <label for="serialNumber">流水号</label>
			 <input type="text" class="form-control" name="serialNumber" id="serialNumber" />
		</div>
		<div class="form-group">
			 <label for="accountType">账户类型 1.热账户 2.冷账户</label>
			 <input type="text" class="form-control" name="accountType" id="accountType" />
		</div>
		<div class="form-group">
			 <label for="coinCode">币种Code</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="changeCount">交易量</label>
			 <input type="text" class="form-control" name="changeCount" id="changeCount" />
		</div>
		<div class="form-group">
			 <label for="changeType">变动类型 1加 2减</label>
			 <input type="text" class="form-control" name="changeType" id="changeType" />
		</div>
		<div class="form-group">
			 <label for="triggerPoint">触发点</label>
			 <input type="text" class="form-control" name="triggerPoint" id="triggerPoint" />
		</div>
		<div class="form-group">
			 <label for="triggerSerialNumber">触发点流水号</label>
			 <input type="text" class="form-control" name="triggerSerialNumber" id="triggerSerialNumber" />
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
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
seajs.use("js/admin/klg/assetsrecord/KlgAssetsRecord",function(o){
	o.add();
});
</script>




