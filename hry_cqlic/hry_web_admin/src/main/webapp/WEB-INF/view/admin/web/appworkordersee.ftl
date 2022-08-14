 <!-- Copyright:    -->
 <!-- appWorkOrderSee.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:48:18      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
	 <div class="col-md-12">
		 <h3 class="page-header">查看工单 <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/language.do?u=/admin/web/appworkorderlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
	 </div>
	 </div>


	 <form id="form" >
         <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="languageDic">语言</label>
                        <@HrySelect key="sysLanguage"  name="languageDic"  id="languageDic" readonly="readonly"  value="${model.languageDic}"> </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="workOrderNo">工单编号</label>
                    <input type="text" class="form-control" name="workOrderNo" id="workOrderNo" value="${model.workOrderNo}" disabled/>
                </div>

                <div class="form-group">
                    <label for="categoryName">工单类型</label>
                    <@HrySelect url="${ctx}/web/appworkordercategory/ajaxSelectType?languageDic=${model.languageDic}"  itemvalue="id"  itemname="typeName"  name="categoryId"  id="categoryId" readonly="readonly"   value="${model.categoryId}">  </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="workContent">工单内容</label>
                    <div>
                        <textarea id="workContent"  name="workContent" readonly="readonly"     style="width:900px;height:300px;"> ${model.workContent}</textarea>
                    </div>
                </div>
                <#if model.state ==1>
                <div class="form-group">
                    <label for="replyMode">回复方式</label>
                    <@HrySelect key="workOrderReplyMode"  name="replyMode"  id="replyMode" readonly="readonly"  value="${model.replyMode}"  > </@HrySelect>
                </div>
                </#if>
            </div>
         </div>
         <#if model.state ==1>
             <div class="row">
                 <div class="col-md-12 column">
                     <label for="replyMode">回复内容</label>
                     <div ueditorid="replyContent_ue" class="appconfig ueditorparent">
                         <textarea id="replyContent_ue" class="ueditor">${model.replyContent}</textarea>
                     </div>
                 </div>
             </div>
         </#if>
	 </form>
 </div>

<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
    $(".ueditor").each(function () {
        UE.getEditor($(this).attr("id"),{
            autoHeightEnabled: false
        });
    });
</script>

 <script type="text/javascript">
     seajs.use(["js/admin/web/appWorkOrder","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
		 o.see();
	 });
 </script>




