 <!-- Copyright:    -->
 <!-- AppConfigSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-14 11:18:59      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppConfig--See  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="configkey">configkey</label>
			 <input type="text" class="form-control" name="configkey" id="configkey" value="${model.configkey}" disabled/>
		</div>
		<div class="form-group">
			 <label for="configname">configname</label>
			 <input type="text" class="form-control" name="configname" id="configname" value="${model.configname}" disabled/>
		</div>
		<div class="form-group">
			 <label for="configdescription">configdescription</label>
			 <input type="text" class="form-control" name="configdescription" id="configdescription" value="${model.configdescription}" disabled/>
		</div>
		<div class="form-group">
			 <label for="datatype">datatype</label>
			 <input type="text" class="form-control" name="datatype" id="datatype" value="${model.datatype}" disabled/>
		</div>
		<div class="form-group">
			 <label for="value">value</label>
			 <input type="text" class="form-control" name="value" id="value" value="${model.value}" disabled/>
		</div>
		<div class="form-group">
			 <label for="typekey">typekey</label>
			 <input type="text" class="form-control" name="typekey" id="typekey" value="${model.typekey}" disabled/>
		</div>
		<div class="form-group">
			 <label for="typename">typename</label>
			 <input type="text" class="form-control" name="typename" id="typename" value="${model.typename}" disabled/>
		</div>
		<div class="form-group">
			 <label for="description">description</label>
			 <input type="text" class="form-control" name="description" id="description" value="${model.description}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="ishidden">ishidden</label>
			 <input type="text" class="form-control" name="ishidden" id="ishidden" value="${model.ishidden}" disabled/>
		</div>
	</div>
 </div>

 </form>
  </div>

 <script type="text/javascript">
 seajs.use("js/admin/web/AppConfig",function(o){
 	o.see();
 });
 </script>




