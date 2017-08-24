package com.trilix.sqltool.repository;

import com.trilix.sqltool.domain.Column;
import com.trilix.sqltool.domain.Table;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class DatabaseDaoJdbc/*    */ implements DatabaseDao {
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private JdbcTemplate jdbcTemplate;

	@Override
	public void setDataSource(Map<String, String> param) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl(
				"jdbc:sqlserver://" + param.get("hostname") + ";databaseName=" + param.get("db"));
		dataSource.setUsername(param.get("username"));
		dataSource.setPassword(param.get("password"));
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Integer testConnection() {
		return Integer.valueOf(this.jdbcTemplate.queryForInt("select 1"));
	}

	@Override
	public List<Table> tableList() {
		String sql = "select [name], schema_name(schema_id) as [schema] from sys.tables order by 2, 1";

		return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper(Table.class));
	}

	@Override
	public List<Column> columnList(String table, String schema) {
		String sql = "select  c.[name], is_identity as isPrimaryKey, ty.name as dataType, c.max_length as [length] from sys.columns c inner join sys.tables t on t.object_id = c.object_id inner join sys.types ty on ty.user_type_id = c.user_type_id where t.name = :table and schema_name(t.schema_id) = :schema";

		SqlParameterSource namedParameters = new MapSqlParameterSource()/* 49 */ .addValue("table", table)
				.addValue("schema", schema);
		return this.namedParameterJdbcTemplate.query(sql, namedParameters, new BeanPropertyRowMapper(Column.class));
	}
}
