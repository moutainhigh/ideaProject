 <!-- Copyright:    -->
 <!-- OtcAppTransactionList.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-26 18:12:38      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">全部订单</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
        <div class="row">

            <div class="_params">
                <form id="table_query_form">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="buyEmail">买方邮箱</label>
                            <input type="text" class="form-control" tablesearch name="buyEmail"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="buyMobilePhone">买方手机号</label>
                            <input type="text" class="form-control" tablesearch name="buyMobilePhone"/>
                        </div>
                    </div>

                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="sellEmail">卖方邮箱</label>
                            <input type="text" class="form-control" tablesearch name="sellEmail"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="sellMobilePhone">卖方手机号</label>
                            <input type="text" class="form-control" tablesearch name="sellMobilePhone"/>
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
        </div>
 	    <#--<div id="toolbar">
             <button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/otcapptransactionadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="see" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>
             <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
 	        </button>
 	    </div>-->
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="false"
 	           data-show-columns="false"
 	           data-show-export="false"
 	           data-search="false"
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
 seajs.use("js/admin/exchange/OtcAppTransaction",function(o){
 	o.list(0);
 });

 </script>

