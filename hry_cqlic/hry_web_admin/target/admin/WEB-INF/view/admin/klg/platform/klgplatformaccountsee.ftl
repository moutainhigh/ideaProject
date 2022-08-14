 <!-- Copyright:    -->
 <!-- KlgPlatformAccountSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-15 16:35:25      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgPlatformAccount--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/platform/klgplatformaccountlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="type">账户类型</label>
			 <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
		</div>
		<div class="form-group">
			 <label for="money">金额</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}" disabled/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/platform/KlgPlatformAccount",function(o){
 	o.see();
 });
 </script>




