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
                基础信息配置
            </h3>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-tabs" id="lang_tab">
                <#if langList??>
                    <#list langList as item >
                        <#if item_index==0>
                            <li class="active">
                                <a data-toggle="tab" class="languageQuery" languageType="baseConfig${item.value}">${item.name}</a>
                            </li>
                        </#if>
                        <#if item_index!=0>
                            <li>
                                <a data-toggle="tab" class="languageQuery" languageType="baseConfig${item.value}">${item.name}</a>
                            </li>
                        </#if>
                    </#list>
                </#if>
            </ul>
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
                    <#list configList as item>
                        <tr>
                            <td style="width: 260px;">${item.configname}</td>
                            <td>
                                <#if item.datatype==1>
                                    <input class="form-control appconfig" type="text" name="${item.configkey}" value='${item.value}' >
                                <#elseif item.datatype==2>
                                    <select name="${item.configkey}"  id="${item.configkey}" class="form-control appconfig">
                                        <option <#if item.value==1>selected="selected" </#if>  value="1">否</option>
                                        <option <#if item.value==0>selected="selected" </#if> value="0">是</option><
                                    </select>
                                <#elseif item.datatype==3>

                                    <div class="hryUpload">
                                        <div class="hry_inputBox">
                                            <input class="form-control hryUpload_filePath appconfig" type="hidden" name="${item.configkey}" value="${item.value}" >
                                            <button type="button" class="btn btn-primary btn-block">上传图片</button>
                                        </div>
                                        <div class="hry_imgBox">
                                        </div>
                                    </div>
                                <#elseif item.datatype==4>
                                    <div name="${item.configkey}" ueditorid="ueditor_${item.configkey}_${item.configid}" class="appconfig ueditorparent">
                                        <textarea id="ueditor_${item.configkey}_${item.configid}" class="ueditor"  style="width:100%;height:300px;">${item.value}</textarea>
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
            <input type="hidden" id="typeKeyFlag" value="${typeKeyFlag}">
            <button type="button" id="saveSubmit" class="btn btn-blue btn-block">保存</button>
        </div>
    </div>
</div>

<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

 <script type="text/javascript">
     $(".ueditor").each(function () {
         UE.delEditor($(this).attr("id"));
         UE.getEditor($(this).attr("id"), {
             autoHeightEnabled: false
         });
     })
 </script>
 <script type="text/javascript">
     seajs.use(["js/admin/web/AppConfig", "js/base/HryUpload"], function (o, HryUpload) {
         HryUpload.upload();
         o.base();
     });
 </script>