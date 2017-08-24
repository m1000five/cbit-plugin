package com.trilix.sqltool.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextPane;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.trilix.sqltool.domain.Column;
import com.trilix.sqltool.domain.Table;
import com.trilix.sqltool.repository.DatabaseDao;
import com.trilix.sqltool.repository.DatabaseDaoJdbc;
import com.trilix.sqltool.ui.ErrorFrame;
import com.trilix.sqltool.ui.LoginFrame;
import com.trilix.sqltool.ui.MainFrame;
import com.trilix.sqltool.ui.NotSelectedFrame;
import com.trilix.sqltool.ui.ResultFrame;

public class Application {
	private DatabaseDao databaseDao;
	private LoginFrame loginFrame;
	private MainFrame mainFrame;
	private ResultFrame resultFrame;
	private ErrorFrame errorFrame;
	private NotSelectedFrame notSelectedFrame;
	private static Logger logger = Logger.getLogger("application");

	public List<Column> removePrimaryKey(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			if (c.getIsPrimaryKey().intValue() != 1) {
				columns.add(c);
			}
		}
		return columns;
	}

	public List<Column> removePrimaryKeyAndTime(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			if (c.getIsPrimaryKey().intValue() != 1) {
				if ((!c.getName().equalsIgnoreCase("ctime")) && (!c.getName().equalsIgnoreCase("mtime"))) {
					columns.add(c);
				}
			}
		}
		return columns;
	}

	public List<Column> removePrimaryKeyAndCtime(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			if (c.getIsPrimaryKey().intValue() != 1) {
				if (!c.getName().equalsIgnoreCase("ctime")) {
					columns.add(c);
				}
			}
		}
		return columns;
	}

	public List<Column> removeTime(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			if ((!c.getName().equalsIgnoreCase("ctime")) && (!c.getName().equalsIgnoreCase("mtime"))) {
				columns.add(c);
			}
		}
		return columns;
	}

	public List<Column> prepareValues(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			columns.add((Column) c.clone());
		}
		for (Column c : columns) {
			c.setName("@" + c.getName());
		}
		return columns;
	}

	public List<Column> prepareValuesAndGetDate(List<Column> columnList) {
		List<Column> columns = new ArrayList();
		for (Column c : columnList) {
			columns.add((Column) c.clone());
		}
		for (Column c : columns) {
			if ((c.getName().equalsIgnoreCase("ctime")) || (c.getName().equalsIgnoreCase("mtime"))) {
				c.setName("getdate()");
			} else {
				c.setName("@" + c.getName());
			}
		}
		return columns;
	}

	public Application() {
		this.databaseDao = new DatabaseDaoJdbc();
		this.loginFrame = new LoginFrame();
		this.mainFrame = new MainFrame();
		this.mainFrame.setDatabaseDao(this.databaseDao);

		this.loginFrame.getBtnConnect().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Map<String, String> param = new HashMap();
					param.put("hostname", Application.this.loginFrame.getTxtHostname().getText());
					param.put("db", Application.this.loginFrame.getTxtDatabase().getText());
					param.put("username", Application.this.loginFrame.getTxtUsername().getText());
					param.put("password", new String(Application.this.loginFrame.getTxtPassword().getPassword()));
					Application.this.databaseDao.setDataSource(param);
					Application.this.databaseDao.testConnection();
					Application.this.loginFrame.setVisible(false);
					Application.this.mainFrame.fillTableList();
					Application.this.mainFrame.setVisible(true);
				} catch (Exception e1) {
					Application.this.errorFrame = new ErrorFrame();
					Application.this.errorFrame.setVisible(true);
					Application.logger.error("Error connecting to database", e1);
				}

			}

		});
		this.mainFrame.getBtnGenerateCode().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Application.this.mainFrame.getCbList().getCheckBoxListSelectedValues().length == 0) {
					Application.this.notSelectedFrame = new NotSelectedFrame();
					Application.this.notSelectedFrame.setVisible(true);
				} else {
					Application.this.resultFrame = new ResultFrame();
					List<Object> tableList = Arrays
							.asList(Application.this.mainFrame.getCbList().getCheckBoxListSelectedValues());
					for (Object o : tableList) {
						Table t = (Table) o;
						List<Column> columnList = Application.this.databaseDao.columnList(t.getName(), t.getSchema());
						try {
							Velocity.init();
							VelocityContext vc = new VelocityContext();
							vc.put("schema", t.getSchema());
							vc.put("table", t.getName());
							vc.put("t", t.getName().substring(0, 1).toLowerCase());
							vc.put("columns", columnList);
							vc.put("columnsWithoutPrimaryKey", Application.this.removePrimaryKey(columnList));
							vc.put("columnsWithoutPrimaryKeyAndTime",
									Application.this.removePrimaryKeyAndTime(columnList));
							vc.put("columnsWithoutPrimaryKeyAndCtime",
									Application.this.removePrimaryKeyAndCtime(columnList));
							vc.put("columnsWithoutTime", Application.this.removeTime(columnList));
							vc.put("valuesWithoutPrimaryKey",
									Application.this.prepareValues(Application.this.removePrimaryKey(columnList)));
							vc.put("valuesWithoutPrimaryKeyWithGetDate", Application.this
									.prepareValuesAndGetDate(Application.this.removePrimaryKey(columnList)));

							if (Application.this.mainFrame.getChckbxCreate().isSelected()) {
								Template tpl = Velocity.getTemplate("tpl_Create.txt");
								StringWriter sw = new StringWriter();
								tpl.merge(vc, sw);
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(),
										sw.toString());
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(), "\n\n");
							}
							if (Application.this.mainFrame.getChckbxRead().isSelected()) {
								Template tpl = Velocity.getTemplate("tpl_Read.txt");
								StringWriter sw = new StringWriter();
								tpl.merge(vc, sw);
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(),
										sw.toString());
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(), "\n\n");
							}
							if (Application.this.mainFrame.getChckbxUpdate().isSelected()) {
								Template tpl = Velocity.getTemplate("tpl_Update.txt");
								StringWriter sw = new StringWriter();
								tpl.merge(vc, sw);
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(),
										sw.toString());
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(), "\n\n");
							}
							if (Application.this.mainFrame.getChckbxDelete().isSelected()) {
								Template tpl = Velocity.getTemplate("tpl_Delete.txt");
								StringWriter sw = new StringWriter();
								tpl.merge(vc, sw);
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(),
										sw.toString());
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(), "\n\n");
							}
							if (Application.this.mainFrame.getChckbxList().isSelected()) {
								Template tpl = Velocity.getTemplate("tpl_List.txt");
								StringWriter sw = new StringWriter();
								tpl.merge(vc, sw);
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(),
										sw.toString());
								Application.this.addTextToTextPane(Application.this.resultFrame.getTextPane(), "\n\n");
							}
						} catch (Exception ex) {
							Application.logger.error("Exception:", ex);
						}
					}
					Application.this.resultFrame.setVisible(true);
				}

			}

		});
		this.loginFrame.setVisible(true);
	}

	private void addTextToTextPane(JTextPane textPane, String text) {
		StringBuffer str = new StringBuffer();
		str.append(textPane.getText());
		str.append(text);
		textPane.setText(str.toString());
	}

	public static void main(String[] args) {
		new Application();
	}
}
