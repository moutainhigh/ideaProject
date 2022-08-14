 <!-- Copyright:    -->
 <!-- BuildRecordModify.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-17 12:01:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">BuildRecord--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/buildrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="startTime">startTime</label>
			 <input type="text" class="form-control" name="startTime" id="startTime" value="${model.startTime}"/>
		</div>
		<div class="form-group">
			 <label for="endTime">endTime</label>
			 <input type="text" class="form-control" name="endTime" id="endTime" value="${model.endTime}"/>
		</div>
		<div class="form-group">
			 <label for="pullTime">pullTime</label>
			 <input type="text" class="form-control" name="pullTime" id="pullTime" value="${model.pullTime}"/>
		</div>
		<div class="form-group">
			 <label for="tradeCoinCode">tradeCoinCode</label>
			 <input type="text" class="form-control" name="tradeCoinCode" id="tradeCoinCode" value="${model.tradeCoinCode}"/>
		</div>
		<div class="form-group">
			 <label for="priceCoinCode">priceCoinCode</label>
			 <input type="text" class="form-control" name="priceCoinCode" id="priceCoinCode" value="${model.priceCoinCode}"/>
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
 seajs.use("js/admin/exchange/BuildRecord",function(o){
 	o.modify();
 });
 </script>






