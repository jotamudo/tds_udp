package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Serveur basique UDP
 */
public class UdpServer {
  private int port;
  private DatagramSocket socket;
  private DatagramPacket lastReceived;

  public UdpServer(int port) throws IOException {
    this.port = port;
    this.socket = new DatagramSocket(null);
    this.socket.bind(new InetSocketAddress(this.port));
  }


  public String receive() throws IOException {
    byte[] bufR = new byte[2048];
    DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    socket.receive(dpR);
    this.lastReceived = dpR;
    return new String(bufR, dpR.getOffset(), dpR.getLength());
  }
  
  public void sendBack(String message, String adress) throws IOException {
    byte[] bufE = message.getBytes();
    DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, 
            this.lastReceived.getAddress(), this.lastReceived.getPort());
    socket.send(dpE);
  }

    // public void execute() throws IOException
    // {
    //     //
    //     System.out.println("Demarrage du serveur ...");
    //
    //     // Le serveur se declare aupres de la couche transport
    //     // sur le port 5099
    //     DatagramSocket socket = new DatagramSocket(null);
    //     socket.bind(new InetSocketAddress(5099));
    //
    //     // Attente du premier message
    //     byte[] bufR = new byte[2048];
    //     DatagramPacket dpR = new DatagramPacket(bufR, bufR.length);
    //     socket.receive(dpR);
    //     String message = new String(bufR, dpR.getOffset(), dpR.getLength());
    //     System.out.println("Message recu = "+message);
    //
    //     // Emission d'un message en retour
    //     byte[] bufE = new String("ok").getBytes();
    //     DatagramPacket dpE = new DatagramPacket(bufE, bufE.length, 
    //             InetAddress.getByName("127.0.0.1"),2000);
    //     socket.send(dpE);
    //     System.out.println("Message envoye = ok");
    //
    //     // Fermeture de la socket
    //     socket.close();
    //     System.out.println("Arret du serveur .");
    // }

        public int getPort() {
                return port;
        }

        public void setPort(int port) {
                this.port = port;
        }

        public DatagramSocket getSocket() {
                return socket;
        }

        public void setSocket(DatagramSocket socket) {
                this.socket = socket;
        }

}
