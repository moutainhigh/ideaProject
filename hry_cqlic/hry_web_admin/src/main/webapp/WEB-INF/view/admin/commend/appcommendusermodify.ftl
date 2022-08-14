 <!-- Copyright:    -->
 <!-- AppCommendUserModify.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 20:10:54      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendUser--Modify  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenduserlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="pid">pid</label>
			 <input type="text" class="form-control" name="pid" id="pid" value="${model.pid}"/>
		</div>
		<div class="form-group">
			 <label for="pname">pname</label>
			 <input type="text" class="form-control" name="pname" id="pname" value="${model.pname}"/>
		</div>
		<div class="form-group">
			 <label for="uid">uid</label>
			 <input type="text" class="form-control" name="uid" id="uid" value="${model.uid}"/>
		</div>
		<div class="form-group">
			 <label for="username">username</label>
			 <input type="text" class="form-control" name="username" id="username" value="${model.username}"/>
		</div>
		<div class="form-group">
			 <label for="receCode">receCode</label>
			 <input type="text" class="form-control" name="receCode" id="receCode" value="${model.receCode}"/>
		</div>
		<div class="form-group">
			 <label for="sid">sid</label>
			 <input type="text" class="form-control" name="sid" id="sid" value="${model.sid}"/>
		</div>
		<div class="form-group">
			 <label for="maxId">maxId</label>
			 <input type="text" class="form-control" name="maxId" id="maxId" value="${model.maxId}"/>
		</div>
		<div class="form-group">
			 <label for="isFrozen">isFrozen</label>
			 <input type="text" class="form-control" name="isFrozen" id="isFrozen" value="${model.isFrozen}"/>
		</div>
		<div class="form-group">
			 <label for="aloneProportion">aloneProportion</label>
			 <input type="text" class="form-control" name="aloneProportion" id="aloneProportion" value="${model.aloneProportion}"/>
		</div>
		<div class="form-group">
			 <label for="oneNumber">oneNumber</label>
			 <input type="text" class="form-control" name="oneNumber" id="oneNumber" value="${model.oneNumber}"/>
		</div>
		<div class="form-group">
			 <label for="twoNumber">twoNumber</label>
			 <input type="text" class="form-control" name="twoNumber" id="twoNumber" value="${model.twoNumber}"/>
		</div>
		<div class="form-group">
			 <label for="threeNumber">threeNumber</label>
			 <input type="text" class="form-control" name="threeNumber" id="threeNumber" value="${model.threeNumber}"/>
		</div>
		<div class="form-group">
			 <label for="laterNumber">laterNumber</label>
			 <input type="text" class="form-control" name="laterNumber" id="laterNumber" value="${model.laterNumber}"/>
		</div>
		<div class="form-group">
			 <label for="isCulCommend">isCulCommend</label>
			 <input type="text" class="form-control" name="isCulCommend" id="isCulCommend" value="${model.isCulCommend}"/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}"/>
		</div>
		</div>
 </div>


 <div class="row">
	 <div class="col-md-2 column">
	 	<button type="button" id="modifySubmit" class="btn btn-blue btn-block" >提交</button>
	 </div>
 </div>
 </form>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendUser",function(o){
 	o.modify();
 });
 </script>






