 <!-- Copyright:    -->
 <!-- EcofundList.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-04 11:06:02      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">
			 <#if status == 1>
			 	生态基金审核列表
			 <#elseif status == 6>
				 生态基金核实列表
			 <#else>
				 生态基金申请记录
			 </#if>
		 </h3>
     </div>
 </div>

 <div class="row">
 	<div class="col-md-12">
		<form id="table_query_form">
			<div class="row">
				<div class="_params">
					<input type="hidden" id="status" name="status" value="${status}">
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="mobilePhone">手机号</label>
							<input type="text" class="form-control" tablesearch name="mobilePhone"/>
						</div>
					</div>
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="email">邮箱</label>
							<input type="text" class="form-control" tablesearch name="email"/>
						</div>
					</div>
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="orderNum">申请单号</label>
							<input type="text" class="form-control" tablesearch name="orderNum"/>
						</div>
					</div>

					<div class="col-md-2 column">
						<div class="form-group">
							<label for="startTime">申请时间_起</label>
							<input type="text" class="form-control" name="startTime" id="startTime" readonly tablesearch
								   data-date-format="yyyy-mm-dd" data-min-view="month">
						</div>
					</div>
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="endTime">申请时间_止</label>
							<input type="text" class="form-control" name="endTime" id="endTime" readonly tablesearch
								   data-date-format="yyyy-mm-dd" data-min-view="month">
						</div>
					</div>
					<#if status == 0>
						<div class="col-md-2 column">
							<div class="form-group">
								<label for="activityStatus">申请状态</label>
								<select id="activityStatus" class="form-control" tablesearch name="activityStatus">
									<option value="">请选择</option>
									<option value="1">申请中</option>
									<option value="2">申请拒绝</option>
									<option value="3">平台通过-用户待确认</option>
									<option value="9">资料核实拒绝-用户待确认</option>
									<option value="4">平台通过-用户拒绝</option>
									<option value="5">平台通过-资料待补充</option>
									<option value="6">补充申请-待核实</option>
									<option value="7">申请已完成</option>
								</select>
							</div>
						</div>
					</#if>
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
			<#if status == 1>
				<button id="applySee" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-edit"></i>申请审核
				</button>
			<#elseif status == 6>
				<button id="applySee" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-edit"></i>申请核实
				</button>
			<#else>
				<button id="applySee" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-edit"></i>查看详情
				</button>
			</#if>
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
 seajs.use(["js/admin/licqb/ecology/Ecofund","js/base/HryDate"],function(o,HryDate){
 	 o.list();
	 HryDate.init();
 });

 </script>

