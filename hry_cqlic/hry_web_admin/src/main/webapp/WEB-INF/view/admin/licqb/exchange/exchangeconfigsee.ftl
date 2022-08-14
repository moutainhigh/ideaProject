 <!-- Copyright:    -->
 <!-- ExchangeConfigSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-12-17 16:32:12      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ExchangeConfig--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangeconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="itemName">项目名称</label>
			 <input type="text" class="form-control" name="itemName" id="itemName" value="${model.itemName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="payCoinCode">支付币种--平台币</label>
			 <input type="text" class="form-control" name="payCoinCode" id="payCoinCode" value="${model.payCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="gainCoinCode">兑换币种</label>
			 <input type="text" class="form-control" name="gainCoinCode" id="gainCoinCode" value="${model.gainCoinCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ratio">兑换汇率</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" value="${model.ratio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="totalNum">本局项目兑换总数</label>
			 <input type="text" class="form-control" name="totalNum" id="totalNum" value="${model.totalNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="singleMinNum">单次购买最低数量</label>
			 <input type="text" class="form-control" name="singleMinNum" id="singleMinNum" value="${model.singleMinNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="soloMaxNum">单人本局最大购买值</label>
			 <input type="text" class="form-control" name="soloMaxNum" id="soloMaxNum" value="${model.soloMaxNum}" disabled/>
		</div>
		<div class="form-group">
			 <label for="itemStartDate">项目启动时间</label>
			 <input type="text" class="form-control" name="itemStartDate" id="itemStartDate" value="${model.itemStartDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="gainStartDate">兑换开始时间</label>
			 <input type="text" class="form-control" name="gainStartDate" id="gainStartDate" value="${model.gainStartDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="gainEndDate">兑换结束时间</label>
			 <input type="text" class="form-control" name="gainEndDate" id="gainEndDate" value="${model.gainEndDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="hasChangeRatio">已兑换比例</label>
			 <input type="text" class="form-control" name="hasChangeRatio" id="hasChangeRatio" value="${model.hasChangeRatio}" disabled/>
		</div>
		<div class="form-group">
			 <label for="status">是否开启 0 未开启 1 开启</label>
			 <input type="text" class="form-control" name="status" id="status" value="${model.status}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/exchange/ExchangeConfig",function(o){
 	o.see();
 });
 </script>




