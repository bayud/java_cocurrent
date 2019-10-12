package forth;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference(19,0);

    static boolean isCharge = false;
    public static void main(String[] args) throws Exception{
        for(int i=0;i<3;i++){
            new Thread(){
                @Override
                public void run(){
                    while (true){
                        while (true){
                            try {
                                Thread.sleep(1000);
                            }catch (InterruptedException e){

                            }
                            Integer m = money.getReference();
                            Integer stamp = money.getStamp();
                            if(m<20&&!isCharge){
                                try {
                                    Thread.sleep(100);
                                }catch (InterruptedException e){

                                }
                                System.out.println("余额小于20,准备充值");
                                if(money.compareAndSet(m,m+20,stamp,stamp+1)){
                                    isCharge = true;
                                    System.out.println("自动余额小于20元，充值20成功，余额："+money.getReference()+"元");
                                    break;
                                }
                            } else {
                                    //System.out.println("无需充值");
                                    break;
                                }
                            }
                        }
                    }
            }.start();
        }

        for(int i=0;i<100;i++) {
            new Thread(){
                @Override
                public void run(){
                    while (true){
                        while (true){
                            try {
                                Thread.sleep(10);
                            }catch (InterruptedException e){

                            }

                            Integer m = money.getReference();
                            Integer stamp = money.getStamp();
                            if(m>25){
                                if(money.compareAndSet(m,19,stamp,stamp+1)){
                                    System.out.println("余额大于25元，消费至19元，余额："+money.getReference()+"元");
                                    break;
                                }
                            }
//                            else if(m<19) {
//                                if(money.compareAndSet(m,19)){
//                                    //System.out.println("手动余额小于10元，充值10元，余额："+money.get()+"元");
//                                    break;
//                                }
//                                //System.out.println("无需充值");
//                                break;
//                            }
                        }
                    }
                }
            }.start();
        }
    }


}
