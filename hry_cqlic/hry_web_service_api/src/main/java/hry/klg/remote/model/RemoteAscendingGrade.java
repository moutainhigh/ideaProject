/**
 * 111
 */

package hry.klg.remote.model;

import java.io.Serializable;

/**
 * 可升等级
 */
public class RemoteAscendingGrade implements Serializable {

    private Long leveId;//可升等级ID;

    private String levelName;//可升等级名称

    public RemoteAscendingGrade() {
    }

    public RemoteAscendingGrade(Long leveId, String levelName) {
        this.leveId = leveId;
        this.levelName = levelName;
    }

    public Long getLeveId() {
        return leveId;
    }

    public void setLeveId(Long leveId) {
        this.leveId = leveId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
