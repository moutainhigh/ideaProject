<!doctype html>
<html>
<head type="402880e3604021992ac3a50005login">
<#include "/base/base.ftl">
    <meta charset="utf-8">
    <title>${cache_appconfig.extraParamConfig_managerName!"后台管理系统"}</title>
    <link rel="icon" type="image/png" href="${fileUrl}${cache_appconfig.extraParamConfig_managerSysLogo}">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/${version}/css/_con.min.css" />
    <style>

        #sign-in{background:url(${ctx}/static/${version}/img/erplgbg_1.jpg) no-repeat center center;}
        #sign-in form{max-width:500px;border-radius: 6px;}
        #sign-in form>.hr-lgpanel{ box-shadow:none;    padding: 20px 50px;}
        #sign-in .hrerp{color:#fff;margin:0;}
        #sign-in .hrerp-tit{padding: 0; margin-top: 1rem;font-family: "微软雅黑";font-size:36px;color:#FFFFFF;}
        #sign-in .hrerp .hre-line{height:2px;border-bottom: 1px solid #96aedf;}
        .hr-lgpanel{/*background:url(static/images/erplg.png) no-repeat center center;*/backgrund:#e9ebf2!important;min-height:368px;
            margin-top:3rem;    border-bottom: 1px solid #ffffff;
            box-shadow: 0 1px 0 0 #ffffff;}
        .hr-lgpanel .input-field .loginIcon_1{
            height:17px;
            margin-top:12px;
            background:url("${ctx}/static/${version}/img/loginIcon_1.png") no-repeat center center;
        }
        .hr-lgpanel .input-field .loginIcon_2{
            height:18px;
            margin-top:12px;
            background:url("${ctx}/static/${version}/img/loginIcon_2.png") no-repeat center center;
        }
        .hr-lgpanel .input-field .loginIcon_3{
            height:18px;
            margin-top:16px;
            background:url("${ctx}/static/${version}/img/loginIcon_3.png") no-repeat center center;
        }
        .hr-lgpanel .input-field .prefix.fa{color:#fff;}
        .hr-lgpanel .input-field .prefix.active{color:#42A5F5;}
        .input-field .prefix~.mbcode{width:30%;}
        .hr-lgpanel .input-field .mbimg{position: relative; top: 0.5rem;  }
        .hr-lgpanel .input-field{margin-top:1.5rem;border-bottom:1px solid #dcdcdc;}
        .hr-lgpanel .input-field .mbtxt{color:#fff;position: relative; top: 0;}
        #sign-in button{font-size:18px;margin-top:2rem;height:48px;line-height: 48px;background:#2d59bc;border-radius:6px; }
        input:-webkit-autofill {
            -webkit-box-shadow : 0 0 0px 1000px #ffffff inset!important;
        }
        @media (min-width: 1680px) {
            #sign-in {
                padding-top:14rem!important;
            }
        }

        .round {

            border: 0px solid #dedede;
            -moz-border-radius: 8px;      /* Gecko browsers */
            -webkit-border-radius: 8px;   /* Webkit browsers */
            border-radius:8px;            /* W3C syntax */
        }
        input[type=date], input[type=datetime-local], input[type=email], input[type=number], input[type=password], input[type=search], input[type=tel], input[type=text], input[type=time], input[type=url], textarea.materialize-textarea{
            border-bottom:0;
            box-shadow: none;
        }
        input[type=date].valid, input[type=date]:focus.valid, input[type=datetime-local].valid, input[type=datetime-local]:focus.valid, input[type=email].valid, input[type=email]:focus.valid, input[type=number].valid, input[type=number]:focus.valid, input[type=password].valid, input[type=password]:focus.valid, input[type=search].valid, input[type=search]:focus.valid, input[type=tel].valid, input[type=tel]:focus.valid, input[type=text].valid, input[type=text]:focus.valid, input[type=time].valid, input[type=time]:focus.valid, input[type=url].valid, input[type=url]:focus.valid, textarea.materialize-textarea.valid, textarea.materialize-textarea:focus.valid{
            border-bottom:0;
            box-shadow: none;
        }
        .validate{
            color:#646464;
            font-size:14px;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #646464;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #646464;
        }
        input:-ms-input-placeholder{
            color: #646464;
        }
        input::-webkit-input-placeholder{
            color: #646464;
        }
        .siteBg{
            width:500px;
            height:109px!important;
            background:url("${ctx}/static/${version}/img/siteBg.png") no-repeat;
            background-size:100% 100%;
            padding-top:30px;
        }
    </style>

</head>
<body >


<section id="sign-in">

    <!-- Background Bubbles -->
    <!--<canvas id="bubble-canvas"></canvas>-->
    <!-- /Background Bubbles -->

    <!-- Sign In Form -->
    <form action="" method="post" id="loginForm" name="loginForm">
        <input type="hidden" id="publicKey" name="publicKey" value="${RSA_publicKey}"/>
        <div class="row hrerp  center-align ">
            <!--<p class="hre-line col s3"></p>-->
            <span class="fs-24 hrerp-tit  col s12" >${cache_appconfig.extraParamConfig_managerName!"后台管理系统"}</span>
            <!--<p class="hre-line col s3"></p>-->
        </div>

        <div class="card-panel hr-lgpanel clearfix">

            <!-- Username -->
            <div class="input-field">
                <!--<i class="fa fa-user prefix"></i>-->
                <i class="prefix loginIcon_1"></i>
                <input  name="username"  id="username" type="text" class="validate" placeholder="请输入用户名">
                <!--<label for="username">用户名</label>-->
            </div>
            <!-- /Username -->

            <!-- Password -->
            <div class="input-field">
                <!--<i class="fa fa-unlock-alt prefix"></i>-->
                <i class="prefix loginIcon_2"></i>
                <input name="password" id="password" type="password" class="validate" placeholder="请输入密码">
                <!--<label for="password">密码</label>-->
            </div>


            <!-- Usercode -->
            <div class="input-field">
                <!--<i class="fa fa-mobile prefix"></i>-->
                <i class="prefix loginIcon_3"></i>
                <input id="mobileImgCode" name="mobileImgCode" type="text" class="validate mbcode" placeholder="验证码">
                <!--<label for="captcha">验证码</label>-->
                <span class="mbimg"><img id="img_captcha"  src="${ctx}/registcode?${t}"  class="round" style="width:100px; height:30px" title="换一张" src="" alt="换一张"> </span>
                <span class="fs-15 mbtxt"> （<a href="javascript:void(0)" style="color:#333333" id="refresh">看不清换一张</a>）</span>
            </div>

            <!-- /google -->
            <#if googleLogin=="true">
            <!--谷歌验证码-->
            <div class="input-field">
                <!--<i class="fa fa-unlock-alt prefix"></i>-->
                <i class="prefix loginIcon_2"></i>
                <input name="google" id="google" type="text" class="validate" placeholder="请输入谷歌验证码">
                <!--<label for="password">密码</label>-->
            </div>
            </#if>

            <!-- /Usercode -->
            <span><font style="color: red;" id="error_msg"></font></span>
            <button type="button" class="waves-effect waves-light btn-large z-depth-0 z-depth-1-hover" id="loginBtn">登录</button>
        </div>

        <div class="links center-align mt-20 siteBg">
            <a href="#" id="copyRight">${cache_appconfig.baseConfig_siteCopyright}</a>
        </div>
    </form>
    <!-- /Sign In Form -->

</section>



</body>
<script type="text/javascript" src="${ctx}/static/${version}/lib/jquery/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/${version}/js/base/utils/module_md5.js"></script>
<script type="text/javascript" src="${ctx}/static/${version}/lib/rsa/jsencrypt.min.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        $("#img_captcha").on("click",function(){
            $(this).attr("src", _ctx+"/registcode?t=" + new Date().getTime());
        })

		$("#refresh").on("click",function(){
            $("#img_captcha").attr("src", _ctx+"/registcode?t=" + new Date().getTime());
        })


        setTimeout(function(){
            $("#img_captcha").attr("src", _ctx+"/registcode?t=" + new Date().getTime());
        }, 1000);

        $("#loginBtn").on("click",function(){

            var username= $("#username").val();
            if(username==''){
                $("#error_msg").html("用户名不能为空");
                return false;
            }
            var password= $("#password").val();
            if(password==''){
                $("#error_msg").html("密码不能为空");
                return false;
            }
            password = hbmd5(password);
            //rsa
            var encrypt = new JSEncrypt();
            encrypt.setPublicKey($("#publicKey").val());
            //password = encrypt.encrypt(password);


            var google_code = $("#google").val();
            if(google_code==''){
                $("#error_msg").html("谷歌验证码不能为空");
                return false;
            }

            var mobileImgCode=$("#mobileImgCode").val();
            if(mobileImgCode==''){
                $("#error_msg").html("图形验证码不能为空");
                return false;
            }


            $.ajax({
                type : "post",
                url : "${ctx}/checklogin",
                cache : false,
                data : {
                    username : username,
                    password : password,
                    mobileImgCode:mobileImgCode,
                    google_code:google_code
                },
                dataType : "json",
                success : function(data) {
                    $("#img_captcha").click();
                    if(data!=undefined){
                        if(data.success){
                            window.location.href= "${ctx}";
                            if(!window.localStorage){
                                return false;
                            }else{
                                // 每次登录清空 currentMenuPath
                                var storage = window.localStorage;
                                storage.removeItem("menu");
                            }
                        }else{

                            $("#error_msg").html(data.msg);
                            if(data.code=="1000"){
                                window.location.reload();
                            }
                        }
                    }else{
                        $("#error_msg").html("登录错误!")
                    }
                },
                error : function(e) {
                    $("#error_msg").html("请求异常!")
                }
            });
        });

    })

    //回车提交监听
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#loginBtn").trigger("click");
        }
    });
</script>

</html>
