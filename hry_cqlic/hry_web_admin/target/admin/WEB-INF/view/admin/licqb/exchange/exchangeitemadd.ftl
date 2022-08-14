 <!-- Copyright:    -->
 <!-- ExchangeItemAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-12-17 16:35:33      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/exchange/exchangeitemlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="itemName">项目名称</label>
			 <input type="text" class="form-control" name="itemName" id="itemName" />
		</div>
		<div class="form-group">
			 <label for="payCoinCode">支付币种--平台币</label>
			 <input type="text" class="form-control" name="payCoinCode" id="payCoinCode" />
		</div>
		<div class="form-group">
			 <label for="gainCoinCode">兑换币种</label>
			 <input type="text" class="form-control" name="gainCoinCode" id="gainCoinCode" />
		</div>
		<div class="form-group">
			 <label for="ratio">兑换汇率</label>
			 <input type="text" class="form-control" name="ratio" id="ratio" />
		</div>
		<div class="form-group">
			 <label for="totalNum">本局项目兑换总数</label>
			 <input type="text" class="form-control" name="totalNum" id="totalNum" />
		</div>
		<div class="form-group">
			 <label for="singleMinNum">单次购买最低数量</label>
			 <input type="text" class="form-control" name="singleMinNum" id="singleMinNum" />
		</div>
		<div class="form-group">
			 <label for="soloMaxNum">单人本局最大购买值</label>
			 <input type="text" class="form-control" name="soloMaxNum" id="soloMaxNum" />
		</div>
		<div class="form-group">
			 <label for="hasBuyNum">已购买数量</label>
			 <input type="text" class="form-control" name="hasBuyNum" id="hasBuyNum" />
		</div>
		<div class="form-group">
			 <label for="itemStartDate">项目启动时间</label>
			 <input type="text" class="form-control" name="itemStartDate" id="itemStartDate" />
		</div>
		<div class="form-group">
			 <label for="itemEndDate">项目结束时间</label>
			 <input type="text" class="form-control" name="itemEndDate" id="itemEndDate" />
		</div>
		<div class="form-group">
			 <label for="periodsNum">项目期数</label>
			 <input type="text" class="form-control" name="periodsNum" id="periodsNum" />
		</div>
		<div class="form-group">
			 <label for="status">是否开启 0 进行中 1 结束</label>
			 <input type="text" class="form-control" name="status" id="status" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
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
seajs.use("js/admin/licqb/exchange/ExchangeItem",function(o){
	o.add();
});
</script>




