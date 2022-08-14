 <!-- Copyright:    -->
 <!-- LevelConfigModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:37:17      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">编辑等级  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/levelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" value="${model.levelName}"/>
		</div>
		<div class="form-group">
			 <label for="sort">等级排序</label>
			 <input type="text" class="form-control" readonly name="sort" id="sort" value="${model.sort}"/>
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
			 <label for="teamPerformance">团队业绩(USDT)</label>
			 <input type="text" class="form-control" name="teamPerformance" id="teamPerformance" value="${model.teamPerformance}"/>
		</div>
		<div class="form-group">
			 <label for="levelAward">等级奖励比例(%)</label>
			 <input type="text" class="form-control" name="levelAward" id="levelAward" value="${model.levelAward}"/>
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
 seajs.use("js/admin/licqb/platform/LevelConfig",function(o){
 	o.modify();
 });
 </script>






