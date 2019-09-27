package tasks;

public class MyTask implements Runnable {
    @Override
    public void run(){
        long pid = Thread.currentThread().getId();
        System.out.println("这是新的线程:"+ pid);
        for(int i=0;i<100;i++){
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println(e);
            }

            System.out.println("线程"+pid + ",第"+i+"秒");
        }
    }
}
