<!-- Copyright:    -->
<!-- AppConfigList.html     -->
<!-- @author:      liushilei  -->
<!-- @version:     V1.0             -->
<!-- @Date:        2018-06-14 11:18:59      -->

<#include "/base/base.ftl">
<link rel="stylesheet" type="text/css" href="${ctx}/static/${version}/lib/jdate/css/jedate.css" />
<script type="text/javascript" src="${ctx}/static/${version}/lib/jdate/jedate-6.5.0.js"></script>
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                平台发币配置
            </h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list rulesCoinKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig"  type="text" name="${item.configkey}"
                                   value='${item.value}'>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                提币时间配置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list extractTimeKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <#if item.datatype==1>
                                <input class="form-control appconfig" type="text" name="${item.configkey}"
                                   value='${item.value}'>
                            <#elseif item.datatype==5>
                                <input type="text" class="form-control jeinput appconfig" name="${item.configkey}" id="${item.configkey}" value='${item.value}' placeholder="hh:mm:ss">
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                兑换时间配置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list exchangeTimeKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <#if item.datatype==1>
                                <input class="form-control appconfig" type="text" name="${item.configkey}"
                                       value='${item.value}'>
                            <#elseif item.datatype==5>
                                <input type="text" class="form-control jeinput appconfig" name="${item.configkey}" id="${item.configkey}" value='${item.value}' placeholder="hh:mm:ss">
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                投资区间配置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list investRangeKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig rulesAwardKey" type="text" name="${item.configkey}" value='${item.value}' >
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                理财时间配置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list investTimeKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <#if item.datatype==1>
                                <input class="form-control appconfig" type="text" name="${item.configkey}"
                                       value='${item.value}'>
                            <#elseif item.datatype==5>
                                <input type="text" class="form-control jeinput appconfig" name="${item.configkey}" id="${item.configkey}" value='${item.value}' placeholder="hh:mm:ss">
                            </#if>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                静态收益设置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list staticGainsKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig" type="text" name="${item.configkey}" value='${item.value}' >
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                见点奖设置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list seeAwardKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig" type="text" name="${item.configkey}" value='${item.value}' >
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                管理奖设置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list manageAwardKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig" type="text" name="${item.configkey}" value='${item.value}' >
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <#--生态入驻规则配置start -->
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                生态入驻规则配置
            </h3>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover" id="configTable">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
                <#list ecologenterKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                            <input class="form-control appconfig" type="text" name="${item.configkey}" value='${item.value}' >
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <#--生态入驻规则配置end -->
    <div class="row">
        <div class="col-md-2 column">
            <button type="button" id="saveSubmit" class="btn btn-blue btn-block">保存</button>
        </div>
    </div>
</div>
<script type="text/javascript">
    seajs.use("js/admin/licqb/platform/RuleConfig", function (o) {
        o.base();
    });
    var allinput = $(".jeinput");
    for(var i = 0 ; i < allinput.length; i++){
        var input = $(allinput[i]);
        var id = input.attr("id");
        jeDate('#'+id,{
            format:"hh:mm:ss",
            minDate:"00:00:00",              //最小日期
            maxDate:"23:59:59",              //最大日期
            format: "hh:mm:ss"
        });
    }

</script>