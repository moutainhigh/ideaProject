 <!-- Copyright:    -->
 <!-- KlgBuyTransactionList.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-16 11:40:59      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">买单延期利息解冻</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="transactionNum">订单号</label>
                         <input type="text" class="form-control" tablesearch name="transactionNum"/>
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
                         <label for="email">邮箱</label>
                         <input type="text" class="form-control" tablesearch name="email"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="surName">姓</label>
                         <input type="text" class="form-control" tablesearch name="surName"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="trueName">名</label>
                         <input type="text" class="form-control" tablesearch name="trueName"/>
                     </div>
                 </div>
                  <input type="hidden" class="form-control" tablesearch name="status" value="4"/>
                  <input type="hidden" class="form-control" tablesearch name="interestStatus" value="1"/>
                 <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">预约时间起</label>
                        <input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">预约时间止</label>
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
            <button id="interestJiedong" class="btn btn-info-blue">
                <i class="glyphicon glyphicon-share"></i>利息解冻
            </button>
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
 seajs.use(["js/admin/klg/transaction/KlgBuyTransaction","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,t){
    HrySelect.init();
 	o.interestList();
 	t.init();
 });

 </script>

