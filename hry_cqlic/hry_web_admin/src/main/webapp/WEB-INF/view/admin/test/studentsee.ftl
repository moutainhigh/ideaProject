 <!-- Copyright:    -->
 <!-- StudentSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-09-25 12:00:48      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">Student--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/test/studentlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="name">name</label>
			 <input type="text" class="form-control" name="name" id="name" value="${model.name}" disabled/>
		</div>
		<div class="form-group">
			 <label for="age">age</label>
			 <input type="text" class="form-control" name="age" id="age" value="${model.age}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sex">sex</label>
			 <input type="text" class="form-control" name="sex" id="sex" value="${model.sex}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="money">money</label>
			 <input type="text" class="form-control" name="money" id="money" value="${model.money}" disabled/>
		</div>
		<div class="form-group">
			 <label for="money1">money1</label>
			 <input type="text" class="form-control" name="money1" id="money1" value="${model.money1}" disabled/>
		</div>
		<div class="form-group">
			 <label for="amount">amount</label>
			 <input type="text" class="form-control" name="amount" id="amount" value="${model.amount}" disabled/>
		</div>
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
 seajs.use("js/admin/test/Student",function(o){
 	o.see();
 });
 </script>




