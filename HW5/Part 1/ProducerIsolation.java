package cs3700;

public class ProducerIsolation implements Runnable{

    String name;
    ItemIsolation item;


    public ProducerIsolation(ItemIsolation item, String name) {
        this.item = item;
        this.name = name;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            for (int i = 1; i <= 100; i++) {
                item.put(name + ": " + i);
            }
        }catch(InterruptedException e) {
            System.out.println(e);
        }
        item.put(name + ": Done");
    }

}
