<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <div class="_params">
                <form id="sonTable_query_form">
                    <input id="customerId" name="customerId" type="hidden" value="${customerId}">
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
                            <label for="sort">用户等级</label>
                            <select id="sort" class="form-control" name="sort" tablesearch>
                                <option value="">请选择</option>
                                <#list configList as level>
                                    <option value="${level.sort}">${level.levelName}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="sort">层级</label>
                            <select id="level" class="form-control" name="level" tablesearch>
                                <option value="">请选择</option>
                                <option value="1">一代</option>
                                <option value="2">二代</option>
                                <option value="3">三代</option>
                                <option value="4">四代</option>
                                <option value="5">五代</option>
                                <option value="6">六代</option>
                                <option value="7">七代</option>
                                <option value="8">八代</option>
                                <option value="9">九代</option>
                                <option value="10">十代</option>
                                <option value="11">十代以上</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div style="margin-top: 26px; width: 150px">
                            <button type="button" id="sonTable_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                            <button type="button" id="sonTable_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                        </div>
                    </div>
                </form>

            </div>
            <div id="sonToolbar">
            </div>
            <table id="sonTable"
                   data-toolbar="#sonToolbar"
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
    seajs.use("js/admin/licqb/record/CommendUser",function(o){
        o.sonList();
    });

</script>

