 <!-- Copyright:    -->
 <!-- ExErc20List.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-08-08 17:02:30      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">上架币种管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
            <div class="row">
                <div class="_params">
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="coinCode">操作人</label>
                            <input type="text" class="form-control" tablesearch name="operator_like" />
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="coinCode">上架币种</label>
                            <input type="text" class="form-control" tablesearch name="name_like" />
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="coinCode">上币类型</label>
                            <@HrySelect key="addCoinType"  name="addCoinType_like"  id="addCoinType"   > </@HrySelect>
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

             <#--<button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/exerc20add')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加新币种
 	        </button>

             <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
 	        </button>-->
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="true"
 	           data-show-columns="true"
 	           data-show-export="true"
 	           data-search="true"
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
 seajs.use("js/admin/exchange/ExErc20",function(o){
 	o.list();
 });

 </script>

