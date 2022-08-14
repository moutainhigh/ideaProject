 <!-- Copyright:    -->
 <!-- MailTemplateSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 15:40:16      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin" >
	 <div class="row">
		 <div class="col-md-12">
			 <h3 class="page-header">查看邮箱模板  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/language.do?u=/admin/web/mailtemplatelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
		 </div>
	 </div>


	 <form id="form" >
		 <div class="row">
			<div class="col-md-4 column">
                <div class="form-group">
                    <label for="languageDic">语言</label>
					<@HrySelect key="sysLanguage"  name="languageDic"  id="languageDic"   value="${model.languageDic}" readonly="readonly"> </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="tempName">模板名称</label>
                    <input type="text" class="form-control" name="tempName" id="tempName" value="${model.tempName}" disabled/>
                </div>
                <div class="form-group">
                    <label for="tempKey">模板类型</label>
					<@HrySelect key="mailTempKey"  name="tempKey"  id="tempKey"   value="${model.tempKey}" readonly="readonly"> </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="tempContent">模板内容</label>
                    <div>
                        <textarea id="tempContent"  name="tempContent"  class="ueditor"  style="width:900px;height:300px;"  > ${model.tempContent}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label for="remark">模板备注</label>
                    <input type="text" class="form-control" name="remark" id="remark" value="${model.remark}" disabled/>
                </div>
                <div class="form-group">
                    <label for="mailConfigId">使用邮箱</label>
					<@HrySelect url="${ctx}/web/mailconfig/mailConfigList"  itemvalue="id"  itemname="emailUser"  name="mailConfigId"  id="mailConfigId"  readonly="readonly" value="${model.mailConfigId}">  </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="tempStatus">是否启用</label>
					 <@HrySelect key="yesno"  name="tempStatus"  id="tempStatus"  value="${model.tempStatus}"  readonly="readonly"> </@HrySelect>
                </div>

			</div>
		 </div>

 	</form>
</div>
  <script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
      $(".ueditor").each(function () {
          UE.getEditor($(this).attr("id"),{
              autoHeightEnabled: false,
              readonly : true
          });
      })
  </script>
 <script type="text/javascript">
     seajs.use(["js/admin/web/MailTemplate","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
 		o.see();
 	});
 </script>




