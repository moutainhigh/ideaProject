 <!-- Copyright:    -->
 <!-- TeamLevelConfigModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:43:18      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">编辑社区等级  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/teamlevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="teamLevelName">社区等级名称</label>
			 <input type="text" class="form-control" name="teamLevelName" id="teamLevelName" value="${model.teamLevelName}"/>
		</div>
		<div class="form-group">
			 <label for="teamSort">社区等级排序</label>
			 <input type="text" class="form-control" readonly name="teamSort" id="teamSort" value="${model.teamSort}"/>
		</div>
		<div class="form-group">
			 <label for="directRecommendNum">直推荐人数</label>
			 <input type="text" class="form-control" name="directRecommendNum" id="directRecommendNum" value="${model.directRecommendNum}"/>
		</div>
		<div class="form-group">
			 <label for="nextRecommendNum">下级及以上用户数</label>
			 <input type="text" class="form-control" name="nextRecommendNum" id="nextRecommendNum" value="${model.nextRecommendNum}"/>
		</div>
		<div class="form-group">
			 <label for="ownAsset">个人资产(USDT)</label>
			 <input type="text" class="form-control" name="ownAsset" id="ownAsset" value="${model.ownAsset}"/>
		</div>
		<div class="form-group">
			 <label for="teamPerformance">团队业绩(USDT)</label>
			 <input type="text" class="form-control" name="teamPerformance" id="teamPerformance" value="${model.teamPerformance}"/>
		</div>
		<div class="form-group">
			 <label for="everyMonthTeamRatio">每月团队新增业绩(%)</label>
			 <input type="text" class="form-control" name="everyMonthTeamRatio" id="everyMonthTeamRatio" value="${model.everyMonthTeamRatio}"/>
		</div>
		<div class="form-group">
			 <label for="teamAwardNum">社区奖励数量(平台币)</label>
			 <input type="text" class="form-control" name="teamAwardNum" id="teamAwardNum" value="${model.teamAwardNum}"/>
		</div>
		<div class="form-group">
			 <label for="weekGrantRatio">周发放比例(%)</label>
			 <input type="text" class="form-control" name="weekGrantRatio" id="weekGrantRatio" value="${model.weekGrantRatio}"/>
		</div>
		<div class="form-group">
			 <label for="monthGrantRatio">月发放比例(%)</label>
			 <input type="text" class="form-control" name="monthGrantRatio" id="monthGrantRatio" value="${model.monthGrantRatio}"/>
		</div>
		<div class="form-group">
			 <label for="yearGrantRatio">年发放比例(%)</label>
			 <input type="text" class="form-control" name="yearGrantRatio" id="yearGrantRatio" value="${model.yearGrantRatio}"/>
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
 seajs.use("js/admin/licqb/platform/TeamLevelConfig",function(o){
 	o.modify();
 });
 </script>






