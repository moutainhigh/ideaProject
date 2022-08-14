 <!-- Copyright:    -->
 <!-- ExErc20See.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-08 17:02:30      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExErc20--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exerc20list')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="contractAddress">contractAddress</label>
			 <input type="text" class="form-control" name="contractAddress" id="contractAddress" value="${model.contractAddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="myprecision">myprecision</label>
			 <input type="text" class="form-control" name="myprecision" id="myprecision" value="${model.myprecision}" disabled/>
		</div>
		<div class="form-group">
			 <label for="addCoinType">addCoinType</label>
			 <input type="text" class="form-control" name="addCoinType" id="addCoinType" value="${model.addCoinType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="operator">operator</label>
			 <input type="text" class="form-control" name="operator" id="operator" value="${model.operator}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExErc20",function(o){
 	o.see();
 });
 </script>




