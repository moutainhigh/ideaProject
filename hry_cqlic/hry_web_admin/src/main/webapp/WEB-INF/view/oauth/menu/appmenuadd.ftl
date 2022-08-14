 <!-- Copyright:   互融云 -->
 <!-- AppMenuAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-06-01 19:44:41      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加菜单</h3>
    </div>
</div>



<form id="form" >

<div class="row">
	<div class="col-md-10 column">
	
		<div class="form-group">
			 <label for="name">父级名称</label>
			 <input type="text" class="form-control" name="parentName" id="parentName" readonly="true"/>
		</div>

		<div class="form-group">
			 <label for="name">菜单名称</label>
			 <input type="text" class="form-control" name="name" id="name" />
		</div>
	
		<div class="form-group">
			 <label for="url">链接</label>
			 <input type="text" class="form-control" name="url" id="url" />
		</div>
		
	
		<div class="form-group">
			 <label for="shirourl">权限</label>
			 <input type="text" class="form-control" name="shirourl" id="shirourl" />
		</div>
		
	
		<div class="form-group">
			 <label for="orderno">排序号</label>
			 <input type="text" class="form-control" name="orderno" id="orderno" value="0"/>
		</div>
	
		<div class="form-group">
			 <label for="type">类型</label>
			 <@HrySelect key="menuType"  name="type"  id="type"   > </@HrySelect>
		</div>

        <div class="form-group">
            <input type="hidden" class="form-control" name="icon" id="icon" value="glyphicon glyphicon-th-large"/>
            <span class="glyphicon glyphicon-th-large" id="iconview" style="font-size:100px;"></span>
        </div>
		<div class="form-group">
            <button class="btn btn-default" type="button" id="selectIcon">选择图标</button>
        </div>
	
	</div>
	
	
</div>

<div class="row">
<div class="col-md-2 column">
	<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/menu/AppMenu",function(o){
	o.add();
	$("#parentName").val($("#tree_selected_name").val());
});
</script>

 <!-- 图标选择弹窗 -->
<div class="panel hide"  id="selectIcon_div">

</div>




