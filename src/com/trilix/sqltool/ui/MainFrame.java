/*     */ package com.trilix.sqltool.ui;

/*     */
/*     */ import com.jidesoft.swing.CheckBoxList;
/*     */ import com.trilix.sqltool.domain.Table;
/*     */ import com.trilix.sqltool.repository.DatabaseDao;
/*     */ import java.awt.EventQueue;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JScrollPane;
/*     */
/*     */ import javax.swing.LayoutStyle;

/*     */ public class MainFrame/*     */ extends JFrame
/*     */ {
	/*     */ private JButton btnGenerateCode;
	/*     */ private CheckBoxList cbList;
	/*     */ private DatabaseDao databaseDao;
	/*     */ private JCheckBox chckbxCreate;
	/*     */ private JCheckBox chckbxRead;
	/*     */ private JCheckBox chckbxUpdate;
	/*     */ private JCheckBox chckbxDelete;
	/*     */ private JCheckBox chckbxList;

	/*     */
	/*     */ public static void main(String[] args)
	/*     */ {
		/* 40 */ EventQueue.invokeLater(new Runnable() {
			/*     */ @Override
			public void run() {
				/*     */ try {
					/* 43 */ LoginFrame loginFrame = new LoginFrame();
					/*     */
					/* 45 */ MainFrame frame = new MainFrame();
					/* 46 */ frame.setVisible(true);
					/*     */ } catch (Exception e) {
					/* 48 */ e.printStackTrace();
					/*     */ }
				/*     */ }
			/*     */ });
		/*     */ }

	/*     */
	/*     */ public void fillTableList() {
		/* 55 */ List<Table> tableList = this.databaseDao.tableList();
		/* 56 */ this.cbList.setListData(tableList.toArray());
		/*     */ }

	/*     */
	/*     */
	/*     */
	/*     */ public MainFrame()
	/*     */ {
		/* 63 */ setTitle("SQL Tool buddy");
		/* 64 */ setDefaultCloseOperation(3);
		/* 65 */ setBounds(100, 100, 597, 556);
		/*     */
		/* 67 */ JMenuBar menuBar = new JMenuBar();
		/* 68 */ setJMenuBar(menuBar);
		/*     */
		/* 70 */ JMenu menuFile = new JMenu("File");
		/* 71 */ menuBar.add(menuFile);
		/*     */
		/* 73 */ JMenuItem menuFileExit = new JMenuItem("Exit");
		/* 74 */ menuFileExit.addActionListener(new ActionListener() {
			/*     */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 76 */ System.exit(0);
				/*     */ }
			/* 78 */ });
		/* 79 */ menuFile.add(menuFileExit);
		/*     */
		/* 81 */ JMenu menuHelp = new JMenu("Help");
		/* 82 */ menuBar.add(menuHelp);
		/*     */
		/* 84 */ JMenuItem menuHelpInstructions = new JMenuItem("Instructions");
		/* 85 */ menuHelpInstructions.addActionListener(new ActionListener() {
			/*     */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 87 */ InstructionFrame instructionFrame = new InstructionFrame();
				/* 88 */ instructionFrame.setVisible(true);
				/*     */ }
			/* 90 */ });
		/* 91 */ menuHelp.add(menuHelpInstructions);
		/*     */
		/* 93 */ JMenuItem menuHelpAbout = new JMenuItem("About");
		/* 94 */ menuHelpAbout.addActionListener(new ActionListener() {
			/*     */ @Override
			public void actionPerformed(ActionEvent e) {
				/* 96 */ AboutFrame aboutFrame = new AboutFrame();
				/* 97 */ aboutFrame.setVisible(true);
				/*     */ }
			/* 99 */ });
		/* 100 */ menuHelp.add(menuHelpAbout);
		/* 101 */ getContentPane().setLayout(null);
		/*     */
		/* 103 */ JScrollPane scrollPane = new JScrollPane();
		/*     */
		/* 105 */ JLabel lblSelectTables = new JLabel("Select tables:");
		/*     */
		/* 107 */ JLabel lblStoredProcedures = new JLabel("Build these stored procedures:");
		/*     */
		/* 109 */ this.chckbxCreate = new JCheckBox("CREATE");
		/* 110 */ this.chckbxCreate.setSelected(true);
		/*     */
		/* 112 */ this.chckbxRead = new JCheckBox("READ");
		/* 113 */ this.chckbxRead.setSelected(true);
		/*     */
		/* 115 */ this.chckbxUpdate = new JCheckBox("UPDATE");
		/* 116 */ this.chckbxUpdate.setSelected(true);
		/*     */
		/* 118 */ this.chckbxDelete = new JCheckBox("DELETE");
		/* 119 */ this.chckbxDelete.setSelected(true);
		/*     */
		/* 121 */ this.chckbxList = new JCheckBox("LIST");
		/* 122 */ this.chckbxList.setSelected(true);
		/*     */
		/* 124 */ this.btnGenerateCode = new JButton("Generate code");
		/*     */
		/* 126 */ GroupLayout groupLayout = new GroupLayout(getContentPane());
		/* 127 */ groupLayout
				.setHorizontalGroup(/* 128 */ groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
						/* 129 */ .addGroup(groupLayout.createSequentialGroup()
								/* 130 */ .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
										/* 131 */ .addGroup(groupLayout.createSequentialGroup()
												/* 132 */ .addContainerGap()
												/* 133 */ .addGroup(groupLayout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														/* 134 */ .addComponent(scrollPane, -1, 561, 32767)
														/* 135 */ .addComponent(lblStoredProcedures)
														/* 136 */ .addComponent(
																lblSelectTables)
														/* 137 */ .addGroup(groupLayout.createSequentialGroup()
																/* 138 */ .addComponent(this.chckbxCreate)
																/* 139 */ .addGap(18)
																/* 140 */ .addComponent(this.chckbxUpdate)
																/* 141 */ .addGap(18)
																/* 142 */ .addComponent(this.chckbxRead)
																/* 143 */ .addGap(18)
																/* 144 */ .addComponent(this.chckbxDelete)
																/* 145 */ .addGap(18)
																/* 146 */ .addComponent(this.chckbxList))))
								/* 147 */ .addGroup(groupLayout.createSequentialGroup()/* 148 */ .addGap(231)
										/* 149 */ .addComponent(this.btnGenerateCode)))
								/* 150 */ .addContainerGap()));
		/*     */
		/* 152 */ groupLayout
				.setVerticalGroup(
						/* 153 */ groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								/* 154 */ .addGroup(
										groupLayout.createSequentialGroup()/* 155 */ .addContainerGap()
												/* 156 */ .addComponent(lblSelectTables)/* 157 */ .addGap(12)
												/* 158 */ .addComponent(scrollPane, -2, 339, -2)
												/* 159 */ .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
												/* 160 */ .addComponent(lblStoredProcedures)
												/* 161 */ .addPreferredGap(
														LayoutStyle.ComponentPlacement.UNRELATED)
												/* 162 */ .addGroup(
														groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																/* 163 */ .addComponent(this.chckbxCreate)
																/* 164 */ .addComponent(this.chckbxUpdate)
																/* 165 */ .addComponent(this.chckbxRead)
																/* 166 */ .addComponent(this.chckbxDelete)
																/* 167 */ .addComponent(this.chckbxList))
						/* 168 */ .addGap(18)/* 169 */ .addComponent(this.btnGenerateCode)
						/* 170 */ .addContainerGap(22, 32767)));
		/*     */
		/*     */
		/* 173 */ this.cbList = new CheckBoxList();
		/* 174 */ scrollPane.setViewportView(this.cbList);
		/* 175 */ getContentPane().setLayout(groupLayout);
		/*     */
		/* 177 */ setLocationRelativeTo(null);
		/*     */ }

	/*     */
	/*     */ public void setDatabaseDao(DatabaseDao databaseDao) {
		/* 181 */ this.databaseDao = databaseDao;
		/*     */ }

	/*     */
	/*     */ public JButton getBtnGenerateCode() {
		/* 185 */ return this.btnGenerateCode;
		/*     */ }

	/*     */
	/*     */ public CheckBoxList getCbList() {
		/* 189 */ return this.cbList;
		/*     */ }

	/*     */
	/*     */ public JCheckBox getChckbxCreate() {
		/* 193 */ return this.chckbxCreate;
		/*     */ }

	/*     */
	/*     */ public JCheckBox getChckbxRead() {
		/* 197 */ return this.chckbxRead;
		/*     */ }

	/*     */
	/*     */ public JCheckBox getChckbxUpdate() {
		/* 201 */ return this.chckbxUpdate;
		/*     */ }

	/*     */
	/*     */ public JCheckBox getChckbxDelete() {
		/* 205 */ return this.chckbxDelete;
		/*     */ }

	/*     */
	/*     */ public JCheckBox getChckbxList() {
		/* 209 */ return this.chckbxList;
		/*     */ }
	/*     */ }
