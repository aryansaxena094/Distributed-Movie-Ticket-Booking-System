package WebService;
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
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class BookMovie extends javax.swing.JFrame {
    private String userID;
    public BookMovie(String userID) {
        initComponents();
        this.userID = userID;
        jLabel2.setText(userID);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Book Movie");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jButton1.setText("Book Movie");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avatar", "Avengers", "Titanic" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jLabel3.setText("Choose Movie :");
        jLabel4.setText("Enter Date(ddmmyyyy):");
        jLabel5.setText("Choose Slot:");
        jLabel6.setText("Enter Capacity:");
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "A", "E" }));
        jLabel2.setText("UserID");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jLabel1)
                        .addGap(150, 150, 150)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        pack();
    }
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String mid_server = userID.substring(0, 3);
            String movieName = "";
            String movieSlot = "";
            int BookingCapacity = 0;
            String movieID = "";
            String date = new SimpleDateFormat("ddMMyy").format(new Date());
            movieName = jComboBox1.getSelectedItem().toString();
            movieSlot = jComboBox2.getSelectedItem().toString();
            BookingCapacity = Integer.parseInt(jTextField2.getText());
            String dateinp = jTextField1.getText();
            LocalDate startDate = LocalDate.now();
            LocalDate endDate = LocalDate.of(Integer.parseInt(dateinp.substring(4, 8)), Integer.parseInt(dateinp.substring(2, 4)), Integer.parseInt(dateinp.substring(0, 2)));
            Period period = Period.between(startDate, endDate);
            int totalDays = period.getDays();
            LogWritterGeneral(userID, "Date for movie booking", dateinp);
            LogWritterGeneral(userID, "Days between booking and current date", Integer.toString(totalDays));
            if (totalDays > 7) {
                JOptionPane.showMessageDialog(this, "Booking beyond a week not allowed. Retry.");
                LogWritterGeneral(userID, "Booking Beyond Allowed Date.", "Days from current date : " + Integer.toString(totalDays));
                System.exit(0);
            } else if (totalDays < 0) {
                JOptionPane.showMessageDialog(this, "Booking for passed date not allowed. Retry.");
                LogWritterGeneral(userID, "Booking for passed date not allowed.", "Date entered : " + dateinp);
                System.exit(0);
            }
            movieID = mid_server + movieSlot + dateinp.substring(0, 4) + dateinp.substring(6, 8);
            String reqForSequencer = "bookMovie," + userID + "," + movieID + "," + movieName + "," + BookingCapacity;
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
            String combinedbookingConfirmation = data(receive).toString();
            String[] bookingConfirmation = combinedbookingConfirmation.split(",");
            if (bookingConfirmation[0].equals(bookingConfirmation[1])) {
                if (bookingConfirmation[0].equals("booked")) {
                    JOptionPane.showMessageDialog(this, " Tickets booked for Movie " + movieName);
                } else if (bookingConfirmation[0].equals("capntavl")) {
                    JOptionPane.showMessageDialog(this, "Entered No of tickets required are not available for the Movie " + movieName + " for date " + dateinp + " and slot " + movieSlot);
                } else if (bookingConfirmation[0].equals("otherbkgcntexcd")) {
                    JOptionPane.showMessageDialog(this, "Max limit to book movie in another theatre exhausted for this week.");
                } else if (bookingConfirmation[0].equals("notavl")) {
                    JOptionPane.showMessageDialog(this, "Movie Not Found");
                }
            }
            else if (bookingConfirmation[2].equals(bookingConfirmation[1])) {
                if (bookingConfirmation[2].equals("booked")) {
                    JOptionPane.showMessageDialog(this, " Tickets booked for Movie " + movieName);
                } else if (bookingConfirmation[2].equals("capntavl")) {
                    JOptionPane.showMessageDialog(this, "Entered No of tickets required are not available for the Movie " + movieName + " for date " + dateinp + " and slot " + movieSlot);
                } else if (bookingConfirmation[2].equals("otherbkgcntexcd")) {
                    JOptionPane.showMessageDialog(this, "Max limit to book movie in another theatre exhausted for this week.");
                } else if (bookingConfirmation[2].equals("notavl")) {
                    JOptionPane.showMessageDialog(this, "Movie Not Found");
                }
            }
            else if (bookingConfirmation[0].equals(bookingConfirmation[2])) {
                if (bookingConfirmation[0].equals("booked")) {
                    JOptionPane.showMessageDialog(this, " Tickets booked for Movie " + movieName);
                } else if (bookingConfirmation[0].equals("capntavl")) {
                    JOptionPane.showMessageDialog(this, "Entered No of tickets required are not available for the Movie " + movieName + " for date " + dateinp + " and slot " + movieSlot);
                } else if (bookingConfirmation[0].equals("otherbkgcntexcd")) {
                    JOptionPane.showMessageDialog(this, "Max limit to book movie in another theatre exhausted for this week.");
                } else if (bookingConfirmation[0].equals("notavl")) {
                    JOptionPane.showMessageDialog(this, "Movie Not Found");
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
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    /**
     * @param args the command line arguments
     */
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}
