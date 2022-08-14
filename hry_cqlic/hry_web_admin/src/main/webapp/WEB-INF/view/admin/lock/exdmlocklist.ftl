<!-- Copyright:    -->
 <!-- ExDmLockList.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:44:56      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-lg-12">
             <h3 class="page-header">锁仓币种管理</h3>
         </div>
     </div>
     <div class="row">
         <div class="_params">
         </div>
     </div>
     <div class="row">
         <div class="col-md-12">
             <form id="table_query_form">
                 <div class="row">
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="coinCode">币种代码</label>
						     <@HrySelect url="${ctx}/exchange/exproduct/findall" itemvalue="coinCode" itemname="coinCode" name="coinCode" id="coinCode"></@HrySelect>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="releaseMethod">释放方式</label>
                             <@HrySelect key="lockReleaseMethod" name="releaseMethod" id="releaseMethod" ></@HrySelect>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div class="form-group">
                             <label for="isLock">锁仓开关</label>
                             <@HrySelect key="switched" name="isLock" id="isLock" ></@HrySelect>
                         </div>
                     </div>
                     <div class="col-md-2 column">
                         <div style="margin-top: 26px;">
                             <button type="button" id="table_query" class="btn  btn-primary"><i class="glyphicon glyphicon-search"></i>查询</button>
                             <button type="button" id="table_reset" class="btn  btn-primary"><i class="glyphicon glyphicon-refresh"></i>重置</button>
                         </div>
                     </div>
                 </div>
             </form>
             <div id="toolbar">
                 <button id="add" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-plus"></i>添加
                 </button>
                 <button id="modify" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-edit"></i>修改
                 </button>
                 <button id="see" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-see"></i>查看
                 </button>
                 <button id="lock_open" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-see"></i>开启
                 </button>
                 <button id="lock_close" class="btn btn-info-blue">
                     <i class="glyphicon glyphicon-see"></i>关闭
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
 </div>
 <script type="text/javascript">
     seajs.use(["js/admin/lock/ExDmLock","js/base/HrySelect","js/base/HryDate"], function (o, HrySelect, HryDate) {
         HryDate.init();
         HrySelect.init();
         o.list();
     });
 </script>