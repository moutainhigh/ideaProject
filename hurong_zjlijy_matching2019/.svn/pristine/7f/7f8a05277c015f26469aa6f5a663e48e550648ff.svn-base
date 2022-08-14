package hry.minigrobot.robot.model;

import hry.customer.person.model.AppPersonInfo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;


import hry.bean.BaseModel;


import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

    /**
     * <p> ExCustomerRobot </p>
     * @author:         wangz
     * @Date :          2019-01-21 19:11:23
     */
    @Table(name="ex_customer_mining_robot")
    public class ExCustomerMiningRobot extends BaseModel {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", unique = true, nullable = false)
        private Long id;  //

        @Column(name= "customerId")
        private Long customerId;  //用户id

        @Column(name= "coinCode")
        private String coinCode;  //交易币

        @Column(name= "fixPriceCoinCode")
        private String fixPriceCoinCode;  //定价币

        @Column(name ="coinCodeIniMoney")
        private BigDecimal coinCodeIniMoney;//交易币初始金额

        @Column(name ="fixPriceCoinCodeIniMoney")
        private BigDecimal fixPriceCoinCodeIniMoney;//定价币初始金额

        @Column(name="coinCodeCurrMoney")
        private BigDecimal coinCodeCurrMoney;//交易币当前余额

        @Column(name="fixPriceCoinCodeCurrMoney")
        private BigDecimal fixPriceCoinCodeCurrMoney;//定价币当前余额

        @Column(name="robotOrderNum")
        private Integer robotOrderNum;//机器人下单次数

        @Column(name="robotOrderNumed")
        private Integer robotOrderNumed;//机器人已经下单次数
        @Column(name="robotCommissionCharges")
        private BigDecimal robotCommissionCharges;//机器人累计手续费

        @Column(name= "robotStatus")
        private Integer robotStatus;  // 0 初始化 1 开启中  2手动停止 3 系统关闭 4 已完成',

        @Column(name= "isDistributeCharges")
        private Integer isDistributeCharges;  // "0未派发1已派发",

        @Column(name="coinCodeConvertPrice")
        private BigDecimal coinCodeConvertPrice;//交易币对挖矿币的折算

        @Column(name="fixPriceCoinCodeConvertPrice")
        private BigDecimal fixPriceCoinCodeConvertPrice;//定价币对挖矿币的折算

        @Column(name = "distributionAmount")
        private BigDecimal distributionAmount;

        public BigDecimal getDistributionAmount() {
            return distributionAmount;
        }

        public void setDistributionAmount(BigDecimal distributionAmount) {
            this.distributionAmount = distributionAmount;
        }

        public Integer getRobotOrderNumed() {
            return robotOrderNumed;
        }

        public void setRobotOrderNumed(Integer robotOrderNumed) {
            this.robotOrderNumed = robotOrderNumed;
        }

        /**
         * <p></p>
         * @author:  wangz
         * @return:  Long
         * @Date :   2019-01-21 19:11:23
         */
        public Long getId() {
            return id;
        }

        /**
         * <p></p>
         * @author:  wangz
         * @param:   @param id
         * @return:  void
         * @Date :   2019-01-21 19:11:23
         */
        public void setId(Long id) {
            this.id = id;
        }


        /**
         * <p>用户id</p>
         * @author:  wangz
         * @return:  Long
         * @Date :   2019-01-21 19:11:23
         */
        public Long getCustomerId() {
            return customerId;
        }

        /**
         * <p>用户id</p>
         * @author:  wangz
         * @param:   @param customerId
         * @return:  void
         * @Date :   2019-01-21 19:11:23
         */
        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }


        /**
         * <p>交易币</p>
         * @author:  wangz
         * @return:  String
         * @Date :   2019-01-21 19:11:23
         */
        public String getCoinCode() {
            return coinCode;
        }

        /**
         * <p>交易币</p>
         * @author:  wangz
         * @param:   @param coinCode
         * @return:  void
         * @Date :   2019-01-21 19:11:23
         */
        public void setCoinCode(String coinCode) {
            this.coinCode = coinCode;
        }


        /**
         * <p>定价币</p>
         * @author:  wangz
         * @return:  String
         * @Date :   2019-01-21 19:11:23
         */
        public String getFixPriceCoinCode() {
            return fixPriceCoinCode;
        }

        /**
         * <p>定价币</p>
         * @author:  wangz
         * @param:   @param fixPriceCoinCode
         * @return:  void
         * @Date :   2019-01-21 19:11:23
         */
        public void setFixPriceCoinCode(String fixPriceCoinCode) {
            this.fixPriceCoinCode = fixPriceCoinCode;
        }


        /**
         * <p>1 机器人已关闭  2机器人已开启 </p>
         * @author:  wangz
         * @return:  Integer
         * @Date :   2019-01-21 19:11:23
         */
        public Integer getRobotStatus() {
            return robotStatus;
        }

        /**
         * <p>1 机器人已关闭  2机器人已开启 </p>
         * @author:  wangz
         * @param:   @param robotStatus
         * @return:  void
         * @Date :   2019-01-21 19:11:23
         */
        public void setRobotStatus(Integer robotStatus) {
            this.robotStatus = robotStatus;
        }

        public BigDecimal getCoinCodeIniMoney() {
            return coinCodeIniMoney;
        }

        public void setCoinCodeIniMoney(BigDecimal coinCodeIniMoney) {
            this.coinCodeIniMoney = coinCodeIniMoney;
        }

        public BigDecimal getFixPriceCoinCodeIniMoney() {
            return fixPriceCoinCodeIniMoney;
        }

        public void setFixPriceCoinCodeIniMoney(BigDecimal fixPriceCoinCodeIniMoney) {
            this.fixPriceCoinCodeIniMoney = fixPriceCoinCodeIniMoney;
        }

        public BigDecimal getCoinCodeCurrMoney() {
            return coinCodeCurrMoney;
        }

        public void setCoinCodeCurrMoney(BigDecimal coinCodeCurrMoney) {
            this.coinCodeCurrMoney = coinCodeCurrMoney;
        }

        public BigDecimal getFixPriceCoinCodeCurrMoney() {
            return fixPriceCoinCodeCurrMoney;
        }

        public void setFixPriceCoinCodeCurrMoney(BigDecimal fixPriceCoinCodeCurrMoney) {
            this.fixPriceCoinCodeCurrMoney = fixPriceCoinCodeCurrMoney;
        }

        public Integer getRobotOrderNum() {
            return robotOrderNum;
        }

        public void setRobotOrderNum(Integer robotOrderNum) {
            this.robotOrderNum = robotOrderNum;
        }

        public BigDecimal getRobotCommissionCharges() {
            return robotCommissionCharges;
        }

        public void setRobotCommissionCharges(BigDecimal robotCommissionCharges) {
            this.robotCommissionCharges = robotCommissionCharges;
        }

        public Integer getIsDistributeCharges() {
            return isDistributeCharges;
        }

        public void setIsDistributeCharges(Integer isDistributeCharges) {
            this.isDistributeCharges = isDistributeCharges;
        }

        public BigDecimal getCoinCodeConvertPrice() {
            return coinCodeConvertPrice;
        }

        public void setCoinCodeConvertPrice(BigDecimal coinCodeConvertPrice) {
            this.coinCodeConvertPrice = coinCodeConvertPrice;
        }

        public BigDecimal getFixPriceCoinCodeConvertPrice() {
            return fixPriceCoinCodeConvertPrice;
        }

        public void setFixPriceCoinCodeConvertPrice(BigDecimal fixPriceCoinCodeConvertPrice) {
            this.fixPriceCoinCodeConvertPrice = fixPriceCoinCodeConvertPrice;
        }
    }
