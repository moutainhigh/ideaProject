/**
 * Copyright:
 * @author:      sunyujie
 * @version:     V1.0
 * @Date:        2018-09-28 21:20:08
 */
package hry.admin.web.model;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p> AppFileUpload </p>
 * @author:         sunyujie
 * @Date :          2018-09-28 21:20:08
 */
@Table(name="app_fileupload")
public class AppFileUpload extends BaseModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;  //

    @Column(name= "appType")
    private String appType;  //应用类型     安卓 0     ，1 ios

    @Column(name= "appVersion")
    private String appVersion;  //版本号

    @Column(name= "appCodePath")
    private String appCodePath;  //二维码图片路径

    @Column(name= "appFilePath")
    private String appFilePath;  //app保存地址

    @Column(name= "appStatus")
    private Integer appStatus;  //app状态  1是正在使用的包  0是失效的包

    @Column(name= "operationUser")
    private String operationUser;  //操作人

    @Column(name= "remark")
    private String remark;  //备注   新版本上线




    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  Long
     * @Date :   2018-09-28 21:20:08
     */
    public Long getId() {
        return id;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param id
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setId(Long id) {
        this.id = id;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getAppType() {
        return appType;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param appType
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setAppType(String appType) {
        this.appType = appType;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param appVersion
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getAppCodePath() {
        return appCodePath;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param appCodePath
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setAppCodePath(String appCodePath) {
        this.appCodePath = appCodePath;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getAppFilePath() {
        return appFilePath;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param appFilePath
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  Integer
     * @Date :   2018-09-28 21:20:08
     */
    public Integer getAppStatus() {
        return appStatus;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param appStatus
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setAppStatus(Integer appStatus) {
        this.appStatus = appStatus;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getOperationUser() {
        return operationUser;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param operationUser
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }


    /**
     * <p></p>
     * @author:  sunyujie
     * @return:  String
     * @Date :   2018-09-28 21:20:08
     */
    public String getRemark() {
        return remark;
    }

    /**
     * <p></p>
     * @author:  sunyujie
     * @param:   @param remark
     * @return:  void
     * @Date :   2018-09-28 21:20:08
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }



}
