package WebService;
import static WebService.RemoveMovie.data;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
public class GetMovieSchedule extends javax.swing.JFrame {
    private String userID;
    public GetMovieSchedule(String userID) {
        initComponents();
        this.userID = userID;
        jLabel2.setText(userID);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Movie Schedule");
        jLabel2.setText("UserID");
        jButton1.setText("Get My Schedule");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(125, 125, 125)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            String reqForSequencer = "getMovieSchedule," + userID;
            DatagramSocket ds = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("172.20.10.5");
            byte buf[] = null;
            buf = reqForSequencer.getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length, ip, 5005);
            ds.send(packet);
            ds.close();
            DatagramSocket dsReceive = new DatagramSocket(5002);
            byte[] receive = new byte[65535];
            DatagramPacket DpReceive = null;
            DpReceive = new DatagramPacket(receive, receive.length);
            dsReceive.setSoTimeout(5000);
            try {
                dsReceive.receive(DpReceive);
                dsReceive.close();
            } catch (SocketTimeoutException e) {
                dsReceive.close();
                JOptionPane.showMessageDialog(this, "No response received within 5 seconds RM informed");
            }
            String combinedshowList = data(receive).toString();
            String[] showList = combinedshowList.split(",");
            if (showList[0].equals(showList[1])) {
                if (showList[0].replaceAll("[{}]", "").isEmpty()) {
                    LogWritterGeneral(userID, "Booking Schedule For User ID " + userID, "No bookings made");
                    JOptionPane.showMessageDialog(this, "No bookings made for " + userID);
                } else {
                    LogWritterGeneral(userID, "Booking schedule for " + userID, showList[0]);
                    jTextArea1.setText(showList[0]);
                    LogWritterGeneral(userID, "Bookings for " + userID, showList[0]);
                }
            }
            else if (showList[1].equals(showList[2])) {
                if (showList[1].replaceAll("[{}]", "").isEmpty()) {
                    LogWritterGeneral(userID, "Booking Schedule For User ID " + userID, "No bookings made");
                    JOptionPane.showMessageDialog(this, "No bookings made for " + userID);
                } else {
                    LogWritterGeneral(userID, "Booking schedule for " + userID, showList[1]);
                    jTextArea1.setText(showList[1]);
                    LogWritterGeneral(userID, "Bookings for " + userID, showList[1]);
                }
            }
            else if (showList[0].equals(showList[2])) {
                if (showList[0].replaceAll("[{}]", "").isEmpty()) {
                    LogWritterGeneral(userID, "Booking Schedule For User ID " + userID, "No bookings made");
                    JOptionPane.showMessageDialog(this, "No bookings made for " + userID);
                } else {
                    LogWritterGeneral(userID, "Booking schedule for " + userID, showList[0]);
                    jTextArea1.setText(showList[0]);
                    LogWritterGeneral(userID, "Bookings for " + userID, showList[0]);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Error Occured RM informed");
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void LogWritterGeneral(String userID, String id, String value) {
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String directory = "C:\\Users\\Rohan\\Documents\\NetBeansProjects\\WebService_Logs" + date;
        try {
            Path path = Paths.get(directory);
            Files.createDirectories(path);
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }
        String fileName = "C:\\Users\\Rohan\\Documents\\NetBeansProjects\\WebService_Logs" + date + "\\" + userID + ".txt";
        try {
            String datecurr = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
            FileWriter myWriter = new FileWriter(fileName, true);
            myWriter.write("\r\n---------------------" + datecurr + "-----------------------\r\n");
            myWriter.write("Request Type : " + id + "\r\n");
            myWriter.write("Value : " + value + "\r\n");
            myWriter.write("---------------------------------------------------------------\r\n");
            myWriter.close();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
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
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
