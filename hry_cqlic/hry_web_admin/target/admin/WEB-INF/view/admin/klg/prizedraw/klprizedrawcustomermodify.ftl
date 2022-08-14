 <!-- Copyright:    -->
 <!-- KlPrizedrawCustomerModify.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-06 11:31:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlPrizedrawCustomer--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/prizedraw/klprizedrawcustomerlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="issueNumber">期号</label>
			 <input type="text" class="form-control" name="issueNumber" id="issueNumber" value="${model.issueNumber}"/>
		</div>
		<div class="form-group">
			 <label for="prizedrawNumber">抽奖号码</label>
			 <input type="text" class="form-control" name="prizedrawNumber" id="prizedrawNumber" value="${model.prizedrawNumber}"/>
		</div>
		<div class="form-group">
			 <label for="startDate">开奖时间</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" value="${model.startDate}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="status">状态(1.未开奖 2.已开奖)</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
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
 seajs.use("js/admin/klg/prizedraw/KlPrizedrawCustomer",function(o){
 	o.modify();
 });
 </script>






