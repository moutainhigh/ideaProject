<#include "/base/base.ftl">
<div class="page-breaded">
   <div class="row">
     <div class="breadTag col-lg-3 col-md-6">
     	<a href="javascript:;" onclick="loadUrl('${ctx}/customer/appcustomer/toCustomer.do?isReal=0')"><i class="fa fa-list"></i>我的工作台</a>
     </div>
     <div class="breadList col-lg-9 col-md-6">
         <span class="pull-left" >
             <#--<a href="javascript:;" onclick="loadUrl('${ctx}/v.do?u=/admin/sys/sysmessagelist')" style="color:#00F;">-->
           <#--<i class="fa-ico-new fa-ico-new-laba"></i>最新公告-->
         <#--</a>-->
		 </span>
         <ul class="pull-left" id="centerRightNewNotice">
         </ul>
         <span class="pull-right breadCompany" style="padding-right:20px;"></span>
     </div>
    </div>
 </div>
 
 <script type="text/javascript">
 	// $(document).ready(function(){
 	// 	$("#centerRightNewNotice").mouseout(function(){
 	// 		$("#centerRightNewNotice li a").css("color","blue");
 	// 	});
 	// 	$("#centerRightNewNotice").mouseleave(function(){
 	// 		$("#centerRightNewNotice li a").css("color","black");
 	// 	});
 	//
 	// });
 	// $(function(){
    // 	$("#centerRightNewNotice").empty();
    // 	$.ajax({
    // 		type : "post",
    // 		url : _ctx + "/sys/sysmessage/queryNotice.do",
    // 		cache : false,
    // 		dataType : "json",
    // 		success : function(data) {
    // 			if (data) {
    // 				for(var i=0; i<data.length;i++){
    // 					if(data[i].title!=null && data[i].title!=""){
    // 						$("#centerRightNewNotice").append("<li class=\"news-item\"><a href=\"javascript:;\" " +
    // 								"onclick=\"loadUrl('"+ _ctx +"/sys/sysmessage/seeQueryNotice.do?sysmessageIds="+data[i].id+"')\">"+data[i].title+"</a>" +
    // 								"<input type=\"hidden\" class=\"form-control\" " +
    // 								"name=\"li_"+i+"_message_id\" value=\""+data[i].id+"\" " +
    // 								"id=\"li_"+i+"_message_id\" /></li>");
    // 					}
    // 				}
    // 				$("#centerRightNewNotice").bootstrapNews({
		// 	            newsPerPage: 1,
		// 	            autoplay: true,
		// 				pauseOnHover: true,
		// 				navigation: false,
		// 	            direction: 'up',
		// 	            newsTickerInterval: 2500,
		// 	            onToDo: function () {
		// 	                //console.log(this);
		// 	            }
		// 	        });
    // 			}
    // 		},
    // 		error : function(e) {
    //
    // 		}
    // 	});
    // });
 </script>