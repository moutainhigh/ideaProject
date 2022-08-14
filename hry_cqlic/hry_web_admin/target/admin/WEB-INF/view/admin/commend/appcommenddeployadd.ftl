 <!-- Copyright:    -->
 <!-- AppCommendDeployAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 19:48:57      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenddeploylist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="commendName">commendName</label>
			 <input type="text" class="form-control" name="commendName" id="commendName" />
		</div>
		<div class="form-group">
			 <label for="RankRatio">RankRatio</label>
			 <input type="text" class="form-control" name="RankRatio" id="RankRatio" />
		</div>
		<div class="form-group">
			 <label for="StandardValue">StandardValue</label>
			 <input type="text" class="form-control" name="StandardValue" id="StandardValue" />
		</div>
		<div class="form-group">
			 <label for="transactionNumber">transactionNumber</label>
			 <input type="text" class="form-control" name="transactionNumber" id="transactionNumber" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="costName">costName</label>
			 <input type="text" class="form-control" name="costName" id="costName" />
		</div>
		<div class="form-group">
			 <label for="states">states</label>
			 <input type="text" class="form-control" name="states" id="states" />
		</div>
		<div class="form-group">
			 <label for="MinHierarchy">MinHierarchy</label>
			 <input type="text" class="form-control" name="MinHierarchy" id="MinHierarchy" />
		</div>
		<div class="form-group">
			 <label for="MaxHierarchy">MaxHierarchy</label>
			 <input type="text" class="form-control" name="MaxHierarchy" id="MaxHierarchy" />
		</div>
		<div class="form-group">
			 <label for="reserveMoney">reserveMoney</label>
			 <input type="text" class="form-control" name="reserveMoney" id="reserveMoney" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/commend/AppCommendDeploy",function(o){
	o.add();
});
</script>




