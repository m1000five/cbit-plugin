package com.trilix.sqltool.domain;

public class Column implements Cloneable {
	private String name;
	private Integer isPrimaryKey;
	private String dataType;
	private Integer length;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsPrimaryKey() {
		return this.isPrimaryKey;
	}

	public void setIsPrimaryKey(Integer isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getLength() {
		return this.length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getNameWithDataType() {
		String tab;
		if (this.name.length() < 3) {
			tab = "\t\t\t\t";
		} else {
			if (this.name.length() < 7) {
				tab = "\t\t\t";
			} else/* 50 */ tab = "\t\t";
		}
		if (this.dataType.endsWith("varchar")) {
			return this.name + tab + this.dataType + "(" + this.length + ")";
		}
		return this.name + tab + this.dataType;
	}

	public String getAsValue(boolean smart) {
		if ((smart) && ((this.name.equalsIgnoreCase("ctime")) || (this.name.equalsIgnoreCase("mtime"))))
			return "getdate()";
		if (this.name.endsWith("()")) {
			return this.name;
		}
		return "@" + this.name;
	}

	@Override
	public String toString() {
		return "Column [name=" + this.name + ", isPrimaryKey=" + this.isPrimaryKey + ", dataType=" + this.dataType
				+ ", length=" + this.length + "]";
	}

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (Exception e) {
		}
		return null;
	}
}
