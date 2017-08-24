/*    */ package com.trilix.sqltool.ui;

/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JLabel;
/*    */
/*    */
/*    */ import javax.swing.LayoutStyle;

/*    */
/*    */ public class ErrorFrame/*    */ extends JDialog
/*    */ {
	/*    */ public static void main(String[] args)
	/*    */ {
		/*    */ try
		/*    */ {
			/* 25 */ ErrorFrame dialog = new ErrorFrame();
			/* 26 */ dialog.setDefaultCloseOperation(2);
			/* 27 */ dialog.setVisible(true);
			/*    */ } catch (Exception e) {
			/* 29 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public ErrorFrame()
	/*    */ {
		/* 37 */ setTitle("SQL Tool buddy - error");
		/* 38 */ setBounds(100, 100, 334, 131);
		/* 39 */ JLabel lblErrorWhileConnecting = new JLabel("Error while connecting to your database.");
		/* 40 */ JButton btnNewButton = new JButton("Ok, I'll try");
		/* 41 */ btnNewButton.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 43 */ ErrorFrame.this.dispose();
				/*    */ }
			/*    */
			/* 46 */ });
		/* 47 */ JLabel lblPleaseCheck = new JLabel("Please check your parameters and try again.");
		/* 48 */ GroupLayout groupLayout = new GroupLayout(getContentPane());
		/* 49 */ groupLayout.setHorizontalGroup(/* 50 */ groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				/* 51 */ .addGroup(groupLayout.createSequentialGroup()
						/* 52 */ .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								/* 53 */ .addGroup(groupLayout.createSequentialGroup()/* 54 */ .addContainerGap()
										/* 55 */ .addComponent(lblPleaseCheck))
						/* 56 */ .addGroup(groupLayout.createSequentialGroup()/* 57 */ .addGap(115)
								/* 58 */ .addComponent(btnNewButton))
						/* 59 */ .addGroup(groupLayout.createSequentialGroup()/* 60 */ .addContainerGap()
								/* 61 */ .addComponent(lblErrorWhileConnecting, -2, 286, -2)))
						/* 62 */ .addContainerGap(22, 32767)));
		/*    */
		/* 64 */ groupLayout.setVerticalGroup(/* 65 */ groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				/* 66 */ .addGroup(groupLayout.createSequentialGroup()/* 67 */ .addContainerGap()
						/* 68 */ .addComponent(lblErrorWhileConnecting)
						/* 69 */ .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						/* 70 */ .addComponent(lblPleaseCheck)
						/* 71 */ .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						/* 72 */ .addComponent(btnNewButton)/* 73 */ .addContainerGap(14, 32767)));
		/*    */
		/* 75 */ getContentPane().setLayout(groupLayout);
		/*    */
		/* 77 */ setLocationRelativeTo(null);
		/*    */ }
	/*    */ }
