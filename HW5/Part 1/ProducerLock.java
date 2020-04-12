package cs3700;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerLock implements Runnable{

    LinkedList<String> list;
    ReentrantLock lock;
    Condition con;
    String name;


    public ProducerLock(LinkedList<String> list, ReentrantLock lock, Condition con, String name) {
        this.list = list;
        this.lock = lock;
        this.con = con;
        this.name = name;
    }

    public void run() {
        for(int i = 0; i < 100; i++) {
            lock.lock();
            while(list.size() == 10) {
                try{
                   con.await();
                }catch(InterruptedException e) {

                }
            }
            list.add(name + ": " + i);
            con.signalAll();
            lock.unlock();
        }
    }
}
