 <!-- Copyright:    -->
 <!-- MailSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:41:28      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Mail--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/maillist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
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
			 <label for="address">address</label>
			 <input type="text" class="form-control" name="address" id="address" value="${model.address}" disabled/>
		</div>
		<div class="form-group">
			 <label for="errorCode">errorCode</label>
			 <input type="text" class="form-control" name="errorCode" id="errorCode" value="${model.errorCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="errorContent">errorContent</label>
			 <input type="text" class="form-control" name="errorContent" id="errorContent" value="${model.errorContent}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/Mail",function(o){
 	o.see();
 });
 </script>




