 <!-- Copyright:   互融云 -->
 <!-- AppUserAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">编辑角色 <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl(_ctx+'/v.do?u=/oauth/user/approlelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">

		<div class="form-group">
			 <label for="name">角色名称</label>
			 <input type="hidden" name="id" id="id" value="${model.id}"/>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" />
		</div>
		<div class="form-group">
			 <label for="remark">角色描述</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-4 column">
		<button type="button"  id="modifySubmit" class="btn  btn-primary btn-block" >提交</button>
	</div>
</div>
</form>

       <!-- 菜单树 -->
       <div class="panel "  id="select_oauth_div" >
           <ul class="nav nav-tabs">
 <#list adminMenuList as menu >
		<#if menu_index==0>
			<li class="active"><a href="#${menu.key}" data-toggle="tab">${menu.name}</a>
            </li>
        <#else>
			<li><a href="#${menu.key}" data-toggle="tab">${menu.name}</a>
            </li>
        </#if>
 </#list>
           </ul>

           <!-- Tab panes -->
           <div class="tab-content">
 <#list adminMenuList as menu >
	 <#if menu_index==0>
		<div class="tab-pane fade in active"  id="${menu.key}">
            <ul id="${menu.key}_tree"  class="ztree" style="width:95%; overflow:auto;height:500px;"></ul>
        </div>
     <#else>
		 <div class="tab-pane fade"  id="${menu.key}">
             <ul id="${menu.key}_tree"  class="ztree" style="width:95%; overflow:auto;height:500px;"></ul>
         </div>
     </#if>
 </#list>
           </div>
       </div>

   </div>

<script type="text/javascript">
    var appnames =[
<#list adminMenuList as menu >
    <#if menu_has_next>
		"${menu.key}",
    <#else>
		 "${menu.key}"];
    </#if>
</#list>

    var apptrees =[
<#list adminMenuList as menu >
    <#if menu_has_next>
		"${menu.key}_tree",
    <#else>
		 "${menu.key}_tree"];
    </#if>
</#list>
seajs.use("js/oauth/user/AppRole",function(o){
	o.modify();
});
</script>






