package udp;

import java.awt.Color;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * Television
 */
public class Television extends UdpServer {
  private JFrame frame;

  public Television(int port, String frameName) throws IOException {
    super(port);
    this.frame = new JFrame(frameName);
  }

  private void setWindow() throws InterruptedException {
    this.frame.setSize(300, 300);
    this.frame.getContentPane().setBackground(Color.BLACK);
    this.frame.setVisible(true);
    Thread.sleep(2000);
  }

  private void setColor(String color, int delay) throws InterruptedException{
    if (color.startsWith("red")) this.frame.getContentPane().setBackground(Color.RED);
    if (color.startsWith("green")) this.frame.getContentPane().setBackground(Color.GREEN);
    this.frame.setVisible(true);
    if (delay != 0) Thread.sleep(delay);
  }

  public void run() throws IOException, InterruptedException {
    this.setWindow();
    while(true){
      String message = this.receive();
      if(message.startsWith("quit")){
        this.frame.dispose();
        break;
      }
      this.setColor(message, 0);
    }
  }
}
