 <!-- Copyright:   互融云 -->
 <!-- AppUserInfoAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-07-04 15:33:43      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('/oauth/user/appuserinfolist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userid">userid</label>
			 <input type="text" class="form-control" name="userid" id="userid" />
		</div>
		<div class="form-group">
			 <label for="age">age</label>
			 <input type="text" class="form-control" name="age" id="age" />
		</div>
		<div class="form-group">
			 <label for="birthday">birthday</label>
			 <input type="text" class="form-control" name="birthday" id="birthday" />
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" />
		</div>
		<div class="form-group">
			 <label for="homeAddress">homeAddress</label>
			 <input type="text" class="form-control" name="homeAddress" id="homeAddress" />
		</div>
		<div class="form-group">
			 <label for="truename">truename</label>
			 <input type="text" class="form-control" name="truename" id="truename" />
		</div>
		<div class="form-group">
			 <label for="picturePath">picturePath</label>
			 <input type="text" class="form-control" name="picturePath" id="picturePath" />
		</div>
		<div class="form-group">
			 <label for="qqNumber">qqNumber</label>
			 <input type="text" class="form-control" name="qqNumber" id="qqNumber" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
		<div class="form-group">
			 <label for="sex">sex</label>
			 <input type="text" class="form-control" name="sex" id="sex" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/oauth/user/AppUserInfo",function(o){
	o.add();
});
</script>




