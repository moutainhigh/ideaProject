 <!-- Copyright:   互融云 -->
 <!-- AppUserModify.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2017-05-27 16:05:55      -->

 <#include "/base/base.ftl">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">重置密码<button type="button"  class="btn  btn-info-blue pull-right"  onclick="loadUrl2Div('${ctx}/v.do?u=/oauth/user/appuserlist','tree_right')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
 <div class="row">
	<div class="col-md-4 column">

        <div class="form-group">
            <label for="username">账号</label>
            <input type="text" class="form-control" name="username" id="username" value="${model.username}" readonly />
        </div>
        <div class="form-group">
            <label for="username">密码</label>
            <input type="text" class="form-control" name="password" id="password"   />
        </div>


	
	</div>
 </div>


 <div class="row">
 <div class="col-md-2 column">
 	<button type="button" id="resetpwSubmit" class="btn  btn-info-blue" >提交</button>
 </div>
 </form>

<script type="text/javascript">
seajs.use(["js/oauth/user/AppUser","js/base/HrySelect"],function(o,HrySelect){
	HrySelect.init();
	o.resetpw();
});
</script>






