 <!-- Copyright:    -->
 <!-- appFileUploadAdd.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-28 21:20:08      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header"> APP发布管理 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appfileuploadlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="appType">应用类型</label>
			<@HrySelect key="mobile_Application_Type"  name="appType"  id="appType"   > </@HrySelect>
		</div>
		<div class="form-group">
			 <label for="appVersion">版本号</label>
			 <input type="text" class="form-control" name="appVersion" id="appVersion" />
		</div>
   <#--     <div class="form-group">
            <label for="remark">备注</label>
            <input type="text" class="form-control" name="remark" id="remark" />
        </div>-->
        <div class="form-group">
           <label for="appType">安装包路径</label>
            <input type="text" class="form-control  appFilePath" readonly name="appFilePath" id="appFilePath" />
        </div>
        <div class="form-group">
            <label for="appFile">上传安装包</label>
            <div class="hryUpload"  style="width: 100%">
                <div class="hry_inputBox">
                    <input class="form-control hryUpload_filePath " type="hidden"
                           name="${item.configkey}" value="${item.value}">
                    <button type="button" class="btn btn-primary btn-block" >上传</button>
                </div>
                <div style="text-align: left"> <label for="appType" >二维码展示：</label> 说明：此二维码将同步在交易所官网展示，无需重复上传</div>
                <div class="hry_imgBox" style=" padding: 14px 0 0 102px;">

                </div>
            </div>
        </div>

			 <input type="hidden" class="form-control appCodePath" name="appCodePath" id="appCodePath" />





	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
	seajs.use(["js/admin/web/appFileUpload", "js/base/HryFileUpload"],function(o,hryFileUpload){
		 hryFileUpload.upload();
		o.add();
	});
</script>




