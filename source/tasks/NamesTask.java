package tasks;

public class NamesTask implements Runnable{
    private String name;

    public String getName() {
        return name;
    }

    public NamesTask(String name){
        this.name = name;
    }
    @Override
    public void run () {
        System.out.println(name+"执行中");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
            System.out.println("中断异常");
        }

    }
}
