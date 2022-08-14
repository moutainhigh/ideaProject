 <!-- Copyright:    -->
 <!-- IcoExperienceAdd.html     -->
 <!-- @author:      houz  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-01-14 10:10:45      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/ico/experience/icoexperiencelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customer_id">账户id</label>
			 <input type="text" class="form-control" name="customer_id" id="customer_id" />
		</div>
		<div class="form-group">
			 <label for="customer_email">账户邮箱</label>
			 <input type="text" class="form-control" name="customer_email" id="customer_email" />
		</div>
		<div class="form-group">
			 <label for="customer_mobile">账户手机</label>
			 <input type="text" class="form-control" name="customer_mobile" id="customer_mobile" />
		</div>
		<div class="form-group">
			 <label for="type">1收入 ，2 支出</label>
			 <input type="text" class="form-control" name="type" id="type" />
		</div>
		<div class="form-group">
			 <label for="account_type">交易类型（0101 锁仓奖励，0102 注册赠送，0201 锁仓扣除，0202 释放扣除）</label>
			 <input type="text" class="form-control" name="account_type" id="account_type" />
		</div>
		<div class="form-group">
			 <label for="coinNum">持币数量</label>
			 <input type="text" class="form-control" name="coinNum" id="coinNum" />
		</div>
		<div class="form-group">
			 <label for="experience">经验值（无正负）</label>
			 <input type="text" class="form-control" name="experience" id="experience" />
		</div>
		<div class="form-group">
			 <label for="experienceNum">流水号</label>
			 <input type="text" class="form-control" name="experienceNum" id="experienceNum" />
		</div>
		<div class="form-group">
			 <label for="oldLevel_id">oldLevel_id</label>
			 <input type="text" class="form-control" name="oldLevel_id" id="oldLevel_id" />
		</div>
		<div class="form-group">
			 <label for="oldLevel">原等级</label>
			 <input type="text" class="form-control" name="oldLevel" id="oldLevel" />
		</div>
		<div class="form-group">
			 <label for="newLevel_id">newLevel_id</label>
			 <input type="text" class="form-control" name="newLevel_id" id="newLevel_id" />
		</div>
		<div class="form-group">
			 <label for="newLevel">newLevel</label>
			 <input type="text" class="form-control" name="newLevel" id="newLevel" />
		</div>
		<div class="form-group">
			 <label for="upgradeNote">升级备注</label>
			 <input type="text" class="form-control" name="upgradeNote" id="upgradeNote" />
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
seajs.use("js/admin/ico/experience/IcoExperience",function(o){
	o.add();
});
</script>




