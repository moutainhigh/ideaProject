<!-- Copyright:    -->
 <!-- AppCustomerSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-13 09:43:00      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-md-12">
             <h3 class="page-header">AppCustomer--See
                 <button type="button" class="btn btn-info pull-right"
                         onclick="loadUrl('${ctx}/customer/appcustomer/toCustomer.do?isReal=0')"><i
                         class="fa fa-mail-forward"></i>返回
                 </button>
             </h3>
         </div>
     </div>


     <form id="form">
         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="atuoPriceType">手机号/邮箱 </label>
                     <input type="text" class="form-control" name="atuoPriceType" id="atuoPriceType"/>
                 </div>
             </div>

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="autoPrice">是否锁定</label>
                     <input type="text" class="form-control" value="${model.isLock}" name="isLock" id="isLock"/>
                 </div>
             </div>

         </div>

         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="atuoPriceType">是否禁用 </label>
                     <input type="text" class="form-control" name="atuoPriceType" value="${model.isLock}"
                            id="atuoPriceType"/>
                 </div>
             </div>

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="autoPrice">是否实名 </label>
                     <input type="text" class="form-control" name="autoPrice" value="${model.isLock}" id="autoPrice"/>
                 </div>
             </div>

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="userName">用户UUID </label>
                     <input type="text" class="form-control" name="userName" value="${model.userName}" id="userName"/>
                 </div>
             </div>
         </div>

         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="created">创建时间 </label>
                     <input type="text" class="form-control" name="created" value="${model.created}" id="created"/>
                 </div>
             </div>

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="autoPrice">证件类型 </label>
                     <input type="text" class="form-control" name="autoPrice" value="${model.isLock}" id="autoPrice"/>
                 </div>
             </div>

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="autoPrice">证件号 </label>
                     <input type="text" class="form-control" name="autoPrice" value="${model.isLock}" id="autoPrice"/>
                 </div>
             </div>
         </div>


     </form>
 </div>

 <script type="text/javascript">
     seajs.use("js/admin/customer/AppCustomer", function (o) {
         o.see();
     });
 </script>




