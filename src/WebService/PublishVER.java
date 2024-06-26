package WebService;
import static WebService.PublishATW.RestartService;
import static WebService.PublishATW.allbookingDetails;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.ws.Endpoint;
public class PublishVER {
    static List<String> users = new ArrayList<String>(999);
    static HashMap<String, Double> moviedetailAvengers = new HashMap<String, Double>();
    static HashMap<String, Double> moviedetailAvatar = new HashMap<String, Double>();
    static HashMap<String, Double> moviedetailtTitanic = new HashMap<String, Double>();
    static HashMap<String, HashMap<String, Double>> allmovieDetails = new HashMap<String, HashMap<String, Double>>();
    static HashMap<String, HashMap<String, Double>> allbookingDetails = new HashMap<String, HashMap<String, Double>>();
    static HashMap<String, Double> cutomerDetails = new HashMap<String, Double>();
    static HashMap<String, Double> movieDetailsToDisplay = new HashMap<String, Double>();
    static HashMap<String, Double> customerDetailsToDisplay = new HashMap<String, Double>();
    static HashMap<String, Double> customerOtherBkgCount = new HashMap<String, Double>();
    static Endpoint endpoint1;
    public static void listMovieShowsForOtherServer(String movieName, String port) throws IOException {
        HashMap<String, Double> movieList = allmovieDetails.get(movieName);
        if (movieList.isEmpty()) {
            movieList.put("0", 0.0);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(movieList);
        byte[] byteData = baos.toByteArray();
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        if (port.equals("ATW")) {
            DatagramPacket packet = new DatagramPacket(byteData, byteData.length, address, 3001);
            socket.send(packet);
            socket.close();
        } else if (port.equals("OUT")) {
            DatagramPacket packet = new DatagramPacket(byteData, byteData.length, address, 3003);
            socket.send(packet);
            socket.close();
        }
    }
    public static void getBookingScheduleforOthers(String customerID, String port) throws IOException {
        HashMap<String, Double> movieList = new HashMap<String, Double>();
        if (!allbookingDetails.isEmpty()) {
            movieList = allbookingDetails.get(customerID);
        } else {
            movieList.put("0", 0.0);
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(movieList);
        byte[] byteData = baos.toByteArray();
        DatagramSocket socket = new DatagramSocket();
        InetAddress address = InetAddress.getByName("localhost");
        if (port.equals("ATW")) {
            DatagramPacket packet = new DatagramPacket(byteData, byteData.length, address, 3004);
            socket.send(packet);
            socket.close();
        } else if (port.equals("OUT")) {
            DatagramPacket packet = new DatagramPacket(byteData, byteData.length, address, 3006);
            socket.send(packet);
            socket.close();
        }
    }
    public static StringBuilder data(byte[] a) {
        if (a == null) {
            return null;
        }
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
    public static void getMovieAvlForOthers(String input, String port) throws SocketException, UnknownHostException, IOException {
        String movieID = input.substring(0, 10);
        String movieName = input.replace(movieID, "").trim();
        String avl = "";
        HashMap<String, Double> movieList = allmovieDetails.get(movieName);
        if (movieList.containsKey(movieID)) {
            avl = "y";
        } else {
            avl = "n";
        }
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;
        buf = avl.getBytes();
        if (port.equals("OUT")) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 3006);
            ds.send(packet);
            ds.close();
        } else if (port.equals("ATW")) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 3004);
            ds.send(packet);
            ds.close();
        }
    }
    public static void StartService(){
        endpoint1 = Endpoint.publish("http://172.20.10.9:8081/movieVER", new VERImpl());
        System.out.println("VER service is published: " + endpoint1.isPublished());
    }
    public static void RestartService(){
        endpoint1.stop();
        endpoint1 = Endpoint.publish("http://172.20.10.9:8081/movieVER", new VERImpl());
        System.out.println("VER service is Restarted: " + endpoint1.isPublished());
    }
    public static void main(String[] args) throws SocketException, IOException, InterruptedException, ClassNotFoundException {
        StartService();
        allmovieDetails.put("Avengers", moviedetailAvengers);
        allmovieDetails.put("Avatar", moviedetailAvatar);
        allmovieDetails.put("Titanic", moviedetailtTitanic);
        DatagramSocket ds = new DatagramSocket(3035);
        byte[] receive = new byte[65535];
        DatagramPacket DpReceive = null;
        while (true) {
            DpReceive = new DatagramPacket(receive, receive.length);
            System.out.println("UDP Waiting!!");
            ds.receive(DpReceive);
            System.out.println("Client:-" + data(receive));
            String rec = data(receive).toString();
            String resultMap = "";
            String inp = "";
            if (rec.substring(0, 4).equals("list")) {
                if (rec.substring(4, 7).equals("ATW")) {
                    inp = rec.replace("listATW", "");
                    listMovieShowsForOtherServer(inp.trim(), "ATW");
                } else if (rec.substring(4, 7).equals("OUT")) {
                    inp = rec.replace("listOUT", "");
                    listMovieShowsForOtherServer(inp.trim(), "OUT");
                }
            } else if (rec.substring(0, 4).equals("bkng")) {
                if (rec.substring(4, 7).equals("ATW")) {
                    inp = rec.replace("bkngATW", "");
                    getBookingScheduleforOthers(inp.trim(), "ATW");
                } else if (rec.substring(4, 7).equals("OUT")) {
                    inp = rec.replace("bkngOUT", "");
                    getBookingScheduleforOthers(inp.trim(), "OUT");
                }
            } else if (rec.substring(0, 6).equals("chkavl")) {
                if (rec.substring(6, 9).equals("OUT")) {
                    inp = rec.replace("chkavlOUT", "");
                    getMovieAvlForOthers(inp.trim(), "OUT");
                } else if (rec.substring(6, 9).equals("ATW")) {
                    inp = rec.replace("chkavlATW", "");
                    getMovieAvlForOthers(inp.trim(), "ATW");
                }
            } else if (rec.substring(0, 2).equals("up")) {
                String custId = rec.substring(2, 10);
                double count = Double.parseDouble(rec.substring(10, 11));
                customerOtherBkgCount.put(custId, count);
            }
            else if (rec.equals("restart")) {
                RestartService();
                String restart = "sendHM";
                DatagramSocket dsRestart = new DatagramSocket();
                InetAddress ip = InetAddress.getByName("172.20.10.5");
                byte buf[] = null;
                buf = restart.getBytes();
                DatagramPacket packet2 = new DatagramPacket(buf, buf.length, ip, 3035);
                dsRestart.send(packet2);
                dsRestart.close();
            } else if (rec.equals("sendHM")) {
                HashMap<String, HashMap<String, Double>> movieList = new HashMap<String, HashMap<String, Double>>();
                movieList.put("Avengers", moviedetailAvengers);
                movieList.put("Avatar", moviedetailAvatar);
                movieList.put("Titanic", moviedetailtTitanic);
                String receiveHM = "receiveHM";
                byte buf[] = null;
                buf = receiveHM.getBytes();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(movieList);                
                byte[] byteData = baos.toByteArray();
                DatagramSocket socket = new DatagramSocket();
                InetAddress address = DpReceive.getAddress();
                DatagramPacket initialPacket = new DatagramPacket(buf, buf.length, address, 3035);
                socket.send(initialPacket);
                Thread.sleep(1000);
                DatagramPacket packet = new DatagramPacket(byteData, byteData.length, address, 6002);
                socket.send(packet);
                socket.close();
            } else if (rec.equals("receiveHM")) {
                HashMap<String, HashMap<String, Double>> data = new HashMap<String, HashMap<String, Double>>();
                DatagramSocket dsHM = new DatagramSocket(6002); 
                byte[] receiveHM = new byte[65535];
                DatagramPacket DpReceiveHM = null;
                while (data.isEmpty()) {
                    DpReceiveHM = new DatagramPacket(receive, receive.length);
                    dsHM.receive(DpReceiveHM);
                    ByteArrayInputStream bais = new ByteArrayInputStream(DpReceiveHM.getData());
                    ObjectInputStream ois = new ObjectInputStream(bais);
                    data = (HashMap<String, HashMap<String, Double>>) ois.readObject();
                    allmovieDetails = data;
                }
                System.out.println("Received HM: "+data.toString()+" from "+DpReceiveHM.getAddress());
                dsHM.close();
            }
            receive = new byte[65535];
        }
    }
}
