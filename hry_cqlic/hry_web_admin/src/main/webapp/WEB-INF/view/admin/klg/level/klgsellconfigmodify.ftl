 <!-- Copyright:    -->
 <!-- KlgLevelConfigModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:43      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">卖出奖励--设置  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klglevelsellconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" value="${model.levelName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellTime">基础卖出时长</label>
			 <input type="text" class="form-control" name="sellTime" id="sellTime" value="${model.sellTime}"/>
		</div>
		<div class="form-group">
			 <label for="candyNum">基础糖果比率</label>
			 <input type="text" class="form-control" name="candyNum" id="candyNum" value="${model.candyNum}"/>
		</div>
		<div class="form-group">
			 <label for="addCandyNum">递增糖果比率</label>
			 <input type="text" class="form-control" name="addCandyNum" id="addCandyNum" value="${model.addCandyNum}"/>
		</div>
		<div class="form-group">
			 <label for="maxSellTime">最高卖出时长</label>
			 <input type="text" class="form-control" name="maxSellTime" id="maxSellTime" value="${model.maxSellTime}"/>
		</div>
		<div class="form-group">
			<label for="maxSellTime">最高奖励时长</label>
			<input type="text" class="form-control" name="maxRewardTime" id="maxRewardTime" value="${model.maxRewardTime}"/>
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
 seajs.use("js/admin/klg/level/KlgLevelConfig",function(o){
 	o.sellmodify();
 });
 </script>






