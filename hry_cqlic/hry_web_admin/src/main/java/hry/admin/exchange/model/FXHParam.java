package hry.admin.exchange.model;

/*
    请求非小号参数
 */
public class FXHParam {
    private String companyCode;
    private String payUrl;
    private String privateKey;
    private String publicKey;
    private String coinCode;
    private String fixCoin;

    public FXHParam() {
    }

    public FXHParam(String companyCode, String payUrl, String privateKey, String publicKey, String coinCode, String fixCoin) {
        this.companyCode = companyCode;
        this.payUrl = payUrl;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.coinCode = coinCode;
        this.fixCoin = fixCoin;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCoinCode() {
        return coinCode;
    }

    public void setCoinCode(String coinCode) {
        this.coinCode = coinCode;
    }

    public String getFixCoin() {
        return fixCoin;
    }

    public void setFixCoin(String fixCoin) {
        this.fixCoin = fixCoin;
    }
}
