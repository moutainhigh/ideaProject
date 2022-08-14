 <!-- Copyright:    -->
 <!-- ExCointoCoinAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:52:02      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加交易对  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/exchange/excointocoin/toList/${tradeArea}')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >
    <input type="hidden" name="tradeArea" id="tradeArea" value="${tradeArea}">
    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="coinCode">交易币种</label>
                <@HrySelect url="${ctx}/exchange/exproduct/findPublished"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode" > </@HrySelect>
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="averagePrice">初始定价</label>
                <input type="text" class="form-control" name="averagePrice" id="averagePrice"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="fixPriceType">交易区类型</label>
                <select class="form-control" id="fixPriceType" name="fixPriceType">
                    <option value="0">真实货币</option>
                    <option value="1" selected="selected">虚拟货币</option>
                </select>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="fixPriceCoinCode">交易区</label>
            <@HrySelect url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"  name="fixPriceCoinCode"  id="fixPriceCoinCode" value="${tradeArea}" readonly="readonly"> </@HrySelect>
            </div>
        </div>

    </div>
    <div class="row">

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="averagePrice">价格小数位</label>
                <input type="text" class="form-control" name="keepDecimalFixPrice" id="keepDecimalFixPrice"/>
            </div>
        </div>
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="averagePrice">数量小数位</label>
                <input type="text" class="form-control" name="keepDecimalCoinCode" id="keepDecimalCoinCode"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="rose">限价涨幅(%)</label>
                <input type="text" class="form-control" name="rose" id="rose"/>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="decline">限价跌幅(%)</label>
                <input type="text" class="form-control" name="decline" id="decline"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="buyFeeRate">买方手续费率(%)</label>
                <input type="text" class="form-control" name="buyFeeRate" id="buyFeeRate"/>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="sellFeeRate">卖方手续费率(%)</label>
                <input type="text" class="form-control" name="sellFeeRate" id="sellFeeRate"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="sellMinCoin">单笔最小下单数量(个) </label>
                <input type="text" class="form-control" name="sellMinCoin" id="sellMinCoin"/>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="oneTimeOrderNum">单笔最大下单数量(个) </label>
                <input type="text" class="form-control" name="oneTimeOrderNum" id="oneTimeOrderNum"/>
            </div>
        </div>

    </div>



<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>
</div>
<script type="text/javascript">
seajs.use(["js/admin/exchange/ExCointoCoin","js/base/HrySelect"],function(o,HrySelect){
	o.add();
    HrySelect.init();
});
</script>




