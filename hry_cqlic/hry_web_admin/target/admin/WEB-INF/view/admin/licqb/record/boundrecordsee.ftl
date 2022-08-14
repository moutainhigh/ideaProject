 <!-- Copyright:    -->
 <!-- BoundRecordSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-04-02 10:43:23      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">BoundRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/record/boundrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oAccountNum">用户原账号</label>
			 <input type="text" class="form-control" name="oAccountNum" id="oAccountNum" value="${model.oAccountNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nAccountNum">用户新账号</label>
			 <input type="text" class="form-control" name="nAccountNum" id="nAccountNum" value="${model.nAccountNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="accountNumType">账号类型: 1 手机号 2 邮箱</label>
			 <input type="text" class="form-control" name="accountNumType" id="accountNumType" value="${model.accountNumType}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/record/BoundRecord",function(o){
 	o.see();
 });
 </script>




