 <!-- Copyright:    -->
 <!-- EcofundModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-04 11:06:02      -->

 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
	 <div class="col-md-12">
		 <h3 class="page-header">
			 <#if model.activityStatus == 1>
				 申请中
			 <#elseif model.activityStatus == 2>
				 申请拒绝
			 <#elseif model.activityStatus == 3>
				 <#if model.itAgain = 1>
					 资料核实拒绝-用户待确认
				 	<#else>
						平台通过-用户待确认
				 </#if>
			 <#elseif model.activityStatus == 4>
				 平台通过-用户拒绝
			 <#elseif model.activityStatus == 5>
				 平台通过-资料待补充
			 <#elseif model.activityStatus == 6>
				 补充申请-待核实
			 <#elseif model.activityStatus == 7>
				 申请已完成
			 </#if>
			 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/licqb/ecology/ecofund/gotoEcoList?status='+${status})" > <i class="fa fa-mail-forward"></i>返回</button></h3>
	 </div>
	 </div>
	 <form id="form" >
		 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
		 <input type="hidden" class="form-control" name="status" id="status" value="${status}"/>
		 <#-- 客户信息--start -->
		 <div class="row">
			<div class="col-md-12">
				<h4 class="page-header">客户信息</h4>
			</div>
		 </div>
		 <div class="row">
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="mobilePhone">手机号</label>
					 <input type="text" class="form-control" id="mobilePhone" name="mobilePhone" value="${model.mobilePhone}" readonly="true">
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="email">邮箱</label>
					 <input type="text" class="form-control" id="email" name="email" value="${model.email}" readonly="true">
				 </div>
			 </div>
		 </div>
		 <#-- 客户信息--end -->
		 <#-- 申请信息--start -->
		 <div class="row">
			 <div class="col-md-12">
				 <h4 class="page-header">申请信息</h4>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="activityName">团队名称</label>
					 <input type="text" class="form-control" id="activityName" name="activityName" value="${model.activityName}" readonly="true">
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="orderNum">申请单号</label>
					 <input type="text" class="form-control" id="orderNum" name="orderNum" value="${model.orderNum}" readonly="true">
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="activityDate">活动时间</label>
					 <input type="text" class="form-control" name="activityDate" id="activityDate" value="${model.activityDate?string("yyyy-MM-dd HH:mm:ss")}" readonly="true"/>
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="activityAddress">活动地址</label>
					 <input type="text" class="form-control" name="activityAddress" id="activityAddress" value="${model.activityAddress}" readonly="true"/>
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="peopleCount">活动人数</label>
					 <input type="text" class="form-control" name="peopleCount" id="peopleCount" value="${model.peopleCount}" readonly="true"/>
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="created">申请时间</label>
					 <input type="text" class="form-control" id="created" name="created" value="${model.created?string("yyyy-MM-dd HH:mm:ss")}" readonly="true">
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-8 column">
				 <div class="form-group">
					 <label for="activityIntro">活动简介</label>
					 <textarea rows="8" readonly="true" class="form-control" id="activityIntro" name="activityIntro" value="${model.activityIntro}">${model.activityIntro}</textarea>
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-8 column">
				 <div class="form-group">
					 <label for="activityImage">活动图片</label>
					 <input type="hidden" name="activityImage" id="activityImage" value="${model.activityImage}"/>
					 <div class="form-group" id="activityImageDiv">

					 </div>
				 </div>
			 </div>
		 </div>
		 <#-- 申请信息--end -->
		 <#-- 申请时回复内容--start-->
		 <#if model.activityStatus != 1>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="activityReply">申请时回复内容</label>
						 <textarea rows="8" class="form-control" id="activityReply" name="activityReply" value="${model.activityReply}" readonly="true">${model.activityReply} </textarea>
					 </div>
				 </div>
			 </div>
		 </#if>
		 <#-- 申请时回复内容--end-->
		 <#-- 补充信息--start -->
		 <#if model.activityStatus == 6 || model.activityStatus == 7 || model.activityStatus == 8 || model.itAgain == 1>
			 <div class="row">
				 <div class="col-md-12">
					 <h4 class="page-header">补充信息</h4>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="againCreated">补充时间</label>
						 <input type="text" class="form-control" id="againCreated" name="againCreated" value="${model.againCreated?string("yyyy-MM-dd HH:mm:ss")}" readonly="true">
					 </div>
				 </div>
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="againActivityDate">活动时间</label>
						 <input type="text" class="form-control" name="againActivityDate" id="againActivityDate" value="${model.againActivityDate?string("yyyy-MM-dd HH:mm:ss")}" readonly="true"/>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="againActivityAddress">活动地址</label>
						 <input type="text" class="form-control" name="againActivityAddress" id="againActivityAddress" value="${model.againActivityAddress}" readonly="true"/>
					 </div>
				 </div>
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="againPeopleCount">活动人数</label>
						 <input type="text" class="form-control" name="againPeopleCount" id="againPeopleCount" value="${model.againPeopleCount}" readonly="true"/>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="againActivityIntro">活动简介</label>
						 <textarea readonly="true" rows="8" class="form-control" id="againActivityIntro" name="againActivityIntro" value="${model.againActivityIntro}">${model.againActivityIntro}</textarea>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="againActivityImage">活动图片</label>
						 <input type="hidden" class="form-control" name="againActivityImage" id="againActivityImage" value="${model.againActivityImage}"/>
						 <div class="form-group" id="againActivityImageDiv">

						 </div>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="againActivityVideo">活动视频</label>
						 <input type="hidden" class="form-control" name="againActivityVideo" id="againActivityVideo" value="${model.againActivityVideo}"/>
						 <div class="form-group" id="againActivityImageDiv">
							 <video  controls="controls" id="filevido" src="${fileUrl}${model.againActivityVideo}" style="display: inline-block;"></video>
						 </div>
					 </div>
				 </div>
			 </div>
			 <#if model.activityStatus == 7 || model.activityStatus == 8 || model.itAgain == 1>
				 <div class="row">
					 <div class="col-md-8 column">
						 <div class="form-group">
							 <label for="againActivityReply">补充时回复内容</label>
							 <textarea rows="8" readonly="true" class="form-control" id="againActivityReply" name="againActivityReply" value="${model.againActivityReply}">${model.againActivityReply}</textarea>
						 </div>
					 </div>
				 </div>
			 </#if>
		 </#if>
		 <#-- 补充信息--end -->

		 <#-- 审核操作--start -->
		 <#if model.activityStatus == 1 && status != 0>
			 <div class="row">
				 <div class="col-md-12">
					 <h4 class="page-header">审核操作</h4>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="activityStatus" style="margin-right:10px">审核结果</label>
						 <input type="radio" name="activityStatus"  value="3" checked="true"> 审核通过
						 <input type="radio" name="activityStatus"  value="2" > 审核拒绝
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="activityReply">回复内容</label>
						 <textarea rows="8" class="form-control" id="activityReply" name="activityReply" value="${model.activityReply}">${model.activityReply}</textarea>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-2 column">
					 <button type="button" id="applyAudit" class="btn btn-info-blue btn-warning" >提交审核</button>
				 </div>
			 </div>
		 </#if>
		 <#-- 审核操作--end -->
		 <#-- 补充材料核实通过--start -->
		 <#if model.activityStatus == 6 && status != 0>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="againActivityReply">补充时回复内容</label>
						 <textarea rows="8" class="form-control" id="againActivityReply" name="againActivityReply" value="${model.againActivityReply}">${model.againActivityReply}</textarea>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-3 column">
					 <button type="button" name = "verify" value="7" id="verify" class="btn btn-info-blue btn-warning" >核实通过</button>
					 <button type="button" style="margin-left: 10px;" name = "verify" value="3" id="verifyRefuse" class="btn btn-info-blue btn-warning" >核实拒绝</button>
				 </div>
			 </div>
		 </#if>
		 <#-- 核实通过--end -->
	 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/ecology/Ecofund",function(o){
 	o.modify();
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






