package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class PingPong {
  private String hostname;
  private int port;

  public PingPong(String hostname, int port){
    this.hostname = hostname;
    this.port = port;
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

  public void execute() throws IOException{
    InetSocketAddress dstaddr = new InetSocketAddress(this.hostname, this.port);
    DatagramSocket socket = new DatagramSocket();

    int losscnt = 0;
    int winscnt = 0;
    for(int i = 1; winscnt < 10; i++){
      System.out.println("===================================");
      System.out.println("Round " + i + " Started!");
      // Start conversation
      String begin = "JOUER";
      byte[] buf_send = begin.getBytes();
      DatagramPacket sent_packet = new DatagramPacket(buf_send, buf_send.length, dstaddr);
      socket.send(sent_packet);
      System.out.println("Sent Packet: " + begin);

      // receive packet
      byte[] buf_received = new byte[2048];
      DatagramPacket received_packet = new DatagramPacket(buf_received, buf_received.length);
      socket.receive(received_packet);
      String message = new String(buf_received, received_packet.getOffset(), received_packet.getLength());
      System.out.println("Packet received: " + message);
      String response = "";

      // respond question
      if(message.equals("PING")) response = "PONG";
      if(message.equals("PONG")) response = "PING";
      byte[] buf_res = response.getBytes();
      System.out.println("Response sent: " + response);
      DatagramPacket res_packet = new DatagramPacket(buf_res, buf_res.length, dstaddr);
      socket.send(res_packet);

      // See the result and increment correct counter :^)
      byte[] buf_result = new byte[2048];
      DatagramPacket result_packet = new DatagramPacket(buf_result, buf_result.length);
      socket.receive(result_packet);
      String result = new String(buf_result, result_packet.getOffset(), result_packet.getLength());
      System.out.println("Result: " + result);

      if(result.equals("GAGNE")) {
        System.out.println("Round Won!");
        winscnt++;
      }
      if(result.equals("PERDU")) {
        System.out.println("Round Lost!");
        losscnt++;
      }
      System.out.println("Round " + i + " Finished!");
      System.out.println("===================================");
    }

    System.out.println("Wins: " + winscnt);
    System.out.println("Losses: " + losscnt);
    socket.close();
    System.out.println("Game Finished 🥳");
  }
}
