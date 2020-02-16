public class Sock {

  private String message; 
  private boolean empty; 
  
  public Sock() {
    empty = true; 
  }
  
  public synchronized String take() {
    while(empty) {
      try{
        wait(); 
      } catch (InterruptedException e) {
        System.out.print(e);
      }
    }
    empty = true; 
    notifyAll(); 
    return message; 
  }
  
  public synchronized void put(String message) {
    while(!empty) {
      try{
        wait(); 
      } catch (InterruptedException e) {
        System.out.print(e);
      }
    }
    
    
    this.message = message;  
    notifyAll(); 
    empty = false; 
  }
}
