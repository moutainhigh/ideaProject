 <!-- Copyright:    -->
 <!-- IcoAccountModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 12:02:43      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">IcoAccount--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/account/icoaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="coinName">币名称</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" value="${model.coinName}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币Code</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="phone">手机号</label>
			 <input type="text" class="form-control" name="phone" id="phone" value="${model.phone}"/>
		</div>
		<div class="form-group">
			 <label for="storageMoney">存储总额</label>
			 <input type="text" class="form-control" name="storageMoney" id="storageMoney" value="${model.storageMoney}"/>
		</div>
		<div class="form-group">
			 <label for="coldMoney">冻结总额</label>
			 <input type="text" class="form-control" name="coldMoney" id="coldMoney" value="${model.coldMoney}"/>
		</div>
		<div class="form-group">
			 <label for="hotMoney">流通总额</label>
			 <input type="text" class="form-control" name="hotMoney" id="hotMoney" value="${model.hotMoney}"/>
		</div>
		<div class="form-group">
			 <label for="publicKey">publicKey</label>
			 <input type="text" class="form-control" name="publicKey" id="publicKey" value="${model.publicKey}"/>
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
 seajs.use("js/admin/ico/account/IcoAccount",function(o){
 	o.modify();
 });
 </script>






