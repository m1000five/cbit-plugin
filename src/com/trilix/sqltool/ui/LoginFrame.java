/*     */ package com.trilix.sqltool.ui;

/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;

/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */
/*     */ public class LoginFrame/*     */ extends JFrame
/*     */ {
	/*     */ private JTextField txtHostname;
	/*     */ private JTextField txtDatabase;
	/*     */ private JTextField txtUsername;
	/*     */ private JPasswordField txtPassword;
	/*     */ private JButton btnConnect;
	/*     */ private JButton btnExit;

	/*     */
	/*     */ public static void main(String[] args)
	/*     */ {
		/* 31 */ EventQueue.invokeLater(new Runnable() {
			/*     */ @Override
			public void run() {
				/*     */ try {
					/* 34 */ LoginFrame frame = new LoginFrame();
					/* 35 */ frame.setVisible(true);
					/*     */ } catch (Exception e) {
					/* 37 */ e.printStackTrace();
					/*     */ }
				/*     */ }
			/*     */ });
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public LoginFrame()
	/*     */ {
		/* 47 */ setTitle("SQL Tool buddy - Login");
		/* 48 */ setDefaultCloseOperation(3);
		/* 49 */ setBounds(100, 100, 386, 222);
		/* 50 */ getContentPane().setLayout(null);
		/*     */
		/* 52 */ JLabel lblDatabaseHostname = new JLabel("Database hostname:");
		/* 53 */ lblDatabaseHostname.setBounds(40, 25, 126, 14);
		/* 54 */ getContentPane().add(lblDatabaseHostname);
		/*     */
		/* 56 */ JLabel lblNewLabel = new JLabel("Database name:");
		/* 57 */ lblNewLabel.setBounds(40, 50, 126, 14);
		/* 58 */ getContentPane().add(lblNewLabel);
		/*     */
		/* 60 */ JLabel lblDatabaseUsername = new JLabel("Database username:");
		/* 61 */ lblDatabaseUsername.setBounds(40, 75, 126, 14);
		/* 62 */ getContentPane().add(lblDatabaseUsername);
		/*     */
		/* 64 */ JLabel lblDatabasePassword = new JLabel("Database password:");
		/* 65 */ lblDatabasePassword.setBounds(40, 100, 126, 14);
		/* 66 */ getContentPane().add(lblDatabasePassword);
		/*     */
		/* 68 */ this.txtHostname = new JTextField();
		/* 69 */ this.txtHostname.setText("localhost");
		/* 70 */ this.txtHostname.setBounds(195, 22, 126, 20);
		/* 71 */ getContentPane().add(this.txtHostname);
		/* 72 */ this.txtHostname.setColumns(10);
		/*     */
		/* 74 */ this.txtDatabase = new JTextField();
		/* 75 */ this.txtDatabase.setBounds(195, 47, 126, 20);
		/* 76 */ getContentPane().add(this.txtDatabase);
		/* 77 */ this.txtDatabase.setColumns(10);
		/*     */
		/* 79 */ this.txtUsername = new JTextField();
		/* 80 */ this.txtUsername.setBounds(195, 72, 126, 20);
		/* 81 */ getContentPane().add(this.txtUsername);
		/* 82 */ this.txtUsername.setColumns(10);
		/*     */
		/* 84 */ this.btnConnect = new JButton("Connect");
		/* 85 */ this.btnConnect.setBounds(67, 140, 89, 23);
		/* 86 */ getContentPane().add(this.btnConnect);
		/*     */
		/* 88 */ this.btnExit = new JButton("Exit");
		/* 89 */ this.btnExit.addActionListener(new ActionListener() {
			/*     */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 91 */ System.exit(0);
				/*     */ }
			/* 93 */ });
		/* 94 */ this.btnExit.setBounds(173, 140, 89, 23);
		/* 95 */ getContentPane().add(this.btnExit);
		/*     */
		/* 97 */ this.txtPassword = new JPasswordField();
		/* 98 */ this.txtPassword.setBounds(195, 97, 126, 20);
		/* 99 */ getContentPane().add(this.txtPassword);
		/*     */
		/* 101 */ setLocationRelativeTo(null);
		/*     */ }

	/*     */
	/*     */ public JButton getBtnConnect() {
		/* 105 */ return this.btnConnect;
		/*     */ }

	/*     */
	/*     */ public void setBtnConnect(JButton btnConnect) {
		/* 109 */ this.btnConnect = btnConnect;
		/*     */ }

	/*     */
	/*     */ public JButton getBtnExit() {
		/* 113 */ return this.btnExit;
		/*     */ }

	/*     */
	/*     */ public void setBtnExit(JButton btnExit) {
		/* 117 */ this.btnExit = btnExit;
		/*     */ }

	/*     */
	/*     */ public JTextField getTxtHostname() {
		/* 121 */ return this.txtHostname;
		/*     */ }

	/*     */
	/*     */ public void setTxtHostname(JTextField txtHostname) {
		/* 125 */ this.txtHostname = txtHostname;
		/*     */ }

	/*     */
	/*     */ public JTextField getTxtDatabase() {
		/* 129 */ return this.txtDatabase;
		/*     */ }

	/*     */
	/*     */ public void setTxtDatabase(JTextField txtDatabase) {
		/* 133 */ this.txtDatabase = txtDatabase;
		/*     */ }

	/*     */
	/*     */ public JTextField getTxtUsername() {
		/* 137 */ return this.txtUsername;
		/*     */ }

	/*     */
	/*     */ public void setTxtUsername(JTextField txtUsername) {
		/* 141 */ this.txtUsername = txtUsername;
		/*     */ }

	/*     */
	/*     */ public JPasswordField getTxtPassword() {
		/* 145 */ return this.txtPassword;
		/*     */ }

	/*     */
	/*     */ public void setTxtPassword(JPasswordField txtPassword) {
		/* 149 */ this.txtPassword = txtPassword;
		/*     */ }
	/*     */ }
