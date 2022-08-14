<!-- Copyright:    -->
<!-- KlgAmountLimitationModify.html     -->
<!-- @author:      lzy  -->
<!-- @version:     V1.0             -->
<!-- @Date:        2019-04-16 16:55:42      -->

<#include "/base/base.ftl">
<link rel="stylesheet" type="text/css" href="${ctx}/static/${version}/lib/jdate/css/jedate.css" />
<script type="text/javascript" src="${ctx}/static/${version}/lib/jdate/jedate-6.5.0.js"></script>
<div class="centerRowBg centerRowBg_admin">
    <div class="row">
        <div class="col-md-12">
            <h3 class="page-header">预约总额度限制  </h3>
        </div>
    </div>

<div class="row">
<div class="col-md-12">
    <form id="form" >
        <input type="hidden" class="form-control" name="id" id="id" value="${model.id}"/>
        <input type="hidden" class="form-control" name="type" id="type" value="${model.type}"/>
        <div class="row">
            <div class="col-md-4 column">
                <div class="form-group">
                         <label for="state">是否限额</label>
                    <@HrySelect key="yesno"  name="state"  id="state"   value="${model.state}" > </@HrySelect>
                </div>
                <div class="form-group">
                    <label for="morningLimit">上午预约购买总额度</label>
                    <input type="text" class="form-control" name="morningLimit" id="morningLimit" value="${model.morningLimit}"/>
                </div>
                <div class="form-group">
                    <label for="pmTime">下午额度开放时间</label>
                    <input type="text" class="form-control jeinput appconfig" name="pmTime" id="pmTime" value="${model.pmTime}"  placeholder="hh:mm:ss">
                </div>
                <div class="form-group">
                    <label for="afternoonLimit">下午预约购买总额度</label>
                    <input type="text" class="form-control" name="afternoonLimit" id="afternoonLimit" value="${model.afternoonLimit}"/>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-md-2 column">
                <button type="button" id="modifySubmit" class="btn btn-info-blue btn-warning" >提交</button>
            </div>
        </div>
    </form>
</div>
</div>
    <div class="row">
            <div class="col-md-6 column">
			    <table class="table table-striped table-bordered table-hover" id="configTable">
			        <thead>
			        <tr>
			            <th class="text-center" style="width: 200px;">预测时间</th>
			            <th class="text-center" style="width: 200px;">前一天剩余差值</th>
			            <th class="text-center" style="width: 200px;">预测排单数量</th>
			            <th class="text-center" style="width: 250px;">当天剩余排单数量</th>
			        </tr>
			        </thead>
			        <tbody>
			            <#list list as item>
						    <tr>
						        <td class="text-center">
						        	${item.created?string("yyyy-MM-dd")} 
						        </td>
						        <td class="text-center">
						            ${item.yesterdaySurplus}
						         </td>
						        <td class="text-center">
						            ${item.smeMoneySum}
						         </td>
						        <td class="text-center">
						            ${item.todaySum}
						         </td>
						    </tr>
						</#list>
			        </tbody>
			    </table>
			 </div>
		</div>
</div>
<script type="text/javascript">
    seajs.use("js/admin/klg/limit/KlgAmountLimitation",function(o){
        o.modify();
    });
    jeDate('#pmTime',{
        format:"hh:mm:ss",
        minDate:"12:00:00",              //最小日期
        maxDate:"23:59:59",              //最大日期
        format: "hh:mm:ss"
    });
</script>






