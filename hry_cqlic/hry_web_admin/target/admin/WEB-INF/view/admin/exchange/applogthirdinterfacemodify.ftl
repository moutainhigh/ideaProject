 <!-- Copyright:    -->
 <!-- AppLogThirdInterfaceModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-20 10:19:20      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppLogThirdInterface--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/applogthirdinterfacelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="ip">ip</label>
			 <input type="text" class="form-control" name="ip" id="ip" value="${model.ip}"/>
		</div>
		<div class="form-group">
			 <label for="url">url</label>
			 <input type="text" class="form-control" name="url" id="url" value="${model.url}"/>
		</div>
		<div class="form-group">
			 <label for="account">account</label>
			 <input type="text" class="form-control" name="account" id="account" value="${model.account}"/>
		</div>
		<div class="form-group">
			 <label for="params">params</label>
			 <input type="text" class="form-control" name="params" id="params" value="${model.params}"/>
		</div>
		<div class="form-group">
			 <label for="result">result</label>
			 <input type="text" class="form-control" name="result" id="result" value="${model.result}"/>
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
 seajs.use("js/admin/exchange/AppLogThirdInterface",function(o){
 	o.modify();
 });
 </script>






