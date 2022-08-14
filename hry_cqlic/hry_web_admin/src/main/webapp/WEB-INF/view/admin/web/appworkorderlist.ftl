 <!-- Copyright:    -->
 <!-- appWorkOrderList.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:48:18      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
		 <div class="col-lg-12">
			 <h3 class="page-header">工单列表</h3>
		 </div>
	 </div>
	 <div class="row">
				<div class="_params">
				</div>
	 </div>
	 <div class="row">
		<div class="col-md-12">
            <div class="_params">
                <form id="table_query_form">
                    <input type="hidden" class="form-control" tablesearch name="languageDic" id="languageDic" value="${keyList[0].value}"/>
                    <div class="row">
						<div class="col-md-2 column">
							<div class="form-group">
								<label for="workOrderNo">工单编号</label>
								<input type="text" class="form-control" tablesearch name="workOrderNo"/>
							</div>
						</div>
						<div class="col-md-2 column">
							<div class="form-group">
								<label for="categoryName">类型</label>
								<input type="text" class="form-control" tablesearch name="categoryName"/>
							</div>
						</div>
						<div class="col-md-2 column">
							<div class="form-group">
								<label for="state">状态</label>
								<select class="form-control" id="state" name="state">
									<option value="">请选择</option>
									<option value="1">已受理</option>
									<option value="0">未受理</option>
								</select>
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
                <ul class="nav nav-tabs">
 					<#if keyList ??>
						<#list keyList as item >
							<#if item.value==keyList[0].value>
								<li class="active"><a href="#${item.name}" data-toggle="tab" class="languageQuery" languageType="${item.value}" >${item.name}</a></li>
							</#if>
							<#if item.value!=keyList[0].value>
								<li ><a href="#${item.name}"  data-toggle="tab" class="languageQuery" languageType="${item.value}">${item.name}</a>  </li>
							</#if>
						</#list>
					</#if>
                </ul>
            </div>
			<div id="toolbar">
			<#--	 <button id="add" class="btn btn-success" onclick="loadUrl('${ctx}/v.do?u=/admin/web/appworkorderadd')" >
					<i class="glyphicon glyphicon-plus"></i>添加
				</button>-->
				 <button id="see" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-share"></i>查看
				</button>
				 <button id="modify" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-edit"></i>受理
				</button>
				<#-- <button id="remove" class="btn btn-info-blue" >
					<i class="glyphicon glyphicon-remove"></i>删除
				</button>-->
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
 </div>
 <script type="text/javascript">
 seajs.use(["js/admin/web/appWorkOrder","js/base/HrySelect"],function(o,HrySelect){
     HrySelect.init();
 	 o.list();
 });

 </script>

