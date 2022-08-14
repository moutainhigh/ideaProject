 <!-- Copyright:    -->
 <!-- IcoCustomerLevelModify.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-16 21:24:40      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoCustomerLevel--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/level/icocustomerlevellist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="level_id">等级id</label>
			 <input type="text" class="form-control" name="level_id" id="level_id" value="${model.level_id}"/>
		</div>
		<div class="form-group">
			 <label for="gradeName">等级名称</label>
			 <input type="text" class="form-control" name="gradeName" id="gradeName" value="${model.gradeName}"/>
		</div>
		<div class="form-group">
			 <label for="experience">经验值</label>
			 <input type="text" class="form-control" name="experience" id="experience" value="${model.experience}"/>
		</div>
		<div class="form-group">
			 <label for="vesion">版本</label>
			 <input type="text" class="form-control" name="vesion" id="vesion" value="${model.vesion}"/>
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
 seajs.use("js/admin/ico/level/IcoCustomerLevel",function(o){
 	o.modify();
 });
 </script>






