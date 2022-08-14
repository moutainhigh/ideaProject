 <!-- Copyright:    -->
 <!-- ExOrderInfoList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:26:42      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">成交记录查询</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
        <form id="table_query_form">
            <div class="row">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="coinCode">交易币种</label>
                		<@HrySelect url="${ctx}/exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="fixPriceCoinCode">交易区</label>
                		<@HrySelect url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"   name="fixPriceCoinCode"  id="fixPriceCoinCode"   > </@HrySelect>
                    </div>
                </div>

                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="orderNum">成交单号</label>
                        <input type="text" class="form-control" tablesearch name="orderNum"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionCount_LT">成交数量小于</label>
                        <input type="text" class="form-control" tablesearch name="transactionCount_LT"/>
                    </div>
                </div>

                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionCount_GT">成交数量大于</label>
                        <input type="text" class="form-control" tablesearch name="transactionCount_GT"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="buyEmail">买方邮箱</label>
                        <input type="text" class="form-control" tablesearch name="buyEmail"/>
                    </div>
                </div>


            </div>
            <div class="row">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="buyMobilePhone">买方手机号</label>
                        <input type="text" class="form-control" tablesearch name="buyMobilePhone"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="buyEntrustNum">买方委托单号</label>
                        <input type="text" class="form-control" tablesearch name="buyEntrustNum"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionPrice_LT">成交价格小于</label>
                        <input type="text" class="form-control" tablesearch name="transactionPrice_LT"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionSum_LT">成交金额小于</label>
                        <input type="text" class="form-control" tablesearch name="transactionSum_LT"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="mobilePhone">成交时间小于</label>
                        <input type="text" class="form-control" name="transactionTime_LT" id="transactionTime_LT" readonly tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
				<div class="col-md-2 column">
                    <div class="form-group">
                        <label for="sellEmail">卖方邮箱</label>
                        <input type="text" class="form-control" tablesearch name="sellEmail"/>
                    </div>
                </div>
            </div>
			<div class="row">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="mobilePhone">卖方手机号</label>
                        <input type="text" class="form-control" tablesearch name="surname"/>
                    </div>
                </div>
				<div class="col-md-2 column">
                    <div class="form-group">
                        <label for="sellEntrustNum">卖方委托单号</label>
                        <input type="text" class="form-control" tablesearch name="sellEntrustNum"/>
                    </div>
                </div>
				<div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionPrice_GT">成交价格大于</label>
                        <input type="text" class="form-control" tablesearch name="transactionPrice_GT"/>
                    </div>
                </div>
				<div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionSum_GT">成交金额大于</label>
                        <input type="text" class="form-control" tablesearch name="transactionSum_GT"/>
                    </div>
                </div>
				<div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionTime_GT">成交时间大于</label>
                        <input type="text" class="form-control" name="transactionTime_GT" id="transactionTime_GT" readonly tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">

                    </div>
                </div>
                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </div>
        </form>
 	    <div id="toolbar">

 	    </div>
 	    <table id="table"
               data-toolbar="#toolbar"
               data-show-refresh="true"
               data-show-columns="true"
               data-show-export="true"
               data-search="true"
               data-detail-view="false"
               data-minimum-count-columns="2"
               data-pagination="true"
               data-id-field="id"
               data-page-list="[10, 25, 50, 100]"
               data-show-footer="false"
               data-side-pagination="server"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>

</div>
 <script type="text/javascript">
 seajs.use(["js/admin/exchange/ExOrderInfo","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
     HryDate.init();
     HrySelect.init();
 	o.list();
 });

 </script>

