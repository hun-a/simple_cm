package com.cubrid.cubridmanager.core.cubrid.table;

import java.sql.Connection;

import com.cubrid.common.core.common.model.SchemaInfo;
import com.cubrid.cubridmanager.core.cubrid.database.model.DatabaseInfo;
import com.cubrid.cubridmanager.core.cubrid.table.task.GetSchemaTask;

public class SchemaProvider {
	protected DatabaseInfo dbInfo;
	protected String tableName;
	protected String errorMessage;

	public SchemaProvider(DatabaseInfo dbInfo, String tableName) {
		this.dbInfo = dbInfo;
		this.tableName = tableName;
	}

	/**
	 * Retrieves the schema of database object.
	 * 
	 * @return schema of table.
	 */
	public SchemaInfo getSchema() {
		GetSchemaTask jdbcTask = new GetSchemaTask(dbInfo, tableName);
		jdbcTask.execute();
		errorMessage = jdbcTask.getErrorMsg();
		return jdbcTask.getSchema();

	}

	/**
	 * Retrieves the schema of database object.
	 * 
	 * @return schema of table.
	 */
	public SchemaInfo getSchema(Connection connection) {
		GetSchemaTask jdbcTask = new GetSchemaTask(connection, dbInfo, tableName);
		jdbcTask.execute();
		errorMessage = jdbcTask.getErrorMsg();
		return jdbcTask.getSchema();

	}
	
	/**
	 * Error messages.
	 * 
	 * @return error messages
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
}
