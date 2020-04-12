package cs3700;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConsumerLock implements Runnable{

    LinkedList<String> list;
    ReentrantLock lock;
    Condition con;
    String name;

    public ConsumerLock(LinkedList<String> list, ReentrantLock lock, Condition con, String name){
        this.list = list;
        this.lock = lock;
        this.con = con;
        this.name = name;
    }

    //lock
    public void run() {
        while(list.size() != 0) {
            lock.lock();
            while(list.size() == 0) {
                try{
                    con.await();
                }catch(InterruptedException e) {
                }
            }
            String next = list.removeFirst();
            //System.out.println(name + ": " + next);
            con.signalAll();
            lock.unlock();
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e) {

            }
        }
    }
}
