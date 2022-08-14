 <!-- Copyright:    -->
 <!-- AppCoinRewardRecordSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 11:44:17      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCoinRewardRecord--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/appcoinrewardrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="customerName">customerName</label>
			 <input type="text" class="form-control" name="customerName" id="customerName" value="${model.customerName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerMobil">customerMobil</label>
			 <input type="text" class="form-control" name="customerMobil" id="customerMobil" value="${model.customerMobil}" disabled/>
		</div>
		<div class="form-group">
			 <label for="recordType">recordType</label>
			 <input type="text" class="form-control" name="recordType" id="recordType" value="${model.recordType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="recordNum">recordNum</label>
			 <input type="text" class="form-control" name="recordNum" id="recordNum" value="${model.recordNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerId">coverCustomerId</label>
			 <input type="text" class="form-control" name="coverCustomerId" id="coverCustomerId" value="${model.coverCustomerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerName">coverCustomerName</label>
			 <input type="text" class="form-control" name="coverCustomerName" id="coverCustomerName" value="${model.coverCustomerName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerMobile">coverCustomerMobile</label>
			 <input type="text" class="form-control" name="coverCustomerMobile" id="coverCustomerMobile" value="${model.coverCustomerMobile}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="failMsg">failMsg</label>
			 <input type="text" class="form-control" name="failMsg" id="failMsg" value="${model.failMsg}" disabled/>
		</div>
		<div class="form-group">
			 <label for="exOrderInfoId">exOrderInfoId</label>
			 <input type="text" class="form-control" name="exOrderInfoId" id="exOrderInfoId" value="${model.exOrderInfoId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="auditor">auditor</label>
			 <input type="text" class="form-control" name="auditor" id="auditor" value="${model.auditor}" disabled/>
		</div>
		<div class="form-group">
			 <label for="operationTime">operationTime</label>
			 <input type="text" class="form-control" name="operationTime" id="operationTime" value="${model.operationTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppCoinRewardRecord",function(o){
 	o.see();
 });
 </script>




