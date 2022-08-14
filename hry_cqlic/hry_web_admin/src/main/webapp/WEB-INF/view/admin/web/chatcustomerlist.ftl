<!-- Copyright:    -->
 <!-- AppCustomerList.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:43:00      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
 <div class="row">
     <div class="col-lg-12">
         <h3 class="page-header">聊天室用户管理</h3>
     </div>
 </div>

 <div class="row">
     <div class="col-md-12">
         <div class="row">

             <div class="_params">
                 <form id="table_query_form">
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
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="email">真实名字</label>
                             <input type="text" class="form-control" tablesearch name="trueName"/>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="mobilePhone">证件号</label>
                             <input type="text" class="form-control" tablesearch name="cardId"/>
                         </div>
                     </div>
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


             <button id="setGag" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/resetPassword">
                 <i class="glyphicon glyphicon-remove"></i>禁言
             </button>
             <button id="releaseGag" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/forbidden">
                 <i class="glyphicon glyphicon-remove"></i>解除禁言
             </button>
             <#--<button id="setAdmin" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/permissible">
                 <i class="glyphicon glyphicon-remove"></i>设置管理员
             </button>-->
             <#--<button id="removeAdmin" class="btn  btn-info-blue" permissions="/admin/customer/appcustomer/fnOpenChange">
                 <i class="glyphicon glyphicon-remove"></i>	取消管理员
             </button>-->

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


    <div id="gagDiv" style="display:none;" class="popOpen">
        <div class="popBox" id="popBox">
            <div class="layui-layer-title">信息</div>
            <span class="layui-layer-setwin" id="popClose"><a class="layui-layer-ico layui-layer-close layui-layer-close1" href="javascript:;"></a></span>
            <form style="text-align: center;">
                <input type="hidden" id="customerId">
                <input type="text" id="gagTime" placeholder="*请以秒为单位" style="text-align: center;margin-top: 20px;"/>
                <div class="col-md-2 column" style="margin: 0px 135px -100px;">
                    <button type="button" id="fnGag" class="btn  btn-info-blue btn-warning"
                            style="margin-top: 18px;margin-left: 8px;">设置
                    </button>
                </div>
            </form>
        </div>
    </div>
    <style>
        .popOpen{
            width:100%;
            height:100%;
            position:fixed;
            left:0;
            top:0;
            background-color: rgba(0,0,0,.3);
            z-index: 19891016;
        }
        .popBox{
            position:fixed;
            z-index: 19891016;
            width: 380px;
            height: 180px;
            left:0;
            right:0;
            margin-left:auto;
            margin-right:auto;
            top:50%;
            margin-top:-90px;
            background-color: #fff;
            -webkit-background-clip: content;
            box-shadow: 1px 1px 50px rgba(0,0,0,.3);
        }
    </style>
</div>
 <script type="text/javascript">
     seajs.use("js/admin/customer/AppCustomer", function (o) {
         o.chatcustomerlist();
     });

 </script>

