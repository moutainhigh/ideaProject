package hry.admin.klg.platform.model;

import hry.klg.model.PlatformAccountadd;
import hry.trade.redis.model.Accountadd;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PlatformAccountUtil {

    private static   String unlockTransactionNum = "KL";

    /**
     * 平台币账户直接转账
     * @param account
     * @param toAccount
     * @param money
     * @return
     */
    public static List<PlatformAccountadd>  ransfer(String account,String toAccount, String money){
        List<PlatformAccountadd> listLock=new ArrayList<>();
        PlatformAccountadd accountAdd = new PlatformAccountadd();//支出账户
        PlatformAccountadd toAccountAdd = new PlatformAccountadd();//收入账户
        String serialNumber=IdGenerate.transactionNum(unlockTransactionNum);
        accountAdd.setAccountType(account);
        accountAdd.setSerialNumber(serialNumber);
        accountAdd.setMoney("-"+money);
        accountAdd.setType(102);// 101 转入102 转出103 充值 等
        toAccountAdd.setAccountType(toAccount);
        toAccountAdd.setSerialNumber(serialNumber);
        toAccountAdd.setMoney(money);
        toAccountAdd.setType(101);// 101 转入102 转出103 充值 等
        listLock.add(accountAdd);
        listLock.add(toAccountAdd);
        return listLock;
    }
   /* *//**
     * 支出--扣除
     * @param accountId
     * @param money
     * @return
     *//*
    public static PlatformAccountadd  accountSub(String account, String money){
        PlatformAccountadd accountaddhot = new PlatformAccountadd();
        accountaddhot.setMoney("-"+money);
        accountaddhot.setAccountType(account);
        accountaddhot.setType(102);
        accountaddhot.setSerialNumber(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }
    *//**
     * 收入--进入
     * @param accountId
     * @param money
     * @return
     *//*
    public static PlatformAccountadd  accountAdd(String account, String money){
        PlatformAccountadd accountaddhot = new PlatformAccountadd();
        accountaddhot.setMoney(money);
        accountaddhot.setAccountType(account);
        accountaddhot.setType(101);
        accountaddhot.setSerialNumber(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }*/
    
    /**
     * 支出--扣除
     * @param accountId
     * @param money
     * @return
     */
    public static PlatformAccountadd  accountSub(String account, String money,String remark){
        PlatformAccountadd accountaddhot = new PlatformAccountadd();
        accountaddhot.setMoney("-"+money);
        accountaddhot.setAccountType(account);
        accountaddhot.setType(102);
        accountaddhot.setRemark(remark==null?"支出":remark);
        accountaddhot.setSerialNumber(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }
    /**
     * 收入--进入
     * @param accountId
     * @param money
     * @return
     */
    public static PlatformAccountadd  accountAdd(String account, String money,String remark){
        PlatformAccountadd accountaddhot = new PlatformAccountadd();
        accountaddhot.setMoney(money);
        accountaddhot.setAccountType(account);
        accountaddhot.setType(101);
        accountaddhot.setRemark(remark==null?"收入":remark);
        accountaddhot.setSerialNumber(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }
}
