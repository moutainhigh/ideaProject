 <!-- Copyright:    -->
 <!-- appWorkOrderCategoryModify.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:46:51      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
	 <div class="col-md-12">
		 <h3 class="page-header">工单类别修改  <button type="button"  class="btn btn-warning pull-right"  onclick="loadUrl('${ctx}/language.do?u=/admin/web/appworkordercategorylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
	 </div>
	 </div>


	 <form id="form" >
	 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
	 <div class="row">
		<div class="col-md-4 column">
			<#--<div class="form-group">
				<label for="sort">排序</label>
				<input type="text" class="form-control" name="sort" id="sort" value="${model.sort}"/>
			</div>-->
			<div class="form-group">
				<label for="languageDic">语言</label>
							<@HrySelect key="sysLanguage"  name="languageDic"  id="languageDic"  value="${model.languageDic}" > </@HrySelect>
			</div>
			<div class="form-group">
				<label for="typeName">类别名称</label>
				<input type="text" class="form-control" name="typeName" id="typeName" value="${model.typeName}"/>
			</div>
			<div class="form-group">
				<label for="describes">描述</label>
				<input type="text" class="form-control" name="describes" id="describes" value="${model.describes}"/>
			</div>
		</div>
	 </div>


	 <div class="row">
		 <div class="col-md-2 column">
			<button type="button" id="modifySubmit" class="btn btn-success btn-warning" >提交</button>
		 </div>
	 </div>
	 </form>
 </div>
 <script type="text/javascript">
     seajs.use(["js/admin/web/appWorkOrderCategory","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
         o.modify();
 });
 </script>






