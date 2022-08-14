 <!-- Copyright:    -->
 <!-- ExRobotLogModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-13 11:25:18      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExRobotLog--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotloglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="customerId">customerId</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="fixCoin">fixCoin</label>
			 <input type="text" class="form-control" name="fixCoin" id="fixCoin" value="${model.fixCoin}"/>
		</div>
		<div class="form-group">
			 <label for="openTime">openTime</label>
			 <input type="text" class="form-control" name="openTime" id="openTime" value="${model.openTime}"/>
		</div>
		<div class="form-group">
			 <label for="closeTime">closeTime</label>
			 <input type="text" class="form-control" name="closeTime" id="closeTime" value="${model.closeTime}"/>
		</div>
		<div class="form-group">
			 <label for="buyTotalNum">buyTotalNum</label>
			 <input type="text" class="form-control" name="buyTotalNum" id="buyTotalNum" value="${model.buyTotalNum}"/>
		</div>
		<div class="form-group">
			 <label for="sellTotalNum">sellTotalNum</label>
			 <input type="text" class="form-control" name="sellTotalNum" id="sellTotalNum" value="${model.sellTotalNum}"/>
		</div>
		<div class="form-group">
			 <label for="phone">phone</label>
			 <input type="text" class="form-control" name="phone" id="phone" value="${model.phone}"/>
		</div>
		<div class="form-group">
			 <label for="email">email</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.email}"/>
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
 seajs.use("js/admin/exchange/ExRobotLog",function(o){
 	o.modify();
 });
 </script>






