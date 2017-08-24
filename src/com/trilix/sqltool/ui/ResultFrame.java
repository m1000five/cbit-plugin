/*    */ package com.trilix.sqltool.ui;

/*    */
/*    */ import java.awt.BorderLayout;

/*    */ import java.awt.Toolkit;
/*    */ import java.awt.datatransfer.Clipboard;
/*    */ import java.awt.datatransfer.StringSelection;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import javax.swing.GroupLayout;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JDialog;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.JTextPane;
/*    */ import javax.swing.border.EmptyBorder;
/*    */
import javax.swing.LayoutStyle;

/*    */ public class ResultFrame extends JDialog
/*    */ {
	/*    */ private JTextPane textPane;
	/* 25 */ private final JPanel contentPanel = new JPanel();

	/*    */
	/*    */
	/*    */ public static void main(String[] args)
	/*    */ {
		/*    */ try
		/*    */ {
			/* 32 */ ResultFrame dialog = new ResultFrame();
			/* 33 */ dialog.setDefaultCloseOperation(2);
			/* 34 */ dialog.setVisible(true);
			/*    */ } catch (Exception e) {
			/* 36 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */
	/*    */
	/*    */ public ResultFrame()
	/*    */ {
		/* 44 */ setTitle("SQL Tool buddy - Results");
		/* 45 */ setBounds(100, 100, 622, 491);
		/* 46 */ getContentPane().setLayout(new BorderLayout());
		/* 47 */ this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		/* 48 */ getContentPane().add(this.contentPanel, "Center");
		/*    */
		/* 50 */ JScrollPane scrollPane = new JScrollPane();
		/*    */
		/* 52 */ JButton btnCopyToClipboard = new JButton("Copy to clipboard");
		/* 53 */ btnCopyToClipboard.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 55 */ StringSelection stringSelection = new StringSelection(ResultFrame.this.textPane.getText());
				/* 56 */ Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				/* 57 */ clipboard.setContents(stringSelection, null);
				/*    */ }
			/*    */
			/* 60 */ });
		/* 61 */ JButton btnClose = new JButton("Close");
		/* 62 */ btnClose.addActionListener(new ActionListener() {
			/*    */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 64 */ ResultFrame.this.dispose();
				/*    */ }
			/* 66 */ });
		/* 67 */ GroupLayout gl_contentPanel = new GroupLayout(this.contentPanel);
		/* 68 */ gl_contentPanel.setHorizontalGroup(/* 69 */ gl_contentPanel
				.createParallelGroup(GroupLayout.Alignment.LEADING)/* 70 */ .addComponent(scrollPane, -1, 596, 32767)
				/* 71 */ .addGroup(gl_contentPanel.createSequentialGroup()/* 72 */ .addGap(168)
						/* 73 */ .addComponent(btnCopyToClipboard)/* 74 */ .addGap(41)/* 75 */ .addComponent(btnClose)
						/* 76 */ .addContainerGap(211, 32767)));
		/*    */
		/* 78 */ gl_contentPanel
				.setVerticalGroup(/* 79 */ gl_contentPanel.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 80 */ .addGroup(gl_contentPanel.createSequentialGroup()
								/* 81 */ .addComponent(scrollPane, -2, 401, -2)
								/* 82 */ .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								/* 83 */ .addGroup(gl_contentPanel.createParallelGroup(GroupLayout.Alignment.BASELINE)
										/* 84 */ .addComponent(btnClose)/* 85 */ .addComponent(btnCopyToClipboard))
						/* 86 */ .addContainerGap(-1, 32767)));
		/*    */
		/*    */
		/* 89 */ this.textPane = new JTextPane();
		/* 90 */ scrollPane.setViewportView(this.textPane);
		/* 91 */ this.contentPanel.setLayout(gl_contentPanel);
		/*    */
		/* 93 */ setLocationRelativeTo(null);
		/*    */ }

	/*    */
	/*    */ public JTextPane getTextPane() {
		/* 97 */ return this.textPane;
		/*    */ }
	/*    */ }
