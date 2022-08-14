 <!-- Copyright:    -->
 <!-- ReleaseRuleDetailsAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-23 16:57:20      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/record/releaseruledetailslist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="customerLevelId">用户等级Id</label>
			 <input type="text" class="form-control" name="customerLevelId" id="customerLevelId" />
		</div>
		<div class="form-group">
			 <label for="teamLevelId">社区等级Id</label>
			 <input type="text" class="form-control" name="teamLevelId" id="teamLevelId" />
		</div>
		<div class="form-group">
			 <label for="teamLevelName">社区等级名称</label>
			 <input type="text" class="form-control" name="teamLevelName" id="teamLevelName" />
		</div>
		<div class="form-group">
			 <label for="teamSort">社区等级排序</label>
			 <input type="text" class="form-control" name="teamSort" id="teamSort" />
		</div>
		<div class="form-group">
			 <label for="startTeamAward">起始团队奖励</label>
			 <input type="text" class="form-control" name="startTeamAward" id="startTeamAward" />
		</div>
		<div class="form-group">
			 <label for="startTime">奖励开始时间</label>
			 <input type="text" class="form-control" name="startTime" id="startTime" />
		</div>
		<div class="form-group">
			 <label for="endTime">奖励结束时间</label>
			 <input type="text" class="form-control" name="endTime" id="endTime" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/licqb/record/ReleaseRuleDetails",function(o){
	o.add();
});
</script>




