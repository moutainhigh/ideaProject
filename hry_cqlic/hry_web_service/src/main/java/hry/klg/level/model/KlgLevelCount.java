/**
 * 111
 */

package hry.klg.level.model;

import java.io.Serializable;

public class KlgLevelCount implements Serializable {

    private Integer count;//计数

    private Integer sort;//等级排序

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
