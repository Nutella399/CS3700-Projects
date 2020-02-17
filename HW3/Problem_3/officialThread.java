import java.util.Random; 

public class officialThread implements Runnable{
  
  Object lock1; 
  Object lock2; 
  char name;
  rankThread rankt;
  Thread rankThread; 
   
  public officialThread(char name, Object lock1, Object lock2, rankThread rankt, Thread rankThread) {
    this.lock1 = lock1;
    this.lock2 = lock2; 
    this.name = name; 
    this.rankt = rankt;
    this.rankThread = rankThread;     
  }
  
  public void run() {
    Random rand = new Random();
    char leaderName = name;
    int rank = rand.nextInt();
    
    try{
      Thread.sleep(1000); 
    }catch(InterruptedException e) {
    
    }
    
    synchronized(lock1){
      
      System.out.println("My name is: " + name + " my rank is: " + rank + 
      " the leader is " + leaderName);
    
      rankt.add(name, rank); 
      rankThread.interrupt();
    }
    
    
    
    while(true){
      synchronized(lock2){  
        try {
          lock2.wait();  
        } catch(InterruptedException e) {
        
        }
        leaderName = rankt.give(); 
        System.out.println("My name is: " + name + " my rank is: " + rank + 
          " the leader is " + leaderName);
      }
    }
  }
}

