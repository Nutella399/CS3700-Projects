package cs3700;

public class ConsumerAtomic implements  Runnable{

    ItemAtomic item;
    String name;

    public ConsumerAtomic( ItemAtomic item , String name){
        this.item = item;
        this.name = name;
    }


    public void run() {
        try{
            //Thread.sleep(500);
            while(item.atomicLength() != 0){
                String next = item.take();
                //System.out.println(name + ": " + next);
                Thread.sleep(100);
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

}
