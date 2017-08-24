package com.trilix.sqltool.repository;

import com.trilix.sqltool.domain.Column;
import com.trilix.sqltool.domain.Table;
import java.util.List;
import java.util.Map;

public abstract interface DatabaseDao {
	public abstract void setDataSource(Map<String, String> paramMap);

	public abstract Integer testConnection();

	public abstract List<Table> tableList();

	public abstract List<Column> columnList(String paramString1, String paramString2);
}
