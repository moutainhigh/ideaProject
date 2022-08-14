package util;

import hry.util.StringUtil;
import hry.util.md5.Md5Encrypt;

/**
 * @program: hurong_parent2018
 * @Date: 2018/9/17 10:28
 * @Author: LXG
 * @Description:
 */
public class StringUtils {

    //安全参数校验

    public static String authCode(String[] args){
        String auth_code = "";
        if (!containEmpty(args)) {
            return auth_code;
        } else {
            auth_code = StringUtil.stringSort(args, "_");
            auth_code = Md5Encrypt.md5(auth_code);
            return auth_code;
        }
    }

    public static boolean containEmpty(String[] s) {
        if (s != null && s.length > 0) {
            String[] var1 = s;
            int var2 = s.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String l = var1[var3];
                if (l == null || "".equals(l)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}
