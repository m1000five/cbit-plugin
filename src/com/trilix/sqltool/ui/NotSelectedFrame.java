/*    */ package com.trilix.sqltool.ui;

/*    */
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */
/*    */import javax.swing.LayoutStyle;

public class NotSelectedFrame extends JDialog
/*    */ {
	/* 19 */ private final JPanel contentPanel = new JPanel();

	/*    */
	/*    */
	/*    */ public static void main(String[] args)
	/*    */ {
		/*    */ try
		/*    */ {
			/* 26 */ NotSelectedFrame dialog = new NotSelectedFrame();
			/* 27 */ dialog.setDefaultCloseOperation(2);
			/* 28 */ dialog.setVisible(true);
			/*    */ } catch (Exception e) {
			/* 30 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public NotSelectedFrame()
	/*    */ {
		/* 38 */ setTitle("SQL Tool buddy - Error");
		/* 39 */ setBounds(100, 100, 304, 123);
		/* 40 */ getContentPane().setLayout(new BorderLayout());
		/* 41 */ this.contentPanel.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
		/* 42 */ getContentPane().add(this.contentPanel, "Center");
		/* 43 */ JLabel lblNoTableSelected = new JLabel("No table selected, nothing to generate!");
		/* 44 */ JButton btnNewButton = new JButton("Ok");
		/* 45 */ btnNewButton.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 47 */ NotSelectedFrame.this.dispose();
				/*    */ }
			/* 49 */ });
		/* 50 */ GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		/* 51 */ gl_contentPanel
				.setHorizontalGroup(/* 52 */ gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 53 */ .addGroup(gl_contentPanel.createSequentialGroup()
								/* 54 */ .addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
										/* 55 */ .addGroup(gl_contentPanel.createSequentialGroup()
												/* 56 */ .addContainerGap()/* 57 */ .addComponent(lblNoTableSelected))
								/* 58 */ .addGroup(gl_contentPanel.createSequentialGroup()/* 59 */ .addGap(95)
										/* 60 */ .addComponent(btnNewButton)))
								/* 61 */ .addContainerGap(78, 32767)));
		/*    */
		/* 63 */ gl_contentPanel
				.setVerticalGroup(/* 64 */ gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 65 */ .addGroup(gl_contentPanel.createSequentialGroup()/* 66 */ .addContainerGap()
								/* 67 */ .addComponent(lblNoTableSelected)
								/* 68 */ .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								/* 69 */ .addComponent(btnNewButton)/* 70 */ .addContainerGap(16, 32767)));
		/*    */
		/* 72 */ this.contentPanel.setLayout(gl_contentPanel);
		/*    */
		/* 74 */ setLocationRelativeTo(null);
		/*    */ }
	/*    */ }
