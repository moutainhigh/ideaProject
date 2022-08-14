 <!-- Copyright:    -->
 <!-- C2cCoinSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 12:06:01      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">C2cCoin--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/c2ccoinlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <label for="isOpen">isOpen</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyPrice">buyPrice</label>
			 <input type="text" class="form-control" name="buyPrice" id="buyPrice" value="${model.buyPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellPrice">sellPrice</label>
			 <input type="text" class="form-control" name="sellPrice" id="sellPrice" value="${model.sellPrice}" disabled/>
		</div>
		<div class="form-group">
			 <label for="minCount">minCount</label>
			 <input type="text" class="form-control" name="minCount" id="minCount" value="${model.minCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="maxCount">maxCount</label>
			 <input type="text" class="form-control" name="maxCount" id="maxCount" value="${model.maxCount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

 </div>
 <script type="text/javascript">
 seajs.use("js/admin/c2c/C2cCoin",function(o){
 	o.see();
 });
 </script>




