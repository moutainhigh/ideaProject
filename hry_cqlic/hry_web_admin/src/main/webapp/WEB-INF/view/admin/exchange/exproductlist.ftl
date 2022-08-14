 <!-- Copyright:    -->
 <!-- ExProductList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-12 15:44:37      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">币种资料管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">

        <form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="coinCode">币种代码</label>
                        <input type="text" class="form-control" tablesearch name="coinCode_like" />
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
             <button id="add" class="btn  btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exproductadd')" permissions="/admin/exchange/exproduct/add" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>

             <button id="modify" class="btn  btn-info-blue" permissions="/admin/exchange/exproduct/modify" >
 	            <i class="glyphicon glyphicon-edit" ></i>修改
 	        </button>
            <button id="publish" class="btn  btn-info-blue"  permissions="/admin/exchange/exproduct/publish">
                <i class="glyphicon  glyphicon-ok"></i>上架
            </button>
            <button id="undercarriage" class="btn btn-info-blue" >
                <i class="glyphicon glyphicon-remove"></i>下架
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
               date-page-size="25"
 	           >
 	    </table>
     </div>
 </div>


 <div class="row">
 </div>
</div>
 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExProduct",function(o){
 	o.list();
 });

 </script>

