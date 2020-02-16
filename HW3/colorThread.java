import java.util.Random; 

public class colorThread implements Runnable{
  
  Sock sock; 
  String color; 
  
  public colorThread(String color, Sock sock) {  
    this.color = color; 
    this.sock = sock; 
  }
  
  public void run() {
    int counterSocks = 0; 
    Random rand = new Random(); 
    int maxSocks = rand.nextInt(100);    
    
    while(counterSocks < maxSocks) {
      try {
        Thread.sleep(1000); 
      }catch(InterruptedException e) {
        System.out.println(e);
      }
      counterSocks++;
      sock.put(color); 
      System.out.println(color + "Sock:  Produced " + counterSocks + " of " + 
        maxSocks + " " + color + " Socks" );    
    }
    sock.put("Done");
  }
}
