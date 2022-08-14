 <!-- Copyright:    -->
 <!-- KlgForecastAdd.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-03 16:36:19      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/forecast/klgforecastlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="yesterdaySurplus">昨天剩余数量</label>
			 <input type="text" class="form-control" name="yesterdaySurplus" id="yesterdaySurplus" />
		</div>
		<div class="form-group">
			 <label for="todaySurplus">今天剩余数量</label>
			 <input type="text" class="form-control" name="todaySurplus" id="todaySurplus" />
		</div>
		<div class="form-group">
			 <label for="todayForecast">预测今天排单数量</label>
			 <input type="text" class="form-control" name="todayForecast" id="todayForecast" />
		</div>
		<div class="form-group">
			 <label for="tomorrowForecast">预测明天排单数量</label>
			 <input type="text" class="form-control" name="tomorrowForecast" id="tomorrowForecast" />
		</div>
		<div class="form-group">
			 <label for="forecastInterval">预测间隔天数</label>
			 <input type="text" class="form-control" name="forecastInterval" id="forecastInterval" />
		</div>
		<div class="form-group">
			 <label for="forecastTime">预测时间</label>
			 <input type="text" class="form-control" name="forecastTime" id="forecastTime" />
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
seajs.use("js/admin/klg/forecast/KlgForecast",function(o){
	o.add();
});
</script>




