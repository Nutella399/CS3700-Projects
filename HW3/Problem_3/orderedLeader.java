public class orderedLeader{
  
  public static void main(String[] args) {
    int n = 2; 
    //String[] names = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"}
    Object lock1 = new Object(); 
    Object lock2 = new Object(); 
    rankThread rank = new rankThread(lock1, lock2);
    Thread rt = new Thread(rank); 
    rt.start(); 
    try{
      Thread one = new Thread(new officialThread('a', lock1, lock2, rank, rt));
      one.start(); 
      Thread.sleep(1000); 
      Thread two = new Thread(new officialThread('b', lock1, lock2, rank, rt));
       two.start();
      Thread.sleep(1000); 
      Thread three = new Thread(new officialThread('c', lock1, lock2, rank, rt));
      three.start(); 
      Thread.sleep(1000); 
      Thread four = new Thread(new officialThread('d', lock1, lock2, rank, rt));
      four.start();
    }catch(InterruptedException e) {
    
    } 
  }
} 
