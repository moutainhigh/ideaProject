<!-- Copyright:    -->
 <!-- LoginAopSee.html     -->
 <!-- @author:      liushilei  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-20 14:42:28      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-md-12">
              <h3 class="page-header">查看用户登录日志
                  <button type="button" class="btn btn-info pull-right"
                          onclick="loadUrl('${ctx}/v.do?u=/admin/web/loginaoplist')"><i class="fa fa-mail-forward"></i>返回
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
                      <label for="ip">ip</label>
                      <input type="text" class="form-control" name="ip" id="ip" value="${model.ip}" disabled/>
                  </div>
                  <div class="form-group">
                      <label for="type">pc、phone</label>
                      <input type="text" class="form-control" name="type" id="type" value="${model.type}" disabled/>
                  </div>
                  <div class="form-group">
                      <label for="methodName">方法名</label>
                      <input type="text" class="form-control" name="methodName" id="methodName"
                             value="${model.methodName}" disabled/>
                  </div>
                  <div class="form-group">
                      <label for="args">参数</label>
                      <input type="text" class="form-control" name="args" id="args" value="${model.args}" disabled/>
                  </div>
                  <div class="form-group">
                      <label for="target">织入增强处理的目标对象</label>
                      <input type="text" class="form-control" name="target" id="target" value="${model.target}"
                             disabled/>
                  </div>
              </div>
          </div>

      </form>
  </div>
 <script type="text/javascript">
     seajs.use("js/admin/web/LoginAop", function (o) {
         o.see();
     });
 </script>




