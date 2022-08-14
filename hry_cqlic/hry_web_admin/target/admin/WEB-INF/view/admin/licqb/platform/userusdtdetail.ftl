<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <div class="_params">
                <form id="userTable_query_form">
                    <input id="customerId" name="customerId" type="hidden" value="${customerId}">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="transactionType">交易类型</label>
                            <select id="transactionType" class="form-control" name="transactionType" tablesearch>
                                <option value="">请选择</option>
                                <option value="1">充币</option>
                                <option value="2">提币</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="transactionNum">交易订单号</label>
                            <input type="text" class="form-control" tablesearch name="transactionNum"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div style="margin-top: 26px; width: 150px">
                            <button type="button" id="userTable_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                            <button type="button" id="userTable_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                        </div>
                    </div>
                </form>

            </div>
            <div id="userToolbar">
            </div>
            <table id="userTable"
                   data-toolbar="#userToolbar"
                   data-show-refresh="false"
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
    seajs.use("js/admin/licqb/platform/UsdtTotal",function(o){
        o.userDetailList();
    });

</script>

