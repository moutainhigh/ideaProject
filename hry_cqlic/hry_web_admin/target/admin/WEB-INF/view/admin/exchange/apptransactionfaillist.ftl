 <!-- Copyright:    -->
 <!-- AppTransactionList.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-06 14:36:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">充值失败查询</h3>
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
               <#-- <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">申请时间起</label>
                        <input type="text" class="form-control" name="entrustTime_GT" id="entrustTime_GT" readonly
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="entrustTime_GT">申请时间止</label>
                        <input type="text" class="form-control" name="entrustTime_LT" id="entrustTime_LT" readonly
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
                    </div>
                </div>-->
               <#-- <div class="col-md-4 column">
                    <div class="form-group">
                        <label for="changeCoin">交易币种</label>
                        <@HrySelect url="${ctx}//exchange/exproduct/findall"  itemvalue="coinCode"  itemname="coinCode"  name="changeCoin"  id="changeCoin"   > </@HrySelect>

                    </div>
                </div>-->
                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </form>
        </div>
 	    <div id="toolbar">
             <#--<button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/apptransactionadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>-->
            <#-- <button id="see" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>-->
            <#-- <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
 	        </button>-->
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
 seajs.use(["js/admin/exchange/AppTransaction","js/base/HrySelect"],function(o,HrySelect){
 	o.faillist();
     HrySelect.init();
 });

 </script>

