 <!-- Copyright:    -->
 <!-- ExLendAccountRecordModify.html     -->
 <!-- @author:      HeC  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-06 16:45:27      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExLendAccountRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/lend/exlendaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">交易对</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="customerId">用户</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="code">交易币种</label>
			 <input type="text" class="form-control" name="code" id="code" value="${model.code}"/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}"/>
		</div>
		<div class="form-group">
			 <label for="count">操作数量</label>
			 <input type="text" class="form-control" name="count" id="count" value="${model.count}"/>
		</div>
		<div class="form-group">
			 <label for="beforeCount">交易前币余额</label>
			 <input type="text" class="form-control" name="beforeCount" id="beforeCount" value="${model.beforeCount}"/>
		</div>
		<div class="form-group">
			 <label for="afterCount">交易后币余额</label>
			 <input type="text" class="form-control" name="afterCount" id="afterCount" value="${model.afterCount}"/>
		</div>
		<div class="form-group">
			 <label for="beforeColdCount">交易前冻结余额</label>
			 <input type="text" class="form-control" name="beforeColdCount" id="beforeColdCount" value="${model.beforeColdCount}"/>
		</div>
		<div class="form-group">
			 <label for="afterColdCount">交易后冻结余额</label>
			 <input type="text" class="form-control" name="afterColdCount" id="afterColdCount" value="${model.afterColdCount}"/>
		</div>
		<div class="form-group">
			 <label for="remark1">remark1</label>
			 <input type="text" class="form-control" name="remark1" id="remark1" value="${model.remark1}"/>
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
 seajs.use("js/admin/lend/ExLendAccountRecord",function(o){
 	o.modify();
 });
 </script>






