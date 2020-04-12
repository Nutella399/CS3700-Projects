package cs3700;

public class ConsumerIsolation implements Runnable{

    ItemIsolation item;
    String name;

    public ConsumerIsolation(ItemIsolation item , String name){
        this.item = item;
        this.name = name;
    }

    public void run() {
        try{
            Thread.sleep(1000);
            while(item.listSize() != 0){
                String next = item.take();
                //System.out.println(name + ": " + next);
                Thread.sleep(1000);
            }
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

}