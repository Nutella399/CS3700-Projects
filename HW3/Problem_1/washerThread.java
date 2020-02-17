public class washerThread implements Runnable{
  
  Sock sock; 
  
  public washerThread(Sock sock) {
    this.sock = sock; 
  }
  
  public void run() {
    String message = sock.take();
    while(true) {
      message = sock.take();
      if(message.equals("Done")) {
        break; 
      }
      System.out.println("Washer Thread: Destoyed " + message + " socks"); 
    }
  }
}
