import java.util.HashMap; 

public class rankThread implements Runnable{
  
  Object lock1; 
  Object lock2; 
  int top = 0; 
  char leader = 'P'; 
  char name; 
  int rank = 0; 
  public rankThread(Object lock1, Object lock2) {
    this.lock1 = lock1; 
    this.lock2 = lock2; 
  }
  
  public void run() {
    while(true) {
      synchronized(lock1){
        try {
         lock1.wait();
        } catch(InterruptedException e) {
         if(rank > top) {
           top = rank; 
           leader = name;  
           synchronized(lock2){
            lock2.notifyAll(); 
           }
         }
        }
      }
    } 
  }
  
  public void add(char name, int rank) {
      this.name = name; 
      this.rank = rank;   
  } 
  
  public char give() {
    return leader; 
  }
}

