 <!-- Copyright:   互融云 -->
 <!-- AppUserAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加用户 <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl2Div(_ctx+'/v.do?u=/oauth/user/appuserlist','tree_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="username">账号</label>
			 <input type="text" class="form-control" name="username" id="username" />
		</div>
		
		<div class="form-group">
			 <label for="password">登录密码</label>
			 <input type="password" class="form-control" name="password" id="password" />
		</div>

		<div class="form-group">
			 <label for="type">部门</label>
            <input type="hidden" class="form-control" name="department_id" id="department_id" />
            <input type="text" class="form-control" name="department_name" id="department_name" readonly="true" />
			 <#--<@HrySelect url="${ctx}/user/apporganization/listdepartment"  itemvalue="id"  itemname="name"  name="department_id"  id="department_id"   > </@HrySelect>-->
		</div>
		
		<div class="form-group">
			 <label for="type">角色</label>
			 <@HrySelect url="${ctx}/user/approle/findall"  itemvalue="id"  itemname="name"  name="roleid"  id="roleid"   > </@HrySelect>
		</div>
		
	</div>
	
</div>

<div class="row">
<div class="col-md-2 column">
	<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
</div>

</form>

<script type="text/javascript">
seajs.use(["js/oauth/user/AppUser","js/base/HrySelect"],function(o,HrySelect){
    $("#department_id").val($("#tree_selected_id").val());
    $("#department_name").val($("#tree_selected_name").val());
	HrySelect.init();
	o.add();
});
</script>




