<!-- Copyright:    -->
 <!-- ExDmTransfColdDetailList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-27 18:01:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">代币资产信息</h3>
         </div>
     </div>

     <div class="row">
         <div class="col-md-12">
             <div class="_params">
                 <form id="table_query_form">
                     <div class="row">
                         <div class="col-md-2 column">
                             <div class="form-group">
                                 <label for="email">代币类型</label>
                                 <input type="text" class="form-control" tablesearch id="input_lname" name="coinType"/>
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


             </div>
             <div id="toolbar">
                 <button id="addfee" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-plus"></i>充值矿工费
                 </button>
                 <button id="collect" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-plus"></i>归集
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

 <#--充值矿工费-->
     <div id="toColdAccount" style="display:none; top:20px;">
         <form style="text-align: center;">
             <input type="hidden" id="customerId">
             <input type="input" id="amount" placeholder="金额" style="text-align: center;margin-top: 20px;"/>
             <div class="col-md-2 column" style="margin: 0px 135px -100px;">
                 <button type="button" id="confirmBtn" class="btn  btn-info-blue btn-warning"
                         style="margin-top: 18px;margin-left: 8px;">提交
                 </button>
             </div>
         </form>
     </div>
 </div>
 <script type="text/javascript">
     seajs.use("js/admin/exchange/ExDmTransfColdDetail", function (o) {
         o.listToken();
     });

 </script>

