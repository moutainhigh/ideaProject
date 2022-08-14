 <!-- Copyright:    -->
 <!-- ExCointoMoneySee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 10:07:05      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExCointoMoney--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/excointomoneylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="lan">lan</label>
			 <input type="text" class="form-control" name="lan" id="lan" value="${model.lan}" disabled/>
		</div>
		<div class="form-group">
			 <label for="exchange">exchange</label>
			 <input type="text" class="form-control" name="exchange" id="exchange" value="${model.exchange}" disabled/>
		</div>
		<div class="form-group">
			 <label for="rate">rate</label>
			 <input type="text" class="form-control" name="rate" id="rate" value="${model.rate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinSymbol">coinSymbol</label>
			 <input type="text" class="form-control" name="coinSymbol" id="coinSymbol" value="${model.coinSymbol}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isSynC2C">isSynC2C</label>
			 <input type="text" class="form-control" name="isSynC2C" id="isSynC2C" value="${model.isSynC2C}" disabled/>
		</div>
		<div class="form-group">
			 <label for="state">state</label>
			 <input type="text" class="form-control" name="state" id="state" value="${model.state}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExCointoMoney",function(o){
 	o.see();
 });
 </script>




