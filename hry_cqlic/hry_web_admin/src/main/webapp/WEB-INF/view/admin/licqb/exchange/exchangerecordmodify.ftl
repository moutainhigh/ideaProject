 <!-- Copyright:    -->
 <!-- ExchangeRecordModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-12-17 16:36:32      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExchangeRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangerecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="itemId">项目id</label>
			 <input type="text" class="form-control" name="itemId" id="itemId" value="${model.itemId}"/>
		</div>
		<div class="form-group">
			 <label for="itemName">项目名称</label>
			 <input type="text" class="form-control" name="itemName" id="itemName" value="${model.itemName}"/>
		</div>
		<div class="form-group">
			 <label for="ratio">兑换汇率</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" value="${model.ratio}"/>
		</div>
		<div class="form-group">
			 <label for="payCoinCode">支付币种--平台币</label>
			 <input type="text" class="form-control" name="payCoinCode" id="payCoinCode" value="${model.payCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="gainCoinCode">兑换币种</label>
			 <input type="text" class="form-control" name="gainCoinCode" id="gainCoinCode" value="${model.gainCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="payNum">支付数量</label>
			 <input type="text" class="form-control" name="payNum" id="payNum" value="${model.payNum}"/>
		</div>
		<div class="form-group">
			 <label for="gainNum">兑换数量</label>
			 <input type="text" class="form-control" name="gainNum" id="gainNum" value="${model.gainNum}"/>
		</div>
		<div class="form-group">
			 <label for="periodsNum">项目期数</label>
			 <input type="text" class="form-control" name="periodsNum" id="periodsNum" value="${model.periodsNum}"/>
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
 seajs.use("js/admin/licqb/exchange/ExchangeRecord",function(o){
 	o.modify();
 });
 </script>






