 <!-- Copyright:    -->
 <!-- MailTemplateList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 15:40:16      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
		 <div class="col-lg-12">
			 <h3 class="page-header">邮件模板管理</h3>
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
				 <button id="add" class="btn  btn-info-blue" onclick="loadUrl('${ctx}/v.do?u=/admin/web/mailtemplateadd')" >
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
 </div>
 <script type="text/javascript">
 seajs.use("js/admin/web/MailTemplate",function(o){
 	o.list();
 });

 </script>

