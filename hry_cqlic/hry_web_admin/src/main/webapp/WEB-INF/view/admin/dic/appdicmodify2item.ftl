 <!-- Copyright:   互融云 -->
 <!-- SysDicAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-03-01 14:17:02      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">修改字典项 </h3>
    </div>
</div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-5 column">
	
		<div class="form-group">
			 <label for="name">名称</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}"/>
		</div>
		
		<div class="form-group">
			 <label for="mkey">键值</label>
			 <input type="text" class="form-control" name="value" id="value" value="${model.value}"/>
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


 <div class="row">
 <div class="col-md-2 column">
 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
 </div>
 </form>

 <script type="text/javascript">
     seajs.use(["js/admin/dic/AppDic","js/base/HryUpload"],function(o,HryUpload){
         HryUpload.upload();
 	o.modifyItemSubmit();
     //非银行删除两个输入框
     if($("#dic_selected_mkey").val()!="bankType"){
         $("#bank_remark1").remove();
         $("#bank_remark2").remove();
     }
 });
 </script>




