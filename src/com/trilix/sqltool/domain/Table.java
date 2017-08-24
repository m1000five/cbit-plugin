package com.trilix.sqltool.domain;

import java.util.List;

public class Table {
	private String name;
	private String schema;
	private List<Column> columns;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchema() {
		return this.schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public List<Column> getColumns() {
		return this.columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	@Override
	public String toString() {
		return this.schema + "." + this.name;
	}
}
