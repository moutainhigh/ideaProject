 <!-- Copyright:    -->
 <!-- IcoLockErrorSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-03-05 10:03:27      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoLockError--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/account/icolockerrorlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="count">次数</label>
			 <input type="text" class="form-control" name="count" id="count" value="${model.count}" disabled/>
		</div>
		<div class="form-group">
			 <label for="state">0等待，1完成</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}" disabled/>
		</div>
		<div class="form-group">
			 <label for="mqMessage">消息体</label>
			 <input type="text" class="form-control" name="mqMessage" id="mqMessage" value="${model.mqMessage}" disabled/>
		</div>
		<div class="form-group">
			 <label for="errorInfo">错误信息</label>
			 <input type="text" class="form-control" name="errorInfo" id="errorInfo" value="${model.errorInfo}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/ico/account/IcoLockError",function(o){
 	o.see();
 });
 </script>




