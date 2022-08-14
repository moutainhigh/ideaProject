 <!-- Copyright:    -->
 <!-- IcoCustomerLevelAdd.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-16 21:24:40      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/level/icocustomerlevellist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">用户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" />
		</div>
		<div class="form-group">
			 <label for="level_id">等级id</label>
			 <input type="text" class="form-control" name="level_id" id="level_id" />
		</div>
		<div class="form-group">
			 <label for="gradeName">等级名称</label>
			 <input type="text" class="form-control" name="gradeName" id="gradeName" />
		</div>
		<div class="form-group">
			 <label for="experience">经验值</label>
			 <input type="text" class="form-control" name="experience" id="experience" />
		</div>
		<div class="form-group">
			 <label for="vesion">版本</label>
			 <input type="text" class="form-control" name="vesion" id="vesion" />
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
seajs.use("js/admin/ico/level/IcoCustomerLevel",function(o){
	o.add();
});
</script>




