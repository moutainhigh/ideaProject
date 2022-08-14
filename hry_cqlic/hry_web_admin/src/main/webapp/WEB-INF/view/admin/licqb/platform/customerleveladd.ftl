 <!-- Copyright:    -->
 <!-- CustomerLevelAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-08-12 17:41:50      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/platform/customerlevellist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="levelId">等级id</label>
			 <input type="text" class="form-control" name="levelId" id="levelId" />
		</div>
		<div class="form-group">
			 <label for="levelName">个人等级名称</label>
			 <input type="text" class="form-control" name="levelName" id="levelName" />
		</div>
		<div class="form-group">
			 <label for="sort">个人等级排序</label>
			 <input type="text" class="form-control" name="sort" id="sort" />
		</div>
		<div class="form-group">
			 <label for="teamLevelId">社区等级id</label>
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
			 <label for="isTeamAward">是否发放社区奖励</label>
			 <input type="text" class="form-control" name="isTeamAward" id="isTeamAward" />
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
seajs.use("js/admin/licqb/platform/CustomerLevel",function(o){
	o.add();
});
</script>




