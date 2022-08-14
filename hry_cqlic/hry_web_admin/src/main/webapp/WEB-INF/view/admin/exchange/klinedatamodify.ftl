 <!-- Copyright:    -->
 <!-- KlineDataModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-11 14:46:04      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlineData--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/klinedatalist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="period">period</label>
			 <input type="text" class="form-control" name="period" id="period" value="${model.period}"/>
		</div>
		<div class="form-group">
			 <label for="openPrice">openPrice</label>
			 <input type="text" class="form-control" name="openPrice" id="openPrice" value="${model.openPrice}"/>
		</div>
		<div class="form-group">
			 <label for="closePrice">closePrice</label>
			 <input type="text" class="form-control" name="closePrice" id="closePrice" value="${model.closePrice}"/>
		</div>
		<div class="form-group">
			 <label for="highPrice">highPrice</label>
			 <input type="text" class="form-control" name="highPrice" id="highPrice" value="${model.highPrice}"/>
		</div>
		<div class="form-group">
			 <label for="lowPrice">lowPrice</label>
			 <input type="text" class="form-control" name="lowPrice" id="lowPrice" value="${model.lowPrice}"/>
		</div>
		<div class="form-group">
			 <label for="time">time</label>
			 <input type="text" class="form-control" name="time" id="time" value="${model.time}"/>
		</div>
		<div class="form-group">
			 <label for="timelong">timelong</label>
			 <input type="text" class="form-control" name="timelong" id="timelong" value="${model.timelong}"/>
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
 seajs.use("js/admin/exchange/KlineData",function(o){
 	o.modify();
 });
 </script>






