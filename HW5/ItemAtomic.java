package cs3700;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ItemAtomic {

    private int atomicCapcity;
    private AtomicReferenceArray<String> atomicList;
    private AtomicInteger counter;

    public ItemAtomic() {
        atomicCapcity = 8;
        atomicList = new AtomicReferenceArray<String>(10);
        counter = new AtomicInteger(0);
    }

    public String take() {
        while(counter.get() >= 0){
            return atomicList.get(counter.decrementAndGet());
        }
        return "";
    }
    public boolean put(String message){
        if(counter.get() < atomicCapcity) {
            atomicList.set(counter.getAndIncrement(), message);
            return true;
        }
        return false;
    }

    public int atomicLength() {
        return counter.get();
    }
}
