 <!-- Copyright:    -->
 <!-- LogCommonAopSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 13:39:20      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">LogCommonAop--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/logcommonaoplist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ip">ip</label>
			 <input type="text" class="form-control" name="ip" id="ip" value="${model.ip}" disabled/>
		</div>
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="methodName">methodName</label>
			 <input type="text" class="form-control" name="methodName" id="methodName" value="${model.methodName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="args">args</label>
			 <input type="text" class="form-control" name="args" id="args" value="${model.args}" disabled/>
		</div>
		<div class="form-group">
			 <label for="target">target</label>
			 <input type="text" class="form-control" name="target" id="target" value="${model.target}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/LogCommonAop",function(o){
 	o.see();
 });
 </script>




