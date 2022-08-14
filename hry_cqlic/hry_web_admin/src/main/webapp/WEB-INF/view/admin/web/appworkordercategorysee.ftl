 <!-- Copyright:    -->
 <!-- appWorkOrderCategorySee.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:46:50      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
	 <div class="col-md-12">
		 <h3 class="page-header">工单类别查看  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/language.do?u=/admin/web/appworkordercategorylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
	 </div>
	 </div>


	 <form id="form" >
		 <div class="row">
			<div class="col-md-4 column">
				<#--<div class="form-group">
					<label for="sort">排序</label>
					<input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
				</div>-->
				<div class="form-group">
					<label for="languageDic">语言</label>
					<@HrySelect key="sysLanguage"  name="languageDic"  id="languageDic"  value="${model.languageDic}" readonly="readonly"> </@HrySelect>
				</div>
				<div class="form-group">
					<label for="typeName">类别名称</label>
					<input type="text" class="form-control" name="typeName" id="typeName" value="${model.typeName}" disabled/>
				</div>
				<div class="form-group">
					<label for="describes">描述</label>
					<input type="text" class="form-control" name="describes" id="describes" value="${model.describes}" disabled/>
				</div>
			</div>
		 </div>

	 </form>
 </div>
 <script type="text/javascript">
     seajs.use(["js/admin/web/appWorkOrderCategory","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
 		o.see();
	 });
 </script>




