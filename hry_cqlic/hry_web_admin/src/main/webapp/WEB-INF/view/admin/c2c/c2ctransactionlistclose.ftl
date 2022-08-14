 <!-- Copyright:    -->
 <!-- C2cTransactionList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 13:34:46      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">C2c关闭订单管理</h3>
     </div>
 </div>
    <#--aaa-->


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
						<@HrySelect url="${ctx}/c2c/c2ccoin/findall"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="businessmanId">交易商</label>
					    <@HrySelect url="${ctx}/c2c/appbusinessman/findall"  itemvalue="id"  itemname="trueName"  name="businessmanId"  id="businessmanId"   > </@HrySelect>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="transactionType">交易类型</label>
                        <select class="form-control" id="transactionType" name="transactionType" tablesearch>
                            <option value="">请选择</option>
                            <option value="1">买</option>
                            <option value="2">卖</option>
                        </select>
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
     seajs.use(["js/admin/c2c/C2cTransaction","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
 	o.list("3");
 });

 </script>

