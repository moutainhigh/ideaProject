 <!-- Copyright:   互融云 -->
 <!-- AppUserModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">部门调动<button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl2Div('${ctx}/v.do?u=/oauth/user/appuserlist','tree_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="type">部门</label>
			 <@HrySelect url="${ctx}/user/apporganization/listorganization"  itemvalue="id"  itemname="name"  name="department_id"  id="department_id" value="${department_id}"   > </@HrySelect>
		</div>

	</div>
 </div>


 <div class="row">
 <div class="col-md-2 column">
 	<button type="button" id="modifySubmit" class="btn  btn-info-blue" >提交</button>
 </div>
 </form>

<script type="text/javascript">
seajs.use(["js/oauth/user/AppUser","js/base/HrySelect"],function(o,HrySelect){
	HrySelect.init();
	o.modifyOrganization();
});
</script>






