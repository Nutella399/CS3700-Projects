import java.util.Queue; 
import java.util.LinkedList; 
import java.util.HashMap; 

public class matchingThread implements Runnable{
  
  Sock sock; 
  Sock match;
  public matchingThread(Sock sock, Sock match) {
    this.sock = sock;
    this.match = match; 
  }
  
  public void run() {
    Queue<String> queue = new LinkedList<String>(); 
    String message; 
    HashMap<String, Integer> sockCounter = new HashMap<String, Integer>(); 
    sockCounter.put("Red", 0); 
    sockCounter.put("Blue", 0); 
    sockCounter.put("Green", 0); 
    sockCounter.put("Orange", 0); 
    int totalSocks = 0; 
    int doneCounter = 0;  
    
    while(doneCounter < 4) {
      message = sock.take(); 
      if(!message.equals("Done")) {
        sockCounter.put(message, sockCounter.get(message)+1); 
      }
      if(message.equals("Done")) {
        doneCounter++; 
      } else if(sockCounter.get(message) >= 2) {
        sockCounter.put(message, sockCounter.get(message) - 2); 
        totalSocks += 2; 
        queue.add(message);  
        System.out.println("Matching Thread: Send " + message + " Socks to Washer."
            + "Total socks " + totalSocks + ". Total inside queue " + queue.size()); 
        match.put(queue.poll()); 
      }
    }
    match.put("Done"); 
  }
}
