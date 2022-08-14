 <!-- Copyright:    -->
 <!-- IcoUpgradeConfigSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-12 17:58:41      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">查看-升级规则  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/rulesconfig/icoupgradeconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="gradeName">等级名称</label>
			 <input type="text" class="form-control" name="gradeName" id="gradeName" value="${model.gradeName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="conditionPara">条件值设定（大于等于)</label>
			 <input type="text" class="form-control" name="conditionPara" id="conditionPara" value="${model.conditionPara}" disabled/>
		</div>
		<div class="form-group">
			 <label for="additionRatio">加成比例(%)</label>
			 <input type="text" class="form-control" name="additionRatio" id="additionRatio" value="${model.additionRatio}" disabled/>
		</div>
        <div class="form-group">
            <label for="sort">等级排序</label>
            <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
        </div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/rulesconfig/IcoUpgradeConfig",function(o){
 	o.see();
 });
 </script>




