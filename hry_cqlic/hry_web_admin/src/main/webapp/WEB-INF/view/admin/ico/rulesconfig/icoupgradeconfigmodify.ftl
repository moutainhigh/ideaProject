 <!-- Copyright:    -->
 <!-- IcoUpgradeConfigModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-12 17:58:41      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">修改-升级规则   <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/rulesconfig/icoupgradeconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="gradeName">等级名称</label>
			 <input type="text" class="form-control" name="gradeName" id="gradeName" value="${model.gradeName}"/>
		</div>
		<div class="form-group">
			 <label for="conditionPara">条件值设定（大于等于)</label>
			 <input type="text" class="form-control" name="conditionPara" id="conditionPara" value="${model.conditionPara}"/>
		</div>
		<div class="form-group">
			 <label for="additionRatio">加成比例(%)</label>
			 <input type="text" class="form-control" name="additionRatio" id="additionRatio" value="${model.additionRatio}"/>
		</div>
        <div class="form-group">
            <label for="sort">等级排序</label>
            <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" />
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
 seajs.use("js/admin/ico/rulesconfig/IcoUpgradeConfig",function(o){
 	o.modify();
 });
 </script>






