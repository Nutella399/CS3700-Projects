public class sockMatching{
  
  public static void main(String[] args) {
   Sock sock = new Sock(); 
   Sock match = new Sock(); 
   
   Thread red = new Thread(new colorThread("Red", sock));
   Thread green = new Thread(new colorThread("Green", sock));
   Thread blue = new Thread(new colorThread("Blue", sock));
   Thread orange = new Thread(new colorThread("Orange", sock));
   Thread matcher = new Thread(new matchingThread(sock, match)); 
   Thread washer = new Thread(new washerThread(match)); 
   
   red.start(); 
   green.start(); 
   blue.start(); 
   orange.start(); 
   matcher.start(); 
   washer.start(); 
  }
}
