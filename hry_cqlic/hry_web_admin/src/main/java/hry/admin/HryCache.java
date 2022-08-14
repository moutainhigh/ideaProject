package hry.admin;

import hry.admin.dic.model.AppDic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 互融云缓存
 */
public class HryCache {

    /**
     * appdic缓存，给hrySelect用
     */
    public static Map<String,List<AppDic>> cache_appdic = new HashMap<String,List<AppDic>>();

    /**
     * 部分appconfig缓存，给页面配置信息用
     */
    public static Map<String,String> cache_appconfig = new HashMap<String, String>();

}
