 <!-- Copyright:    -->
 <!-- KlgPlatformAccountRecordModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-15 16:36:13      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgPlatformAccountRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/platform/klgplatformaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="serialNumber">serialNumber</label>
			 <input type="text" class="form-control" name="serialNumber" id="serialNumber" value="${model.serialNumber}"/>
		</div>
		<div class="form-group">
			 <label for="type">类型</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}"/>
		</div>
		<div class="form-group">
			 <label for="money">金额</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="nowMoney">nowMoney</label>
			 <input type="text" class="form-control" name="nowMoney" id="nowMoney" value="${model.nowMoney}"/>
		</div>
		<div class="form-group">
			 <label for="accountId">accountId</label>
			 <input type="text" class="form-control" name="accountId" id="accountId" value="${model.accountId}"/>
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
 seajs.use("js/admin/klg/platform/KlgPlatformAccountRecord",function(o){
 	o.modify();
 });
 </script>






