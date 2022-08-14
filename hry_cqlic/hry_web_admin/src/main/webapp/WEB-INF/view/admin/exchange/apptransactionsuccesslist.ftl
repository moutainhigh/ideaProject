 <!-- Copyright:    -->
 <!-- AppTransactionList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-06 14:36:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">充值成功查询</h3>
     </div>
 </div>
 <div class="row">
     <div class="col-md-12">
        <div class="_params">
            <form id="table_query_form">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">用户邮箱</label>
                        <input type="text" class="form-control" tablesearch name="email"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">用户手机</label>
                        <input type="text" class="form-control" tablesearch name="mobile"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">用户姓氏</label>
                        <input type="text" class="form-control" tablesearch name="surname"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">用户名字</label>
                        <input type="text" class="form-control" tablesearch name="trueName"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="mobilePhone">交易订单号</label>
                        <input type="text" class="form-control" tablesearch name="transactionNum"/>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="email">银行卡号</label>
                        <input type="text" class="form-control" tablesearch name="custromerAccountNumber"/>
                    </div>
                </div>

                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </form>
        </div>
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


 <div class="row">
 </div>
 </div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/AppTransaction",function(o){
 	o.successlist(3);
 });

 </script>

