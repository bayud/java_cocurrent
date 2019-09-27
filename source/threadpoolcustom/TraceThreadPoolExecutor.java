package threadpoolcustom;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExecutor extends ThreadPoolExecutor {
    public TraceThreadPoolExecutor(
            int coreSize,int maxSize,long keepAliveTime,TimeUnit unit,BlockingQueue<Runnable> workqeue
    ){
        super(coreSize,maxSize,keepAliveTime,unit,workqeue);
    }

    @Override
    public void execute(Runnable command) {
        super.execute(wrap(command,clientTrace(),Thread.currentThread().getName()));
    }
    private Exception clientTrace(){
        return new Exception("Client stack here");
    }
    private Runnable wrap(final Runnable task, final Exception clientStack,String clientThreadName) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    task.run();
                } catch (Exception e) {
                    clientStack.printStackTrace();
                    throw e;
                }
            }
        };
    }
}
