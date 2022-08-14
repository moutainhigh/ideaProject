package hry.util;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:HelloHeSir@gmail.com">Mr_He</a>
 */
public class ThreadPoolOtc {

    private static final String THREAD_NAME = "Otc-Thread-%d";

    private static final Integer POOL_MIN = 20;

    private static final Integer POOL_SIZE = 1000;

    private static final Long KEEP_ALIVE_TIME = 60*60*1000L;

    private static ThreadPoolExecutor threadPool;

    static {
        threadPool = new ThreadPoolExecutor(POOL_MIN, POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new BasicThreadFactory.Builder().namingPattern(THREAD_NAME).build());
    }

    /**
     * 运行线程
     *
     * @param runnable 传入一个new好的线程
     */
    public static void exe(Runnable runnable) {
        threadPool.execute(runnable);
    }

    /**
     * 运行线程
     *
     * @param runnable 传入一个new好的线程
     */
    public static Future<?> submit(Runnable runnable) {
        return threadPool.submit(runnable);
    }

}
