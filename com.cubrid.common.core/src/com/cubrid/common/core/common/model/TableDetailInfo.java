package com.cubrid.common.core.common.model;

import static com.cubrid.common.core.util.NoOp.noOp;

import java.math.BigDecimal;

public class TableDetailInfo implements
Comparable<TableDetailInfo> { // FIXME add copyright and add descriptions for all methods
	private final int RECORD_UNCOUNT = -1;
	private String tableName;
	private String tableDesc;
	private long recordsCount = RECORD_UNCOUNT;
	private int columnsCount;
	private int pkCount;
	private int ukCount;
	private int fkCount;
	private int indexCount;
	private BigDecimal recordsSize;
	private boolean hasUnCountColumnSize;
	private String classType;
	private String partitioned;

	public TableDetailInfo() {
		noOp();
	}

	public TableDetailInfo(String tableName, String tableDesc, int recordsCount, int columnsCount,
			int pkCount, int ukCount, int fkCount, int indexCount, BigDecimal recordsSize,
			boolean hasUnCountColumnSize) {
		this.tableName = tableName;
		this.tableDesc = tableDesc;
		this.recordsCount = recordsCount;
		this.columnsCount = columnsCount;
		this.pkCount = pkCount;
		this.ukCount = ukCount;
		this.fkCount = fkCount;
		this.indexCount = indexCount;
		this.recordsSize = recordsSize;
		this.hasUnCountColumnSize = hasUnCountColumnSize;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public long getRecordsCount() {
		return recordsCount;
	}

	public void setRecordsCount(long recordsCount) {
		this.recordsCount = recordsCount;
	}

	public int getColumnsCount() {
		return columnsCount;
	}

	public void setColumnsCount(int columnsCount) {
		this.columnsCount = columnsCount;
	}

	public int getPkCount() {
		return pkCount;
	}

	public void setPkCount(int pkCount) {
		this.pkCount = pkCount;
	}

	public int getUkCount() {
		return ukCount;
	}

	public void setUkCount(int ukCount) {
		this.ukCount = ukCount;
	}

	public int getFkCount() {
		return fkCount;
	}

	public void setFkCount(int fkCount) {
		this.fkCount = fkCount;
	}

	public int getIndexCount() {
		return indexCount;
	}

	public void setIndexCount(int indexCount) {
		this.indexCount = indexCount;
	}

	public BigDecimal getRecordsSize() {
		return recordsSize;
	}

	public void setRecordsSize(BigDecimal recordsSize) {
		this.recordsSize = recordsSize;
	}

	public boolean isHasUnCountColumnSize() {
		return hasUnCountColumnSize;
	}

	public void setHasUnCountColumnSize(boolean hasUnCountColumnSize) {
		this.hasUnCountColumnSize = hasUnCountColumnSize;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public String getClassType() {
		return classType;
	}

	public void setPartitioned(String partitioned) {
		this.partitioned = partitioned;
	}

	public String getPartitioned() {
		return partitioned;
	}

	/**
	 * @param obj TableDetailInfo the given object to compare
	 * @return int the value 0 if the name of argument obj is equal to class
	 *         name; a value less than 0 if the class name is lexicographically
	 *         less than the name of argument obj; and a value greater than 0 if
	 *         the class name is lexicographically greater than the name of
	 *         argument obj.
	 */
	public int compareTo(TableDetailInfo obj) {
		return tableName.compareTo(obj.tableName);
	}

	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Copy all the attribute from source to target
	 *
	 * @param source
	 * @param target
	 */
	public static void copyAllAttribute(TableDetailInfo source, TableDetailInfo target) {
		if (source != null && target != null) {
			target.setTableName(source.getTableName());
			target.setClassType(source.getClassType());
			target.setColumnsCount(source.getColumnsCount());
			target.setFkCount(source.getFkCount());
			target.setHasUnCountColumnSize(source.isHasUnCountColumnSize());
			target.setIndexCount(source.getIndexCount());
			target.setPartitioned(source.getPartitioned());
			target.setPkCount(source.getPkCount());
			target.setRecordsCount(source.getRecordsCount());
			target.setRecordsSize(source.getRecordsSize());
			target.setTableDesc(source.getTableDesc());
			target.setUkCount(source.getUkCount());
		}
	}
}
