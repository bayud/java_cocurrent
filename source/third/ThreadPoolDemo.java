package third;

import tasks.MyTask;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    /**
     * 创建线程工厂ThreadFactory
     */
    public static void main(String[] args)throws InterruptedException{
        MyTask task = new MyTask();
        ExecutorService es = new ThreadPoolExecutor(
                5, 5, 0L, TimeUnit.MICROSECONDS,
                new SynchronousQueue<>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //设置成守护线程
                        t.setDaemon(true);
                        System.out.println("create:" + t);
                        return t;
                    }
                }
//                , new RejectedExecutionHandler() {
//                    @Override
//                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                        System.out.println(r.toString()+" is discard");
//                    }
//                }
        );
        for (int i=0;i<6;i++){
            es.submit(task);
        }
        Thread.sleep(10000);
    }
}
