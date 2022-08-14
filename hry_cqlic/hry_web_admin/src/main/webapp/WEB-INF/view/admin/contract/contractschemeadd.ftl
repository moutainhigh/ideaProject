<!-- Copyright:    -->
 <!-- ContractSchemeAdd.html     -->
 <!-- @author:      hec  -->
 <!-- @version:     V1.0             -->
 <!-- @Date:        2018-12-27 16:37:41      -->

<#include "/base/base.ftl">
 <div class="centerRowBg centerRowBg_admin">
     <div class="row">
         <div class="col-md-12">
             <h3 class="page-header">合约方案添加
                 <button type="button" class="btn btn-info-blue pull-right"
                         onclick="loadUrl('${ctx}/v.do?u=/admin/contract/contractschemelist')"><i
                         class="fa fa-mail-forward"></i>返回
                 </button>
             </h3>
         </div>
     </div>


     <form id="form">
         <div class="row">

             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="name">合约名称</label>
                     <input type="text" class="form-control" name="name" id="name"/>
                 </div>
             </div>
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="cycle">周期</label>
                     <select class="form-control" id="cycle" name="cycle">
                         <option value="1">当周</option>
                         <option value="2">次周</option>
                         <option value="3">季度</option>
                     </select>
                 </div>
             </div>
         </div>
         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="calculateWeek">清算/交割时间 星期几</label>
                     <select class="form-control" id="calculateWeek" name="calculateWeek">
                         <option value="1">星期一</option>
                         <option value="2">星期二</option>
                         <option value="3">星期三</option>
                         <option value="4">星期四</option>
                         <option value="5">星期五</option>
                         <option value="6">星期六</option>
                         <option value="7">星期日</option>
                     </select>
                 </div>
             </div>
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="calculateTime">具体清算/交割 时间</label>
                     <input type="text" class="form-control" name="calculateTime" id="calculateTime"
                            data-date-format="hh:ii" data-min-view="hour" start-view="0"
                            value="${(info.birthday?string("hh:ii"))!}">
                 </div>
             </div>
         </div>

         <div class="row">
             <div class="col-md-4 column">
                 <div class="form-group">
                     <label for="multiple">杠杆倍数</label>
                 </div>
                 <div class="form-group multipleBox">
                     <ul>
                         <li>
                             <input type="checkbox" name="multiple" id="multiple" value="10">X10
                         </li>
                         <li>
                             <input type="checkbox" name="multiple" id="multiple" value="20">X20
                         </li>
                         <li>
                             <input type="checkbox" name="multiple" id="multiple" value="30">X30
                         </li>
                     </ul>
                 </div>
             </div>
         </div>

         <div class="row">
             <div class="col-md-2 column">
                 <button type="button" id="addSubmit" class="btn btn-info-blue btn-block">提交</button>
             </div>
         </div>

     </form>

 </div>
<script type="text/javascript">
    seajs.use(["js/admin/contract/ContractScheme","lib/datepicker/js/bootstrap-datetimepicker"], function (o) {
        o.add();
        $('#calculateTime').datetimepicker({
            autoclose:true,//选中关闭
            todayBtn: true,//今日按钮
            weekStart:1,
            todayHighlight:true,
            startView:1
        });
    });
</script>
<style type="text/css">
    .multipleBox ul {
        display: flex;
    }

    .multipleBox ul li {
        margin-right: 20px;
    }
</style>




