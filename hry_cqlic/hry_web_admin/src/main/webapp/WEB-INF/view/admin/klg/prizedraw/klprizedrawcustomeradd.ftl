 <!-- Copyright:    -->
 <!-- KlPrizedrawCustomerAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-06 11:31:26      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/prizedraw/klprizedrawcustomerlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="issueNumber">期号</label>
			 <input type="text" class="form-control" name="issueNumber" id="issueNumber" />
		</div>
		<div class="form-group">
			 <label for="prizedrawNumber">抽奖号码</label>
			 <input type="text" class="form-control" name="prizedrawNumber" id="prizedrawNumber" />
		</div>
		<div class="form-group">
			 <label for="startDate">开奖时间</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="status">状态(1.未开奖 2.已开奖)</label>
			 <input type="text" class="form-control" name="status" id="status" />
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
seajs.use("js/admin/klg/prizedraw/KlPrizedrawCustomer",function(o){
	o.add();
});
</script>




