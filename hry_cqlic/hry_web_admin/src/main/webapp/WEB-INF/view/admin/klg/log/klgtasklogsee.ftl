 <!-- Copyright:    -->
 <!-- KlgTaskLogSee.html     -->
 <!-- @author:      yaozhuo  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2019-05-06 15:30:25      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">KlgTaskLog--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/klg/log/klgtaskloglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="functionName">{name:'方法名称'}</label>
			 <input type="text" class="form-control" name="functionName" id="functionName" value="${model.functionName}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isException">{name:'是否出现异常 0否 1是'}</label>
			 <input type="text" class="form-control" name="isException" id="isException" value="${model.isException}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ipaddress">{name:'ip地址'}</label>
			 <input type="text" class="form-control" name="ipaddress" id="ipaddress" value="${model.ipaddress}" disabled/>
		</div>
		<div class="form-group">
			 <label for="lasttime">{name:'持续时间'}</label>
			 <input type="text" class="form-control" name="lasttime" id="lasttime" value="${model.lasttime}" disabled/>
		</div>
		<div class="form-group">
			 <label for="startDate">{name:'开始时间'}</label>
			 <input type="text" class="form-control" name="startDate" id="startDate" value="${model.startDate}" disabled/>
		</div>
		<div class="form-group">
			 <label for="endDate">{name:'结束时间'}</label>
			 <input type="text" class="form-control" name="endDate" id="endDate" value="${model.endDate}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/klg/log/KlgTaskLog",function(o){
 	o.see();
 });
 </script>




