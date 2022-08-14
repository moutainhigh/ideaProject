 <!-- Copyright:   互融云 -->
 <!-- SysDicAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-03-01 14:17:02      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加字典  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=admin/dic/appdictree')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-5 column">
		
		<div class="form-group">
			 <label for="name">字典名称</label>
			 <input type="text" class="form-control" name="name" id="name" />
		</div>
		
		<div class="form-group">
			 <label for="mkey">字典标识</label>
			 <input type="text" class="form-control" name="mkey" id="mkey" />
		</div>
		
		<input type="hidden" name="type" value="2" />
	
	</div>
	
</div>

<div class="row">
<div class="col-md-2 column">
	<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/dic/AppDic",function(o){
	o.add();
});
</script>




