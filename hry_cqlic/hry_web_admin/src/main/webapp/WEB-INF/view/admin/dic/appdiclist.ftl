 <!-- Copyright:   互融云 -->
 <!-- RfieldList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-02-20 11:21:41      -->

<#include "/base/base.ftl">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">字段列表</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 	    <div id="toolbar">
             <button id="add" class="btn  btn-info-blue" onclick="loadUrl2Div('${ctx}/v.do?u=admin/dic/appdicadd2item','dicList_right')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="see" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>
             <button id="modify" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn  btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
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


 <div class="row">
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/dic/AppDic",function(o){
	//获取规则集ID 传到list方法中
 	var pkey = $("#dic_selected_mkey").val();
 	o.list(pkey);
 });

 </script>

