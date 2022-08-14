 <!-- Copyright:    -->
 <!-- EcologPaySee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-05 16:38:22      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">EcologPay--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/ecology/ecologpaylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="enterId">入驻订单Id</label>
			 <input type="text" class="form-control" name="enterId" id="enterId" value="${model.enterId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="orderNum">单号</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" value="${model.orderNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="payDate">缴费时间</label>
			 <input type="text" class="form-control" name="payDate" id="payDate" value="${model.payDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="acceptAddress">收款地址</label>
			 <input type="text" class="form-control" name="acceptAddress" id="acceptAddress" value="${model.acceptAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="paymentAddress">付款地址</label>
			 <input type="text" class="form-control" name="paymentAddress" id="paymentAddress" value="${model.paymentAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="verifyDate">核实时间</label>
			 <input type="text" class="form-control" name="verifyDate" id="verifyDate" value="${model.verifyDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="baseValidityDay">当前规则中有效期天数</label>
			 <input type="text" class="form-control" name="baseValidityDay" id="baseValidityDay" value="${model.baseValidityDay}" disabled/>
		</div>
		<div class="form-group">
			 <label for="residueValidityDay">上笔剩余天数</label>
			 <input type="text" class="form-control" name="residueValidityDay" id="residueValidityDay" value="${model.residueValidityDay}" disabled/>
		</div>
		<div class="form-group">
			 <label for="validityDay">实际保证期有效天数</label>
			 <input type="text" class="form-control" name="validityDay" id="validityDay" value="${model.validityDay}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/ecology/EcologPay",function(o){
 	o.see();
 });
 </script>




