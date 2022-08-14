 <!-- Copyright:    -->
 <!-- TaskSee.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2022-05-19 10:31:38      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Task--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/xzsn/task/tasklist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="taskName">任务名称</label>
			 <input type="text" class="form-control" name="taskName" id="taskName" value="${model.taskName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskTypeId">任务属性id</label>
			 <input type="text" class="form-control" name="taskTypeId" id="taskTypeId" value="${model.taskTypeId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskTypeName">任务属性名称</label>
			 <input type="text" class="form-control" name="taskTypeName" id="taskTypeName" value="${model.taskTypeName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isOpen">是否公开 1 是，2 否</label>
			 <input type="text" class="form-control" name="isOpen" id="isOpen" value="${model.isOpen}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskGrade">任务级别 1 一般，2 紧急</label>
			 <input type="text" class="form-control" name="taskGrade" id="taskGrade" value="${model.taskGrade}" disabled/>
		</div>
		<div class="form-group">
			 <label for="initiatorId">发起人id</label>
			 <input type="text" class="form-control" name="initiatorId" id="initiatorId" value="${model.initiatorId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="initiatorName">发起人姓名</label>
			 <input type="text" class="form-control" name="initiatorName" id="initiatorName" value="${model.initiatorName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="undertakerIds">承办人id集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="undertakerIds" id="undertakerIds" value="${model.undertakerIds}" disabled/>
		</div>
		<div class="form-group">
			 <label for="undertakerNames">承办人姓名（用逗号分隔）</label>
			 <input type="text" class="form-control" name="undertakerNames" id="undertakerNames" value="${model.undertakerNames}" disabled/>
		</div>
		<div class="form-group">
			 <label for="copyIds">抄送人id集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="copyIds" id="copyIds" value="${model.copyIds}" disabled/>
		</div>
		<div class="form-group">
			 <label for="copyNames">抄送人姓名集合（用逗号分隔）</label>
			 <input type="text" class="form-control" name="copyNames" id="copyNames" value="${model.copyNames}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskStartDate">任务计划开始时间</label>
			 <input type="text" class="form-control" name="taskStartDate" id="taskStartDate" value="${model.taskStartDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskEndDate">任务计划结束时间</label>
			 <input type="text" class="form-control" name="taskEndDate" id="taskEndDate" value="${model.taskEndDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskContent">任务内容</label>
			 <input type="text" class="form-control" name="taskContent" id="taskContent" value="${model.taskContent}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskAffix">任务附件</label>
			 <input type="text" class="form-control" name="taskAffix" id="taskAffix" value="${model.taskAffix}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskPlanContent">任务完成进度内容</label>
			 <input type="text" class="form-control" name="taskPlanContent" id="taskPlanContent" value="${model.taskPlanContent}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskPlanAffix">任务完成进度附件</label>
			 <input type="text" class="form-control" name="taskPlanAffix" id="taskPlanAffix" value="${model.taskPlanAffix}" disabled/>
		</div>
		<div class="form-group">
			 <label for="taskState">任务状态 0 未分配 1 待办 2 承办 3 已办（未确认）4 任务结束（已确认）</label>
			 <input type="text" class="form-control" name="taskState" id="taskState" value="${model.taskState}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isDel">是否删除 0 否 1 是</label>
			 <input type="text" class="form-control" name="isDel" id="isDel" value="${model.isDel}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/xzsn/task/Task",function(o){
 	o.see();
 });
 </script>




