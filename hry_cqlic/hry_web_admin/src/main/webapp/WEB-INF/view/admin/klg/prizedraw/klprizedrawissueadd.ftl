 <!-- Copyright:    -->
 <!-- KlPrizedrawIssueAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-06 11:32:40      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">大奖基金-添加  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/prizedraw/klprizedrawissuelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="issueNumber">期号</label>
			 <input type="text" class="form-control" name="issueNumber" id="issueNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="primeNumber">质数</label>
			 <input type="text" class="form-control" name="primeNumber" id="primeNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="mondayNumber">周一号码</label>
			 <input type="text" class="form-control" name="mondayNumber" id="mondayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="tuesdayNumber">周二号码</label>
			 <input type="text" class="form-control" name="tuesdayNumber" id="tuesdayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="wednesdayNumber">周三号码</label>
			 <input type="text" class="form-control" name="wednesdayNumber" id="wednesdayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="thursdayNumber">周四号码</label>
			 <input type="text" class="form-control" name="thursdayNumber" id="thursdayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="fridayNumber">周五号码</label>
			 <input type="text" class="form-control" name="fridayNumber" id="fridayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="saturdayNumber">周六号码</label>
			 <input type="text" class="form-control" name="saturdayNumber" id="saturdayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="sundayNumber">周日号码</label>
			 <input type="text" class="form-control" name="sundayNumber" id="sundayNumber" />
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="startDate">开始时间</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}">
             <span style="color:red;">注：只可选择周一的时间</span>
		</div>
	</div>
	<div class="col-md-3 column">
		<div class="form-group">
			 <label for="endDate">结束时间</label>
			 <!--input type="text" class="form-control" name="endDate" id="endDate" readonly tablesearch
                               data-date-format="yyyy-mm-dd" data-min-view="month"
                               value="${(info.birthday?string("yyyy-MM-dd"))!}"-->
             <input type="hidden" class="form-control" name="endDate" id="endDate" />
             <input type="text" class="form-control" id="endDateText" disabled/>
             <span style="color:red;">说明：根据开始时间自动计算开奖时间 ，为开始时间+7天，即开始时间的下周一</span>
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
seajs.use(["js/admin/klg/prizedraw/KlPrizedrawIssue","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,t){
    HrySelect.init();
 	t.init();
	o.add();
});
</script>




