 <!-- Copyright:    -->
 <!-- UsdtTotalAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-11 12:01:59      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/usdttotallist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="payMoney">充值总数</label>
			 <input type="text" class="form-control" name="payMoney" id="payMoney" />
		</div>
		<div class="form-group">
			 <label for="extractMoney">提取总数</label>
			 <input type="text" class="form-control" name="extractMoney" id="extractMoney" />
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
seajs.use("js/admin/licqb/platform/UsdtTotal",function(o){
	o.add();
});
</script>




