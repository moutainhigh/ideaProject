 <!-- Copyright:    -->
 <!-- AppCategoryLangnameAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-26 15:47:20      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appcategorylangnamelist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="categoryId">categoryId</label>
			 <input type="text" class="form-control" name="categoryId" id="categoryId" />
		</div>
		<div class="form-group">
			 <label for="langType">langType</label>
			 <input type="text" class="form-control" name="langType" id="langType" />
		</div>
		<div class="form-group">
			 <label for="dicKey">dicKey</label>
			 <input type="text" class="form-control" name="dicKey" id="dicKey" />
		</div>
		<div class="form-group">
			 <label for="langName">langName</label>
			 <input type="text" class="form-control" name="langName" id="langName" />
		</div>
		<div class="form-group">
			 <label for="langContent">langContent</label>
			 <input type="text" class="form-control" name="langContent" id="langContent" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
		<div class="form-group">
			 <label for="dicId">dicId</label>
			 <input type="text" class="form-control" name="dicId" id="dicId" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>

<script type="text/javascript">
seajs.use("js/admin/web/AppCategoryLangname",function(o){
	o.add();
});
</script>




