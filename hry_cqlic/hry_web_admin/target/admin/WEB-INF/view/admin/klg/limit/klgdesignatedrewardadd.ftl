<!-- Copyright:    -->
 <!-- AppMessageAdd.html     -->
 <!-- @author:      lizhiying  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-05 10:21:55      -->

<#include "/base/base.ftl">
  <div class="centerRowBg centerRowBg_admin">
      <div class="row">
          <div class="col-md-12">
              <h3 class="page-header">指定奖励添加
              </h3>
          </div>
      </div>
      <div class="row">
          <div class="col-md-12">
              <div class="form-group">
                      <span>
                      <label for="title"></label>
                          <button type="button" id="addUser" class="btn btn-primary ">添加指定人</button>
                          <button type="button" id="selectedUser" class="btn btn-primary">指定人(0人)</button>
                      </span>
              </div>
          </div>
      </div>
      <form id="form">
          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                        <label for="langCode">级差等级</label>
                      <@HrySelect url="${ctx}/klg/level/klggradation/findAll"  itemvalue="gradation"  itemname="name"  name="gradation"  id="gradation"> </@HrySelect>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                      <label for="langCode">见点奖最高可拿代数（代）</label>
                      <@HrySelect key="klg_algebra" name="pointAlgebra" id="pointAlgebra" ></@HrySelect>
                  </div>
              </div>
          </div>
          <div class="row">
              <div class="col-md-12">
                  <div class="form-group">
                      <label for="langCode">奖金额度（USDT）</label>
                      <input type="text" class="form-control" id="rewardNum" name="rewardNum"/>
                  </div>
              </div>
          </div>
      </form>

      <div class="row">
          <div class="col-md-2 column">
              <button type="button" id="addSubmitAndSend" class="btn btn-primary btn-block">保存</button>
          </div>
      </div>
  </div>

<input type="hidden" id="userids">
<script type="text/javascript">
    seajs.use(["js/admin/klg/limit/KlgDesignatedRewardAdd","js/base/HrySelect"],function(o,HrySelect){
        HrySelect.init();
        o.add();
        o.example();
    });
</script>

<div class="row hide" id="searchDiv" style="margin-left: 32px">
    <div class="col-md-10">
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
    <div id="tableDiv" class="col-md-10">

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



