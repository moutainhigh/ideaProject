 <!-- Copyright:    -->
 <!-- AppServerMonitorSee.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-19 11:31:39      -->

 <#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
 <div class="row">
 <div class="col-md-12">
     <h3 class="page-header">查看监控服务  <button type="button"  class="btn btn-info-blue pull-right"  onclick="loadUrl('${ctx}/v.do?u=/admin/web/appservermonitorlist')" > <i class="fa fa-mail-forward"></i>返回</button></h3>
 </div>
 </div>


 <form id="form" >
 <div class="row">
 	<div class="col-md-4 column">
            <div class="form-group">
                <label for="serverName">服务名称</label>
                <input type="text" class="form-control" name="serverName" id="serverName" disabled value="${model.serverName}"/>
            </div>
            <div class="form-group">
                <label for="serverHost">服务地址</label>
                <input type="text" class="form-control" name="serverHost" id="serverHost"  disabled value="${model.serverHost}"/>
            </div>
            <div class="form-group">
                <label for="serverPort">服务端口</label>
                <input type="text" class="form-control" name="serverPort" id="serverPort" disabled value="${model.serverPort}"/>
            </div>
            <div class="form-group">
                <label for="type">服务类型</label>
			<@HrySelect key="serverType"  name="type"  id="type" readonly="readonly"  value="${model.type}"> </@HrySelect>
            </div>

            <div class="form-group">
                <label for="isOpen">是否启用</label>
			<@HrySelect key="yesno"  name="isOpen"  id="isOpen" readonly="readonly"  value="${model.isOpen}"> </@HrySelect>
            </div>
            <div class="form-group">
                <label for="mailConfigId">使用邮箱</label>
					<@HrySelect url="${ctx}/web/mailconfig/mailConfigList" readonly="readonly" itemvalue="id"  itemname="emailUser"  name="mailConfigId"  id="mailConfigId"  value="${model.mailConfigId}">  </@HrySelect>
            </div>
            <div class="form-group">
                <label for="sendEmails">被通知邮箱账号(多个账号用英文逗号分隔)</label>
                <input type="text" class="form-control" name="sendEmails" id="sendEmails" readonly="readonly" value="${model.sendEmails}"/>
            </div>

		<#--<div class="form-group">
			 <label for="sendPhone">sendPhone</label>
			 <input type="text" class="form-control" name="sendPhone" id="sendPhone" value="${model.sendPhone}" disabled/>
		</div>-->
	</div>
 </div>

 </form>
 
 </div>

 <script type="text/javascript">
     seajs.use(["js/admin/web/AppServerMonitor","js/base/HrySelect"],function(o,HrySelect){
         HrySelect.init();
 	o.see();
 });
 </script>




