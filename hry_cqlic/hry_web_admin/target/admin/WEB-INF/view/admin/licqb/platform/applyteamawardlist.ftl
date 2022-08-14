 <!-- Copyright:    -->
 <!-- ApplyTeamAwardList.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:44:07      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">社区资质审核列表</h3>
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
							<label for="socialType">社交类型</label>
							<select class="form-control" id="socialType" tablesearch name="socialType">
								<option value="">请选择</option>
								<option value="4">Twitter</option>
								<option value="3">facebook</option>
								<option value="5">Telegram</option>
								<option value="2">微信</option>
								<option value="1">QQ</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="applyStatus">申请状态</label>
							<select class="form-control" id="applyStatus" tablesearch name="applyStatus">
								<option value="">请选择</option>
								<option value="1">填写账户中</option>
								<option value="2">上传图片中</option>
								<option value="3">申请完成</option>
							</select>
						</div>
					</div>
					<div class="col-md-2 column">
						<div class="form-group">
							<label for="auditStatus">审核状态</label>
							<select class="form-control" id="auditStatus" tablesearch name="auditStatus">
								<option value="">请选择</option>
								<option value="0">审核中</option>
								<option value="1">审核通过</option>
								<option value="2">审核拒绝</option>
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
			</div>
		</form>
 	    <div id="toolbar">
			<button id="allAudit" class="btn btn-info-blue" name="allAudit">
 	            <i class="glyphicon glyphicon-share"></i>审核批量通过
 	        </button>
			<button id="audit" class="btn btn-info-blue" name="audit">
				<i class="glyphicon glyphicon-share"></i>审核
			</button>
			<button id="see" class="btn btn-info-blue" name="see">
				<i class="glyphicon glyphicon-share"></i>查看
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
	 <#--获取审核auditDiv-->
	<div id="auditDiv" style="display:none; top:20px;">
		 <form style="margin-left: 20px">
			 <div style="margin-top: 20px;">
			 <span >审核说明:</span>
			 <textarea style="vertical-align:top;  height: 150px;width: 300px;" readonly name="auditExplain" id="auditExplain" ></textarea>
			 </div>
		 </form>
	 </div>

 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/platform/ApplyTeamAward",function(o){
 	o.list();
 	o.init();
 });
 //查看
 function showPic(is){
	 var src = $(is).attr("src");
	 var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" class="closePicture">×</p></div>'
	 $('body').append(imgHtml);
 }
 $(function(){
	 //查看关闭
	 $("body").on("click", ".closePicture", function removeImg(obj) {
		 $(this).parent("div").remove();
	 })
 })
 </script>

