 <!-- Copyright:   互融云 -->
 <!-- AppUserSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">查看用户<button type="button"  class="btn btn-info pull-right"  onclick="loadUrl2Div('${ctx}/v.do?u=/oauth/user/appuserlist','tree_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="email">账号</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.username}" readonly />
		</div>
		
		
		<div class="form-group">
			 <label for="type">部门</label>
			 <@HrySelect url="${ctx}/user/apporganization/listdepartment.do"  itemvalue="id"  itemname="name"  name="department_id"  id="department_id" value="${department_id}" readonly="true"  > </@HrySelect>
		</div>
		
		<div class="form-group">
			 <label for="type">角色</label>
			 <@HrySelect url="${ctx}/user/approle/findall.do"  itemvalue="id"  itemname="name"  name="roleid"  id="roleid" value="${roleid}"  readonly="true" > </@HrySelect>
		</div>
		
	</div>
	
 </div>

 </form>

<script type="text/javascript">
seajs.use(["js/oauth/user/AppUser","js/base/HrySelect"],function(o,HrySelect){
	HrySelect.init();
	o.see();
});
</script>




