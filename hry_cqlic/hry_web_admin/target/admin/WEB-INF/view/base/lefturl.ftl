<#include "/base/base.ftl">
<div class="shrinkMenu" id="shrinkMenu">
	 <i class="fa fa-navicon"></i>&nbsp;
	   <span id="shrinkMenu_span">收起左侧菜单</span>
</div>
<div class="shrinkMenuopen" id="shrinkMenuopen" style="display:none;">
	 <i class="fa fa-navicon"></i>&nbsp;
    <span id="shrinkMenuopen_span">展开左侧菜单</span>
</div>
<div class="sidebar-nav navbar-collapse">
	<ul class="nav" id="side-menu" style="overflow:auto;height:600px;">
        <#list menulist as menu >
            <li>
                <a href="JavaScript:void(0);" <#if  menu.url!="" > src="${ctx}${menu.url}" </#if> mkey="${menu.mkey}" >
                    <span class="menuTxt">
                        <i class="${menu.icon}"></i>
                        ${menu.name}
                    </span>
                    <#if  menu.sonmenus ?? && (menu.sonmenus ?size > 0) > <span class="fa arrow"></span> </#if>
                </a>
                <#if  menu.sonmenus ?? && (menu.sonmenus ?size > 0) >
                    <ul class="nav nav-second-level" id="leftClass">
                        <#list menu.sonmenus as menu2 >
                            <li>
                                <a href="JavaScript:void(0);" <#if  menu2.url!="" > src="${ctx}${menu2.url}" </#if> mkey="${menu2.mkey}" pkey="${menu.mkey}">
                                    <i class="${menu2.icon}"></i>
                                    ${menu2.name}
                                    <#if  menu2.sonmenus ?? && (menu2.sonmenus ?size > 0) > <span class="fa arrow"></span> </#if>
                                </a>
                                <#if  menu2.sonmenus ?? && (menu2.sonmenus ?size > 0) >
                                    <ul class="nav nav-third-level">
                                        <#list menu2.sonmenus as menu3 >
                                            <li><a href="JavaScript:void(0)" src="${ctx}${menu3.url}" mkey="${menu3.mkey}" pkey="${menu2.mkey}" ppkey="${menu.mkey}"><i class="${menu3.icon}"></i>${menu3.name}</a></li>
                                        </#list>
                                    </ul>
                                </#if>
                            </li>
                        </#list>
                    </ul>
                </#if>
            </li>
        </#list>
	</ul>
</div>
<script>
$(document).ready(function(){
	$("#shrinkMenu").click(function(){  
    $("#shrinkMenu").css({display:'none'});  
    $("#platform_menu").css({left:'-160px'}); 
    $("#page-wrapperbox").css({left:'0px'});
    $("#shrinkMenuopen").css({display:'block'});      
});  

$("#shrinkMenuopen").click(function(){  
    $("#shrinkMenu").css({display:'block'});  
    $("#platform_menu").css({left:'15px'}); 
    $("#page-wrapperbox").css({left:'200px'});
    $("#shrinkMenuopen").css({display:'none'});      
});
});
$("#leftClass li a").click(function(){
	$("#leftClass li a").removeClass("active");//清除所有id=leftClass中所有a标签中的active
    $(this).addClass("active");//添加一个active
});
</script>