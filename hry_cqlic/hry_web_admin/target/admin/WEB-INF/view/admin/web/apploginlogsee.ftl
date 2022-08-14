<!-- Copyright:    -->
 <!-- AppLoginLogSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:43:26      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
       <div class="row">
           <div class="col-md-12">
               <h3 class="page-header">查看后台登录日志
                   <button type="button" class="btn btn-info pull-right"
                           onclick="loadUrl('${ctx}/v.do?u=/admin/web/apploginloglist')"><i
                           class="fa fa-mail-forward"></i>返回
                   </button>
               </h3>
           </div>
       </div>


       <form id="form">
           <div class="row">
               <div class="col-md-4 column">
                   <div class="form-group">
                       <label for="userId">用户ID</label>
                       <input type="text" class="form-control" name="userId" id="userId" value="${model.userId}"
                              disabled/>
                   </div>
                   <div class="form-group">
                       <label for="userName">用户名</label>
                       <input type="text" class="form-control" name="userName" id="userName" value="${model.userName}"
                              disabled/>
                   </div>
                   <div class="form-group">
                       <label for="loginTime">登录时间</label>
                       <input type="text" class="form-control" name="loginTime" id="loginTime"
                              value="${model.loginTime?string("yyyy-MM-dd HH:mm:ss")}" disabled/>
                   </div>
                   <#--<div class="form-group">
                       <label for="logoutTime">登出时间</label>
                       <input type="text" class="form-control" name="logoutTime" id="logoutTime"
                              value="${model.logoutTime?string("yyyy-MM-dd HH:mm:ss")}" disabled/>
                   </div>-->
                   <div class="form-group">
                       <label for="ip">ip</label>
                       <input type="text" class="form-control" name="ip" id="ip" value="${model.ip}" disabled/>
                   </div>
               </div>
           </div>

       </form>
   </div>
 <script type="text/javascript">
     seajs.use("js/admin/web/AppLoginLog", function (o) {
         o.see();
     });
 </script>




