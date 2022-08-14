<!-- Copyright:    -->
 <!-- ExLendAccountList.html     -->
 <!-- @author:      HeC  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-18 14:48:05      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">币账户列表</h3>
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
                             <label for="coinCode">交易对</label>
                		    <@HrySelect url="${ctx}/lend/exlendconfig/getCoinCodes"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"></@HrySelect>
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
                    data-page-list="[10, 25, 50, 100, ALL]"
                    data-show-footer="false"
                    data-side-pagination="server"
             >
             </table>
         </div>
     </div>


 </div>

 <script type="text/javascript">
     seajs.use(["js/admin/lend/ExLendAccount", "js/base/HrySelect", "js/base/HryDate"], function (o, HrySelect, HryDate) {
         HrySelect.init();
         HryDate.init();
         o.list();
     });

 </script>

