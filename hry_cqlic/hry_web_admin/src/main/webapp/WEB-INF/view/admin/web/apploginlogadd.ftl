<!-- Copyright:    -->
 <!-- AppLoginLogAdd.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:43:26      -->

<#include "/base/base.ftl">
   <div class="centerRowBg centerRowBg_admin">
       <div class="row">
           <div class="col-md-12">
               <h3 class="page-header">添加后台登录日志
                   <button type="button" class="btn  btn-info-blue pull-right"
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
                       <input type="text" class="form-control" name="userId" id="userId"/>
                   </div>
                   <div class="form-group">
                       <label for="userName">用户名</label>
                       <input type="text" class="form-control" name="userName" id="userName"/>
                   </div>
                   <div class="form-group">
                       <label for="loginTime">登录时间</label>
                       <input type="text" class="form-control" name="loginTime" id="loginTime"
                              readonly data-date-format="yyyy-mm-dd hh:ii:ss" data-min-view="hour"/>
                   </div>
                   <div class="form-group">
                       <label for="logoutTime">登出时间</label>
                       <input type="text" class="form-control" name="logoutTime" id="logoutTime"
                       readonly data-date-format="yyyy-mm-dd hh:ii:ss" data-min-view="hour"/>
                   </div>
                   <div class="form-group">
                       <label for="ip">ip</label>
                       <input type="text" class="form-control" name="ip" id="ip"/>
                   </div>
               </div>
           </div>

           <div class="row">
               <div class="col-md-2 column">
                   <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
               </div>
           </div>

       </form>
   </div>
<script type="text/javascript">
    seajs.use(["js/admin/web/AppLoginLog","js/base/HryDate"], function (o, HryDate) {
        HryDate.init();
        o.add();
    });
</script>




