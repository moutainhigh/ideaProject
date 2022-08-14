 <!-- Copyright:   互融云 -->
 <!-- AppUserAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">修改密码</h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
        <div class="form-group">
            <label for="oldpassword">原密码</label>
            <input type="password" class="form-control" name="oldpassword" id="oldpassword" value=""/>
        </div>
		<div class="form-group">
			 <label for="password">新密码</label>
			 <input type="password" class="form-control" name="password" id="password" value=""/>
		</div>
		
		<div class="form-group">
			 <label for="repassword">重复密码</label>
			 <input type="password" class="form-control" name="repassword" id="repassword" value="" />
		</div>
		
	</div>
	
</div>

<div class="row">
<div class="col-md-2 column">
	<button type="button" id="resetpwSubmit" class="btn btn-blue btn-block" >提交</button>
</div>

</form>
   </div>

<script type="text/javascript">
seajs.use(["js/oauth/user/AppUser"],function(o){
	o.resetpw2();
});
</script>




