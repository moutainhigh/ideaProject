 <!-- Copyright:    -->
 <!-- TaskModify.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2022-05-19 10:31:38      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Task--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/xzsn/task/tasklist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="taskName">任务名称</label>
			 <input type="text" class="form-control" name="taskName" id="taskName" value="${model.taskName}"/>
		</div>
		<div class="form-group">
			 <label for="taskTypeId">任务属性id</label>
			 <input type="text" class="form-control" name="taskTypeId" id="taskTypeId" value="${model.taskTypeId}"/>
		</div>
		<div class="form-group">
			 <label for="taskTypeName">任务属性名称</label>
			 <input type="text" class="form-control" name="taskTypeName" id="taskTypeName" value="${model.taskTypeName}"/>
		</div>
		<div class="form-group">
			 <label for="isOpen">是否公开 1 是，2 否</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}"/>
		</div>
		<div class="form-group">
			 <label for="taskGrade">任务级别 1 一般，2 紧急</label>
			 <input type="text" class="form-control" name="taskGrade" id="taskGrade" value="${model.taskGrade}"/>
		</div>
		<div class="form-group">
			 <label for="initiatorId">发起人id</label>
			 <input type="text" class="form-control" name="initiatorId" id="initiatorId" value="${model.initiatorId}"/>
		</div>
		<div class="form-group">
			 <label for="initiatorName">发起人姓名</label>
			 <input type="text" class="form-control" name="initiatorName" id="initiatorName" value="${model.initiatorName}"/>
		</div>
		<div class="form-group">
			 <label for="undertakerIds">承办人id集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="undertakerIds" id="undertakerIds" value="${model.undertakerIds}"/>
		</div>
		<div class="form-group">
			 <label for="undertakerNames">承办人姓名（用逗号分隔）</label>
			 <input type="text" class="form-control" name="undertakerNames" id="undertakerNames" value="${model.undertakerNames}"/>
		</div>
		<div class="form-group">
			 <label for="copyIds">抄送人id集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="copyIds" id="copyIds" value="${model.copyIds}"/>
		</div>
		<div class="form-group">
			 <label for="copyNames">抄送人姓名集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="copyNames" id="copyNames" value="${model.copyNames}"/>
		</div>
		<div class="form-group">
			 <label for="taskStartDate">任务计划开始时间</label>
			 <input type="text" class="form-control" name="taskStartDate" id="taskStartDate" value="${model.taskStartDate}"/>
		</div>
		<div class="form-group">
			 <label for="taskEndDate">任务计划结束时间</label>
			 <input type="text" class="form-control" name="taskEndDate" id="taskEndDate" value="${model.taskEndDate}"/>
		</div>
		<div class="form-group">
			 <label for="taskContent">任务内容</label>
			 <input type="text" class="form-control" name="taskContent" id="taskContent" value="${model.taskContent}"/>
		</div>
		<div class="form-group">
			 <label for="taskAffix">任务附件</label>
			 <input type="text" class="form-control" name="taskAffix" id="taskAffix" value="${model.taskAffix}"/>
		</div>
		<div class="form-group">
			 <label for="taskPlanContent">任务完成进度内容</label>
			 <input type="text" class="form-control" name="taskPlanContent" id="taskPlanContent" value="${model.taskPlanContent}"/>
		</div>
		<div class="form-group">
			 <label for="taskPlanAffix">任务完成进度附件</label>
			 <input type="text" class="form-control" name="taskPlanAffix" id="taskPlanAffix" value="${model.taskPlanAffix}"/>
		</div>
		<div class="form-group">
			 <label for="taskState">任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）</label>
			 <input type="text" class="form-control" name="taskState" id="taskState" value="${model.taskState}"/>
		</div>
		<div class="form-group">
			 <label for="isDel">是否删除 0 否 1 是</label>
			 <input type="text" class="form-control" name="isDel" id="isDel" value="${model.isDel}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/xzsn/task/Task",function(o){
 	o.modify();
 });
 </script>






