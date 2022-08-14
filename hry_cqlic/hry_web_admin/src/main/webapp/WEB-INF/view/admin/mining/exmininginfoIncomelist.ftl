<!-- Copyright:    -->
 <!-- ExMiningInfoList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-13 16:12:26      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">收入详情</h3>
         </div>
     </div>

     <div class="row">
         <div class="col-md-12">
             <form id="table_query_form">
                 <div class="row">
                     <div class="_params">
                         <input type="hidden" class="form-control" tablesearch name="type"  value="1"/>
                         <div class="col-md-2 column">
                             <div class="form-group">
                                 <label for="transactionTime_GT">查询日期</label>
                                 <input type="text" class="form-control" name="tableTime" id="queryDate" readonly tablesearch  data-date-format="yyyy-mm-dd" data-min-view="month" value="${(info.birthday?string("yyyy-MM-dd"))!}">

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


 </div>

 <script type="text/javascript">
     seajs.use(["js/admin/mining/ExMiningInfo","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
         HryDate.init();
         HrySelect.init();
         o.incomelist();
     });

 </script>

