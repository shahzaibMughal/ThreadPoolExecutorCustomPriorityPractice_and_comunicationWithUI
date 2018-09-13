package ThreadPoolExecutor;

import java.util.concurrent.TimeUnit;

public class ExecutorSupplier {

    private static final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    private PriorityThreadPoolExecutor mForBackgroundTask;
    private static ExecutorSupplier mInstance;

    private ExecutorSupplier() {
        mForBackgroundTask = new PriorityThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS
        );
    }

    public static ExecutorSupplier getsInstance() {
        if(mInstance==null)
        {
            synchronized (ExecutorSupplier.class)
            {
                mInstance = new ExecutorSupplier();
            }
            return mInstance;
        }
        return mInstance;
    }
    public PriorityThreadPoolExecutor getmForBackgroundTask() {
        return mForBackgroundTask;
    }
}
