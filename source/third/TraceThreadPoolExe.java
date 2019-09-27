package third;

import threadpoolcustom.TraceThreadPoolExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class TraceThreadPoolExe {
    public static void main(String[] args){
        TraceThreadPoolExecutor es = new TraceThreadPoolExecutor(5,5,0L, TimeUnit.MICROSECONDS, new SynchronousQueue<>());

    }

}
