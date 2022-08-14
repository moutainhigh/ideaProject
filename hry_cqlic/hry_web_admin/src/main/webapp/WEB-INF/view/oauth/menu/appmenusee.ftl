 <!-- Copyright:   互融云 -->
 <!-- AppMenuSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-06-01 19:44:41      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppMenu--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('/oauth/menu/appmenulist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="isOpen">isOpen</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="isOutLink">isOutLink</label>
			 <input type="text" class="form-control" name="isOutLink" id="isOutLink" value="${model.isOutLink}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="pid">pid</label>
			 <input type="text" class="form-control" name="pid" id="pid" value="${model.pid}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="shiroUrl">shiroUrl</label>
			 <input type="text" class="form-control" name="shiroUrl" id="shiroUrl" value="${model.shiroUrl}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="url">url</label>
			 <input type="text" class="form-control" name="url" id="url" value="${model.url}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="orderNo">orderNo</label>
			 <input type="text" class="form-control" name="orderNo" id="orderNo" value="${model.orderNo}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="appName">appName</label>
			 <input type="text" class="form-control" name="appName" id="appName" value="${model.appName}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="SaasId">SaasId</label>
			 <input type="text" class="form-control" name="SaasId" id="SaasId" value="${model.SaasId}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="created">created</label>
			 <input type="text" class="form-control" name="created" id="created" value="${model.created}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="modified">modified</label>
			 <input type="text" class="form-control" name="modified" id="modified" value="${model.modified}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="mkey">mkey</label>
			 <input type="text" class="form-control" name="mkey" id="mkey" value="${model.mkey}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="pkey">pkey</label>
			 <input type="text" class="form-control" name="pkey" id="pkey" value="${model.pkey}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="type">type</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="isVisible">isVisible</label>
			 <input type="text" class="form-control" name="isVisible" id="isVisible" value="${model.isVisible}" disabled/>
		</div>
	</div>
	
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/menu/AppMenu",function(o){
 	o.see();
 });
 </script>




