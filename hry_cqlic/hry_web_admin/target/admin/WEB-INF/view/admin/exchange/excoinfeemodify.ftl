 <!-- Copyright:    -->
 <!-- ExCoinFeeModify.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-29 19:24:35      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExCoinFee--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/excoinfeelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="coinId">币的ID</label>
			 <input type="text" class="form-control" name="coinId" id="coinId" value="${model.coinId}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种名称</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="coinName">币种代码</label>
			 <input type="text" class="form-control" name="coinName" id="coinName" value="${model.coinName}"/>
		</div>
		<div class="form-group">
			 <label for="userName">用户名</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="status">1提币 2购买 3出售</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}"/>
		</div>
		<div class="form-group">
			 <label for="volume">成交量</label>
			 <input type="text" class="form-control" name="volume" id="volume" value="${model.volume}"/>
		</div>
		<div class="form-group">
			 <label for="feeType">手续费类型 0固定费率 1百分比费率</label>
			 <input type="text" class="form-control" name="feeType" id="feeType" value="${model.feeType}"/>
		</div>
		<div class="form-group">
			 <label for="fee">手续费</label>
			 <input type="text" class="form-control" name="fee" id="fee" value="${model.fee}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
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
 seajs.use("js/admin/exchange/ExCoinFee",function(o){
 	o.modify();
 });
 </script>






