package hry.util;

import com.alibaba.druid.util.StringUtils;
import hry.manage.remote.model.User;
import hry.util.shiro.PasswordHelper;

public class CheckPayPassword {

    public  static  boolean checkPayPassword(String password, User user){
        if(StringUtils.isEmpty(password)){
            return  false;
        }
        PasswordHelper passwordHelper = new PasswordHelper();
        String encryAccountPw = passwordHelper.encryString(password, user.getSalt());
        if(!encryAccountPw.equals(user.getAccountPassWord())){
            return  false;
        }
        return  true;
    }
}
