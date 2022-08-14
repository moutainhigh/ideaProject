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
                                <#if item.configkey=="coinCode" && item.value !="" >
                                    <input class="form-control appconfig"  type="text" name="${item.configkey}"
                                           value='${item.value}'>
                                <#else>
                                    <input class="form-control appconfig"  type="text" name="${item.configkey}"
                                           value='${item.value}'>
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
                开盘时间设置
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
                    <#list rulesTimeKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                          <#if item.datatype==1>
                              <input class="form-control appconfig" type="text" name="${item.configkey}"
                                     value='${item.value}'>
                          <#elseif item.datatype==5>

                            <div class='col-md-8'>
                                <input type="text" class="form-control jeinput appconfig" name="${item.configkey}" id="${item.configkey}" value='${item.value}' placeholder="hh:mm:ss">
                          <#elseif item.datatype==2>
                                        <#--  <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                                              <option <#if item.value==1>selected="selected" </#if> value="1">否</option>
                                              <option <#if item.value==0>selected="selected" </#if> value="0">是</option>
                                              <
                                          </select>-->
                                    <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                                        <option <#if isOpen==1>selected="selected" </#if> value="1">是</option>
                                        <option <#if isOpen==0>selected="selected" </#if> value="0">否</option>
                                    </select>
                          </#if>
                        </td>
                    </tr>
                    </#list>
             <#--    <tr>
                        <td>开关</td>
                        <td>
                            <select name="isOpen" id="isOpen" class="form-control appconfig">
                                <option <#if isOpen==1>selected="selected" </#if> value="1">否</option>
                                <option <#if isOpen==0>selected="selected" </#if> value="0">是</option>
                            </select>
                        </td>
                    </tr>-->
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <h3 class="page-header">
                糖果奖励分配
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
                    <#list rulesAwardKey as item>
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
                预约买入设置
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
                    <#list rulesPurchaseKey as item>
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
                大奖基金设置
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
                    <#list luckDrawKey as item>
                    <tr>
                        <td style="width: 260px;">${item.configname}</td>
                        <td>
                          <#if item.datatype==1>
                              <input class="form-control appconfig" type="text" name="${item.configkey}"
                                     value='${item.value}'>
                          <#elseif item.datatype==5>

                            <div class='col-md-8'>
                                <input type="text" class="form-control jeinput appconfig" name="${item.configkey}" id="${item.configkey}" value='${item.value}' placeholder="hh:mm:ss">
                          <#elseif item.datatype==2>
                                        <#--  <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                                              <option <#if item.value==1>selected="selected" </#if> value="1">否</option>
                                              <option <#if item.value==0>selected="selected" </#if> value="0">是</option>
                                              <
                                          </select>-->
                                    <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                                        <option <#if item.value==1>selected="selected" </#if> value="1">是</option>
                                        <option <#if item.value==0>selected="selected" </#if> value="0">否</option>
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
        <div class="col-md-2 column">
            <button type="button" id="saveSubmit" class="btn btn-blue btn-block">保存</button>
        </div>
    </div>
</div>
 <script type="text/javascript">
     seajs.use(["js/admin/klg/KlgRulesConfig"], function (o) {
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