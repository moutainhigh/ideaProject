 <!-- Copyright:    -->
 <!-- KlineDataAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-11 14:46:04      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">添加K线节点<button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/exchange/klinedatalist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="coinCode">交易对</label>
			 <input type="text" class="form-control" name="coinCode" id="coinCode" />
		</div>
		<div class="form-group">
			 <label for="period">选择周期</label>
			 <input type="text" class="form-control" name="period" id="period" />
		</div>
		<div class="form-group">
			 <label for="openPrice">开盘价</label>
			 <input type="text" class="form-control" name="openPrice" id="openPrice" />
		</div>
		<div class="form-group">
			 <label for="closePrice">收盘价</label>
			 <input type="text" class="form-control" name="closePrice" id="closePrice" />
		</div>
		<div class="form-group">
			 <label for="highPrice">最高价</label>
			 <input type="text" class="form-control" name="highPrice" id="highPrice" />
		</div>
		<div class="form-group">
			 <label for="lowPrice">最低价</label>
			 <input type="text" class="form-control" name="lowPrice" id="lowPrice" />
		</div>
		<div class="form-group">
			 <label for="time">时间</label>
			 <input type="text" class="form-control" name="time" id="time" />
		</div>
		<div class="form-group">
			 <label for="timelong">时间戳</label>
			 <input type="text" class="form-control" name="timelong" id="timelong" />
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
seajs.use("js/admin/exchange/KlineData",function(o){
	o.add();
});
</script>




