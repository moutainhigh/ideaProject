 <!-- Copyright:    -->
 <!-- LogCommonAopAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 13:39:21      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/logcommonaoplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" />
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" />
		</div>
		<div class="form-group">
			 <label for="ip">ip</label>
			 <input type="text" class="form-control" name="ip" id="ip" />
		</div>
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" />
		</div>
		<div class="form-group">
			 <label for="methodName">methodName</label>
			 <input type="text" class="form-control" name="methodName" id="methodName" />
		</div>
		<div class="form-group">
			 <label for="args">args</label>
			 <input type="text" class="form-control" name="args" id="args" />
		</div>
		<div class="form-group">
			 <label for="target">target</label>
			 <input type="text" class="form-control" name="target" id="target" />
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
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
seajs.use("js/admin/web/LogCommonAop",function(o){
	o.add();
});
</script>




