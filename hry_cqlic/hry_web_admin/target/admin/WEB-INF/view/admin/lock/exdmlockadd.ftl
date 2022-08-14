<!-- Copyright:    -->
 <!-- ExDmLockAdd.html     -->
 <!-- @author:      liuchenghui  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-06-29 11:44:56      -->

<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">添加信息
                <button type="button" class="btn btn-info-blue pull-right"
                        onclick="loadUrl('${ctx}/v.do?u=/admin/lock/exdmlocklist')"><i
                        class="fa fa-mail-forward"></i>返回
                </button>
            </h3>
        </div>
    </div>

    <form id="form">
        <input type="hidden" id="platCoin" value="${platCoin}">
        <input type="hidden" id="lockfrequency" value="${lockfrequency}">
        <h4>锁仓规则</h4>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="coinCode">币种代码</label>
                    <@HrySelect url="${ctx}/exchange/exproduct/findall" itemvalue="coinCode" itemname="coinCode" name="coinCode" id="coinCode"></@HrySelect>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockStartLimit">锁仓起点额度（个）</label>
                    <input type="number" class="form-control" name="lockStartLimit" id="lockStartLimit"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockMethod">锁仓方式</label>
                    <@HrySelect key="lockMethod" name="lockMethod" id="lockMethod"></@HrySelect>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockRatio">锁仓比例（%）</label>
                    <input type="number" class="form-control" name="lockRatio" id="lockRatio"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockStartTime">规则开始时间</label>
                    <input type="text" class="form-control" name="lockStartTime" id="lockStartTime" readonly data-date-format="yyyy-mm-dd" data-min-view="month">
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockDuration">规则持续周期（天）</label>
                    <input type="number" class="form-control" name="lockDuration" id="lockDuration" >
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="lockCycle">锁仓周期（天）</label>
                    <input type="number" class="form-control" name="lockCycle" id="lockCycle"/>
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
                    <@HrySelect key="lockReleaseMethod" name="releaseMethod" id="releaseMethod" ></@HrySelect>
                </div>
            </div>
            <div class="col-md-3 column">
                <div class="form-group">
                    <label for="releaseMethodVal">&nbsp;</label>
                    <input type="number" class="form-control" name="releaseMethodVal" id="releaseMethodVal"/>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="releaseFrequency">释放频率（天）</label>
                    <input type="text" class="form-control" name="releaseFrequency" id="releaseFrequency"/>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="isCirculation">是否流通</label>
                    <@HrySelect key="yesno" name="isCirculation" id="isCirculation" readonly="readonly"></@HrySelect>
                </div>
            </div>
        </div>
        <#--<div class="row" hidden>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="dailyReleaseOfUserRatio">日释放用户比例（选填，%）</label>
                    <input type="number" class="form-control" name="dailyReleaseOfUserRatio" id="dailyReleaseOfUserRatio" disabled/>
                </div>
            </div>
            <div class="col-md-6 column">
                <div class="form-group">
                    <label for="releaseSortMethod">释放排序方式</label>
                    <select id="releaseSortMethod" name="releaseSortMethod" class="form-control" disabled>
                        <option value="">请选择</option>
                        <option value="1">按注册时间</option>
                    </select>
                </div>
            </div>
        </div>-->

        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="addSubmit" class="btn btn-blue btn-block">提交</button>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    seajs.use(["js/admin/lock/ExDmLock","js/base/HrySelect","js/base/HryDate"], function (o, HrySelect, HryDate) {
        HryDate.init();
        HrySelect.init();
        $('#lockStartTime').datetimepicker('setStartDate', new Date());
        o.init();
        o.add();
    });
</script>




