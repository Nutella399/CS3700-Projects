package cs3700;

import java.util.LinkedList;

public class ItemIsolation {

    private LinkedList<String> list;
    private int capacity;

    public ItemIsolation() {
        capacity = 10;
        list = new LinkedList<String>();
    }

    public synchronized String take() {
        while(list.size() == 0) {
            try{
               wait();
            }catch(InterruptedException e ) {
                System.out.println(e);
            }
        }
        notifyAll();
        return list.removeFirst();
    }

    public synchronized void put(String message){
        while(list.size() == capacity) {
            try{
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        list.add(message);
        notifyAll();
    }


    public int listSize() {
        return list.size();
    }
}

