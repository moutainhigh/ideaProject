package hry.util;

import java.util.HashMap;
import java.util.Map;

public class HryCache {

	/**
     * 电脑端language缓存
     */
    public static Map<String,Map<String,String>> pc_cache_language = new HashMap<String,Map<String,String>>();

    /**
     * 手机端language缓存
     */
    public static Map<String,Map<String,String>> app_cache_language = new HashMap<String,Map<String,String>>();

}
