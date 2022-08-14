 <!-- Copyright:    -->
 <!-- ExRobotAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加机器人  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exrobotlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoPrice">交易币/交易区 </label>
                <@HrySelect url="${ctx}/exchange/exrobot/selectlist"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoUsername">交易账号 </label>
                <input type="text" class="form-control" name="autoUsername" id="autoUsername"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoPrice">定价/市价下单 </label>
                <select class="form-control" id="atuoPriceType" name="atuoPriceType">
                    <option  value = "1">定价下单</option>
                    <option  value = "2">市价下单</option>
                    <option  value = "3">第三方价格下单</option>
                </select>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoPrice">价格基准 </label>
                <input type="text" class="form-control" name="autoPrice" id="autoPrice"/>
            </div>
        </div>

    </div>


    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoPriceFloat">价格浮动(%) </label>
                <input type="text" class="form-control" name="autoPriceFloat" id="autoPriceFloat"/>
            </div>
        </div>

        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoCount">数量基准 </label>
                <input type="text" class="form-control" name="autoCount" id="autoCount"/>
            </div>
        </div>

    </div>


    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">
                <label for="autoCountFloat">数量浮动(%) </label>
                <input type="text" class="form-control" name="autoCountFloat" id="autoCountFloat"/>
            </div>
        </div>

    </div>


    <div class="row">
        <div class="col-md-4 column">
            <div class="form-group">

                <select class="form-control" id="priceSource"  name="priceSource">
                    <option  value = "">成交价来源</option>
                    <option  value = "okcoin">okcoin</option>
                    <option  value = "bittrex">bittrex</option>
                    <option  value = "kkcoin">可酷</option>
                </select>
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
seajs.use(["js/admin/exchange/ExRobot","js/base/HrySelect"],function(o,HrySelect){
	o.add();
    HrySelect.init();
});
</script>




