 <!-- Copyright:   互融云 -->
 <!-- AppUserAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加部门<button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl2Div('${ctx}/v.do?u=/oauth/user/appuserlist','tree_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">名称</label>
			 <input type="text" class="form-control" name="name" id="name" />
		</div>
	
		<div class="form-group">
			 <label for="orderno">排序号</label>
			 <input type="number" class="form-control" name="orderno" id="orderno" value="0" />
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
seajs.use("js/oauth/user/AppOrganization",function(o){
	o.add();
});
</script>




