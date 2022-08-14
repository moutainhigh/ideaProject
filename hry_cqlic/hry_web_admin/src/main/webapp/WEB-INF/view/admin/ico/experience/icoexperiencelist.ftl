 <!-- Copyright:    -->
 <!-- IcoExperienceList.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 10:10:45      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">经验值明细</h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
		<div class="_params">

			<form id="table_query_form">
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="customer_email">邮箱</label>
						<input type="text" class="form-control" tablesearch name="customer_email"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="customer_mobile">手机号</label>
						<input type="text" class="form-control" tablesearch name="customer_mobile"/>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="type">收支类型</label>
						<@HrySelect key="Income_type"  name="type"  id="type" tablesearch="true"  > </@HrySelect>
					</div>
				</div>

				<div class="col-md-2 column">
					<div class="form-group">
						<label for="account_type">交易类型</label>
						<@HrySelect key="experience_account_type"  name="account_type"  id="account_type" tablesearch="true"> </@HrySelect>
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="startTime">出局时间_起</label>
						<input type="text" class="form-control" name="startTime" id="startTime" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month"
							   value="${(info.birthday?string("yyyy-MM-dd"))!}">
					</div>
				</div>
				<div class="col-md-2 column">
					<div class="form-group">
						<label for="endTime">出局时间_止</label>
						<input type="text" class="form-control" name="endTime" id="endTime" readonly tablesearch
							   data-date-format="yyyy-mm-dd" data-min-view="month"
							   value="${(info.birthday?string("yyyy-MM-dd"))!}">
					</div>
				</div>

				<div class="col-md-2 column">
					<div style="margin-top: 26px;">
						<button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
						<button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
					</div>
				</div>
			</form>

		</div>
 	    <div id="toolbar">
            <!--
             <button id="add" class="btn btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/ico/experience/icoexperienceadd')" >
 	            <i class="glyphicon glyphicon-plus"></i>添加
 	        </button>
             <button id="see" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-share"></i>查看
 	        </button>
             <button id="modify" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-edit"></i>编辑
 	        </button>
             <button id="remove" class="btn btn-info-blue" >
 	            <i class="glyphicon glyphicon-remove"></i>删除
 	        </button>
            -->
 	    </div>
 	    <table id="table"
 	           data-toolbar="#toolbar"
 	           data-show-refresh="false"
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
 seajs.use(["js/admin/ico/experience/IcoExperience","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
     o.list();
     HrySelect.init();
     HryDate.init();

 });
 </script>

