 <!-- Copyright:    -->
 <!-- KlgLevelConfigAdd.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-11 17:30:43      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">星级会员--添加 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/level/klglevelconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="levelName">等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" />
		</div>
		<div class="form-group">
			<label for="buyNum">购买金额(USDT)</label>
			<input type="text" placeholder="预约金额限制，多个逗号分隔" class="form-control" name="buyNums" id="buyNums" />
		</div>
		<div class="form-group">
			<label for="bonusMultiple">奖金倍数</label>
			<input type="text" class="form-control" name="bonusMultiple" id="bonusMultiple" />
		</div>
		<div class="form-group">
			 <label for="sort">等级排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" />
		</div>
		<div class="form-group">
			 <label for="pointAlgebra">见点奖最高可拿代数（代）</label>
			<@HrySelect key="klg_algebra"  name="pointAlgebra"  id="pointAlgebra" tablesearch="true"> </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="luckDrawCount">抽奖次数限制</label>
			 <input type="text" class="form-control" name="luckDrawCount" id="luckDrawCount" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
 </div>
 </div>

 </form>

</div>
<script type="text/javascript">
seajs.use("js/admin/klg/level/KlgLevelConfig",function(o){
	o.add();
});
</script>




