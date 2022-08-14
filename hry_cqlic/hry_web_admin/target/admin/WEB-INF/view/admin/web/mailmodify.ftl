 <!-- Copyright:    -->
 <!-- MailModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:41:28      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Mail--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/maillist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="title">title</label>
			 <input type="text" class="form-control" name="title" id="title" value="${model.title}"/>
		</div>
		<div class="form-group">
			 <label for="content">content</label>
			 <input type="text" class="form-control" name="content" id="content" value="${model.content}"/>
		</div>
		<div class="form-group">
			 <label for="address">address</label>
			 <input type="text" class="form-control" name="address" id="address" value="${model.address}"/>
		</div>
		<div class="form-group">
			 <label for="errorCode">errorCode</label>
			 <input type="text" class="form-control" name="errorCode" id="errorCode" value="${model.errorCode}"/>
		</div>
		<div class="form-group">
			 <label for="errorContent">errorContent</label>
			 <input type="text" class="form-control" name="errorContent" id="errorContent" value="${model.errorContent}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/Mail",function(o){
 	o.modify();
 });
 </script>






