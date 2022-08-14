 <!-- Copyright:    -->
 <!-- UsdtTotalList.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-11 12:01:59      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">平台USDT统计列表</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
				<div class="col-md-2 column">
					<label for="startTime">统计时间_起</label>
					<div class="form-group">
						<input type="text" class="form-control" name="created_GT" id="entrustTime_GT" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month" value="">
					</div>

				</div>
				<div class="col-md-2 column">
					<label for="endTime">统计时间_止</label>
					<div class="form-group">
						<input type="text" class="form-control" name="created_LT" id="entrustTime_LT" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month" value="">
					</div>
				</div>
				<div class="col-md-2 column">
					<div style="<#if status != 0>margin-top: 26px;</#if>">
						<button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
						<button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
					</div>
				</div>
            </div>
        </div>
        </form>
 	    <div id="toolbar">
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
 seajs.use(["js/admin/licqb/platform/UsdtTotal","js/base/HryDate"],function(o, HryDate){
 	o.list();
 	HryDate.init();
 });

 </script>

