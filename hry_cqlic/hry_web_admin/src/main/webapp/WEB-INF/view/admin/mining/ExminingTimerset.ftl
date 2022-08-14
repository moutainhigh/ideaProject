
 <!-- Copyright:   北京互融时代软件有限公司 -->
 <!-- C2ctransactionList.html     -->
 <!-- @author:      jidn  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-07-07 14:06:38      -->
 <#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-md-12">
             <h3 class="page-header">定时器配置 </h3>
         </div>
     </div>
     <div class="row">
         <hr>
         <div class="col-md-4 column">
             <h3 >挖矿定时器</h3>
		 </div>
	 </div>
     <form id="processForm">
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="miningIfStart">是否开启</label>
					<@HrySelect key="noyes" name="miningIfStart" id="miningIfStart" value="${timer.miningIfStart}"></@HrySelect>
                </div>
                <div class="form-group">
					<input type="hidden" name="type" value="0">
                    <label for="platformAvgTimer">平台币奖励计算</label>
                    <input type="text" class="form-control" name="platformAvgTimer" id="platformAvgTimer" value="${timer.platformAvgTimer}" />
					<#if timer.platformAvgTimerType??>
						<@HrySelect key="timeType" name="platformAvgTimerType" id="platformAvgTimerType" value="${timer.platformAvgTimerType}"></@HrySelect>
					<#else>
						<@HrySelect key="timeType" name="platformAvgTimerType" id="platformAvgTimerType" value="h"></@HrySelect>
					</#if>
                </div>
                <div class="form-group">
                    <label for="platformReturnTimer">平台币发放时间</label>
                    <input type="text" class="form-control" name="platformReturnTimer" id="platformReturnTimer" value="${timer.platformReturnTimer}"/>
				<#if timer.platformReturnTimerType??>
					<@HrySelect key="timeType" name="platformReturnTimerType" id="platformReturnTimerType" value="${timer.platformReturnTimerType}"></@HrySelect>
				<#else>
					<@HrySelect key="timeType" name="platformReturnTimerType" id="platformReturnTimerType" value="h"></@HrySelect>
				</#if>
                </div>
                <div class="form-group">
                <#--    <label for="platformStartTimer">发币开始时间</label>
                    <input type="text" class="form-control" name="platformStartTimer" id="platformStartTimer" readonly    data-date-format="yyyy-mm-dd" data-min-view="month" value="${timer.platformStartTimer}">-->
                    <label for="platformType">是否自动</label><@HrySelect key="noyes" name="platformType" id="platformType" value="${timer.platformType}"></@HrySelect>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="processSubmit" class="btn btn-info-blue btn-block" >保存</button>
            </div>
        </div>
	</form>
     <div class="row">
         <hr>
         <div class="col-md-4 column">
             <h3 >分红定时器</h3>
         </div>
     </div>
	<form id="processBonusForm">
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="extrustId">是否开启</label>
					<@HrySelect key="noyes" name="bonusIfStart" id="bonusIfStart" value="${timer1.bonusIfStart}"></@HrySelect>
                </div>
                <div class="form-group">
                    <input type="hidden" name="type" value="1">
                    <label for="extrustId">持有者记录</label>
                    <input type="text" class="form-control" name="bonusRecordTimer" id="bonusRecordTimer" value="${timer1.bonusRecordTimer}"/>
					<#if timer1.bonusRecordTimerType??>
						<@HrySelect key="timeType" name="bonusRecordTimerType" id="bonusRecordTimerType" value="${timer1.bonusRecordTimerType}"></@HrySelect>
					<#else>
						<@HrySelect key="timeType" name="bonusRecordTimerType" id="bonusRecordTimerType" value="h"></@HrySelect>
					</#if>

                </div>
                <div class="form-group">
                    <label for="customerId">分红时间</label>
                    <input type="text" class="form-control" name="bonusReturnTimer" id="bonusReturnTimer" value="${timer1.bonusReturnTimer}"/>
					<@HrySelect key="timeType" name="bonusReturnTimerType" id="bonusReturnTimerType" value="${timer1.bonusReturnTimerType}"></@HrySelect>
                </div>
                <div class="form-group">
                   <#-- <label for="customerId">分红开始时间</label>
                    <input type="text" class="form-control" name="bonusReturnStart" id="bonusReturnStart" readonly   data-date-format="yyyy-mm-dd" data-min-view="month" value="${timer1.bonusReturnStart}">-->
                    <label for="customerId">是否自动</label><@HrySelect key="noyes" name="bonusReturnType" id="bonusReturnType" value="${timer1.bonusReturnType}"></@HrySelect>
                </div>

            </div>
        </div>

        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="processBonusSubmit" class="btn btn-info-blue btn-block" >保存</button>
            </div>
        </div>
	</form>
     <div class="row">
         <hr>
         <div class="col-md-4 column">
             <h3 >锁仓释放定时器</h3>
         </div>
     </div>
	<form id="processLockForm">
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                    <label for="extrustId">锁仓释放定时器</label>
					<@HrySelect key="switched" name="unlockIfStart" id="unlockIfStart" value="${timer2.unlockIfStart}"></@HrySelect>
                </div>
                <#--<div class="form-group">
                    <input type="hidden" name="type" value="2">
                    <label for="extrustId">释放频率（天）</label>
                    <input type="text" class="form-control" name="lockfrequency" id="lockfrequency" value="${timer2.lockfrequency}"/>
                </div>-->
                <#--<div class="form-group">
                    <label for="customerId">锁仓释放比列%</label>
                    <input type="text" class="form-control" name="lockfrequencyScale" id="lockfrequencyScale" value="${timer2.lockfrequencyScale}"/>
                </div>-->
            </div>
        </div>

        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="processLockSubmit" class="btn btn-info-blue btn-block" >保存</button>
            </div>
        </div>
	


	</form>
</div>
<script type="text/javascript">
    seajs.use(["js/admin/mining/ExminingTimerset","js/base/HrySelect","js/base/HryDate"],function(o,HrySelect,HryDate){
        HryDate.init();
        HrySelect.init();
        o.init();
    });
</script>

