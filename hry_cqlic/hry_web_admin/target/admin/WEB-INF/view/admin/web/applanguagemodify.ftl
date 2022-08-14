 <!-- Copyright:    -->
 <!-- AppLanguageModify.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 17:47:13      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppLanguage--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/applanguagelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="langKey">langKey</label>
			 <input type="text" class="form-control" name="langKey" id="langKey" value="${model.langKey}"/>
		</div>
		<div class="form-group">
			 <label for="langVal">langVal</label>
			 <input type="text" class="form-control" name="langVal" id="langVal" value="${model.langVal}"/>
		</div>
		<div class="form-group">
			 <label for="langType">langType</label>
			 <input type="text" class="form-control" name="langType" id="langType" value="${model.langType}"/>
		</div>
		<div class="form-group">
			 <label for="langCode">langCode</label>
			 <input type="text" class="form-control" name="langCode" id="langCode" value="${model.langCode}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppLanguage",function(o){
 	o.modify();
 });
 </script>






