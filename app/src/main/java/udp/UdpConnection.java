package udp;

import java.io.IOException;
import java.net.DatagramSocket;

/**
 * UdpConnection
 */
public class UdpConnection {
  private DatagramSocket socket;
  private String hostname;
  private int port;

public UdpConnection(String hostname, int port) throws IOException {
    this.socket = new DatagramSocket();
    this.hostname = hostname;
    this.port = port;
  }

  public DatagramSocket getSocket() {
    return socket;
  }

  public void setSocket(DatagramSocket socket) {
    this.socket = socket;
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }
}
