<!-- Copyright:    -->
<!-- DealRecordList.html     -->
<!-- @author:      zhouming  -->
<!-- @version:     V1.0             -->
<!-- @Date:        2019-08-14 15:22:23      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">社区奖励记录</h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <form id="table_query_form">
                <div class="row">
                    <div class="_params">
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
                                <label for="dealType">奖励方式</label>
                                <select id="dealType" class="form-control" tablesearch name="oneDealType">
                                    <option value="">请选择</option>
                                    <option value="1" selected>静态收益</option>
                                    <option value="2">见点奖</option>
                                    <option value="3">管理奖</option>
                                    <option value="4">级别奖</option>
                                    <option value="9">周释放</option>
                                    <option value="10">月释放</option>
                                    <option value="11">年释放</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="startTime">奖励时间_起</label>
                                <input type="text" class="form-control" name="startTime" id="startTime" readonly tablesearch
                                       data-date-format="yyyy-mm-dd" data-min-view="month">
                            </div>
                        </div>
                        <div class="col-md-2 column">
                            <div class="form-group">
                                <label for="endTime">奖励时间_止</label>
                                <input type="text" class="form-control" name="endTime" id="endTime" readonly tablesearch
                                       data-date-format="yyyy-mm-dd" data-min-view="month">
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
    $(function() {
        var startTime = newDate();
        var endTime = newDate();
        $("#startTime").val(startTime);
        $("#endTime").val(endTime);
    })
    function newDate() {
        var now = new Date();
        var year = now.getFullYear(); //得到年份
        var month = now.getMonth();//得到月份
        var date = now.getDate();//得到日期
        month = month + 1;
        if (month < 10) month = "0" + month;
        if (date < 10) date = "0" + date;
        var time = year + "-" + month + "-" + date;
        return time;
    }
    seajs.use(["js/admin/licqb/record/DealRecord","js/base/HryDate"],function(o,HryDate){
        o.awardList(1);
        HryDate.init();
    });
</script>

