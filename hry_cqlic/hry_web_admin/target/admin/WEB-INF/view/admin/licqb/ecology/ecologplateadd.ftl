 <!-- Copyright:    -->
 <!-- EcologPlateAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-05 16:37:08      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加版块分类<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/ecology/ecologplatelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			<label for="languageKey">全球化key</label>
			<input type="text" class="form-control" name="languageKey" id="languageKey" />
		</div>
		<div class="form-group">
			 <label for="plateName">板块名称</label>
			 <input type="text" class="form-control" name="plateName" id="plateName" />
		</div>
		<div class="form-group">
			 <label for="sort">排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" />
		</div>
		<div class="form-group">
			 <label for="isOpen">是否开启</label>
			 <select class="form-control" name="isOpen" id="isOpen">
				<option value="1" <#if model.isOpen == 1>selected</#if>>开启</option>
				<option value="2" <#if model.isOpen == 2>selected</#if> >关闭</option>
			 </select>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/licqb/ecology/EcologPlate",function(o){
	o.add();
});
</script>




