/*    */ package com.trilix.sqltool.ui;

/*    */
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.border.EmptyBorder;

/*    */
/*    */ public class InstructionFrame/*    */ extends JDialog
/*    */ {
	/* 20 */ private final JPanel contentPanel = new JPanel();

	/*    */
	/*    */
	/*    */ public static void main(String[] args)
	/*    */ {
		/*    */ try
		/*    */ {
			/* 27 */ InstructionFrame dialog = new InstructionFrame();
			/* 28 */ dialog.setDefaultCloseOperation(2);
			/* 29 */ dialog.setVisible(true);
			/*    */ } catch (Exception e) {
			/* 31 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public InstructionFrame()
	/*    */ {
		/* 39 */ setTitle("SQL Tool budy - Instructions");
		/* 40 */ setBounds(100, 100, 412, 215);
		/* 41 */ getContentPane().setLayout(new BorderLayout());
		/* 42 */ this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		/* 43 */ getContentPane().add(this.contentPanel, "Center");
		/*    */
		/* 45 */ JTextPane txtpnThisToolWill = new JTextPane();
		/* 46 */ txtpnThisToolWill.setEditable(false);
		/* 47 */ txtpnThisToolWill.setText(
				"This tool will build basic stored procedures for your database and save your time in typing. I made this tool because I was bored to death every time I create a new database and start writing CRUD stored procedures ...\r\nOnly SQL Server database is supported. If you have any questions, feel free to contact me. ");
		/*    */
		/* 49 */ JButton btnOkGoHide = new JButton("Ok, enough said");
		/* 50 */ btnOkGoHide.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 52 */ InstructionFrame.this.dispose();
				/*    */ }
			/* 54 */ });
		/* 55 */ GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		/* 56 */ gl_contentPanel
				.setHorizontalGroup(/* 57 */ gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 58 */ .addGroup(gl_contentPanel.createSequentialGroup()/* 59 */ .addGap(129)
								/* 60 */ .addComponent(btnOkGoHide)/* 61 */ .addContainerGap(146, 32767))
				/* 62 */ .addComponent(txtpnThisToolWill, -1, 386, 32767));
		/*    */
		/* 64 */ gl_contentPanel
				.setVerticalGroup(/* 65 */ gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 66 */ .addGroup(GroupLayout.Alignment.TRAILING,
								gl_contentPanel.createSequentialGroup()
										/* 67 */ .addComponent(txtpnThisToolWill, -1, 115, 32767)/* 68 */ .addGap(18)
										/* 69 */ .addComponent(btnOkGoHide)/* 70 */ .addContainerGap()));
		/*    */
		/* 72 */ this.contentPanel.setLayout(gl_contentPanel);
		/*    */
		/* 74 */ setLocationRelativeTo(null);
		/*    */ }
	/*    */ }
