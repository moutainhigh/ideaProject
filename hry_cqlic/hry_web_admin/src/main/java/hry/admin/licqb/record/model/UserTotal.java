package hry.admin.licqb.record.model;

public class UserTotal {

    private Integer userTotal;

    private Integer upUserTotal;

    private Integer notUpUserTotal;

    private Integer maxTier;
    
    
    public Integer getUpUserTotal() {
        return upUserTotal;
    }

    public void setUpUserTotal(Integer upUserTotal) {
        this.upUserTotal = upUserTotal;
    }

    public Integer getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(Integer userTotal) {
        this.userTotal = userTotal;
    }

    public Integer getNotUpUserTotal() {
        return notUpUserTotal;
    }

    public void setNotUpUserTotal(Integer notUpUserTotal) {
        this.notUpUserTotal = notUpUserTotal;
    }

    public Integer getMaxTier() {
        return maxTier;
    }

    public void setMaxTier(Integer maxTier) {
        this.maxTier = maxTier;
    }
}
