 <!-- Copyright:    -->
 <!-- appFileUploadList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-28 21:20:08      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">APP版本管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="coinCode">应用类型</label>
						<@HrySelect key="mobile_Application_Type"   name="appType_EQ"   id="appType"  > </@HrySelect>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="coinCode">状态是否有效</label>
                      <@HrySelect key="yesno"   name="appStatus_EQ"  id="appStatus" > </@HrySelect>
                    </div>
                </div>
                <div class="col-md-2 column">
                    <div style="margin-top: 26px;">
                        <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                        <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                    </div>
                </div>
            </div>
        </div>
        </form>
 	    <div id="toolbar">
             <button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/web/appfileuploadadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>

             <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>一键还原
 	        </button>
            <button id="modifyVeision" class="btn btn-info-blue" >
                <i class="glyphicon glyphicon-edit"></i>修改版本号
            </button>
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="true"
 	           data-show-columns="false"
 	           data-show-export="false"
 	           data-search="false"
 	           data-detail-view="false"
 	           data-minimum-count-columns="2"
 	           data-pagination="true"
 	           data-id-field="id"
 	           data-page-list="[10, 25, 50, 100]"
 	           data-show-footer="false"  
 	           data-side-pagination="server"
 	           >
 	    </table>
     </div>

 </div>


 </div>

 <script type="text/javascript">
 seajs.use(["js/admin/web/appFileUpload","js/base/HrySelect"],function(o,HrySelect){
      HrySelect.init();
 	o.list();
 });

 </script>

