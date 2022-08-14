 <!-- Copyright:    -->
 <!-- C2cCoinAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 12:06:01      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">审核密码设置 <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/c2c/c2ccoinlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >


    <div class="row">
        <div class="col-md-4 column">
            <#if auditpassword==null>
            <div class="form-group">
                <label for="c2cTimeOut">设置审核密码</label>
                <input type="password" class="form-control" name="auditpassword"  id="auditpassword" />
            </div>
            <#else>
                <button type="button" onclick="loadUrl('${ctx}/v.do?u=/oauth/sys/auditpasswordpage')" class="btn btn-info-blue btn-block" >重置审核密码</button>

            </#if>
        </div>
    </div>

<hr/>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-blue btn-block" >提交</button>
	</div>
</div>

</form>
      </div>

<script type="text/javascript">
seajs.use(["js/oauth/sys/Config"],function(o){
	o.auditpassword();
});
</script>




