public class washerThread implements Runnable{
  
  Matches match; 
  
  public washerThread(Matches match) {
    this.match = match; 
  }
  
  public void run() {
    String message = match.take();
    while(true) {
      message = match.take();
      if(message.equals("Done")) {
        break; 
      }
      System.out.println("Washer Thread: Destoyed " + message + " socks"); 
    }
  }
}
