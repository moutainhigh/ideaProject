 <!-- Copyright:    -->
 <!-- BuildRecordAdd.html     -->
 <!-- @author:      denghf  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-17 12:01:26      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">K线修复设定  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}//v.do?u=admin/exchange/buildrecordadd')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
        <div class="form-group">
            <label for="tradeCoinCode">交易对</label>
			<@HrySelect url="${ctx}/exchange/exrobot/selectlist"  itemvalue="coinCode"  itemname="coinCode"  name="coinCode"  id="coinCode"   > </@HrySelect>
        </div>
		<div class="form-group">
			 <label for="startTime">修复起点时间</label>
            <input style="text-align: center;margin-top: 20px;" type="text" class="form-control" name="entrustTime_GT" id="startTime" readonly
                   data-date-format="yyyy-mm-dd hh:ii" data-minute-step="1"
            >
		</div>
		<div class="form-group">
			 <label for="endTime">修复终点时间</label>
            <input type="text" class="form-control" name="entrustTime_GT" id="endTime" readonly
                   data-date-format="yyyy-mm-dd hh:ii" data-minute-step="1" style="text-align: center;margin-top: 20px;"
            >
		</div>


		<#--<div class="form-group">
			 <label for="priceCoinCode">priceCoinCode</label>
			 <input type="text" class="form-control" name="priceCoinCode" id="priceCoinCode" />
		</div>-->
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="confirmBuild" class="btn btn-info-blue btn-block" >确认修复</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use(["js/admin/exchange/BuildRecord","js/base/HrySelect","js/base/HryDateTop"],function(o,HrySelect,HryDate){
	o.add();
    HrySelect.init();
    HryDate.init();
});
</script>




