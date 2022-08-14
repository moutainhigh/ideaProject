<!-- Copyright:    -->
 <!-- AppMessageModify.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:21:55      -->

<#include "/base/base.ftl">

  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-md-12">
              <h3 class="page-header">编辑站内信
                  <button type="button" class="btn btn-success pull-right"
                          onclick="loadUrl('${ctx}/v.do?u=/admin/web/appmessagesee')"><i
                          class="fa fa-mail-forward"></i>返回
                  </button>
              </h3>
          </div>
      </div>

      <form id="form">

          <input type="hidden" id="id" name="id" value="${model.id}">
          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                      <label for="langCode">语种</label>
			            <@HrySelect key="sysLanguage" name="messageCategory" id="langCode" value="${model.messageCategory}"></@HrySelect>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                      <label for="title">信件标题</label>
                      <input type="text" class="form-control" id="title" value="${model.title}" name="title"/>
                  </div>
              </div>
          </div>

          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                      <label for="title"></label>
                      <button type="button" id="addUser" class="btn btn-primary">添加收件人</button>
                      <button type="button" id="selectedUser" class="btn btn-primary ">收件人(${receiverSize}人)</button>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-md-12">
                  <div>
                  </div>

                  <div class="row">
                      <div class="col-md-12">
                          <div class="form-group">
                              <label class="content">信件正文</label>
                              <div ueditorid="ueditor_content" class="appconfig ueditorparent">
                                  <textarea id="ueditor_content" value="" class="ueditor"></textarea>
                              </div>
                          </div>
                      </div>
                  </div>

                  <div class="row">
                      <input type="hidden" name="appResourceSet" id="appResourceSet"/>
                      <input type="hidden" name="categoryId" id="categoryId"/>
                      <input type="hidden" name="categoryName" id="categoryName"/>
                      <input type="hidden" name="isAll" id="isAll"/>
                      <input type="hidden" name="receiveUserIdList" id="receiveUserIdList"/>
                      <div class="col-md-2 column">
                          <button type="button" id="addSubmit1" class="btn btn-primary btn-block">保存到草稿箱</button>
                      </div>
                      <div class="col-md-2 column">
                          <button type="button" id="addSubmitAndSend" class="btn btn-primary btn-block">发送</button>
                      </div>
                      <div class="col-md-2 column">
                          <button type="button" id="reset" class="btn btn-primary btn-block">重置</button>
                      </div>
                  </div>
      </form>
  </div>
 <input type="hidden" value="${model.content}" id="titlecontent">
 <input type="hidden" id="userids" value="${ids}">
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
<script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/lang/zh-cn/zh-cn.js"></script>
 <script type="text/javascript">
     $(".ueditor").each(function () {
         UE.getEditor($(this).attr("id"), {
             autoHeightEnabled: false,
             initialFrameHeight: 450
         });
     });
     //初始化ueditor
     var ue = UE.getEditor('ueditor_content');
     var titlecontent = $("#titlecontent").val();
     ue.ready(function () {//编辑器初始化完成再赋值
         ue.setContent(titlecontent);  //赋值给UEditor
     });
     $("#edui126_body,#edui127_body,#edui21_body,#edui14_body,#edui15_body,#edui16_body,#edui17_body,#edui18_body,#edui19_body,#edui20_body,#edui277_body,#edui280_body,#edui83_button_body,#edui77_button_body,#edui276_body,#edui273_body,#edui191_state,#edui77_state,#edui83_state,#edui92_state,#edui105_state").hide()


 </script>

 <script type="text/javascript">
     seajs.use("js/admin/web/AppMessage", function (o) {
         o.modify();
         o.add();
         o.example();
     });
 </script>

 <div class="row hide" id="searchDiv" style="margin-left: 32px">
     <div class="col-md-12">
         <div class="row">
             <div class="_params">
                 <form id="table_query_form1">
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
                             <label for="email">姓</label>
                             <input type="text" class="form-control" tablesearch name="surname"/>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="email">名</label>
                             <input type="text" class="form-control" tablesearch name="trueName"/>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="email">常用语种</label>
                             <@HrySelect key="sysLanguage" name="commonLanguage" id="commonLanguage"></@HrySelect>
                         </div>
                     </div>

                     <div class="col-md-4 column">
                         <div style="margin-top: 26px;">
                             <button type="button" id="table_query1" class="btn  btn-primary"><i
                                     class="glyphicon glyphicon-search"></i>查询
                             </button>
                             <button type="button" id="table_reset1" class="btn  btn-primary"><i
                                     class="glyphicon glyphicon-refresh"></i>重置
                             </button>
                             <button type="button" id="addUserbtn" class="btn  btn-primary"><i
                                     class="glyphicon glyphicon-refresh"></i>添加
                             </button>
                         </div>
                     </div>
                 </form>
             </div>
         </div>
     </div>
     <div id="tableDiv" class="col-md-12">

     </div>
 </div>


<div class="row hide" id="delUserDiv" style="margin-left: 32px">
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
                            <label for="email">姓</label>
                            <input type="text" class="form-control" tablesearch name="surname"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="email">名</label>
                            <input type="text" class="form-control" tablesearch name="trueName"/>
                        </div>
                    </div>
                    <div class="col-md-2 column">
                        <div class="form-group">
                            <label for="email">常用语种</label>
                            <@HrySelect key="sysLanguage"  name="commonLanguage" id="commonLanguage1" ></@HrySelect>
                        </div>
                    </div>
                    <div class="col-md-4 column">
                        <div style="margin-top: 26px;">
                            <button type="button" id="table_query" class="btn  btn-primary"><i
                                    class="glyphicon glyphicon-search"></i>查询
                            </button>
                            <button type="button" id="table_reset" class="btn  btn-primary"><i
                                    class="glyphicon glyphicon-refresh"></i>重置
                            </button>
                            <button type="button" id="delUser" class="btn  btn-primary"><i
                                    class="glyphicon glyphicon-refresh"></i>删除
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div id="delUserTableDiv" class="col-md-12">

    </div>
</div>






