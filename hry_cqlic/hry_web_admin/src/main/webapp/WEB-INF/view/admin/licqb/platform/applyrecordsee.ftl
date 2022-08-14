 <!-- Copyright:    -->
 <!-- ApplyRecordSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:46:02      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">ApplyRecord--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/applyrecordlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="applyId">社区奖励申请Id</label>
			 <input type="text" class="form-control" name="applyId" id="applyId" value="${model.applyId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" value="${model.customerId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="email">用户邮箱</label>
			 <input type="text" class="form-control" name="email" id="email" value="${model.email}" disabled/>
		</div>
		<div class="form-group">
			 <label for="mobliePhone">用户手机号</label>
			 <input type="text" class="form-control" name="mobliePhone" id="mobliePhone" value="${model.mobliePhone}" disabled/>
		</div>
		<div class="form-group">
			 <label for="socialType">社交类型: 1 QQ 2 微信 3 facebook</label>
			 <input type="text" class="form-control" name="socialType" id="socialType" value="${model.socialType}" disabled/>
		</div>
		<div class="form-group">
			 <label for="socialAccount">社交账户</label>
			 <input type="text" class="form-control" name="socialAccount" id="socialAccount" value="${model.socialAccount}" disabled/>
		</div>
		<div class="form-group">
			 <label for="socialGroupImg">社交群图片</label>
			 <input type="text" class="form-control" name="socialGroupImg" id="socialGroupImg" value="${model.socialGroupImg}" disabled/>
		</div>
		<div class="form-group">
			 <label for="applyStaus">申请步骤：1 填写社交账户 2 上传社交群图片 3申请完成</label>
			 <input type="text" class="form-control" name="applyStaus" id="applyStaus" value="${model.applyStaus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="auditStaus">审核状态：1 申请通过 2申请拒绝</label>
			 <input type="text" class="form-control" name="auditStaus" id="auditStaus" value="${model.auditStaus}" disabled/>
		</div>
		<div class="form-group">
			 <label for="auditExplain">审核说明</label>
			 <input type="text" class="form-control" name="auditExplain" id="auditExplain" value="${model.auditExplain}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/licqb/platform/ApplyRecord",function(o){
 	o.see();
 });
 </script>




