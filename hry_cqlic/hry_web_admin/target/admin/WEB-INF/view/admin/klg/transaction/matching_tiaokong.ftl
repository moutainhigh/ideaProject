 <!-- Copyright:    -->
 <!-- YwkCustomerMinerSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-16 16:44:13      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
  <div class="row">
  	<!--div class="col-md-12 column">
		<div class="form-group">
			 <label for="buyBuyUsdtNum">匹配成功买单买入SME数量：${map.buyBuyUsdtNum}&nbsp;SME</label>
		</div>
	</div>
  	<div class="col-md-12 column">
		<div class="form-group">
			 <label for="buyBuySmeNum">匹配成功买单付款USDT数量：${map.buyBuySmeNum}&nbsp;USDT</label>
		</div>
	</div>
  	<div class="col-md-12 column">
		<div class="form-group">
			 <label for="sellSellSmeSum">匹配成功卖单卖出SME数量：${map.sellSellSmeSum}&nbsp;SME</label>
		</div>
	</div>
  	<div class="col-md-12 column">
		<div class="form-group">
			 <label for="sellSellUsdtSum">匹配成功卖单收入USDT数量：${map.sellSellUsdtSum}&nbsp;USDT</label>
		</div>
	</div>
  	<div class="col-md-12 column">
		<div class="form-group">
			 <label for="buySurplus">买单已支付剩余数量：${map.buySurplus}&nbsp;USDT</label>
		</div>
	</div-->
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="tiaokongSme">调控SME数量</label>
			 <input type="text" class="form-control" name="tiaokongSme" id="tiaokongSme"  value="${map.tiaokongSme}"/>
		</div>
	</div>
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="tiaokongUsdt">调控USDT数量</label>
			 <input type="text" class="form-control" name="tiaokongUsdt" id="tiaokongUsdt"  value="${map.tiaokongUsdt}"/>
		</div>
	</div>
	
 </div>
 <div class="row">
	<div class="col-md-4 column"  style="float:right;"> 
		<button type="button" id="tiaokongSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>
 </div>

 <script type="text/javascript">
 
 </script>




