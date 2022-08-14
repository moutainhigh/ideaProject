<!-- Copyright:    -->
 <!-- AppConfigList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-14 11:18:59      -->

<#include "/base/base.ftl">
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
                                <#if item.configkey=="coinCode" && item.value !="" >
                                    <input class="form-control appconfig" disabled="disabled"  type="text"
                                           value='${item.value}'>
                                <#else>
                                    <input class="form-control appconfig"  type="text" name="${item.configkey}"
                                           value='${item.value}'>
                                </#if>
                             <#--   <input class="form-control appconfig"  type="text" name="${item.configkey}"
                                       value='${item.value}'>-->
                             </td>
                        </tr>
                    </#list>
                    <tr>
                        <td style="width: 260px;">发行数量</td>
                        <td>
                            <input class="form-control appconfig" disabled="disabled" type="text"
                                   value='${issueQuantity}'>
                        </td>
                    </tr>

                    <tr>
                        <td style="width: 260px;">剩余可售数量</td>
                        <td>
                            <input class="form-control appconfig"  disabled="disabled" type="text"
                                   value='${surplusAvailable}'>
                        </td>
                    </tr>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                ICO设置
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
                    <#list rulesICOKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                          <#if item.datatype==1>
                              <input class="form-control appconfig" type="text" name="${item.configkey}"
                                     value='${item.value}'>
                          <#elseif item.datatype==5>
                                <input type="text" class="form-control appconfig" name="${item.configkey}" id="${item.configkey}" readonly tablesearch
                                       data-date-format="yyyy-mm-dd" data-min-view="month"
                                       value='${item.value}'>
                          <#elseif item.datatype==2>
                                          <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                                              <option <#if item.value==1>selected="selected" </#if> value="1">否</option>
                                              <option <#if item.value==0>selected="selected" </#if> value="0">是</option>
                                              <
                                          </select>
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
                常规业务设置
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
                    <#list rulesCommonKey as item>
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
                推荐佣金设置
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
                    <#list rulesCommissionKey as item>
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
        <div class="col-md-2 column">
            <button type="button" id="saveSubmit" class="btn btn-blue btn-block">保存</button>
        </div>
    </div>
</div>
 <script type="text/javascript">
     seajs.use(["js/admin/ico/IcoRulesConfig","js/base/HryDate"], function (o,HryDate) {
         o.base();
         HryDate.init();
     });
 </script>