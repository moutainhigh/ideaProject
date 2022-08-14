 <!-- Copyright:    -->
 <!-- ExLendAccountRecordSee.html     -->
 <!-- @author:      HeC  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-06 16:45:27      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExLendAccountRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/lend/exlendaccountrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">交易对</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">用户</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="code">交易币种</label>
			 <input type="text" class="form-control" name="code" id="code" value="${model.code}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark">备注</label>
			 <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
		</div>
		<div class="form-group">
			 <label for="count">操作数量</label>
			 <input type="text" class="form-control" name="count" id="count" value="${model.count}" disabled/>
		</div>
		<div class="form-group">
			 <label for="beforeCount">交易前币余额</label>
			 <input type="text" class="form-control" name="beforeCount" id="beforeCount" value="${model.beforeCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="afterCount">交易后币余额</label>
			 <input type="text" class="form-control" name="afterCount" id="afterCount" value="${model.afterCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="beforeColdCount">交易前冻结余额</label>
			 <input type="text" class="form-control" name="beforeColdCount" id="beforeColdCount" value="${model.beforeColdCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="afterColdCount">交易后冻结余额</label>
			 <input type="text" class="form-control" name="afterColdCount" id="afterColdCount" value="${model.afterColdCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="remark1">remark1</label>
			 <input type="text" class="form-control" name="remark1" id="remark1" value="${model.remark1}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/lend/ExLendAccountRecord",function(o){
 	o.see();
 });
 </script>




