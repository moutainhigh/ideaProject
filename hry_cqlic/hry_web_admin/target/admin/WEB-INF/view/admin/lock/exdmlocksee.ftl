 <!-- Copyright:    -->
 <!-- ExDmLockSee.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:44:56      -->

 <#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">查看信息
                <button type="button" class="btn btn-info-blue pull-right"
                        onclick="loadUrl('${ctx}/v.do?u=/admin/lock/exdmlocklist')"><i
                        class="fa fa-mail-forward"></i>返回
                </button>
            </h3>
        </div>
    </div>

 <form id="form">
     <input type="hidden" name="id" id="id" value="${model.id}"/>
     <input type="hidden" id="platCoin" value="${platCoin}">
     <h4>锁仓规则</h4>
     <div class="row">
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="coinCode">币种代码</label>
                    <@HrySelect url="${ctx}/exchange/exproduct/findall" itemvalue="coinCode" itemname="coinCode" name="coinCode" id="coinCode" readonly="readonly" value="${model.coinCode}"></@HrySelect>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockStartLimit">锁仓起点额度（个）</label>
                 <input type="number" class="form-control" name="lockStartLimit" id="lockStartLimit" disabled value="${model.lockStartLimit}"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockMethod">锁仓方式</label>
                    <@HrySelect key="lockMethod" name="lockMethod" id="lockMethod" readonly="readonly" value="${model.lockMethod}"></@HrySelect>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockRatio">锁仓比例（%）</label>
                 <input type="number" class="form-control" name="lockRatio" id="lockRatio" disabled value="${model.lockRatio}"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockStartTime">规则开始时间</label>
                 <input type="text" class="form-control" name="lockStartTime" id="lockStartTime" disabled data-date-format="yyyy-mm-dd" data-min-view="month" value="${(model.lockStartTime?string("yyyy-MM-dd"))!}"/>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockDuration">规则持续周期</label>
                 <input type="text" class="form-control" name="lockDuration" id="lockDuration" disabled value="${model.lockDuration}"/>
             </div>
         </div>
     </div>
     <div class="row">
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="lockCycle">锁仓周期</label>
                 <input type="number" class="form-control" name="lockCycle" id="lockCycle" disabled value="${model.lockCycle}"/>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
             </div>
         </div>
     </div>
     <h4>释放规则</h4>
     <div class="row">
         <div class="col-md-3 column">
             <div class="form-group">
                 <label for="releaseMethod">释放方式</label>
                    <@HrySelect key="lockReleaseMethod" name="releaseMethod" id="releaseMethod" readonly="readonly" value="${model.releaseMethod}"></@HrySelect>
             </div>
         </div>
         <div class="col-md-3 column">
             <div class="form-group">
                 <label for="releaseMethodVal">&nbsp;</label>
                 <input type="number" class="form-control" name="releaseMethodVal" id="releaseMethodVal" disabled value="${model.releaseMethodVal}"/>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="releaseFrequency">释放频率（天）</label>
                 <input type="text" class="form-control" name="releaseFrequency" id="releaseFrequency" disabled value="${model.releaseFrequency}"/>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="isCirculation">是否流通</label>
                    <@HrySelect key="yesno" name="isCirculation" id="isCirculation" readonly="readonly" value="${model.isCirculation}"></@HrySelect>
             </div>
         </div>
     </div>
     <#--<div class="row" hidden>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="dailyReleaseOfUserRatio">日释放用户比例（选填，%）</label>
                 <input type="number" class="form-control" name="dailyReleaseOfUserRatio" id="dailyReleaseOfUserRatio" disabled value="${model.dailyReleaseOfUserRatio}"/>
             </div>
         </div>
         <div class="col-md-6 column">
             <div class="form-group">
                 <label for="releaseSortMethod">释放排序方式</label>
                 <select id="releaseSortMethod" name="releaseSortMethod" class="form-control" disabled value="${model.releaseSortMethod}">
                     <option value="">请选择</option>
                     <option value="1">按注册时间</option>
                 </select>
             </div>
         </div>
     </div>-->
 </form>

 <script type="text/javascript">
 seajs.use(["js/admin/lock/ExDmLock","js/base/HrySelect","js/base/HryDate"], function (o, HrySelect, HryDate) {
	 HryDate.init();
	 HrySelect.init();
	 o.init();
 	 o.see();
 });
 </script>




