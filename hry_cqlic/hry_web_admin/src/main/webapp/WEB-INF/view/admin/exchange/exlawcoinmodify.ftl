 <!-- Copyright:    -->
 <!-- ExLawcoinModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-22 09:57:59      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExLawcoin--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exlawcoinlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">法币币种</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
		</div>
		<div class="form-group">
			 <label for="coinSymbol">法币符号</label>
			 <input type="text" class="form-control" name="coinSymbol" id="coinSymbol" value="${model.coinSymbol}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">币种代号</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="coinDecimal">保留小数位数</label>
			 <input type="text" class="form-control" name="coinDecimal" id="coinDecimal" value="${model.coinDecimal}"/>
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
 seajs.use("js/admin/exchange/ExLawcoin",function(o){
 	o.modify();
 });
 </script>






