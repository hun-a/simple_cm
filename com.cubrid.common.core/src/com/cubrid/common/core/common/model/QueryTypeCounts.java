package com.cubrid.common.core.common.model;

public class QueryTypeCounts {
	private int selects;
	private int inserts;
	private int updates;
	private int deletes;
	private int creates;
	private int alters;
	private int drops;
	private int extras;

	public boolean existModifyingQuery() {
		return updates > 0 || inserts > 0 || deletes > 0 || creates > 0
				|| alters > 0 || drops > 0;
	}

	public int getSelects() {
		return selects;
	}

	public void setSelects(int selects) {
		this.selects = selects;
	}

	public void increaseSelects() {
		this.selects++;
	}

	public int getInserts() {
		return inserts;
	}

	public void setInserts(int inserts) {
		this.inserts = inserts;
	}

	public void increaseInserts() {
		this.inserts++;
	}

	public int getUpdates() {
		return updates;
	}

	public void setUpdates(int updates) {
		this.updates = updates;
	}

	public void increaseUpdates() {
		this.updates++;
	}

	public int getDeletes() {
		return deletes;
	}

	public void setDeletes(int deletes) {
		this.deletes = deletes;
	}

	public void increaseDeletes() {
		this.deletes++;
	}

	public int getCreates() {
		return creates;
	}

	public void setCreates(int creates) {
		this.creates = creates;
	}

	public void increaseCreates() {
		this.creates++;
	}

	public int getAlters() {
		return alters;
	}

	public void setAlters(int alters) {
		this.alters = alters;
	}

	public void increaseAlters() {
		this.alters++;
	}

	public int getDrops() {
		return drops;
	}

	public void setDrops(int drops) {
		this.drops = drops;
	}

	public void increaseDrops() {
		this.drops++;
	}

	public int getExtras() {
		return extras;
	}

	public void setExtras(int extras) {
		this.extras = extras;
	}

	public void increaseExtras() {
		this.extras++;
	}
}
