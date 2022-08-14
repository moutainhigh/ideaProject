 <!-- Copyright:    -->
 <!-- IcoUpgradeRecordModify.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-17 10:48:14      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoUpgradeRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/upgraderecord/icoupgraderecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">用户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" value="${model.customer_id}"/>
		</div>
		<div class="form-group">
			 <label for="oldLevel_id">oldLevel_id</label>
			 <input type="text" class="form-control" name="oldLevel_id" id="oldLevel_id" value="${model.oldLevel_id}"/>
		</div>
		<div class="form-group">
			 <label for="oldLevel">旧等级名称</label>
			 <input type="text" class="form-control" name="oldLevel" id="oldLevel" value="${model.oldLevel}"/>
		</div>
		<div class="form-group">
			 <label for="newLevel_id">newLevel_id</label>
			 <input type="text" class="form-control" name="newLevel_id" id="newLevel_id" value="${model.newLevel_id}"/>
		</div>
		<div class="form-group">
			 <label for="newLevel">新等级名称</label>
			 <input type="text" class="form-control" name="newLevel" id="newLevel" value="${model.newLevel}"/>
		</div>
		<div class="form-group">
			 <label for="upgradeNote">升级备注</label>
			 <input type="text" class="form-control" name="upgradeNote" id="upgradeNote" value="${model.upgradeNote}"/>
		</div>
		<div class="form-group">
			 <label for="type">等级变化类型（1，升级，2 降级）</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/upgraderecord/IcoUpgradeRecord",function(o){
 	o.modify();
 });
 </script>






