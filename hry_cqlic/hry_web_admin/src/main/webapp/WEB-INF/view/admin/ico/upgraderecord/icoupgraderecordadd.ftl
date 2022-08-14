 <!-- Copyright:    -->
 <!-- IcoUpgradeRecordAdd.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-17 10:48:14      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/upgraderecord/icoupgraderecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="oldLevel_id">oldLevel_id</label>
			 <input type="text" class="form-control" name="oldLevel_id" id="oldLevel_id" />
		</div>
		<div class="form-group">
			 <label for="oldLevel">旧等级名称</label>
			 <input type="text" class="form-control" name="oldLevel" id="oldLevel" />
		</div>
		<div class="form-group">
			 <label for="newLevel_id">newLevel_id</label>
			 <input type="text" class="form-control" name="newLevel_id" id="newLevel_id" />
		</div>
		<div class="form-group">
			 <label for="newLevel">新等级名称</label>
			 <input type="text" class="form-control" name="newLevel" id="newLevel" />
		</div>
		<div class="form-group">
			 <label for="upgradeNote">升级备注</label>
			 <input type="text" class="form-control" name="upgradeNote" id="upgradeNote" />
		</div>
		<div class="form-group">
			 <label for="type">等级变化类型（1，升级，2 降级）</label>
			 <input type="text" class="form-control" name="type" id="type" />
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
seajs.use("js/admin/ico/upgraderecord/IcoUpgradeRecord",function(o){
	o.add();
});
</script>




