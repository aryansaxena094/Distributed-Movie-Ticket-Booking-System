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
public class CancelMovie extends javax.swing.JFrame {
    private String userID;
    public CancelMovie(String userID) {
        initComponents();
        this.userID = userID;
        jLabel2.setText(userID);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Cancel Movie");
        jLabel2.setText("UserID");
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avatar", "Avengers", "Titanic" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jLabel3.setText("Choose Movie :");
        jLabel4.setText("Movie ID:");
        jLabel5.setText("No Of Tickets To Cancel:");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jButton1.setText("Cancel Ticket");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(154, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(140, 140, 140)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 179, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jButton1)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        pack();
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String movieName = jComboBox1.getSelectedItem().toString();
            String movieID = jTextField1.getText();
            String nooftktstocancel = jTextField2.getText();
            String reqForSequencer = "cancelMovie," + userID + "," + movieID + "," + movieName + "," + nooftktstocancel;
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
            String combinedcancelConfirmation = data(receive).toString();
            String[] cancelConfirmation = combinedcancelConfirmation.split(",");
            LogWritterGeneral(userID, "Cancellation Confirmation", combinedcancelConfirmation);
            if (cancelConfirmation[0].equals(cancelConfirmation[1])) {
                if (cancelConfirmation[0].equals("cancelled")) {
                    JOptionPane.showMessageDialog(this, nooftktstocancel + " tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "Partially Cancelled");
                } else if (cancelConfirmation[0].equals("allcancelled")) {
                    JOptionPane.showMessageDialog(this, "All tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "All Tickets Cancelled");
                } else if (cancelConfirmation[0].equals("cncltktexceed")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No of tickets requested exceeded the number of tickets booked");
                    JOptionPane.showMessageDialog(this, "Number of tickets requested to cancel were more than the number of booked tickets");
                } else if (cancelConfirmation[0].equals("nobooking")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No booking found");
                    JOptionPane.showMessageDialog(this, "No bookings for the entered movie id for Customer ID " + userID);
                }
            }
            else if (cancelConfirmation[2].equals(cancelConfirmation[1])) {
                if (cancelConfirmation[1].equals("cancelled")) {
                    JOptionPane.showMessageDialog(this, nooftktstocancel + " tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "Partially Cancelled");
                } else if (cancelConfirmation[1].equals("allcancelled")) {
                    JOptionPane.showMessageDialog(this, "All tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "All Tickets Cancelled");
                } else if (cancelConfirmation[1].equals("cncltktexceed")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No of tickets requested exceeded the number of tickets booked");
                    JOptionPane.showMessageDialog(this, "Number of tickets requested to cancel were more than the number of booked tickets");
                } else if (cancelConfirmation[1].equals("nobooking")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No booking found");
                    JOptionPane.showMessageDialog(this, "No bookings for the entered movie id for Customer ID " + userID);
                }
            }
            else if (cancelConfirmation[0].equals(cancelConfirmation[2])) {
                if (cancelConfirmation[0].equals("cancelled")) {
                    JOptionPane.showMessageDialog(this, nooftktstocancel + " tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "Partially Cancelled");
                } else if (cancelConfirmation[0].equals("allcancelled")) {
                    JOptionPane.showMessageDialog(this, "All tickets for the movie id " + movieID + " cancelled");
                    LogWritterGeneral(userID, "Cancellation Confirmation", "All Tickets Cancelled");
                } else if (cancelConfirmation[0].equals("cncltktexceed")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No of tickets requested exceeded the number of tickets booked");
                    JOptionPane.showMessageDialog(this, "Number of tickets requested to cancel were more than the number of booked tickets");
                } else if (cancelConfirmation[0].equals("nobooking")) {
                    LogWritterGeneral(userID, "Cancellation Confirmation", "No booking found");
                    JOptionPane.showMessageDialog(this, "No bookings for the entered movie id for Customer ID " + userID);
                }
            }
            else{
                JOptionPane.showMessageDialog(this, "Error Occured RM informed");
            }
        } catch (Exception e) {
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
    /**
     * @param args the command line arguments
     */
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}
