 <!-- Copyright:    -->
 <!-- AppCommendUserSee.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 20:10:54      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">AppCommendUser--See  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenduserlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="pid">pid</label>
			 <input type="text" class="form-control" name="pid" id="pid" value="${model.pid}" disabled/>
		</div>
		<div class="form-group">
			 <label for="pname">pname</label>
			 <input type="text" class="form-control" name="pname" id="pname" value="${model.pname}" disabled/>
		</div>
		<div class="form-group">
			 <label for="uid">uid</label>
			 <input type="text" class="form-control" name="uid" id="uid" value="${model.uid}" disabled/>
		</div>
		<div class="form-group">
			 <label for="username">username</label>
			 <input type="text" class="form-control" name="username" id="username" value="${model.username}" disabled/>
		</div>
		<div class="form-group">
			 <label for="receCode">receCode</label>
			 <input type="text" class="form-control" name="receCode" id="receCode" value="${model.receCode}" disabled/>
		</div>
		<div class="form-group">
			 <label for="sid">sid</label>
			 <input type="text" class="form-control" name="sid" id="sid" value="${model.sid}" disabled/>
		</div>
		<div class="form-group">
			 <label for="maxId">maxId</label>
			 <input type="text" class="form-control" name="maxId" id="maxId" value="${model.maxId}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isFrozen">isFrozen</label>
			 <input type="text" class="form-control" name="isFrozen" id="isFrozen" value="${model.isFrozen}" disabled/>
		</div>
		<div class="form-group">
			 <label for="aloneProportion">aloneProportion</label>
			 <input type="text" class="form-control" name="aloneProportion" id="aloneProportion" value="${model.aloneProportion}" disabled/>
		</div>
		<div class="form-group">
			 <label for="oneNumber">oneNumber</label>
			 <input type="text" class="form-control" name="oneNumber" id="oneNumber" value="${model.oneNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="twoNumber">twoNumber</label>
			 <input type="text" class="form-control" name="twoNumber" id="twoNumber" value="${model.twoNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="threeNumber">threeNumber</label>
			 <input type="text" class="form-control" name="threeNumber" id="threeNumber" value="${model.threeNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="laterNumber">laterNumber</label>
			 <input type="text" class="form-control" name="laterNumber" id="laterNumber" value="${model.laterNumber}" disabled/>
		</div>
		<div class="form-group">
			 <label for="isCulCommend">isCulCommend</label>
			 <input type="text" class="form-control" name="isCulCommend" id="isCulCommend" value="${model.isCulCommend}" disabled/>
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" value="${model.saasId}" disabled/>
		</div>
	</div>
 </div>

 </form>

 <script type="text/javascript">
 seajs.use("js/admin/commend/AppCommendUser",function(o){
 	o.see();
 });
 </script>




