 <!-- Copyright:    -->
 <!-- KlgDesignatedRewardecordList.html     -->
 <!-- @author:      lzy  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-04-18 19:00:43      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">指定奖励记录</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
 		<form id="table_query_form">
        <div class="row">
            <div class="_params">
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="email">邮箱</label>
						<input type="text" class="form-control" tablesearch name="email"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="mobilePhone">手机号</label>
						<input type="text" class="form-control" tablesearch name="mobilePhone"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="operatorId">操作人</label>
						<@HrySelect url="${ctx}/user/appuser/findUserName" itemvalue="id" itemname="username" name="operatorId" id="operatorId"></@HrySelect>

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
 seajs.use(["js/admin/klg/limit/KlgDesignatedRewardecord","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
	 o.list();
	 HrySelect.init();
	 HryDate.init();

 });
 </script>

