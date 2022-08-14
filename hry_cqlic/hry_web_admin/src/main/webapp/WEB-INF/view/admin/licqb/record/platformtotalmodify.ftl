 <!-- Copyright:    -->
 <!-- platformTotalModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-04-02 11:10:41      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">platformTotal--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/record/platformtotallist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">币种代码</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="todayAddNum">今日新增量</label>
			 <input type="text" class="form-control" name="todayAddNum" id="todayAddNum" value="${model.todayAddNum}"/>
		</div>
		<div class="form-group">
			 <label for="ayerAllNum">昨日总量</label>
			 <input type="text" class="form-control" name="ayerAllNum" id="ayerAllNum" value="${model.ayerAllNum}"/>
		</div>
		<div class="form-group">
			 <label for="ayerConvertNum">昨日兑换量</label>
			 <input type="text" class="form-control" name="ayerConvertNum" id="ayerConvertNum" value="${model.ayerConvertNum}"/>
		</div>
		<div class="form-group">
			 <label for="ayerSurplusNum">昨日剩余量（昨日总量-昨日已兑换量）</label>
			 <input type="text" class="form-control" name="ayerSurplusNum" id="ayerSurplusNum" value="${model.ayerSurplusNum}"/>
		</div>
		<div class="form-group">
			 <label for="todayAllNum">今日总量（今日新增+昨日剩余量）</label>
			 <input type="text" class="form-control" name="todayAllNum" id="todayAllNum" value="${model.todayAllNum}"/>
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
 seajs.use("js/admin/licqb/record/PlatformTotal",function(o){
 	o.modify();
 });
 </script>






