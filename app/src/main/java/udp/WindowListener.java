package udp;

import java.awt.Color;

import java.io.IOException;

import javax.swing.JFrame;

/**
 * Window
 */
public class WindowListener extends UdpClientServer {
  private JFrame frame;

  public WindowListener(int port, String frameName) throws IOException {
    super(port);
    this.frame = new JFrame(frameName);
  }

  public void setWindow() throws InterruptedException {
    this.frame.setSize(300, 300);
    this.frame.getContentPane().setBackground(Color.BLACK);
    this.frame.setVisible(true);
  }

  public void setColor(String color, int delay) throws InterruptedException{
    if (delay != 0) Thread.sleep(delay);
    if (color.startsWith("red")) this.frame.getContentPane().setBackground(Color.RED);
    if (color.startsWith("green")) this.frame.getContentPane().setBackground(Color.GREEN);
    this.frame.setVisible(true);
  }

  public JFrame getFrame() {
    return this.frame;
  }

  public void setFrame(JFrame frame) {
    this.frame = frame;
  }
}
