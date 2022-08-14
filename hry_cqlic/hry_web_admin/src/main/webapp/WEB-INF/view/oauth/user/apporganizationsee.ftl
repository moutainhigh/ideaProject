 <!-- Copyright:   互融云 -->
 <!-- AppUserSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppUser--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/oauth/user/appuserlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="username">username</label>
			 <input type="text" class="form-control" name="username" id="username" value="${model.username}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="password">password</label>
			 <input type="text" class="form-control" name="password" id="password" value="${model.password}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="salt">salt</label>
			 <input type="text" class="form-control" name="salt" id="salt" value="${model.salt}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="isdelete">isdelete</label>
			 <input type="text" class="form-control" name="isdelete" id="isdelete" value="${model.isdelete}" disabled/>
		</div>
	</div>
	
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="islock">islock</label>
			 <input type="text" class="form-control" name="islock" id="islock" value="${model.islock}" disabled/>
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
	
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/oauth/user/AppUser",function(o){
 	o.see();
 });
 </script>




