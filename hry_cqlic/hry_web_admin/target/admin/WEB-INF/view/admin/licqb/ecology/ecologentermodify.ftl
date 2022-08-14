 <!-- Copyright:    -->
 <!-- EcologEnterModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-05 16:37:53      -->

 <#include "/base/base.ftl">
 <style>
	 .fileinput-buttons {
		 height: 30px;
		 line-height: 20px;
	 }
	 .btns {
		 display: inline-block;
		 padding: 4px 0px;
		 margin-bottom: 0;
		 font-size: 14px;
		 width: 70px;
		 vertical-align: middle;
      }

      .fileinput-buttons input{
         position: absolute;
         top: 0;
         right: 0;
         margin: 0;
         opacity: 0;
         font-size: 14px;
         direction: ltr;
         cursor: pointer;
      }
 </style>
 <div class="centerRowBg centerRowBg_admin">
	 <div class="row">
		 <div class="col-md-12">
			 <h3 class="page-header">
				 <#if status == 3>
				 	编辑
				 <#else >
					 <#if model.enterStatus == 1>
						 申请中
					 <#elseif model.enterStatus == 2>
						 申请拒绝
					 <#elseif model.enterStatus == 3>
						 待付款
					 <#elseif model.enterStatus == 4>
						 用户拒绝
					 <#elseif model.enterStatus == 5>
						 待核实
					 <#elseif model.enterStatus == 6>
						 <#if model.renewStatus == 1>
							 核实通过-续费待核实
							 <#else>
							 核实通过
						 </#if>
					 <#elseif model.enterStatus == 7>
						 核实未通过
					 <#else>
						 已到期
					 </#if>
				 </#if>

				 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/licqb/ecology/ecologenter/gotoEcoLogEnterList?status=${status}')" > <i class="fa fa-mail-forward"></i>返回</button>
			 </h3>
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
					 <label for="orderNum">申请单号</label>
					 <input type="text" class="form-control" id="orderNum" name="orderNum" value="${model.orderNum}" readonly="true">
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
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="plateId">入驻板块</label>
					 <select class="form-control" id="plateId" name="plateId" <#if status != 3>readonly="true"</#if> >
						 <#list ecologPlateList as plate>
							 <option value="${plate.id}" <#if plate.id == model.plateId> selected</#if> >${plate.plateName}</option>
						 </#list>
					 </select>
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="enterLevel">入驻等级</label>
					 <select class="form-control" id="enterLevel" name="enterLevel" <#if status != 3>readonly="true"</#if> >
						 <option value="A" <#if model.enterLevel == 'A'> selected</#if> >A</option>
						 <option value="B" <#if model.enterLevel == 'B'> selected</#if> >B</option>
					 </select>
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="enterName">入驻名称</label>
					 <input type="text" class="form-control" name="enterName" id="enterName" value="${model.enterName}" <#if status != 3>readonly="true"</#if> />
				 </div>
			 </div>
			 <div class="col-md-4 column">
				 <div class="form-group">
					 <label for="enterLogo">入驻logo</label>
					 <input type="hidden" name="enterLogo" id="enterLogo" value="${model.enterLogo}"/>
					 <div class="form-group" id="enterLogoDiv">
						 <img style="width: 82px;height: 75px; margin-right: 10px" name="img" src="${fileUrl}${model.enterLogo}">
						 <if status == 3>
							 <span class="btns fileinput-buttons">
								<span>上传</span>
								<input type="file" id="uploading">
							 </span>
						 </if>
					 </div>
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-8 column">
				 <div class="form-group">
					 <label for="downloadLink">下载链接</label>
					 <input type="text" class="form-control" name="downloadLink" id="downloadLink" value="${model.downloadLink}" <#if status != 3>readonly="true"</#if> />
				 </div>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-8 column">
				 <div class="form-group">
					 <label for="enterApplyIntro">入驻申请简介</label>
					 <textarea rows="8" <#if status != 3>readonly="true"</#if> class="form-control" id="enterApplyIntro" name="enterApplyIntro" value="${model.enterApplyIntro}">${model.enterApplyIntro}</textarea>
				 </div>
			 </div>
		 </div>
		 <#-- 申请信息--end -->

		 <#-- 查看回复start --->
		 <#if model.enterStatus != 1>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="enterReply">回复内容</label>
						 <textarea rows="8" <#if status != 3>readonly="true"</#if> class="form-control" id="enterReply" name="enterReply" value="${model.enterReply}">${model.enterReply}</textarea>
					 </div>
				 </div>
			 </div>
		 </#if>
		 <#-- 查看回复end-->

		 <#-- 审核操作--start -->
		 <#if model.enterStatus == 1 && status != 0 && status != 3>
			 <div class="row">
				 <div class="col-md-12">
					 <h4 class="page-header">审核操作</h4>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="enterStatus" style="margin-right:10px">审核结果</label>
						 <input type="radio" name="enterStatus"  value="3" checked="true"> 审核通过
						 <input type="radio" name="enterStatus"  value="2" > 审核拒绝
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-8 column">
					 <div class="form-group">
						 <label for="enterReply">回复内容</label>
						 <textarea rows="8" class="form-control" id="enterReply" name="enterReply" value="${model.enterReply}">${model.enterReply}</textarea>
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

		 <#-- 核实操作--start -->
		 <#if (model.enterStatus == 5 || model.renewStatus == 1) && status != 0 && status != 3>
			 <div class="row">
				 <div class="col-md-12">
					 <h4 class="page-header">核实操作</h4>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="acceptAddress">收款地址</label>
						 <input type="text" class="form-control" name="acceptAddress" id="acceptAddress" value="${model.acceptAddress}" readonly="true"/>
					 </div>
				 </div>
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="paymentAddress">付款地址</label>
						 <input type="text" class="form-control" name="paymentAddress" id="paymentAddress" value="${model.paymentAddress}" readonly="true"/>
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-4 column">
					 <div class="form-group">
						 <label for="enterStatus" style="margin-right:10px">核实结果</label>
						 <input type="radio" name="verifyRadio"  value="6" checked="true"> 核实通过
						 <input type="radio" name="verifyRadio"  value="7" > 核实拒绝
					 </div>
				 </div>
			 </div>
			 <div class="row">
				 <div class="col-md-2 column">
					 <button type="button" id="applyCheck" class="btn btn-info-blue btn-warning" >提交审核</button>
				 </div>
			 </div>
		 </#if>
		 <#-- 核实操作--end -->

		 <#-- 编辑保存  -->
		 <#if status == 3>
			 <div class="row">
				 <div class="col-md-2 column">
					 <button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
				 </div>
			 </div>
		 </#if>
		 <#-- 编辑保存  -->
	 </form>
	 <#if model.enterStatus != 1 && status != 2>
		 <div class="row">
			 <div class="col-md-12">
				 <h4 class="page-header">缴费记录</h4>
			 </div>
		 </div>
		 <div class="row">
			 <div class="col-md-12">
				 <div id="toolbar">
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
						data-page-list="[10, 25, 50, 100]"
						data-show-footer="false"
						data-side-pagination="server"
				 >
				 </table>
			 </div>
		 </div>
	 </#if>
 </div>


 <script type="text/javascript">
 seajs.use("js/admin/licqb/ecology/EcologEnter",function(o){
 	o.modify();
 	o.enterPayList();
 });
 </script>






