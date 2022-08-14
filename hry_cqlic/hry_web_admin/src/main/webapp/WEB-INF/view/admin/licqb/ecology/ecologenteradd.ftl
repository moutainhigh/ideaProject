 <!-- Copyright:    -->
 <!-- EcologEnterAdd.html     -->
 <!-- @author:      zhouming  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2020-06-05 16:37:53      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
<div class="row">
    <div class="col-md-12">
        <h3 class="page-header">SysCustomer--Add  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/licqb/ecology/ecologenterlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
    </div>
</div>


<form id="form" >

<div class="row">
	<div class="col-md-4 column">
		<div class="form-group">
			 <label for="customerId">用户Id</label>
			 <input type="text" class="form-control" name="customerId" id="customerId" />
		</div>
		<div class="form-group">
			 <label for="orderNum">单号</label>
			 <input type="text" class="form-control" name="orderNum" id="orderNum" />
		</div>
		<div class="form-group">
			 <label for="plateId">板块Id</label>
			 <input type="text" class="form-control" name="plateId" id="plateId" />
		</div>
		<div class="form-group">
			 <label for="enterLevel">入驻等级 A(前三) B </label>
			 <input type="text" class="form-control" name="enterLevel" id="enterLevel" />
		</div>
		<div class="form-group">
			 <label for="enterName">入驻名称</label>
			 <input type="text" class="form-control" name="enterName" id="enterName" />
		</div>
		<div class="form-group">
			 <label for="enterLogo">入驻logo</label>
			 <input type="text" class="form-control" name="enterLogo" id="enterLogo" />
		</div>
		<div class="form-group">
			 <label for="downloadLink">下载链接</label>
			 <input type="text" class="form-control" name="downloadLink" id="downloadLink" />
		</div>
		<div class="form-group">
			 <label for="enterApplyIntro">申请入驻简介</label>
			 <input type="text" class="form-control" name="enterApplyIntro" id="enterApplyIntro" />
		</div>
		<div class="form-group">
			 <label for="enterStatus">1 申请中 2 后台审核拒绝 3 后台审核通过(待付款) 4 用户拒绝 5 用户通过（待核实） 6 核实通过 7 核实未通过 8 已到期</label>
			 <input type="text" class="form-control" name="enterStatus" id="enterStatus" />
		</div>
		<div class="form-group">
			 <label for="renewStatus">0 未申请续费 1 续费待核实 </label>
			 <input type="text" class="form-control" name="renewStatus" id="renewStatus" />
		</div>
		<div class="form-group">
			 <label for="enterReply">入驻申请回复</label>
			 <input type="text" class="form-control" name="enterReply" id="enterReply" />
		</div>
		<div class="form-group">
			 <label for="validityDay">实际保证期有效天数</label>
			 <input type="text" class="form-control" name="validityDay" id="validityDay" />
		</div>
		<div class="form-group">
			 <label for="expireDate">到期时间</label>
			 <input type="text" class="form-control" name="expireDate" id="expireDate" />
		</div>
		<div class="form-group">
			 <label for="saasId">saasId</label>
			 <input type="text" class="form-control" name="saasId" id="saasId" />
		</div>
	</div>
</div>

<div class="row">
	<div class="col-md-2 column">
		<button type="button" id="addSubmit" class="btn btn-info-blue btn-block" >提交</button>
	</div>
</div>

</form>

</div>
<script type="text/javascript">
seajs.use("js/admin/licqb/ecology/EcologEnter",function(o){
	o.add();
});
</script>




