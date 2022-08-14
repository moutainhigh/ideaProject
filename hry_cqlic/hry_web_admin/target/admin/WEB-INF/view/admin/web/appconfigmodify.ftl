 <!-- Copyright:    -->
 <!-- AppConfigModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-14 11:18:59      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppConfig--Modify  <button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="configkey">configkey</label>
			 <input type="text" class="form-control" name="configkey" id="configkey" value="${model.configkey}"/>
		</div>
		<div class="form-group">
			 <label for="configname">configname</label>
			 <input type="text" class="form-control" name="configname" id="configname" value="${model.configname}"/>
		</div>
		<div class="form-group">
			 <label for="configdescription">configdescription</label>
			 <input type="text" class="form-control" name="configdescription" id="configdescription" value="${model.configdescription}"/>
		</div>
		<div class="form-group">
			 <label for="datatype">datatype</label>
			 <input type="text" class="form-control" name="datatype" id="datatype" value="${model.datatype}"/>
		</div>
		<div class="form-group">
			 <label for="value">value</label>
			 <input type="text" class="form-control" name="value" id="value" value="${model.value}"/>
		</div>
		<div class="form-group">
			 <label for="typekey">typekey</label>
			 <input type="text" class="form-control" name="typekey" id="typekey" value="${model.typekey}"/>
		</div>
		<div class="form-group">
			 <label for="typename">typename</label>
			 <input type="text" class="form-control" name="typename" id="typename" value="${model.typename}"/>
		</div>
		<div class="form-group">
			 <label for="description">description</label>
			 <input type="text" class="form-control" name="description" id="description" value="${model.description}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		<div class="form-group">
			 <label for="ishidden">ishidden</label>
			 <input type="text" class="form-control" name="ishidden" id="ishidden" value="${model.ishidden}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn  btn-info-blue btn-warning" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppConfig",function(o){
 	o.modify();
 });
 </script>






