 <!-- Copyright:    -->
 <!-- KlgPlatformAccountRecordList.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-15 16:36:13      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">平台账户流水</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="serialNumber">流水号</label>
						<input type="text" class="form-control" tablesearch name="serialNumber"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="operatorId">账户类型</label>
						<@HrySelect key="account_type" name="accountType" id="accountType"></@HrySelect>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="remark">操作类型</label>
						<input type="text" class="form-control" tablesearch name="remark"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="startTime">开始时间_起</label>
						<input type="text" class="form-control" name="startTime" id="startTime" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month">
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="endTime">结束时间_止</label>
						<input type="text" class="form-control" name="endTime" id="endTime" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month">
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
 seajs.use(["js/admin/klg/platform/KlgPlatformAccountRecord","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
	 o.list();
	 HrySelect.init();
	 HryDate.init();
 });
 </script>

