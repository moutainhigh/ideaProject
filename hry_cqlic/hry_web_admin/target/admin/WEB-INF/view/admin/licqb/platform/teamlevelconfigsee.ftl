 <!-- Copyright:    -->
 <!-- TeamLevelConfigSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:43:18      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">TeamLevelConfig--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/teamlevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="teamLevelName">社区等级名称</label>
			 <input type="text" class="form-control" name="teamLevelName" id="teamLevelName" value="${model.teamLevelName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="teamSort">社区等级排序</label>
			 <input type="text" class="form-control" name="teamSort" id="teamSort" value="${model.teamSort}" disabled/>
		</div>
		<div class="form-group">
			 <label for="directRecommendNum">直推荐人数</label>
			 <input type="text" class="form-control" name="directRecommendNum" id="directRecommendNum" value="${model.directRecommendNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="nextRecommendNum">下级及以上用户数</label>
			 <input type="text" class="form-control" name="nextRecommendNum" id="nextRecommendNum" value="${model.nextRecommendNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ownAsset">个人资产(USDT)</label>
			 <input type="text" class="form-control" name="ownAsset" id="ownAsset" value="${model.ownAsset}" disabled/>
		</div>
		<div class="form-group">
			 <label for="teamPerformance">团队业绩(USDT)</label>
			 <input type="text" class="form-control" name="teamPerformance" id="teamPerformance" value="${model.teamPerformance}" disabled/>
		</div>
		<div class="form-group">
			 <label for="everyMonthTeamRatio">每月团队新增业绩(%)</label>
			 <input type="text" class="form-control" name="everyMonthTeamRatio" id="everyMonthTeamRatio" value="${model.everyMonthTeamRatio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="teamAwardNum">社区奖励数量(平台币)</label>
			 <input type="text" class="form-control" name="teamAwardNum" id="teamAwardNum" value="${model.teamAwardNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="weekGrantRatio">周发放比例(%)</label>
			 <input type="text" class="form-control" name="weekGrantRatio" id="weekGrantRatio" value="${model.weekGrantRatio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="monthGrantRatio">月发放比例(%)</label>
			 <input type="text" class="form-control" name="monthGrantRatio" id="monthGrantRatio" value="${model.monthGrantRatio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="yearGrantRatio">月发放比例(%)</label>
			 <input type="text" class="form-control" name="yearGrantRatio" id="yearGrantRatio" value="${model.yearGrantRatio}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/platform/TeamLevelConfig",function(o){
 	o.see();
 });
 </script>




