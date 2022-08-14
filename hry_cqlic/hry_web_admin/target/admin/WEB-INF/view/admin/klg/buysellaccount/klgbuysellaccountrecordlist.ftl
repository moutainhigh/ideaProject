 <!-- Copyright:    -->
 <!-- KlgBuySellAccountRecordList.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-22 17:02:55      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">买卖剩余账户明细</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="accountName">账户类型</label>
                         <select class="form-control" id="accountName" name="accountName" tablesearch>
                         	<option value=""></option>
			            	<option value="sellSurplusAccount">卖出剩余账户</option>
			            	<option value="buySurplusAccount">支付剩余账户</option>
			            </select>
                     </div>
                 </div>
                <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="orderNum">触发点订单号</label>
                         <input type="text" class="form-control" tablesearch name="orderNum"/>
                     </div>
                 </div>
                <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="triggered">触发点</label>
                         <input type="text" class="form-control" tablesearch name="triggered"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">查询时间起</label>
                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">查询时间止</label>
                        <input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
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
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="true"
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
 	           >
 	    </table>
     </div>
 </div>


 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/klg/buysellaccount/KlgBuySellAccountRecord","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
    HrySelect.init();
 	o.list();
 	t.init();
 });

 </script>

