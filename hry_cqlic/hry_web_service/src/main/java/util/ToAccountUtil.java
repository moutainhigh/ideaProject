package util;

import hry.trade.redis.model.Accountadd;
import hry.util.idgenerate.IdGenerate;
import hry.util.idgenerate.NumConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ToAccountUtil {

    private static   String unlockTransactionNum = NumConstant.Ex_Dm_Transaction;

    /**
     * 冻结账户  可用转冻结
     * @param accountId
     * @param money
     * @return
     */
    public static List<Accountadd>  frozenAssets(Long accountId, BigDecimal money){
        List<Accountadd> listLock=new ArrayList<>();
        Accountadd accountaddhot = new Accountadd();
        accountaddhot.setAccountId(accountId);
        accountaddhot.setMoney(money.multiply(new BigDecimal(-1)));
        accountaddhot.setMonteyType(1);
        accountaddhot.setAcccountType(1);
        accountaddhot.setRemarks(102);
        accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

        Accountadd accountaddcold = new Accountadd();
        accountaddcold.setAccountId(accountId);
        accountaddcold.setMoney(money);
        accountaddcold.setMonteyType(2);
        accountaddcold.setAcccountType(1);
        accountaddcold.setRemarks(102);
        accountaddcold.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));
        listLock.add(accountaddcold);
        listLock.add(accountaddhot);
        return listLock;

    }

    /**
     * 解冻账户  冻结转可用
     * @param accountId
     * @param money
     * @return
     */
    public static List<Accountadd>  unblockedAssets(Long accountId, BigDecimal money){
        List<Accountadd> listLock=new ArrayList<>();
        Accountadd accountaddhot = new Accountadd();
        accountaddhot.setAccountId(accountId);
        accountaddhot.setMoney(money);
        accountaddhot.setMonteyType(1);
        accountaddhot.setAcccountType(1);
        accountaddhot.setRemarks(102);
        accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));

        Accountadd accountaddcold = new Accountadd();
        accountaddcold.setAccountId(accountId);
        accountaddcold.setMoney(money.multiply(new BigDecimal(-1)));
        accountaddcold.setMonteyType(2);
        accountaddcold.setAcccountType(1);
        accountaddcold.setRemarks(102);
        accountaddcold.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));
        listLock.add(accountaddcold);
        listLock.add(accountaddhot);
        return listLock;
    }

    /**
     * 支出--扣除可用
     * @param accountId
     * @param money
     * @return
     */
    public static Accountadd  expenditureHotAssets(Long accountId, BigDecimal money){
        Accountadd accountaddhot = new Accountadd();
        accountaddhot.setAccountId(accountId);
        accountaddhot.setMoney(money.multiply(new BigDecimal(-1)));
        accountaddhot.setMonteyType(1);
        accountaddhot.setAcccountType(1);
        accountaddhot.setRemarks(201);
        accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }

    /**
     * 支出--扣除冻结
     * @param accountId
     * @param money
     * @return
     */
    public static Accountadd  expenditureColdAssets(Long accountId, BigDecimal money){
        Accountadd accountaddhot = new Accountadd();
        accountaddhot.setAccountId(accountId);
        accountaddhot.setMoney(money.multiply(new BigDecimal(-1)));
        accountaddhot.setMonteyType(2);
        accountaddhot.setAcccountType(1);
        accountaddhot.setRemarks(201);
        accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }
    /**
     * 收入--进入可用
     * @param accountId
     * @param money
     * @return
     */
    public static Accountadd  ncomeAssets(Long accountId, BigDecimal money){
        Accountadd accountaddhot = new Accountadd();
        accountaddhot.setAccountId(accountId);
        accountaddhot.setMoney(money);
        accountaddhot.setMonteyType(1);
        accountaddhot.setAcccountType(1);
        accountaddhot.setRemarks(202);
        accountaddhot.setTransactionNum(IdGenerate.transactionNum(unlockTransactionNum));
        return accountaddhot;
    }
}
