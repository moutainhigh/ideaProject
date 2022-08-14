<!-- Copyright:    -->
 <!-- AppCustomerList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:43:00      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <#--<h3 class="page-header">未实名用户管理</h3>-->
         <h3 class="page-header">用户管理</h3>
     </div>
 </div>

 <div class="row">

     <div class="col-md-12">
         <div class="row">

             <div class="_params">
                 <form id="table_query_form">
                     <#--<div class="col-md-2 column">
                         <div class="form-group">
                             <label for="email">实名状态</label>
                             <select class="form-control" tablesearch name="states">
                                 <option value="">请选择</option>
                                 <option value="0">未实名</option>
                                 <option value="1">待审核</option>
                             &lt;#&ndash;<option value="2">已实名</option>&ndash;&gt;
                                 <option value="3">已拒绝</option>
                             </select>
                         </div>
                     </div>-->
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">邮箱</label>
                         <input type="text" class="form-control" tablesearch name="email"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="mobilePhone">手机号</label>
                         <input type="text" class="form-control" tablesearch name="mobilePhone"/>
                     </div>
                 </div>
                 <#--<div class="col-md-2 column">
                     <div class="form-group">
                         <label for="email">真实姓名</label>
                         <input type="text" class="form-control" tablesearch name="trueName"/>
                     </div>
                 </div>
                 <div class="col-md-2 column">
                     <div class="form-group">
                         <label for="mobilePhone">证件号</label>
                         <input type="text" class="form-control" tablesearch name="cardId"/>
                     </div>
                 </div>-->
                 <div class="col-md-2 column">
                     <div style="margin-top: 26px;">
                         <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                         <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                     </div>
                 </div>
                 </form>
             </div>

         </div>
         <div id="toolbar">

             <#--<button id="activeEmail" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/active">
                 <i class="glyphicon glyphicon-remove"></i>邮件激活
             </button>-->
             <button id="resetSecurity" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/resetSecurity">
                 <i class="glyphicon glyphicon-remove"></i>重置安全策略
             </button>
             <#--<button id="audit" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/audit">
                 <i class="glyphicon glyphicon-remove"></i>实名审核
             </button>
             <button id="moreAudit" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/moreAudit">
                 <i class="glyphicon glyphicon-remove"></i>实名批量通过
             </button>
             <button id="auditall" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/auditall">
                 <i class="glyphicon glyphicon-remove"></i>清除实名信息
             </button>-->
             <button id="resetPassword" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/resetPassword">
                 <i class="glyphicon glyphicon-remove"></i>重置密码
                 <input type="hidden" id="publicKey" name="publicKey" value="${RSA_publicKey}"/>
             </button>
             <button id="forbidden" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/forbidden">
                 <i class="glyphicon glyphicon-remove"></i>禁用
             </button>
             <button id="permissible" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/permiss">
                 <i class="glyphicon glyphicon-remove"></i>解禁
             </button>
             <!--button id="fnOpenChange" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/open">
                 <i class="glyphicon glyphicon-remove"></i>交易开启
             </button>
             <button id="fnCloseChange" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/close">
                 <i class="glyphicon glyphicon-remove"></i>交易关闭
             </button-->

             <button id="openDynamic" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/openDynamic">
                 <i class="glyphicon glyphicon-plus"></i>开启动态收益
             </button>
             <button id="closeDynamic" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/closeDynamic">
                 <i class="glyphicon glyphicon-remove"></i>关闭动态收益
             </button>

         </div>
         <table id="table"
                data-toolbar="#toolbar"
                data-show-refresh="true"
                data-show-columns="false"
                data-show-export="false"
                data-search="false"
                data-detail-view="false"
                data-minimum-count-columns="2"
                data-pagination="true"
                data-id-field="id"
                data-page-list="[10, 25, 50, 100]"
                data-show-footer="false"
                data-side-pagination="server"
         >
         </table>
     </div>
 </div>




 <div id="div_resetloginpassword" style="display:none; top:20px;">
     <form style="text-align: center;">
         <input type="hidden" id="customerId">
         <input type="password" id="password" placeholder="修改登录密码" style="text-align: center;margin-top: 20px;"/>
         <div class="col-md-2 column" style="margin: 0px 135px -100px;">
             <button type="button" id="modifyPassWord" class="btn  btn-info-blue btn-warning"
                     style="margin-top: 18px;margin-left: 8px;">提交
             </button>
         </div>
     </form>
 </div>

</div>
<script type="text/javascript" src="${ctx}/static/${version}/lib/rsa/jsencrypt.min.js"></script>
 <script type="text/javascript">
     seajs.use(["js/admin/customer/AppCustomer","js/base/HrySelect"], function (o,HrySelect) {
         o.list();
     HrySelect.init();
     });

 </script>

