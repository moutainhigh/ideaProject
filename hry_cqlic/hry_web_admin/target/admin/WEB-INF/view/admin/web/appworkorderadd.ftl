 <!-- Copyright:    -->
 <!-- appWorkOrderAdd.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-02 09:48:18      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
	<div class="row">
		<div class="col-md-12">
			<h3 class="page-header">添加工单(未使用) <button type="button"  class="btn btn-success pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appworkorderlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
		</div>
	</div>


	<form id="form" >

	<div class="row">
		<div class="col-md-4 column">
			<div class="form-group">
				 <label for="customerId">customerId</label>
				 <input type="text" class="form-control" name="customerId" id="customerId" />
			</div>
			<div class="form-group">
				 <label for="workOrderNo">workOrderNo</label>
				 <input type="text" class="form-control" name="workOrderNo" id="workOrderNo" />
			</div>
			<div class="form-group">
				 <label for="categoryId">categoryId</label>
				 <input type="text" class="form-control" name="categoryId" id="categoryId" />
			</div>
			<div class="form-group">
				 <label for="categoryName">categoryName</label>
				 <input type="text" class="form-control" name="categoryName" id="categoryName" />
			</div>
			<div class="form-group">
				 <label for="workContent">workContent</label>
				 <input type="text" class="form-control" name="workContent" id="workContent" />
			</div>
			<div class="form-group">
				 <label for="state">state</label>
				 <input type="text" class="form-control" name="state" id="state" />
			</div>
			<div class="form-group">
				 <label for="replyMode">replyMode</label>
				 <input type="text" class="form-control" name="replyMode" id="replyMode" />
			</div>
			<div class="form-group">
				 <label for="sort">sort</label>
				 <input type="text" class="form-control" name="sort" id="sort" />
			</div>
			<div class="form-group">
				 <label for="replyContent">replyContent</label>
				 <input type="text" class="form-control" name="replyContent" id="replyContent" />
			</div>
			<div class="form-group">
				 <label for="language">language</label>
				 <input type="text" class="form-control" name="language" id="language" />
			</div>
			<div class="form-group">
				 <label for="processTime">processTime</label>
				 <input type="text" class="form-control" name="processTime" id="processTime" />
			</div>
			<div class="form-group">
				 <label for="saasId">saasId</label>
				 <input type="text" class="form-control" name="saasId" id="saasId" />
			</div>
			<div class="form-group">
				 <label for="languageDic">languageDic</label>
				 <input type="text" class="form-control" name="languageDic" id="languageDic" />
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-2 column">
			<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
		</div>
	</div>

	</form>
 </div>
<script type="text/javascript">
seajs.use(["js/admin/web/appWorkOrder","js/base/HrySelect"],function(o,HrySelect){
	HrySelect.init();
	o.add();
});
</script>




