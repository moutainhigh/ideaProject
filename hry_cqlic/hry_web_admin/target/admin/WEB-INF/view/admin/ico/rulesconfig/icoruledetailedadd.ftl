 <!-- Copyright:    -->
 <!-- IcoRuleDetailedAdd.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-12 18:38:38      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加-发行  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/rulesconfig/icoruledetailedlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="periods">期数</label>
			 <input type="text" class="form-control" name="periods" id="periods" />
		</div>
		<div class="form-group">
			 <label for="saleNum">可售数量</label>
			 <input type="text" class="form-control" name="saleNum" id="saleNum" />
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
seajs.use("js/admin/ico/rulesconfig/IcoRuleDetailed",function(o){
	o.add();
});
</script>




