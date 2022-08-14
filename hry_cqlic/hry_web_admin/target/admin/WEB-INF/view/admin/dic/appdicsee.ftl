 <!-- Copyright:   互融云 -->
 <!-- SysDicSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-03-01 14:17:02      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">查看字典项  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl2Div('${ctx}/v.do?u=admin/dic/appdiclist','dicList_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-5 column">
		<div class="form-group">
			 <label for="name">名称</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
	
		<div class="form-group">
			 <label for="value">键值</label>
			 <input type="text" class="form-control" name="value" id="value" value="${model.value}" disabled/>
		</div>

        <div class="form-group" id="bank_remark1">
            <label for="value">银行代码</label>
            <input type="text" class="form-control" name="remark1" id="remark1" value="${model.remark1}" />
        </div>

        <div class="form-group" id="bank_remark2">
            <label for="value">银行log</label>
            <div class="hryUpload" style="width: 100%">
                <div class="hry_inputBox">
                    <input class="form-control hryUpload_filePath " type="hidden" name="remark2"  value="${model.remark2}" >
                    <button type="button" class="btn btn-info btn-block">上传图片</button>
                </div>
                <div class="hry_imgBox">

                </div>
            </div>
        </div>

	</div>
	

	
 </div>

 </form>

 <script type="text/javascript">
     seajs.use(["js/base/HryUpload"],function(HryUpload){
         HryUpload.upload();
         //非银行删除两个输入框
         if($("#dic_selected_mkey").val()!="bankType"){
             $("#bank_remark1").remove();
             $("#bank_remark2").remove();
         }
     });
 </script>




