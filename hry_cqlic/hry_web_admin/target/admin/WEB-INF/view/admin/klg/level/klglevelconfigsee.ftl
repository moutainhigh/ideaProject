 <!-- Copyright:    -->
 <!-- KlgLevelConfigSee.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:43      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">星级会员--查看  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klglevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" value="${model.levelName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sort">等级排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
		</div>
		<div class="form-group">
			 <label for="buyNum">购买限制</label>
			 <input type="text" class="form-control" name="buyNum" id="buyNum" value="${model.buyNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="bonusMultiple">奖金倍数</label>
			 <input type="text" class="form-control" name="bonusMultiple" id="bonusMultiple" value="${model.bonusMultiple}" disabled/>
		</div>
		<div class="form-group">
			 <label for="pointAlgebra">见点代数</label>
			 <input type="text" class="form-control" name="pointAlgebra" id="pointAlgebra" value="${model.pointAlgebra}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sellTime">基础卖出时长</label>
			 <input type="text" class="form-control" name="sellTime" id="sellTime" value="${model.sellTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="candyNum">基础糖果比率</label>
			 <input type="text" class="form-control" name="candyNum" id="candyNum" value="${model.candyNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="addCandyNum">递增糖果比率</label>
			 <input type="text" class="form-control" name="addCandyNum" id="addCandyNum" value="${model.addCandyNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="maxSellTime">最高卖出时长</label>
			 <input type="text" class="form-control" name="maxSellTime" id="maxSellTime" value="${model.maxSellTime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="recommendNum">推荐个数</label>
			 <input type="text" class="form-control" name="recommendNum" id="recommendNum" value="${model.recommendNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="recommendSort">推荐星级别</label>
			 <input type="text" class="form-control" name="recommendSort" id="recommendSort" value="${model.recommendSort}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/level/KlgLevelConfig",function(o){
 	o.see();
 });
 </script>




