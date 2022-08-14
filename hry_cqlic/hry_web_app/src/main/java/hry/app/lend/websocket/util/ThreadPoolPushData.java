package hry.app.lend.websocket.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 * @createTime 2018/7/27 10:30
 * @Description: 首页数据推送
 */
public class ThreadPoolPushData {

    private static final String THREAD_NAME = "trade-lend-Thread-%d";

    private static final Integer POOL_MIN = 20;

    private static final Integer POOL_SIZE = 500;

    private static ThreadPoolExecutor threadPool;

    static {
        threadPool = new ThreadPoolExecutor(POOL_MIN, POOL_SIZE,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new BasicThreadFactory.Builder().namingPattern(THREAD_NAME).build());
    }

    /**
     * 运行线程
     * @param runnable 传入一个new好的线程
     */
    public static void exe(Runnable runnable) {
        threadPool.execute(runnable);
    }

}