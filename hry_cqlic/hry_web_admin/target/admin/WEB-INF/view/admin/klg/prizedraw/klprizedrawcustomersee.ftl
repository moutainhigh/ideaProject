 <!-- Copyright:    -->
 <!-- KlPrizedrawCustomerSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-06 11:31:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlPrizedrawCustomer--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/prizedraw/klprizedrawcustomerlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="issueNumber">期号</label>
			 <input type="text" class="form-control" name="issueNumber" id="issueNumber" value="${model.issueNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="prizedrawNumber">抽奖号码</label>
			 <input type="text" class="form-control" name="prizedrawNumber" id="prizedrawNumber" value="${model.prizedrawNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="startDate">开奖时间</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" value="${model.startDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">状态(1.未开奖 2.已开奖)</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/prizedraw/KlPrizedrawCustomer",function(o){
 	o.see();
 });
 </script>




