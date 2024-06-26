package WebService;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
public class Login extends javax.swing.JFrame {
    public Login() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); 
        jLabel1.setText("Login");
        jTextField1.setToolTipText("Username");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jLabel2.setText("UserName");
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Single Software Failure");
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jRadioButton1, org.jdesktop.beansbinding.ELProperty.create("${actionCommand}"), jRadioButton1, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Highly Available");
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jRadioButton2, org.jdesktop.beansbinding.ELProperty.create("${actionCommand}"), jRadioButton2, org.jdesktop.beansbinding.BeanProperty.create("actionCommand"));
        bindingGroup.addBinding(binding);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jLabel3.setText("Select Type Of Failure");
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jLabel3))
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addContainerGap(51, Short.MAX_VALUE))
        );
        bindingGroup.bind();
        pack();
    }
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private String checkLoginID(String userID) {
        String typeOfCust = "";
        if (userID.length() == 8) {
            if (userID.substring(0, 3).equals("ATW") || userID.substring(0, 3).equals("OUT") || userID.substring(0, 3).equals("VER")) {
                if (userID.charAt(3) == 'A') {
                    typeOfCust = "A";
                } else if (userID.charAt(3) == 'C') {
                    typeOfCust = "C";
                } else {
                    typeOfCust = "X";
                }
            } else {
                typeOfCust = "X";
            }
        } else {
            typeOfCust = "X";
        }
        return typeOfCust;
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
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int time_out = 0;
            String ssf = "n";
            String typeOfFailure = buttonGroup1.getSelection().getActionCommand();
            if (typeOfFailure.equals("Single Software Failure")) {                
                ssf = "y";
            } else if (typeOfFailure.equals("Highly Available")) {
                time_out = 6000; 
            }
            System.out.println(typeOfFailure);
            URL urlATW = new URL("http:
            QName qNameATW = new QName("http:
            Service serviceATW = Service.create(urlATW, qNameATW);
            IATW atw = serviceATW.getPort(IATW.class);
            URL urlVER = new URL("http:
            QName qNameVER = new QName("http:
            Service serviceVER = Service.create(urlVER, qNameVER);
            IVER ver = serviceVER.getPort(IVER.class);
            URL urlOUT = new URL("http:
            QName qNameOUT = new QName("http:
            Service serviceOUT = Service.create(urlOUT, qNameOUT);
            IOUT out = serviceOUT.getPort(IOUT.class);
            String userID = jTextField1.getText();
            String typeofcust = checkLoginID(userID);
            String userCheckServer = "";
            if (userID.substring(0, 3).equals("ATW")) {
                userCheckServer = atw.checkUserExists(userID);
            } else if (userID.substring(0, 3).equals("VER")) {
                userCheckServer = ver.checkUserExists(userID);
            } else if (userID.substring(0, 3).equals("OUT")) {
                userCheckServer = out.checkUserExists(userID);
            }
            if (!userCheckServer.equals("y")) {
                JOptionPane.showMessageDialog(this, "User ID does not exist!!");
            } else {
                switch (typeofcust) {
                    case "A":
                        LogWritterGeneral(userID, "Login", "Admin");
                        try {
                            LogWritterGeneral(userID, "Admin " + userID, "LoggedIn");
                            Admin adminFrame = new Admin(userID,time_out,ssf);
                            adminFrame.setVisible(true);
                            this.dispose();
                        } catch (Exception e) {
                            LogWritterGeneral(userID, "Exception Occured", e.toString());
                            System.out.println(e);
                            JOptionPane.showMessageDialog(this, e);
                        }
                        break;
                    case "C":
                        LogWritterGeneral(userID, "Login", "Client");
                        try {
                            LogWritterGeneral(userID, "Customer " + userID, "LoggedIn");
                            JOptionPane.showMessageDialog(this, "Customer Logged In");
                            Customer custFrame = new Customer(userID);
                            custFrame.setVisible(true);
                            this.dispose();
                        } catch (Exception e) {
                            LogWritterGeneral(userID, "Exception Occured", e.toString());
                            System.err.println(e);
                        }
                        break;
                    default:
                        LogWritterGeneral(userID, "Login", "Invalid User ID");
                        JOptionPane.showMessageDialog(this, "Invalid Customer ID. Please Try Again");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http:
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
}
