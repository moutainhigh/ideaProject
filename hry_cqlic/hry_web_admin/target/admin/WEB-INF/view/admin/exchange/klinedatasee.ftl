 <!-- Copyright:    -->
 <!-- KlineDataSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-11 14:46:04      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlineData--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/klinedatalist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="period">period</label>
			 <input type="text" class="form-control" name="period" id="period" value="${model.period}" disabled/>
		</div>
		<div class="form-group">
			 <label for="openPrice">openPrice</label>
			 <input type="text" class="form-control" name="openPrice" id="openPrice" value="${model.openPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="closePrice">closePrice</label>
			 <input type="text" class="form-control" name="closePrice" id="closePrice" value="${model.closePrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="highPrice">highPrice</label>
			 <input type="text" class="form-control" name="highPrice" id="highPrice" value="${model.highPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lowPrice">lowPrice</label>
			 <input type="text" class="form-control" name="lowPrice" id="lowPrice" value="${model.lowPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="time">time</label>
			 <input type="text" class="form-control" name="time" id="time" value="${model.time}" disabled/>
		</div>
		<div class="form-group">
			 <label for="timelong">timelong</label>
			 <input type="text" class="form-control" name="timelong" id="timelong" value="${model.timelong}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/KlineData",function(o){
 	o.see();
 });
 </script>




