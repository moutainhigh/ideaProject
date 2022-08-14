package hry.klg.limit.model;

public enum AmountLimitType {
    GrabSheet("1"),//抢单 1
    NewPeople("2"),//新人 2
    Subscribe("3"); //预约 3

    private String key;
    private AmountLimitType( String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
