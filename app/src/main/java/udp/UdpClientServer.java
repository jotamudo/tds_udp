package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.HashMap;


/**
 * UdpClientServer
 */
public class UdpClientServer {
  private int portSrc;
  private DatagramSocket socketSrv;
  // hostname: connection
  private HashMap<String, Integer> cons = new HashMap<String, Integer>();
  private DatagramPacket lastReceived, lastSent;
  
  // Initialize server
  public UdpClientServer(int portSrc) throws IOException {
    this.portSrc = portSrc;
    this.socketSrv = new DatagramSocket(null);
    this.socketSrv.bind(new InetSocketAddress(this.portSrc));
  }

  public void addConnection(String hostnameDest, int port) throws IOException{
    this.cons.put(hostnameDest, port);
  }

  public void removeConnection(String hostnameDest){
    this.cons.remove(hostnameDest);
  }

  public String receive() throws IOException {
    byte[] readBuf = new byte[2048];
    DatagramPacket readDP = new DatagramPacket(readBuf, readBuf.length);
    this.socketSrv.receive(readDP);
    this.addConnection(readDP.getAddress().toString(), readDP.getPort());
    this.lastReceived = readDP;
    return new String(readBuf, readDP.getOffset(), readDP.getLength());
  }

  public void message(String message, String hostnameDest) throws IOException {
    int port = cons.get(hostnameDest);
    byte[] sendBuf = message.getBytes();
    InetSocketAddress adrDest = new InetSocketAddress(hostnameDest, port);
    DatagramPacket sendDP = new DatagramPacket(sendBuf, sendBuf.length, adrDest);
    this.lastSent = sendDP;
    this.socketSrv.send(sendDP);
  }

  public void messageLast(String message) throws IOException {
    byte[] sendBuf = message.getBytes();
    String hostname = this.lastReceived.getAddress().toString();
    int port = this.lastReceived.getPort();
    InetSocketAddress adrDest = new InetSocketAddress(hostname, port);

    DatagramPacket sendDP = new DatagramPacket(sendBuf, sendBuf.length, adrDest);
    this.socketSrv.send(sendDP);
  }

  public int getPortSrc() {
    return this.portSrc;
  }

  public void setPortSrc(int portSrc) {
    this.portSrc = portSrc;
  }

  public DatagramSocket getSocketSrv() {
    return this.socketSrv;
  }

  public void setSocketSrv(DatagramSocket socketSrv) {
    this.socketSrv = socketSrv;
  }

  public HashMap<String, Integer> getCons() {
    return this.cons;
  }

  public void setCons(HashMap<String, Integer> cons) {
    this.cons = cons;
  }

  public DatagramPacket getLastReceived() {
    return this.lastReceived;
  }

  public void setLastReceived(DatagramPacket lastReceived) {
    this.lastReceived = lastReceived;
  }

  public DatagramPacket getLastSent() {
    return this.lastSent;
  }

  public void setLastSent(DatagramPacket lastSent) {
    this.lastSent = lastSent;
  }
}
