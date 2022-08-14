<!-- Copyright:    -->
 <!-- WhiteListAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-11 14:27:22      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">添加白名单
                <button type="button" class="btn btn-info-blue pull-right"
                        onclick="loadUrl('${ctx}/v.do?u=/admin/web/whitelistlist')"><i class="fa fa-mail-forward"></i>返回
                </button>
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="_params">
                    <form id="table_query_form">
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="email">邮箱</label>
                                <input type="text" class="form-control" tablesearch name="email_like"/>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="mobilePhone">手机号</label>
                                <input type="text" class="form-control" tablesearch name="mobilePhone_like"/>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="trueName">姓名</label>
                                <input type="text" class="form-control" tablesearch name="trueName_like"/>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="cardId">证件号码</label>
                                <input type="text" class="form-control" tablesearch name="cardId"/>
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
                    </form>
                </div>
            </div>
            <div id="toolbar">
                <button id="addWhite" class="btn btn-info-blue">
                    <i class="glyphicon glyphicon-plus"></i>添加
                </button>
            </div>
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
                   data-page-list="[10, 25, 50, 100]"
                   data-show-footer="false"
                   data-side-pagination="server"
            >
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">
    seajs.use("js/admin/web/WhiteList", function (o) {
        o.addWhite();
    });
</script>