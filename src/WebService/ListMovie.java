package WebService;
import static WebService.RemoveMovie.data;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class ListMovie extends javax.swing.JFrame {
    private String userID;
    public ListMovie(String userID) {
        initComponents();
        this.userID = userID;
        jLabel2.setText(userID);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("List Of Movie Slots");
        jLabel2.setText("UserID");
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avatar", "Avengers", "Titanic" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jLabel3.setText("Choose Movie :");
        jButton1.setText("Show List");
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
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addGap(42, 42, 42)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );
        pack();
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
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
        }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String movieName = jComboBox1.getSelectedItem().toString();
            String reqForSequencer = "listMovie," + userID + "," + movieName;
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
            System.out.println("Shows For " + movieName);
            if (showList[0].equals(showList[1])) {
            if (showList[0].replaceAll("[{}]", "").isEmpty()) {
                LogWritterGeneral(userID, "List Of Shows For " + movieName, "No shows available");
                JOptionPane.showMessageDialog(this, "No shows available");
            } else {
                jTextArea1.setText(showList[0]);
                LogWritterGeneral(userID, "List Of Shows For " + movieName, showList[0]);
            }}
            else if (showList[1].equals(showList[2])) {
            if (showList[1].replaceAll("[{}]", "").isEmpty()) {
                LogWritterGeneral(userID, "List Of Shows For " + movieName, "No shows available");
                JOptionPane.showMessageDialog(this, "No shows available");
            } else {
                jTextArea1.setText(showList[1]);
                LogWritterGeneral(userID, "List Of Shows For " + movieName, showList[1]);
            }}
            else if (showList[0].equals(showList[2])) {
            if (showList[0].replaceAll("[{}]", "").isEmpty()) {
                LogWritterGeneral(userID, "List Of Shows For " + movieName, "No shows available");
                JOptionPane.showMessageDialog(this, "No shows available");
            } else {
                jTextArea1.setText(showList[0]);
                LogWritterGeneral(userID, "List Of Shows For " + movieName, showList[0]);
            }}
            else{
                JOptionPane.showMessageDialog(this, "Error Occured RM informed");
            }
        } catch (Exception e) {
            System.out.println(e);
             e.printStackTrace();
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
}
