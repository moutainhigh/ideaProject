 <!-- Copyright:    -->
 <!-- AppCommendUserAdd.html     -->
 <!-- @author:      tianpengyu  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-25 20:10:54      -->

<#include "/base/base.ftl">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/commend/appcommenduserlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="pid">pid</label>
			 <input type="text" class="form-control" name="pid" id="pid" />
		</div>
		<div class="form-group">
			 <label for="pname">pname</label>
			 <input type="text" class="form-control" name="pname" id="pname" />
		</div>
		<div class="form-group">
			 <label for="uid">uid</label>
			 <input type="text" class="form-control" name="uid" id="uid" />
		</div>
		<div class="form-group">
			 <label for="username">username</label>
			 <input type="text" class="form-control" name="username" id="username" />
		</div>
		<div class="form-group">
			 <label for="receCode">receCode</label>
			 <input type="text" class="form-control" name="receCode" id="receCode" />
		</div>
		<div class="form-group">
			 <label for="sid">sid</label>
			 <input type="text" class="form-control" name="sid" id="sid" />
		</div>
		<div class="form-group">
			 <label for="maxId">maxId</label>
			 <input type="text" class="form-control" name="maxId" id="maxId" />
		</div>
		<div class="form-group">
			 <label for="isFrozen">isFrozen</label>
			 <input type="text" class="form-control" name="isFrozen" id="isFrozen" />
		</div>
		<div class="form-group">
			 <label for="aloneProportion">aloneProportion</label>
			 <input type="text" class="form-control" name="aloneProportion" id="aloneProportion" />
		</div>
		<div class="form-group">
			 <label for="oneNumber">oneNumber</label>
			 <input type="text" class="form-control" name="oneNumber" id="oneNumber" />
		</div>
		<div class="form-group">
			 <label for="twoNumber">twoNumber</label>
			 <input type="text" class="form-control" name="twoNumber" id="twoNumber" />
		</div>
		<div class="form-group">
			 <label for="threeNumber">threeNumber</label>
			 <input type="text" class="form-control" name="threeNumber" id="threeNumber" />
		</div>
		<div class="form-group">
			 <label for="laterNumber">laterNumber</label>
			 <input type="text" class="form-control" name="laterNumber" id="laterNumber" />
		</div>
		<div class="form-group">
			 <label for="isCulCommend">isCulCommend</label>
			 <input type="text" class="form-control" name="isCulCommend" id="isCulCommend" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
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
seajs.use("js/admin/commend/AppCommendUser",function(o){
	o.add();
});
</script>




