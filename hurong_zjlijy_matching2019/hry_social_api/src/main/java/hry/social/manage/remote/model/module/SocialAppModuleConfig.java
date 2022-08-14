/**
 * Copyright:
 *
 * @author: lixin
 * @version: V1.0
 * @Date: 2019-04-11 14:32:20
 */
package hry.social.manage.remote.model.module;


import hry.bean.BaseModel;

import javax.persistence.*;

/**
 * <p> SocialAppModuleConfig </p>
 *
 * @author: lixin
 * @Date :          2019-04-11 14:32:20
 */
@Table(name = "social_app_module_config")
public class SocialAppModuleConfig extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //

    @Column(name = "moduleName")
    private String moduleName;  //功能名称

    @Column(name = "areaName")
    private String areaName;  //国家地区

    @Column(name = "status")
    private String status;  //功能开启状态 0未开启，1已开启

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SocialAppModuleConfig{" + "id=" + id + ", moduleName='" + moduleName + '\'' + ", areaName='" + areaName + '\'' + ", status='" + status + '\'' + '}';
    }
}
