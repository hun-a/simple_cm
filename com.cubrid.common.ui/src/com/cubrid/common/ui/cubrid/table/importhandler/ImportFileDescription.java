package com.cubrid.common.ui.cubrid.table.importhandler;

import java.util.ArrayList;
import java.util.List;

public final class ImportFileDescription {

	private int totalCount;
	private int sheetNum;
	private final List<String> firstRowCols = new ArrayList<String>();
	private List<Integer> itemsNumberOfSheets;

	public ImportFileDescription() {
		//do nothing.
	}

	public ImportFileDescription(int totalCount, int sheetNum,
			List<String> firstRowCols) {
		this.totalCount = totalCount;
		this.sheetNum = sheetNum;
		setFirstRowCols(firstRowCols);
	}

	/**
	 * Retrieves the total row count of file.
	 * 
	 * @return integer
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * Set total count
	 * 
	 * @param totalCount of integer
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * Get the sheet number.
	 * 
	 * @return integer
	 */
	public int getSheetNum() {
		return sheetNum;
	}

	/**
	 * Set sheet number.
	 * 
	 * @param sheetNum of integer.
	 */
	public void setSheetNum(int sheetNum) {
		this.sheetNum = sheetNum;
	}

	/**
	 * Retrieves the first row.
	 * 
	 * @return values of first row
	 */
	public List<String> getFirstRowCols() {
		return firstRowCols;
	}

	/**
	 * Set first row columns
	 * 
	 * @param firstRowCols of string list.
	 */
	public void setFirstRowCols(List<String> firstRowCols) {
		this.firstRowCols.clear();
		if (firstRowCols != null) {
			this.firstRowCols.addAll(firstRowCols);
		}
	}

	/**
	 * Get the itemsNumberOfSheets
	 * 
	 * @return the itemsNumberOfSheets
	 */
	public List<Integer> getItemsNumberOfSheets() {
		return itemsNumberOfSheets;
	}

	/**
	 * @param itemsNumberOfSheets the itemsNumberOfSheets to set
	 */
	public void setItemsNumberOfSheets(List<Integer> itemsNumberOfSheets) {
		this.itemsNumberOfSheets = itemsNumberOfSheets;
	}

}
