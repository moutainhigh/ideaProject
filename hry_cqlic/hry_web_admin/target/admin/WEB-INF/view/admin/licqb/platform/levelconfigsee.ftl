 <!-- Copyright:    -->
 <!-- LevelConfigSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:37:17      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">LevelConfig--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/levelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" value="${model.levelName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sort">等级排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
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
			 <label for="teamPerformance">团队业绩(USDT)</label>
			 <input type="text" class="form-control" name="teamPerformance" id="teamPerformance" value="${model.teamPerformance}" disabled/>
		</div>
		<div class="form-group">
			 <label for="levelAward">等级奖励(%)</label>
			 <input type="text" class="form-control" name="levelAward" id="levelAward" value="${model.levelAward}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/platform/LevelConfig",function(o){
 	o.see();
 });
 </script>




