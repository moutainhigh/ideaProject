 <!-- Copyright:    -->
 <!-- AppCommendDeploySee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:48:57      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendDeploy--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenddeploylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="commendName">commendName</label>
			 <input type="text" class="form-control" name="commendName" id="commendName" value="${model.commendName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="RankRatio">RankRatio</label>
			 <input type="text" class="form-control" name="RankRatio" id="RankRatio" value="${model.RankRatio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="StandardValue">StandardValue</label>
			 <input type="text" class="form-control" name="StandardValue" id="StandardValue" value="${model.StandardValue}" disabled/>
		</div>
		<div class="form-group">
			 <label for="transactionNumber">transactionNumber</label>
			 <input type="text" class="form-control" name="transactionNumber" id="transactionNumber" value="${model.transactionNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="costName">costName</label>
			 <input type="text" class="form-control" name="costName" id="costName" value="${model.costName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="states">states</label>
			 <input type="text" class="form-control" name="states" id="states" value="${model.states}" disabled/>
		</div>
		<div class="form-group">
			 <label for="MinHierarchy">MinHierarchy</label>
			 <input type="text" class="form-control" name="MinHierarchy" id="MinHierarchy" value="${model.MinHierarchy}" disabled/>
		</div>
		<div class="form-group">
			 <label for="MaxHierarchy">MaxHierarchy</label>
			 <input type="text" class="form-control" name="MaxHierarchy" id="MaxHierarchy" value="${model.MaxHierarchy}" disabled/>
		</div>
		<div class="form-group">
			 <label for="reserveMoney">reserveMoney</label>
			 <input type="text" class="form-control" name="reserveMoney" id="reserveMoney" value="${model.reserveMoney}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendDeploy",function(o){
 	o.see();
 });
 </script>




