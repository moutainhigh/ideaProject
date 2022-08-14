 <!-- Copyright:    -->
 <!-- KlgLevelConfigModify.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:43      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">星级会员--修改  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klglevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
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
			 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}"/>
		</div>
		<div class="form-group">
			 <label for="buyNum">购买限制</label>
			 <input type="text" placeholder="预约金额限制，多个逗号分隔" class="form-control" name="buyNums" id="buyNums" value="${model.buyNums}"/>
		</div>
		<div class="form-group">
			 <label for="bonusMultiple">奖金倍数</label>
			 <input type="text" class="form-control" name="bonusMultiple" id="bonusMultiple" value="${model.bonusMultiple}"/>
		</div>
		<div class="form-group">
			 <label for="pointAlgebra">见点代数</label>
			<@HrySelect key="klg_algebra"  name="pointAlgebra"  id="pointAlgebra" tablesearch="true" value="${model.pointAlgebra}"> </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="luckDrawCount">抽奖次数限制</label>
			 <input type="text" class="form-control" name="luckDrawCount" id="luckDrawCount" value="${model.luckDrawCount}"/>
		</div>
		<#--<div class="form-group">
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
			 <label for="recommendNum">推荐人数</label>
			 <input type="text" class="form-control" name="recommendNum" id="recommendNum" value="${model.recommendNum}"/>
		</div>
		<div class="form-group">
			 <label for="recommendSort">推荐星级别</label>
			 <input type="text" class="form-control" name="recommendSort" id="recommendSort" value="${model.recommendSort}"/>
		</div>-->
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
 	o.modify();
 });
 </script>






