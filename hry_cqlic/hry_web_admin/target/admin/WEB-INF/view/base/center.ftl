<#--
<#include "/base/base.ftl">
             <style>
                 .fixed-table-pagination {
                     display: none !important;
                 }
                 .fixed-table-container tbody tr td{
                     height:38px;
                     padding-bottom:0!important;
                 }
             </style>
             
            <div class="centerRowBg centerRowBg_admin">
                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">基础统计</h3>
                    </div>
                </div>
                <div class="baseTopBox">
                    <div class="col-md-6">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="baseTop">
                                    <div class="baseTopLeft">截止昨日总交易量<br>（折合USDT）</div>
                                    <div class="baseTopRight">
                                        <#if yesterdayTrade?exists>
                                            <#list yesterdayTrade?split("") as yt>
                                                <#if yt == ','>
                                                    ${yt}
                                                <#else>
                                                    <span>${yt}</span>
                                                </#if>
                                            </#list>
                                            <#else>
                                            <span>0</span>
                                            <span>0</span>
                                            <span>0</span>,
                                            <span>0</span>
                                            <span>0</span>
                                            <span>0</span>,
                                            <span>0</span>
                                            <span>0</span>
                                            <span>0</span>,
                                            <span>0</span>
                                            <span>0</span>
                                            <span>0</span>,
                                            <span>0</span>
                                            <span>0</span>
                                            <span>0</span>
                                        </#if>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 newAdd">
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增客户<span>${new_customer}</span></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增交易<span>${new_trade}</span></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增充币<span>${new_postCoin}</span></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增提币<span>${new_getCoin}</span></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增挖矿<span>${new_mining}</span></div>
                                </div>
                                <div class="col-md-4">
                                    <div class="col-lg-12">昨日新增分红<span>${new_dividend}</span></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="row baseCharts">
                            <div class="col-lg-12">
                                <div class="col-md-4">
                                        <div id="container1"></div>
                                        <div class="Recharge"><a href="javascript:void(0);" target="_blank" >充值</a></div>
                                </div>
                                <div class="col-md-4">
                                        <div id="container2"></div>
                                        <div class="Recharge"><a href="javascript:void(0);" target="_blank" >充值</a></div>
                                </div>
                                <div class="col-md-4">
                                        <div id="container3"></div>
                                        <div class="Recharge"><a href="javascript:void(0);" target="_blank" >充值</a></div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="row">
                    <div class="col-lg-12">
                        <h3 class="page-header">模块快捷入口</h3>
                    </div>
                </div>
                <div class="row rowMargin">
                    <div class="col-md-12" style="padding:0;">
                        <div id="" class="btnBox">
                            <div class="col-md-2">
                                <div class="col-lg-12 adminIndexBtn adminBg_1" permissions="/customer/appcustomer/toCustomer.do?isReal=1"
                                     onclick="loadUrl(_ctx + '/customer/appcustomer/toCustomer.do?isReal=1')">
                                    <i class="iconfont icon-people"></i>
                                    注册用户管理
                                </div>
                            </div>
                            <div class="col-md-2" >
                                <div class="col-lg-12 adminIndexBtn adminBg_2" permissions="/v.do?u=admin/exchange/exdigitalmoneyaccountlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exdigitalmoneyaccountlist')">
                                    <i class="iconfont icon-xiangao"></i>
                                    货币信息管理
                                </div>
                            </div>
                            <div class="col-md-4" >
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_3" permissions="/admin/exchange/exdmtransactionlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exdmtransactionlist')">
                                    <i class="iconfont icon-financial_fill"></i>
                                    充币提币管理
                                </div>
                            </div>
                            <div class="col-md-2" >
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_4" permissions="/v.do?u=admin/exchange/exproductlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exproductlist')">
                                    <i class="iconfont icon-questions"></i>
                                    币种资料管理
                                </div>
                            </div>


                            <div class="col-md-2" >
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_8" permissions="/web/appconfig/page/extraParamConfig"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/web/appmessagecategorylist')">
                                    <i class="iconfont icon-computer"></i>
                                    站点内容管理
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_5" permissions="/v.do?u=admin/c2c/c2ctransactionlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/c2c/appbusinessmanlist')">
                                    <i class="iconfont icon-group"></i>
              C2C交易管理
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_4" permissions="/v.do?u=admin/exchange/exentrustlistnow"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exentrustlistnow')">
                                    <i class="iconfont icon-collection"></i>
                                    委托交易管理
                                </div>
                            </div>
                            <div class="col-md-2">
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_2" permissions="/v.do?u=/admin/lock/exdmlockrecordlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=/admin/lock/exdmlocklist')">
                                    <i class="iconfont icon-lock"></i>
                                    解锁仓管理
                                </div>
                            </div>
                            <div class="col-md-2" >
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_4" permissions="/web/appconfig/page/commendConfig"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/commend/appcommenduserlist')">
                                    <i class="iconfont icon-emoji"></i>
                                    推荐用户管理
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div id="" class="col-lg-12 adminIndexBtn adminBg_6"  permissions="/v.do?u=admin/web/chatcustomerlist"
                                     onclick="loadUrl(_ctx + '/v.do?u=admin/web/chatcustomerlist')">
                                    <i class="iconfont icon-interactive"></i>
                                    聊天室管理
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="rowCenter">
                    <div class="rowCenterBox col-lg-6">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3 class="pageHeader"><i class="iconfont icon-yanjing"></i>服务监控<a href="javascript:void(0);" onclick="loadUrl(_ctx + '/v.do?u=admin/web/appservermonitorlist')">更多>></a></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <table id="table"
                                       data-detail-view="false"
                                       data-minimum-count-columns="2"
                                       data-pagination="true"
                                       data-id-field="id"
                                       data-page-size="5"
                                       data-show-footer="false"
                                       data-side-pagination="server"
                                >
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="rowCenterBox col-lg-6">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3 class="pageHeader"><i class="iconfont icon-ditu-shouzhi"></i>定时器监控</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <table id="table2"
                                       data-detail-view="false"
                                       data-minimum-count-columns="2"
                                       data-pagination="true"
                                       data-id-field="id"
                                       data-page-size="5"
                                       data-show-footer="false"
                                       data-side-pagination="server"
                                >
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="rowCenter">
                    <div class="rowCenterBox col-lg-6">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3 class="pageHeader"><i class="iconfont icon-yanjing"></i>K线机器人监控<a href="javascript:void(0);" onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotmonitorlist')">更多>></a></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <table id="table3"
                                       data-detail-view="false"
                                       data-minimum-count-columns="2"
                                       data-pagination="true"
                                       data-id-field="id"

                                       data-show-footer="false"
                                       data-side-pagination="server"
                                >
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="rowCenterBox col-lg-6">
                        <div class="row">
                            <div class="col-lg-12">
                                <h3 class="pageHeader"><i class="iconfont icon-yanjing"></i>深度机器人监控<a href="javascript:void(0);" onclick="loadUrl(_ctx + '/v.do?u=admin/exchange/exrobotdeepmonitorlist')">更多>></a></h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <table id="table4"
                                       data-detail-view="false"
                                       data-minimum-count-columns="2"
                                       data-pagination="true"
                                       data-id-field="id"

                                       data-show-footer="false"
                                       data-side-pagination="server"
                                >
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

            </div>


<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/highcharts7/highcharts.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/highcharts7/exporting.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/highcharts7/no-data-to-display.js"></script>

<script type="text/javascript">
    seajs.use("js/base/center", function (c) {
        c.init();
        c.list();
        c.list2();
        c.list3();
        c.list4();
    });
</script>

-->