<#include "/base/base.ftl">
<div class="centerRowBg centerRowBg_admin">
<div class="order-right-box">

    <input type="hidden" id="tradeNum" value="${app.transactionNum}"/>
    <input type="hidden" id="releaseId" value="${releaseId}"/>
    <input type="hidden" id="paymentTerm" value="${paymentTerm}"/>
    <h3 class="page-header">申诉详情</h3>
    <div class="order-thumb">
        <form>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>订单号：</label>
                    <input disabled class="form-control" value="${app.transactionNum}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>创建时间：</label>
                    <input disabled class="form-control" value="${app.created?date}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>卖方昵称：</label>
                    <input disabled class="form-control" value="${app.sellUserName}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>交易币种：</label>
                    <input disabled class="form-control" value="${app.coinCode}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>买方昵称：</label>
                    <input disabled class="form-control" value="${app.buyUserName}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>单价：</label>
                    <input disabled class="form-control" value="${tradeMoney} ${app.coinCode}" />
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>交易类型：</label>
                    <input disabled class="form-control" value="<#if isFixed==0>市价交易<#elseif isFixed==1>定价交易</#if>"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>数量：</label>
                    <input disabled class="form-control" value="${app.tradeNum} ${app.coinCode}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>交易方式：</label>
                    <input disabled class="form-control" value="<#if app.transactionMode==1>在线购买<#elseif app.transactionMode==2>在线出售</#if>"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>总金额：</label>
                    <input disabled class="form-control" value="${app.tradeMoney} CNY"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>支付方式：</label>
                    <input disabled class="form-control" value="${app.tradeMoney} CNY"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>总金额：</label>
                    <input disabled class="form-control" value="${app.payType}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>手续费：</label>
                    <input disabled class="form-control" value="${app.sellfee} ${app.coinCode}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>交易状态：</label>
                    <input disabled class="form-control" value="${app.stateZHCN}"/>
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="form-group">
                    <label>支付信息：</label>
                    <span id="payTypeRemake_id" style="display:none">${payTypeRemake}</span></h3>
                    <textarea id="editor" name="" type="text/plain" readonly="readonly" style="height:50px;"></textarea>
                <#--
                 <input disabled class="form-control" value="${payTypeRemake}"/>
                -->
                </div>
            </div>

            <!-- 买方 -->
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>买方诉求：</label>
                    <input disabled class="form-control" value="${appAppealRemote.appeal}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>买方描述：</label>
                    <input disabled class="form-control" value="${appAppealRemote.content}"/>
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="form-group">
                    <label>买方证据：</label>
                    <div class="payment-imgs">
                        <div class="payment-img-item">
		      <#list appAppealRemote.imgUrl as list>
                  <img src="${list}" style='width: 120px;'>
              </#list>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 卖方 -->
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>卖方诉求：</label>
                    <input disabled class="form-control" value="${appAppealRemote.appealSell}"/>
                </div>
            </div>
            <div class="col-md-4 column">
                <div class="form-group">
                    <label>卖方描述：</label>
                    <input disabled class="form-control" value="${appAppealRemote.contentSell}"/>
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="form-group">
                    <label>卖方证据：</label>
                    <div class="payment-imgs">
                        <div class="payment-img-item">
		      <#list appAppealRemote.imgUrlSell as list>
                  <img src="${list}" style='width: 120px;'>
              </#list>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-12 column">
                <div class="form-group">
                    <label>平台回复内容：</label>
                    <textarea class="form-control" id="platFormContent"><#if (appAppealRemote.platFormContent)??>${appAppealRemote.platFormContent}</#if></textarea>
                </div>
            </div>
        </form>
        <div class="appeal-btn-box col-md-1">
            <input class="btn btn-primary" type="button" value="回复" id="order_reply">
        </div>
        <div class="appeal-btn-box col-md-1">
            <input class="btn btn-primary" type="button" value="取消订单" id="order_cancel">
            <!--  <button id="cancel" class="btn btn-info" >
                 <i class="glyphicon glyphicon-share"></i>取消订单
             </button> -->
        </div>
        <div class="appeal-btn-box col-md-1">
            <input class="btn btn-primary" type="button" value="订单成立" id="order_pass">
            <!--   <button id="examine" data-type="11" class="btn btn-warning" >
               <i class="glyphicon glyphicon-edit"></i>订单成立
           </button> -->
        </div>
        <div class="appeal-btn-box col-md-1">
            <input class="btn btn-primary" type="button" value="驳回" id="order_reject">
            <!--  <button id="reject" data-type="12" class="btn btn-danger" >
              <i class="glyphicon glyphicon-remove"></i>驳回
          </button> -->
        </div>

    </div>

    </div>
    <script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.config.js"></script>
    <script type="text/JavaScript" charset="utf-8" src="${ctx}/static/${version}/lib/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript">
//支付信息回显
 $(function(){
 	var ue = UE.getEditor('editor');
 	var payTypeRemake = $("#payTypeRemake_id").html();
     //判断ueditor 编辑器是否创建成功
     ue.addListener("ready", function () {
     // editor准备好之后才可以使用
   	 	ue.setContent(payTypeRemake);
   	 	ue.setDisabled();

     });
 });


 seajs.use("js/admin/exchange/OtcAppTransaction",function(o){
 	o.see();
 });
</script>