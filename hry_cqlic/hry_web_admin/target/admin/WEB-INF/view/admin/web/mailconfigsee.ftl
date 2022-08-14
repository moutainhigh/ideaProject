 <!-- Copyright:    -->
 <!-- MailConfigSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 15:39:56      -->

 <#include "/base/base.ftl">

<div class="centerRowBg centerRowBg_admin" >
	 <div class="row">
		 <div class="col-md-12">
			 <h3 class="page-header">查看邮箱配置  <button type="button"  class="btn btn-info pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/mailconfiglist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
		 </div>
	 </div>


	 <form id="form" >
		 <div class="row">
			<div class="col-md-4 column">
				<div class="form-group">
					 <label for="sort">排序</label>
					 <input type="text" class="form-control" name="sort" id="sort" value="${model.sort}" disabled/>
				</div>
				<div class="form-group">
					<label for="host">服务器</label>
					<input type="text" class="form-control" name="host" id="host" value="${model.host}" disabled/>
				</div>
				<div class="form-group">
					 <label for="port">端口</label>
					 <input type="text" class="form-control" name="port" id="port" value="${model.port}" disabled/>
				</div>

				<div class="form-group">
					 <label for="protocol">协议</label>
					 <input type="text" class="form-control" name="protocol" id="protocol" value="${model.protocol}" disabled/>
				</div>

				<div class="form-group">
					 <label for="emailUser">发送邮箱</label>
					 <input type="text" class="form-control" name="emailUser" id="emailUser" value="${model.emailUser}" disabled/>
				</div>
				<div class="form-group">
					 <label for="agreedPwd">认证密码</label>
					 <input type="text" class="form-control" name="agreedPwd" id="agreedPwd" value="${model.agreedPwd}" disabled/>
				</div>
				<div class="form-group">
					 <label for="customName">发送用户</label>
					 <input type="text" class="form-control" name="customName" id="customName" value="${model.customName}" disabled/>
				</div>
				<div class="form-group">
					 <label for="prefix">邮箱标题前缀</label>
					 <input type="text" class="form-control" name="prefix" id="prefix" value="${model.prefix}" disabled/>
				</div>
				<div class="form-group">
					 <label for="status">启用状态</label>
					 <@HrySelect key="yesno"  name="status"  id="status"  value="${model.status}" readonly="readonly"> </@HrySelect>
				</div>
			</div>
		 </div>

	 </form>
</div>
 <script type="text/javascript">
 seajs.use("js/admin/web/MailConfig",function(o){
 	o.see();
 });
 </script>




