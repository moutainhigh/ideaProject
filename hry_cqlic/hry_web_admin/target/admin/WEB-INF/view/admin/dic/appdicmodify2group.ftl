 <!-- Copyright:   互融云 -->
 <!-- SysDicAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-03-01 14:17:02      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">修改分类 </h3>
    </div>
</div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-5 column">
	
		<div class="form-group">
			 <label for="name">分类名称</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
		</div>
		
		<div class="form-group">
			 <label for="mkey">分类标识</label>
			 <input type="text" class="form-control" name="mkey" id="mkey" value="${model.mkey}"/>
		</div>
	
		
	</div>
	

	
 
 </div>


 <div class="row">
 <div class="col-md-2 column">
 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/dic/AppDic",function(o){
 	o.modify();
 });
 </script>




