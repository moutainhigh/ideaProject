 <!-- Copyright:    -->
 <!-- KlPrizedrawIssueModify.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-06-06 11:32:40      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">大奖基金-编辑  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/prizedraw/klprizedrawissuelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="issueNumber">期号</label>
			 <input type="text" class="form-control" name="issueNumber" id="issueNumber" value="${model.issueNumber}" disabled/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="primeNumber">质数</label>
			 <input type="text" class="form-control" name="primeNumber" id="primeNumber" value="${model.primeNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="lotteryNumber">开奖号码</label>
			 <input type="text" class="form-control" name="lotteryNumber" id="lotteryNumber" value="${model.lotteryNumber}" disabled/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="startDate">开始时间</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" value="${model.startDate?string("yyyy-MM-dd")}" disabled/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="endDate">结束时间</label>
			 <input type="text" class="form-control" name="endDate" id="endDate" value="${model.endDate?string("yyyy-MM-dd")}" disabled/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="mondayNumber">周一号码</label>
			 <input type="text" class="form-control" name="mondayNumber" id="mondayNumber" value="${model.mondayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="tuesdayNumber">周二号码</label>
			 <input type="text" class="form-control" name="tuesdayNumber" id="tuesdayNumber" value="${model.tuesdayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="wednesdayNumber">周三号码</label>
			 <input type="text" class="form-control" name="wednesdayNumber" id="wednesdayNumber" value="${model.wednesdayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="thursdayNumber">周四号码</label>
			 <input type="text" class="form-control" name="thursdayNumber" id="thursdayNumber" value="${model.thursdayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="fridayNumber">周五号码</label>
			 <input type="text" class="form-control" name="fridayNumber" id="fridayNumber" value="${model.fridayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="saturdayNumber">周六号码</label>
			 <input type="text" class="form-control" name="saturdayNumber" id="saturdayNumber" value="${model.saturdayNumber}"/>
		</div>
	</div>
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="sundayNumber">周日号码</label>
			 <input type="text" class="form-control" name="sundayNumber" id="sundayNumber" value="${model.sundayNumber}"/>
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
 seajs.use("js/admin/klg/prizedraw/KlPrizedrawIssue",function(o){
 	o.modify();
 });
 </script>






