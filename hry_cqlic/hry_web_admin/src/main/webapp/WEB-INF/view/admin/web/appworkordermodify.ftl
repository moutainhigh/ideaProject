 <!-- Copyright:    -->
 <!-- appWorkOrderModify.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:48:19      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
	 <div class="col-md-12">
		 <h3 class="page-header">受理工单  <button type="button"  class="btn btn-warning pull-right"  onclick="loadUrl('${ctx}/language.do?u=/admin/web/appworkorderlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
	 </div>
	 </div>


	 <form id="form" >
	 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
	 <input type="hidden" class="form-control" name="customerId" id="customerId" value="${model.customerId}"/>
	 <div class="row">
		<div class="col-md-4 column">
            <div class="form-group">
                <label for="languageDic">语言</label>
					<@HrySelect key="sysLanguage"  name="languageDic"  id="languageDic" readonly="readonly"  value="${model.languageDic}"> </@HrySelect>
            </div>
			<div class="form-group">
				 <label for="workOrderNo">工单编号</label>
				 <input type="text" class="form-control" name="workOrderNo" id="workOrderNo" value="${model.workOrderNo}"  readonly="readonly"/>
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
            <div class="form-group">
                <label for="replyMode">回复方式</label>
				<@HrySelect key="workOrderReplyMode"  name="replyMode"  id="replyMode"  value="${model.replyMode}"  > </@HrySelect>
            </div>
		</div>
	 </div>
	 <div class="row">
         <div class="col-md-12 column">
             <label for="replyMode">回复内容</label>
             <div ueditorid="replyContent_ue" class="appconfig ueditorparent">
                 <textarea id="replyContent_ue" class="ueditor">${model.replyContent}</textarea>
             </div>
             <input type="hidden" name="replyContent" id="replyContent_hide"/>
         </div>
	 </div>

	 <div class="row">
		 <div class="col-md-2 column">
			<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >提交</button>
		 </div>
	 </div>
	 </form>
 </div>
<code id="sysHtml" style="display: none;">
    <p>尊敬的客户：___<span style="text-decoration: none;">您好！</span></p><p><br/></p><p style="margin-bottom: 5px; text-indent: 2em; text-align: left;"><span style="text-decoration: none;">回复内容：_____________________________________</span></p><p style="text-align: left; text-indent: 2em; margin-left: 500px;"><br/>客服：_____</p>
</code>
 <code id="telHtml" style="display: none;">
     <p>回复电话：________</p><p><br/></p><p>回复内容：________</p><p><br/></p><p>回复时间：________</p><p><br/></p><p>回复客服：________</p>
 </code>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>

 <script type="text/javascript">
 seajs.use(["js/admin/web/appWorkOrder","js/base/HrySelect"],function(o,HrySelect){
     HrySelect.init();
     o.initUE();
     o.init();
 	 o.modify();
 });
 </script>






