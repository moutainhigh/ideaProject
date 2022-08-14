/**
 * Copyright:
 *
 * @author: javalx
 * @version: V1.0
 * @Date: 2019-06-11 17:55:29
 */
package hry.social.manage.remote.model.task;


import hry.bean.BaseModel;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p> SocialTaskReward </p>
 * @author: javalx
 * @Date :          2019-06-11 17:55:29  
 */
public class SocialTaskType extends BaseModel {


    private String name;//类型名称


    private String pkey;//类型Key


    private String value;//类型标识


    private List<SocialTaskItem> taskList;//类型下任务列表

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<SocialTaskItem> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<SocialTaskItem> taskList) {
        this.taskList = taskList;
    }
}
