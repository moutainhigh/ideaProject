 <!-- Copyright:    -->
 <!-- AppCoinRewardRecordModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 11:44:17      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCoinRewardRecord--Modify  <button type="button"  class="btn btn-warning pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/appcoinrewardrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="customerName">customerName</label>
			 <input type="text" class="form-control" name="customerName" id="customerName" value="${model.customerName}"/>
		</div>
		<div class="form-group">
			 <label for="customerMobil">customerMobil</label>
			 <input type="text" class="form-control" name="customerMobil" id="customerMobil" value="${model.customerMobil}"/>
		</div>
		<div class="form-group">
			 <label for="recordType">recordType</label>
			 <input type="text" class="form-control" name="recordType" id="recordType" value="${model.recordType}"/>
		</div>
		<div class="form-group">
			 <label for="recordNum">recordNum</label>
			 <input type="text" class="form-control" name="recordNum" id="recordNum" value="${model.recordNum}"/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerId">coverCustomerId</label>
			 <input type="text" class="form-control" name="coverCustomerId" id="coverCustomerId" value="${model.coverCustomerId}"/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerName">coverCustomerName</label>
			 <input type="text" class="form-control" name="coverCustomerName" id="coverCustomerName" value="${model.coverCustomerName}"/>
		</div>
		<div class="form-group">
			 <label for="coverCustomerMobile">coverCustomerMobile</label>
			 <input type="text" class="form-control" name="coverCustomerMobile" id="coverCustomerMobile" value="${model.coverCustomerMobile}"/>
		</div>
		<div class="form-group">
			 <label for="status">status</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		<div class="form-group">
			 <label for="failMsg">failMsg</label>
			 <input type="text" class="form-control" name="failMsg" id="failMsg" value="${model.failMsg}"/>
		</div>
		<div class="form-group">
			 <label for="exOrderInfoId">exOrderInfoId</label>
			 <input type="text" class="form-control" name="exOrderInfoId" id="exOrderInfoId" value="${model.exOrderInfoId}"/>
		</div>
		<div class="form-group">
			 <label for="remark">remark</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="auditor">auditor</label>
			 <input type="text" class="form-control" name="auditor" id="auditor" value="${model.auditor}"/>
		</div>
		<div class="form-group">
			 <label for="operationTime">operationTime</label>
			 <input type="text" class="form-control" name="operationTime" id="operationTime" value="${model.operationTime}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-success btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppCoinRewardRecord",function(o){
 	o.modify();
 });
 </script>






