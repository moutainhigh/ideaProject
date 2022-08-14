 <!-- Copyright:    -->
 <!-- KlgExceptionLogSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-05-06 15:31:10      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgExceptionLog--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/log/klgexceptionloglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="functionName">{name:'方法名称'}</label>
			 <input type="text" class="form-control" name="functionName" id="functionName" value="${model.functionName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="exceptionText">{name:'异常原因'}</label>
			 <input type="text" class="form-control" name="exceptionText" id="exceptionText" value="${model.exceptionText}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ipaddress">{name:'ip地址'}</label>
			 <input type="text" class="form-control" name="ipaddress" id="ipaddress" value="${model.ipaddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerMinerId">{name:'矿机ID'}</label>
			 <input type="text" class="form-control" name="customerMinerId" id="customerMinerId" value="${model.customerMinerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="mobile">mobile</label>
			 <input type="text" class="form-control" name="mobile" id="mobile" value="${model.mobile}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/log/KlgExceptionLog",function(o){
 	o.see();
 });
 </script>




