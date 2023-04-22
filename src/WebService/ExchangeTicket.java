package WebService;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import javax.swing.JOptionPane;
public class ExchangeTicket extends javax.swing.JFrame {
private String userID;
    public ExchangeTicket(String userID) {
        initComponents();
        this.userID = userID;
        jLabel2.setText(userID);
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setText("Exchange Movie Ticket");
        jLabel2.setText("UserID");
        jLabel3.setText("Select Movie To Exchange: ");
        jLabel4.setText("Enter Movie ID to Exchange:");
        jLabel5.setText("Enter No Of Tickets To Exhange:");
        jLabel6.setText("Select New Movie: ");
        jLabel7.setText("Enter Movie ID of New Movie:");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avatar", "Avengers", "Titanic" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Avatar", "Avengers", "Titanic" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jButton1.setText("Exchange Ticket");
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
                .addGap(141, 141, 141)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(26, 26, 26))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(81, 81, 81)
                                .addComponent(jTextField3))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel6))
                                .addGap(67, 67, 67)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox2, 0, 179, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, 179, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField1)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jButton1)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pack();
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try{
        String movieId = jTextField1.getText();
        String movieName = jComboBox1.getSelectedItem().toString();
        String newMovieID = jTextField3.getText();
        String new_movieName = jComboBox2.getSelectedItem().toString();
        String noofexch = jTextField2.getText();
        String reqForSequencer = "bookingExchange," + userID + "," + movieId + "," + movieName+','+newMovieID+','+new_movieName+','+noofexch;
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
        String bookingExhange = data(receive).toString();
                    if (bookingExhange.equals("booked")) {
                        JOptionPane.showMessageDialog(this, noofexch + " Tickets exchanged for Movie " + new_movieName);
                    } else if (bookingExhange.equals("capntavl")) {
                        JOptionPane.showMessageDialog(this, "Entered No of tickets required could not be exchanged for the Movie ");
                    } else if (bookingExhange.equals("otherbkgcntexcd")) {
                        JOptionPane.showMessageDialog(this, "Max limit to book movie in another theatre exhausted for this week.");
                    } 
                     else if (bookingExhange.equals("n")) {
                          JOptionPane.showMessageDialog(this, "Some error occured during booking. Booking could not be completed.");
                     }
                     else if(bookingExhange.equals("doesnotcontain")){
                         JOptionPane.showMessageDialog(this, "No bookings for this customer id.");
                     }
                    else if(bookingExhange.equals("olddoesntexist")){
                         JOptionPane.showMessageDialog(this, "No bookings for this MOVIE id by the customer.");
                     }
        }
        catch(Exception e){}
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
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
}
