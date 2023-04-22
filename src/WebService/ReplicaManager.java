package WebService;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
public class ReplicaManager {
    public void restartServices0() {
        try {
            AddMovie.time_out = 0;
            AddMovie.ssf = "n";
            String restart = "restart";
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("172.20.10.5");
            byte buf[] = null;
            buf = restart.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 3034);
            ds.send(packet);
            DatagramPacket packet2 = new DatagramPacket(buf, buf.length, ip, 3035);
            ds.send(packet2);
            DatagramPacket packet3 = new DatagramPacket(buf, buf.length, ip, 3036);
            ds.send(packet3);
            ds.close();
            System.out.println("Replica 1 Restarted");
        } catch (Exception e) {
            System.err.println("Error while restarting services: " + e.getMessage());
        }
    }
    public void restartServices1() {
        try {
            AddMovie.time_out = 0;
            AddMovie.ssf = "n";
            String restart = "restart";
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("10.0.0.170");
            byte buf[] = null;
            buf = restart.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 3034);
            ds.send(packet);
            DatagramPacket packet2 = new DatagramPacket(buf, buf.length, ip, 3035);
            ds.send(packet2);
            DatagramPacket packet3 = new DatagramPacket(buf, buf.length, ip, 3036);
            ds.send(packet3);
            ds.close();
            System.out.println("Replica 2 Restarted");
        } catch (Exception e) {
            System.err.println("Error while restarting services: " + e.getMessage());
        }
    }
    public void restartServices2() {
        try {
            AddMovie.time_out = 0;
            AddMovie.ssf = "n";
            String restart = "restart";
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("172.20.10.5");
            byte buf[] = null;
            buf = restart.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 3034);
            ds.send(packet);
            DatagramPacket packet2 = new DatagramPacket(buf, buf.length, ip, 3035);
            ds.send(packet2);
            DatagramPacket packet3 = new DatagramPacket(buf, buf.length, ip, 3036);
            ds.send(packet3);
            ds.close();
            System.out.println("Replica 3 Restarted");
        } catch (Exception e) {
            System.err.println("Error while restarting services: " + e.getMessage());
        }
    }
}
