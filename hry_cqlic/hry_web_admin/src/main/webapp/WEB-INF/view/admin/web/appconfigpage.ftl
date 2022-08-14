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
                <input type="hidden" name="typeKey" id="typeKey" value="${typeKey}"/>
			     <#if typeKey=="baseConfig" >基础信息配置
                 <#elseif typeKey=="noticeConfig">短信模板管理
                 <#elseif typeKey=="registerConfig">注册协议管理
                 <#elseif typeKey=="smsConfig">短信接口管理
                 <#elseif typeKey=="ftpConfig">FTP参数配置
                 <#elseif typeKey=="juheConfig">实名认证管理
                 <#elseif typeKey=="pictureConfig">图片上传配置
                 <#elseif typeKey=="interfaceConfigCS">客服配置管理
                 <#elseif typeKey=="emailTempConfig">邮件模板管理
                 <#elseif typeKey=="emailInterConfig">邮件接口管理
                 <#elseif typeKey=="appQRCodeConfig">APP下载二维码
                 <#elseif typeKey=="commendConfig">推荐佣金配置
                 <#elseif typeKey=="financeConfig">金融参数配置
                 <#elseif typeKey=="interfaceConfig">第三方接口开关配置
                 <#elseif typeKey=="extraParamConfig">附加信息配置
                 <#elseif typeKey=="klinedataconfig">交易对实时价格
                 <#elseif typeKey=="hisklinedataconfig">交易对历史行情
                 <#elseif typeKey=="realtimepriceconfig">单币种实时价格
                 <#elseif typeKey=="realTimePairPriceConfig">多币种实时价格
                 <#elseif typeKey=="getBalanceConfig">接口余额查询
                 <#else>基础参数配置
                 </#if>

            </h3>
        </div>
    </div>
    <div class="row">
        <div class="_params">
        </div>
    </div>


    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>配置名称</th>
                    <th>配置参数</th>
                </tr>
                </thead>
                <tbody>
			<#list configList as item>
            <tr>
                <td>${item.configname}</td>
                <td>
					<#if item.datatype==1>
                        <input class="form-control appconfig" type="text" name="${item.configkey}"
                               value='${item.value}'>
                    <#elseif item.datatype==2>
                        <#if item.configkey="coinCodeForRmbType" >
                        <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                            <option <#if item.value==1>selected="selected" </#if> value="1">真实货币</option>
                            <option <#if item.value==2>selected="selected" </#if> value="2">虚拟货币</option>
                            <
                        </select>
                        <#else>
                        <select name="${item.configkey}" id="${item.configkey}" class="form-control appconfig">
                            <option <#if item.value==1>selected="selected" </#if> value="1">否</option>
                            <option <#if item.value==0>selected="selected" </#if> value="0">是</option>
                            <
                        </select>
                        </#if>
                    <#elseif item.datatype==3>

                        <div class="hryUpload">
                            <div class="hry_inputBox">
                                <input class="form-control hryUpload_filePath appconfig" type="hidden"
                                       name="${item.configkey}" value="${item.value}">
                                <button type="button" class="btn btn-primary btn-block">上传图片</button>
                            </div>
                            <div class="hry_imgBox">

                            </div>
                        </div>

                    <#elseif item.datatype==4>
                        <div name="${item.configkey}" ueditorid="ueditor_${item.configkey}"
                             class="appconfig ueditorparent">
                            <textarea id="ueditor_${item.configkey}" class="ueditor"
                                      style="width:900px;height:300px;">${item.value}</textarea>
                        </div>
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
    <#--交易对实时行情-->
    <#if typeKey=="klinedataconfig">
        <div class="col-md-2 column">
            <button type="button" id="testInterface" class="btn btn-blue btn-block">测试接口</button>
        </div>

    </#if>
    <#--实时价格（单个币种）-->
     <#if typeKey=="realtimepriceconfig">
        <div class="col-md-2 column">
            <button type="button" id="testRealTimePriceInterface" class="btn btn-blue btn-block">测试接口</button>
        </div>
     </#if>
    <#--实时价格-->
     <#if typeKey=="realTimePairPriceConfig">
        <div class="col-md-2 column">
            <button type="button" id="testMoreRealTime" class="btn btn-blue btn-block">测试接口</button>
        </div>
     </#if>
      <#if typeKey=="getBalanceConfig">
        <div class="col-md-2 column">
            <button type="button" id="testHistoryInterface" class="btn btn-blue btn-block">测试接口</button>
        </div>
      </#if>
        <#--历史行情接口-->
         <#if typeKey=="hisklinedataconfig">
        <div class="col-md-2 column">
            <button type="button" id="hisklinedataconfig" class="btn btn-blue btn-block">测试接口</button>
        </div>
         </#if>

        <#--短信接口-->
          <#if typeKey=="smsConfig">
        <div class="col-md-2 column">
            <button type="button" id="smsConfigTest" class="btn btn-blue btn-block">测试接口</button>
        </div>
          </#if>

        <#--实名认证接口-->
          <#if typeKey=="juheConfig">
        <div class="col-md-2 column">
            <button type="button" id="juheConfigTest" class="btn btn-blue btn-block">测试接口</button>
        </div>
          </#if>


    </div>


</div>


 <script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>


 <script type="text/javascript">

     seajs.use(["js/admin/web/AppConfig", "js/base/HryUpload"], function (o, HryUpload) {
         HryUpload.upload();
         o.page();
     });

 </script>
<script type="text/javascript">
    $(".ueditor").each(function () {
        UE.getEditor($(this).attr("id"), {
            autoHeightEnabled: false
        });
    })
</script>
