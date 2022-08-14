 <!-- Copyright:    -->
 <!-- LogCommonAopModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 13:39:21      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">LogCommonAop--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/logcommonaoplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}"/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="ip">ip</label>
			 <input type="text" class="form-control" name="ip" id="ip" value="${model.ip}"/>
		</div>
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
		</div>
		<div class="form-group">
			 <label for="methodName">methodName</label>
			 <input type="text" class="form-control" name="methodName" id="methodName" value="${model.methodName}"/>
		</div>
		<div class="form-group">
			 <label for="args">args</label>
			 <input type="text" class="form-control" name="args" id="args" value="${model.args}"/>
		</div>
		<div class="form-group">
			 <label for="target">target</label>
			 <input type="text" class="form-control" name="target" id="target" value="${model.target}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/LogCommonAop",function(o){
 	o.modify();
 });
 </script>






