 <!-- Copyright:    -->
 <!-- YwkCustomerMinerSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-11-16 16:44:13      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
  <div class="row">
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="dianfeiNum">买单总数量</label>
			 <input type="text" class="form-control biandong" name="buySum" id="buySum" value="${map.buySum}"/>
		</div>
	</div>
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="dianfeiNum">卖总数量</label>
			 <input type="text" class="form-control biandong" name="sellSum" id="sellSum"  value="${map.sellSum}"/>
		</div>
	</div>
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="remark">剩余买单</label>
			 <input type="text" class="form-control" name="buyPay" id="buyPay" value="${map.buyPay}"/>
		</div>
	</div>
  	<div class="col-md-6 column">
		<div class="form-group">
			 <label for="remark">剩余卖单</label>
			 <input type="text" class="form-control" name="sellPay" id="sellPay" value="${map.sellPay}"/>
		</div>
	</div>
	
 </div>
 <div class="row">
	<div class="col-md-4 column"  style="float:right;"> 
		<button type="button" id="peipeiSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>
 </div>

 <script type="text/javascript">
 
 </script>




