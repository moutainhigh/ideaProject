 <!-- Copyright:    -->
 <!-- AppCommendDeployModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:48:57      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendDeploy--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenddeploylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="commendName">commendName</label>
			 <input type="text" class="form-control" name="commendName" id="commendName" value="${model.commendName}"/>
		</div>
		<div class="form-group">
			 <label for="RankRatio">RankRatio</label>
			 <input type="text" class="form-control" name="RankRatio" id="RankRatio" value="${model.RankRatio}"/>
		</div>
		<div class="form-group">
			 <label for="StandardValue">StandardValue</label>
			 <input type="text" class="form-control" name="StandardValue" id="StandardValue" value="${model.StandardValue}"/>
		</div>
		<div class="form-group">
			 <label for="transactionNumber">transactionNumber</label>
			 <input type="text" class="form-control" name="transactionNumber" id="transactionNumber" value="${model.transactionNumber}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="costName">costName</label>
			 <input type="text" class="form-control" name="costName" id="costName" value="${model.costName}"/>
		</div>
		<div class="form-group">
			 <label for="states">states</label>
			 <input type="text" class="form-control" name="states" id="states" value="${model.states}"/>
		</div>
		<div class="form-group">
			 <label for="MinHierarchy">MinHierarchy</label>
			 <input type="text" class="form-control" name="MinHierarchy" id="MinHierarchy" value="${model.MinHierarchy}"/>
		</div>
		<div class="form-group">
			 <label for="MaxHierarchy">MaxHierarchy</label>
			 <input type="text" class="form-control" name="MaxHierarchy" id="MaxHierarchy" value="${model.MaxHierarchy}"/>
		</div>
		<div class="form-group">
			 <label for="reserveMoney">reserveMoney</label>
			 <input type="text" class="form-control" name="reserveMoney" id="reserveMoney" value="${model.reserveMoney}"/>
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
 seajs.use("js/admin/commend/AppCommendDeploy",function(o){
 	o.modify();
 });
 </script>






