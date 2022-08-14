<meta name="renderer" content="webkit">

<!-- 项目根路径  改为拦截器取值
  #assign ctx=request.getContextPath()
-->
<!-- 时间戳 -->
<#assign t="${.now?long}">
<!-- js版本 -->
<#assign version="dist">
<#assign  fileUrl="http://hry-exchange-public.oss-cn-beijing.aliyuncs.com/">
<#--<#assign  fileUrl="https://10086coin.s3.ap-northeast-2.amazonaws.com/">-->
<#--<#assign  fileUrl="https://xunq.blob.core.windows.net/hry-exchange-public/">-->

<script type="text/JavaScript">
    var _ctx = "${ctx}";
    var _fileUrl = "${fileUrl}";
    var _version="${version}"
</script>

