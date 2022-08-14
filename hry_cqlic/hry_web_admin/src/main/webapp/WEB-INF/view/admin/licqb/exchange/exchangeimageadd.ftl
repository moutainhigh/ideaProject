 <!-- Copyright:    -->
 <!-- ExchangeImageAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-12-17 16:38:08      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangeimagelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="configId">兑换配置id</label>
			 <input type="text" class="form-control" name="configId" id="configId" />
		</div>
		<div class="form-group">
			 <label for="languagetype">语种编码</label>
			 <input type="text" class="form-control" name="languagetype" id="languagetype" />
		</div>
		<div class="form-group">
			 <label for="image">图片路径</label>
			 <input type="text" class="form-control" name="image" id="image" />
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
seajs.use("js/admin/licqb/exchange/ExchangeImage",function(o){
	o.add();
});
</script>




