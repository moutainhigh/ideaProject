 <!-- Copyright:   互融云 -->
 <!-- AppUserInfoModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-07-04 15:33:43      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppUserInfo--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('/oauth/user/appuserinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userid">userid</label>
			 <input type="text" class="form-control" name="userid" id="userid" value="${model.userid}"/>
		</div>
		<div class="form-group">
			 <label for="age">age</label>
			 <input type="text" class="form-control" name="age" id="age" value="${model.age}"/>
		</div>
		<div class="form-group">
			 <label for="birthday">birthday</label>
			 <input type="text" class="form-control" name="birthday" id="birthday" value="${model.birthday}"/>
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.email}"/>
		</div>
		<div class="form-group">
			 <label for="homeAddress">homeAddress</label>
			 <input type="text" class="form-control" name="homeAddress" id="homeAddress" value="${model.homeAddress}"/>
		</div>
		<div class="form-group">
			 <label for="truename">truename</label>
			 <input type="text" class="form-control" name="truename" id="truename" value="${model.truename}"/>
		</div>
		<div class="form-group">
			 <label for="picturePath">picturePath</label>
			 <input type="text" class="form-control" name="picturePath" id="picturePath" value="${model.picturePath}"/>
		</div>
		<div class="form-group">
			 <label for="qqNumber">qqNumber</label>
			 <input type="text" class="form-control" name="qqNumber" id="qqNumber" value="${model.qqNumber}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="sex">sex</label>
			 <input type="text" class="form-control" name="sex" id="sex" value="${model.sex}"/>
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
 seajs.use("js/oauth/user/AppUserInfo",function(o){
 	o.modify();
 });
 </script>






