 <!-- Copyright:    -->
 <!-- AppCommendRankModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:49:45      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendRank--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommendranklist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="userId">userId</label>
			 <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}"/>
		</div>
		<div class="form-group">
			 <label for="userName">userName</label>
			 <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"/>
		</div>
		<div class="form-group">
			 <label for="fixMoney">fixMoney</label>
			 <input type="text" class="form-control" name="fixMoney" id="fixMoney" value="${model.fixMoney}"/>
		</div>
		<div class="form-group">
			 <label for="fixCoin">fixCoin</label>
			 <input type="text" class="form-control" name="fixCoin" id="fixCoin" value="${model.fixCoin}"/>
		</div>
		<div class="form-group">
			 <label for="coinCode">coinCode</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" value="${model.coinCode}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendRank",function(o){
 	o.modify();
 });
 </script>






