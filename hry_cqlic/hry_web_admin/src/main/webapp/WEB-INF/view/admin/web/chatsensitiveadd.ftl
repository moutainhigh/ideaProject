<!-- Copyright:    -->
 <!-- chatSensitiveAdd.html     -->
 <!-- @author:      sunyujie  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:30:51      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-md-12">
             <h3 class="page-header">敏感词添加
                 <button type="button" class="btn btn-success pull-right"
                         onclick="loadUrl('${ctx}/v.do?u=/admin/web/chatsensitivelist')"><i
                         class="fa fa-mail-forward"></i>返回
                 </button>
             </h3>
         </div>
     </div>


     <form id="form">

         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="sensitiveWords">敏感词</label>
                     <input type="text" class="form-control" name="sensitiveWords" id="sensitiveWords"/>
                 </div>
             </div>
         </div>

         <div class="row">
             <div class="col-md-2 column">
                 <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
             </div>
         </div>

     </form>
     <div class="centerRowBg centerRowBg_admin">

         <script type="text/javascript">
             seajs.use("js/admin/web/chatSensitive", function (o) {
                 o.add();
             });
         </script>




