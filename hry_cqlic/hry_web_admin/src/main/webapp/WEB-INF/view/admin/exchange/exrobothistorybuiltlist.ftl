<!-- Copyright:    -->
 <!-- ExRobotList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 16:33:44      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">历史行情构建</h3>
        </div>
    </div>
    <div class="row">
        <div class="_params">
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="_params">

            </div>
            <form id="table_query_form">
                <div class="row">
                    <div class="_params">
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="coinCode">交易区</label>
							<@HrySelect url="${ctx}/exchange/extradingarea/queryOpenArea"  itemvalue="tradingArea"  itemname="tradingArea"   name="fixPriceCoinCode_EQ"  id="fixPriceCoinCode_EQ"   > </@HrySelect>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div style="margin-top: 26px;">
                                <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                                <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div id="toolbar">
               <#-- 开始时间
                <input type="text" class="form-control" name="entrustTime_GT" id="entrustTime_GT" readonly
                       data-date-format="yyyy-mm-dd hh:ii:00" data-minute-step="1"
                >
                结束时间
                <input type="text" class="form-control" name="entrustTime_GT" id="entrustTime_GT" readonly
                       data-date-format="yyyy-mm-dd hh:ii:00" data-minute-step="1"
                >

                <button id="setMoreAccount" class="btn  btn-info-blue" >
                    <i class="glyphicon glyphicon-edit"></i>批量设定账号
                </button>-->

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
                   data-page-size="50"
            >
            </table>
        </div>
    </div>


    <div class="row">
    </div>

<#--构建历史数据-->
    <div id="buildHistoryDiv" style="display:none; top:20px;">
        <form style="text-align: center;">
            <input type="hidden" id="ids">
            开始时间
            <input style="text-align: center;margin-top: 20px;" type="text" class="form-control" name="entrustTime_GT" id="startTime" readonly
                   data-date-format="yyyy-mm-dd hh:ii" data-minute-step="1"
            >
            结束时间
            <input type="text" class="form-control" name="entrustTime_GT" id="endTime" readonly
                   data-date-format="yyyy-mm-dd hh:ii" data-minute-step="1" style="text-align: center;margin-top: 20px;"
            >

            <button id="confirmBuild" class="btn  btn-info-blue" >
                <i class="glyphicon glyphicon-edit"></i>确认构建
            </button>
        </form>
    </div>

</div>
 <script type="text/javascript">
     seajs.use(["js/admin/exchange/ExRobotDeep","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
         o.historybuild(1);
         HrySelect.init();
         HryDate.init();
     });

 </script>

