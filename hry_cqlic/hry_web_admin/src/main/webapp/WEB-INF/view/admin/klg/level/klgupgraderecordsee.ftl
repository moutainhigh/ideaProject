 <!-- Copyright:    -->
 <!-- KlgUpgradeRecordSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-05-17 13:37:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgUpgradeRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klgupgraderecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldLevelId">oldLevelId</label>
			 <input type="text" class="form-control" name="oldLevelId" id="oldLevelId" value="${model.oldLevelId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldLevel">旧等级名称</label>
			 <input type="text" class="form-control" name="oldLevel" id="oldLevel" value="${model.oldLevel}" disabled/>
		</div>
		<div class="form-group">
			 <label for="newLevelId">newLevelId</label>
			 <input type="text" class="form-control" name="newLevelId" id="newLevelId" value="${model.newLevelId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="newLevel">新等级名称</label>
			 <input type="text" class="form-control" name="newLevel" id="newLevel" value="${model.newLevel}" disabled/>
		</div>
		<div class="form-group">
			 <label for="upgradeNote">升级备注</label>
			 <input type="text" class="form-control" name="upgradeNote" id="upgradeNote" value="${model.upgradeNote}" disabled/>
		</div>
		<div class="form-group">
			 <label for="type">等级变化类型（1，升级，2 降级）</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldGradation">老的级别差</label>
			 <input type="text" class="form-control" name="oldGradation" id="oldGradation" value="${model.oldGradation}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oldPointAlgebra">老的见点代数</label>
			 <input type="text" class="form-control" name="oldPointAlgebra" id="oldPointAlgebra" value="${model.oldPointAlgebra}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nowGradation">新的级别差</label>
			 <input type="text" class="form-control" name="nowGradation" id="nowGradation" value="${model.nowGradation}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nowPointAlgebra">新的见点代数</label>
			 <input type="text" class="form-control" name="nowPointAlgebra" id="nowPointAlgebra" value="${model.nowPointAlgebra}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/level/KlgUpgradeRecord",function(o){
 	o.see();
 });
 </script>




