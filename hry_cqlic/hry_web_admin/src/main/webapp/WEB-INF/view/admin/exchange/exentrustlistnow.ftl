<!-- Copyright:    -->
 <!-- ExEntrustList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 11:12:40      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">当前委托</h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <form id="table_query_form">
                <div class="row">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="email">邮箱</label>
                            <input type="text" class="form-control" tablesearch name="email"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="mobilePhone">手机号</label>
                            <input type="text" class="form-control" tablesearch name="mobilePhone"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="coinCode">交易币种</label>
                		<@HrySelect url="${ctx}/exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="fixPriceCoinCode">交易区</label>
                		<@HrySelect url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"  name="fixPriceCoinCode"  id="fixPriceCoinCode"   > </@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="entrustTime_GT">开始时间</label>
                            <input type="text" class="form-control" name="entrustTime_GT" id="entrustTime_GT" readonly tablesearch
                                   data-date-format="yyyy-mm-dd" data-min-view="month"
                                   value="${(info.birthday?string("yyyy-MM-dd"))!}">
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="entrustTime_GT">结束时间</label>
                            <input type="text" class="form-control" name="entrustTime_LT" id="entrustTime_LT" readonly tablesearch
                                   data-date-format="yyyy-mm-dd" data-min-view="month"
                                   value="${(info.birthday?string("yyyy-MM-dd"))!}">
                        </div>
                    </div>


                </div>
                <div class="row">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="email">姓氏</label>
                            <input type="text" class="form-control" tablesearch name="surname"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="mobilePhone">名字</label>
                            <input type="text" class="form-control" tablesearch name="trueName"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="entrustWay">出价类型</label>
						<@HrySelect key="entrustWay"  name="entrustWay"  id="entrustWay"   > </@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="entrustType">委托类型</label>
						<@HrySelect key="entrustType"  name="entrustType"  id="entrustType"   > </@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="source">委托来源</label>
						<@HrySelect key="source"  name="source"  id="source"   > </@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div style="margin-top: 26px;">
                            <button type="button" id="table_query" class="btn  btn-primary"><i
                                    class="glyphicon glyphicon-search"></i>查询
                            </button>
                            <button type="button" id="table_reset" class="btn  btn-primary"><i
                                    class="glyphicon glyphicon-refresh"></i>重置
                            </button>
                        </div>
                    </div>
                </div>
            </form>

            <div id="toolbar">
                <button id="cancel" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-share"></i>取消委托
                </button>


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
    <div class="col s4 hide" id="matchDetailDiv">

        <!-- begin row -->

        <div class="row">
            <!-- begin col-3 -->
            <div class="col-sm-12">
                <div class="table-responsive fund-dtable">
                    <table class="table" id="table2">
                        <thead>
                        <tr class="active">
                            <th>成交时间</th>
                            <th>成交数量</th>
                            <th>成交价格</th>
                            <th>成交额</th>

                        </tr>
                        </thead>
                        <tbody id="tbody">


                        </tbody>
                    </table>
                </div>

            </div>
            <!-- end col-3 -->
        </div>
        <!-- end row -->
    </div>

</div>
 <script type="text/javascript">
     seajs.use(["js/admin/exchange/ExEntrust", "js/base/HrySelect", "js/base/HryDate"], function (o, HrySelect, HryDate) {
         HryDate.init();
         HrySelect.init();
         o.list("listnow");
     });

 </script>

