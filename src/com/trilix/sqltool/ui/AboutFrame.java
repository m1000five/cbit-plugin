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
import javax.swing.LayoutStyle;

/*    */
/*    */ public class AboutFrame extends JDialog
/*    */ {
	/* 19 */ private final JPanel contentPanel = new JPanel();

	/*    */
	/*    */
	/*    */ public static void main(String[] args)
	/*    */ {
		/*    */ try
		/*    */ {
			/* 26 */ AboutFrame dialog = new AboutFrame();
			/* 27 */ dialog.setDefaultCloseOperation(2);
			/* 28 */ dialog.setVisible(true);
			/*    */ } catch (Exception e) {
			/* 30 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public AboutFrame()
	/*    */ {
		/* 38 */ setTitle("SQL Tool buddy - Information");
		/* 39 */ setBounds(100, 100, 297, 218);
		/* 40 */ getContentPane().setLayout(new BorderLayout());
		/* 41 */ this.contentPanel.setBorder(new javax.swing.border.EmptyBorder(5, 5, 5, 5));
		/* 42 */ getContentPane().add(this.contentPanel, "Center");
		/* 43 */ JLabel lblNewLabel = new JLabel("Version: 1.0");
		/* 44 */ JLabel lblAuthorFranjoStipanovic = new JLabel("Author: Franjo Stipanovic");
		/* 45 */ JLabel lblEmailFritzfsgmailcom = new JLabel("E-mail: fritzfs@gmail.com");
		/* 46 */ JLabel lblTwitterWwwtwittercomfritzfs = new JLabel("Twitter: www.twitter.com/fritzfs");
		/* 47 */ JLabel lblBlogFritzfsblogspotcom = new JLabel("Blog: fritzfs.blogspot.com");
		/* 48 */ JButton btnNewButton = new JButton("Ok, boring ...");
		/* 49 */ btnNewButton.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 51 */ AboutFrame.this.dispose();
				/*    */ }
			/* 53 */ });
		/* 54 */ GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		/* 55 */ gl_contentPanel
				.setHorizontalGroup(
						/* 56 */ gl_contentPanel
								.createParallelGroup(
										GroupLayout.Alignment.LEADING)
								/* 57 */ .addGroup(
										gl_contentPanel.createSequentialGroup()/* 58 */ .addContainerGap()
												/* 59 */ .addGroup(gl_contentPanel
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														/* 60 */ .addComponent(lblNewLabel, -2, 71, -2)
														/* 61 */ .addComponent(lblAuthorFranjoStipanovic)
														/* 62 */ .addComponent(lblEmailFritzfsgmailcom)
														/* 63 */ .addComponent(lblBlogFritzfsblogspotcom)
														/* 64 */ .addComponent(lblTwitterWwwtwittercomfritzfs))
										/* 65 */ .addContainerGap(216, 32767))
				/* 66 */ .addGroup(GroupLayout.Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
						/* 67 */ .addContainerGap(90, 32767)/* 68 */ .addComponent(btnNewButton)/* 69 */ .addGap(84)));
		/*    */
		/* 71 */ gl_contentPanel
				.setVerticalGroup(
						/* 72 */ gl_contentPanel
								.createParallelGroup(
										GroupLayout.Alignment.LEADING)
								/* 73 */ .addGroup(gl_contentPanel.createSequentialGroup()/* 74 */ .addContainerGap()
										/* 75 */ .addComponent(lblNewLabel)
										/* 76 */ .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										/* 77 */ .addComponent(lblAuthorFranjoStipanovic)
										/* 78 */ .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										/* 79 */ .addComponent(lblEmailFritzfsgmailcom)
										/* 80 */ .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										/* 81 */ .addComponent(lblTwitterWwwtwittercomfritzfs)
										/* 82 */ .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										/* 83 */ .addComponent(lblBlogFritzfsblogspotcom)/* 84 */ .addGap(18)
										/* 85 */ .addComponent(btnNewButton)/* 86 */ .addContainerGap(14, 32767)));
		/*    */
		/* 88 */ this.contentPanel.setLayout(gl_contentPanel);
		/*    */
		/* 90 */ setLocationRelativeTo(null);
		/*    */ }
	/*    */ }
