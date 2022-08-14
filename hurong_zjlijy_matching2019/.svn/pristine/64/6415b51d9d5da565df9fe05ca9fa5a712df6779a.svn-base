/**
 * Copyright:   北京互融时代软件有限公司
 *
 * @author: Yuan Zhicheng
 * @version: V1.0
 * @Date: 2015年9月16日 上午11:04:39
 */
package hry.social.manage.remote.model.base;

import java.io.Serializable;

/**
 * @author : javalx
 * @version : V1.0
 * @desc : 封装json结果集
 * @date : 2019/5/31 17:26
 */
public class RemoteResult implements Serializable {

    private Boolean success = false;    // 返回是否成功
    private String msg = "";            // 返回信息
    private Object obj = null;          // 返回其他对象信息
    private String code = "";           //提示代码

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RemoteResult{" + "success=" + success + ", msg='" + msg + '\'' + ", obj=" + obj + ", code='" + code + '\'' + '}';
    }
}
