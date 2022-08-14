 <!-- Copyright:    -->
 <!-- AppMessageTemplateSee.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:23:25      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppMessageTemplate--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appmessagetemplatelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="templateId">templateId</label>
			 <input type="text" class="form-control" name="templateId" id="templateId" value="${model.templateId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="title">title</label>
			 <input type="text" class="form-control" name="title" id="title" value="${model.title}" disabled/>
		</div>
		<div class="form-group">
			 <label for="content">content</label>
			 <input type="text" class="form-control" name="content" id="content" value="${model.content}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppMessageTemplate",function(o){
 	o.see();
 });
 </script>




