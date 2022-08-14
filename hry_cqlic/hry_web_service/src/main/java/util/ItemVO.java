package util;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

//lzy
public class ItemVO<T> implements Delayed {

    private  long exprieTime;

    private T data;

    public ItemVO(long exprieTime, T data) {
        super();
        this.exprieTime = exprieTime;
        this.data = data;
    }

    public long getExprieTime() {
        return exprieTime;
    }

    public T getData() {
        return data;
    }

    /**
     * 返回激活时间的剩余日期
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        long time=unit.convert(this.exprieTime-System.currentTimeMillis(),unit);
        return time;
    }

    /**
     * Delayed接口继承Comparble接口，按剩余时间排序，剩余最少的拍在前面
     * 计算精度纳秒
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        //getDelay(TimeUnit.NANOSECONDS);//新加入的元素激活剩余日期
      //  o.getDelay(TimeUnit.NANOSECONDS);//当前元素激活剩余日期
        long time=getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);
        int result=(time==0)? 0:((time<0)? -1:1);
        return result;
    }
}
