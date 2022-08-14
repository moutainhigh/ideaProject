 <!-- Copyright:    -->
 <!-- ExCointoCoinList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:52:02      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">委托交易-交易对管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
        <form id="table_query_form">
            <div class="row">
                <input type="hidden" name="tradeArea" id="tradeArea" value="${defaultTradeArea}" tablesearch>
                <#--<div class="_params">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="coinCode">交易区</label>
							<@HrySelect  url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"  name="fixPriceCoinCode_EQ"  id="fixPriceCoinCode_EQ"   > </@HrySelect>
						</div>
                    </div>
                    <div class="col-md-2 column">
                        <div style="margin-top: 26px;">
                            <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                            <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                        </div>
                    </div>
                </div>-->
            </div>
        </form>
        <ul class="nav nav-tabs" id="tradeArea_tab">
            <#if tradeAreaList??>
                <#list tradeAreaList as item >
                    <#if (defaultTradeArea == 'none' && item_index == 0) || item.tradingArea == defaultTradeArea>
                        <li class="active"><a href="#${item.tradingArea}" data-toggle="tab" class="tradeAreaQuery" tradeArea="${item.tradingArea}" >${item.tradingArea}</a></li>
                    <#else>
                        <li ><a href="#${item.tradingArea}" data-toggle="tab" class="tradeAreaQuery" tradeArea="${item.tradingArea}">${item.tradingArea}</a>  </li>
                    </#if>
                </#list>
            </#if>
        </ul>

 	    <div id="toolbar">
             <button id="add" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/add">
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="modify" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/modify">
 	            <i class="glyphicon glyphicon-edit"></i>修改
 	        </button>
             <button id="open" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/open">
 	            <i class="glyphicon glyphicon-remove"></i>开启
 	        </button>
            <button id="close" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/close">
                <i class="glyphicon glyphicon-remove"></i>关闭
            </button>
            <button id="remove" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/close">
                <i class="glyphicon glyphicon-remove"></i>删除
            </button>
            <button id="addKlineRobot" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/close">
                <i class="glyphicon glyphicon-remove"></i>添加K线机器人
            </button>
            <button id="AddDeepRobot" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/close">
                <i class="glyphicon glyphicon-remove"></i>添加深度机器人
            </button>
            <button id="testInterface" class="btn  btn-info-blue" permissions="/admin/exchange/excointocoin/close">
                <i class="glyphicon glyphicon-remove"></i>测试接口
            </button>
 	    </div>
 	    <table id="table"
               data-toolbar="#toolbar"
               data-show-refresh="false"
               data-show-columns="false"
               data-show-export="false"
               data-search="false"
               data-detail-view="false"
               data-minimum-count-columns="2"
               data-pagination="true"
               data-id-field="id"
               data-page-list="[10, 25, 50, 100]"
               data-show-footer="false"
               data-side-pagination="server"
               data-page-size="50"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>
</div>
 <script type="text/javascript">
 seajs.use(["js/admin/exchange/ExCointoCoin","js/base/HrySelect"],function(o,HrySelect){
     HrySelect.init();
 	o.list();
 });

 </script>

