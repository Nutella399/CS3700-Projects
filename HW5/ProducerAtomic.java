package cs3700;

public class ProducerAtomic implements Runnable{

    String name;
    ItemAtomic item;

    public ProducerAtomic(ItemAtomic item, String name) {
        this.item = item;
        this.name = name;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            if(!item.put(name + ": " + i)) {
                i--;
            }
        }
        item.put(name + ": Done");
    }
}
