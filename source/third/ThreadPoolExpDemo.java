package third;

import tasks.NamesTask;

import java.sql.Statement;
import java.util.concurrent.*;

public class ThreadPoolExpDemo {
    static ExecutorService es = new ThreadPoolExecutor(5, 5, 0L,
            TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
//                    String name = ((NamesTask)r).getName();
                    Thread t = new Thread(r);
                    t.setName("线程-"+t.getId());
                    return t;
                }
            }
    ){
        @Override
        protected void beforeExecute(Thread t,Runnable r){
            System.out.println(((NamesTask)r).getName()+"开始执行...");
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            System.out.println("执行完成:"+((NamesTask)r).getName());
        }

        @Override
        protected void terminated() {
            System.out.println("线程池退出");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            es.execute(new NamesTask(String.valueOf(i)));
        }
        es.shutdown();
        //获取可用CPU数量
        int a = Runtime.getRuntime().availableProcessors();
        System.out.println("可用cpu数量："+a);
        // 最优线程大小
        // Ntheads =  Ncpu * Ucpu(cpu使用率0~1)*（1+W/C(等待/计算时间))

    }

}
