package udp;

import java.io.IOException;
import javax.swing.JFrame;

/**
 * WindowPingPong
 */
public class WindowPingPong extends WindowListener {

  
  public WindowPingPong(int port, String frameName) throws IOException, InterruptedException {
    super(port, frameName);
    this.setWindow();
  }

  public void run(String hostname, int port, int delay) {
    try{
      this.addConnection(hostname, port);
        while(true){
          String lastColor = "red";
          if(lastColor.equals("red")){
            lastColor = "green";
            this.message("green", hostname);
          }

          if(lastColor.equals("green")){
            lastColor = "red";
            this.message("red", hostname);
          }

          Thread.sleep(delay);
          String msg = this.receive();
          if (msg.startsWith("quit")) {
            System.out.println("exiting");
            JFrame frame = this.getFrame();
            frame.dispose();
            break;
          }
          this.setColor(msg, 0);
        }
    } 
    catch (IOException e) {}
    catch (InterruptedException e) {}
  }
}
