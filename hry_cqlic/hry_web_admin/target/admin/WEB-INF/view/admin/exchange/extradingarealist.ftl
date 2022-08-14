 <!-- Copyright:    -->
 <!-- ExTradingAreaList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-10-23 11:12:53      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">交易区管理</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
                <div class="col-md-2 column">
                    <div class="form-group">
                        <label for="struts">状态</label>
						 <@HrySelect key="switched"  name="struts_EQ"  id="struts"> </@HrySelect>
                    </div>
                </div>
            </div>
            <div class="col-md-2 column">
                <div style="margin-top: 26px;">
                    <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                    <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                </div>
            </div>
        </div>
        </form>
 	    <div id="toolbar">
             <button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/extradingareaadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加交易区
 	        </button>
             <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
            <button id="open" class="btn btn-info-blue" >
                <i class="glyphicon glyphicon-share"></i>开启
            </button>
             <button id="close" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>关闭
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
 	           data-page-list="[10, 25, 50, 100, ALL]"
 	           data-show-footer="false"  
 	           data-side-pagination="server"
 	           >
 	    </table>
     </div>
 </div>


 </div>

 <script type="text/javascript">
 seajs.use("js/admin/exchange/ExTradingArea",function(o){
 	o.list();
 });

 </script>

