 <!-- Copyright:    -->
 <!-- BuildRecordSee.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-17 12:01:26      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">BuildRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/buildrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="startTime">startTime</label>
			 <input type="text" class="form-control" name="startTime" id="startTime" value="${model.startTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="endTime">endTime</label>
			 <input type="text" class="form-control" name="endTime" id="endTime" value="${model.endTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="pullTime">pullTime</label>
			 <input type="text" class="form-control" name="pullTime" id="pullTime" value="${model.pullTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="tradeCoinCode">tradeCoinCode</label>
			 <input type="text" class="form-control" name="tradeCoinCode" id="tradeCoinCode" value="${model.tradeCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="priceCoinCode">priceCoinCode</label>
			 <input type="text" class="form-control" name="priceCoinCode" id="priceCoinCode" value="${model.priceCoinCode}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/BuildRecord",function(o){
 	o.see();
 });
 </script>




