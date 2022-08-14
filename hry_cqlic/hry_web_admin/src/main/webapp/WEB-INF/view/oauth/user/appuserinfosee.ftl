 <!-- Copyright:   互融云 -->
 <!-- AppUserInfoSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-07-04 15:33:43      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppUserInfo--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('/oauth/user/appuserinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userid">userid</label>
			 <input type="text" class="form-control" name="userid" id="userid" value="${model.userid}" disabled/>
		</div>
		<div class="form-group">
			 <label for="age">age</label>
			 <input type="text" class="form-control" name="age" id="age" value="${model.age}" disabled/>
		</div>
		<div class="form-group">
			 <label for="birthday">birthday</label>
			 <input type="text" class="form-control" name="birthday" id="birthday" value="${model.birthday}" disabled/>
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.email}" disabled/>
		</div>
		<div class="form-group">
			 <label for="homeAddress">homeAddress</label>
			 <input type="text" class="form-control" name="homeAddress" id="homeAddress" value="${model.homeAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="truename">truename</label>
			 <input type="text" class="form-control" name="truename" id="truename" value="${model.truename}" disabled/>
		</div>
		<div class="form-group">
			 <label for="picturePath">picturePath</label>
			 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${model.picturePath}" disabled/>
		</div>
		<div class="form-group">
			 <label for="qqNumber">qqNumber</label>
			 <input type="text" class="form-control" name="qqNumber" id="qqNumber" value="${model.qqNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sex">sex</label>
			 <input type="text" class="form-control" name="sex" id="sex" value="${model.sex}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/oauth/user/AppUserInfo",function(o){
 	o.see();
 });
 </script>




