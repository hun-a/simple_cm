package com.cubrid.cubridmanager.core.cubrid.table.model;

import java.util.Map;

public class ClassAuthorizations {
	private String className;
	private boolean selectPriv = false;
	private boolean insertPriv = false;
	private boolean updatePriv = false;
	private boolean alterPriv = false;
	private boolean deletePriv = false;
	private boolean indexPriv = false;
	private boolean executePriv = false;
	private boolean grantSelectPriv = false;
	private boolean grantInsertPriv = false;
	private boolean grantUpdatePriv = false;
	private boolean grantAlterPriv = false;
	private boolean grantDeletePriv = false;
	private boolean grantIndexPriv = false;
	private boolean grantExecutePriv = false;
	private boolean allPriv = false;

	public ClassAuthorizations() {

	}
	public ClassAuthorizations(String pName, int authNum) {
		className = pName;

		for (int m1 = 0; m1 < 16; m1++) {
			if ((authNum & (1 << m1)) != 0) {
				switch (m1) {
				case 0:
					selectPriv = true;
					break;
				case 1:
					insertPriv = true;
					break;
				case 2:
					updatePriv = true;
					break;
				case 3:
					deletePriv = true;
					break;
				case 4:
					alterPriv = true;
					break;
				case 5:
					indexPriv = true;
					break;
				case 6:
					executePriv = true;
					break;
				case 8:
					grantSelectPriv = true;
					break;
				case 9:
					grantInsertPriv = true;
					break;
				case 10:
					grantUpdatePriv = true;
					break;
				case 11:
					grantDeletePriv = true;
					break;
				case 12:
					grantAlterPriv = true;
					break;
				case 13:
					grantIndexPriv = true;
					break;
				case 14:
					grantExecutePriv = true;
					break;
				default:

				}
			}
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isSelectPriv() {
		return selectPriv;
	}

	public void setSelectPriv(boolean selectPriv) {
		this.selectPriv = selectPriv;
	}

	public boolean isInsertPriv() {
		return insertPriv;
	}

	public void setInsertPriv(boolean insertPriv) {
		this.insertPriv = insertPriv;
	}

	public boolean isUpdatePriv() {
		return updatePriv;
	}

	public void setUpdatePriv(boolean updatePriv) {
		this.updatePriv = updatePriv;
	}

	public boolean isAlterPriv() {
		return alterPriv;
	}

	public void setAlterPriv(boolean alterPriv) {
		this.alterPriv = alterPriv;
	}

	public boolean isDeletePriv() {
		return deletePriv;
	}

	public void setDeletePriv(boolean deletePriv) {
		this.deletePriv = deletePriv;
	}

	public boolean isIndexPriv() {
		return indexPriv;
	}

	public void setIndexPriv(boolean indexPriv) {
		this.indexPriv = indexPriv;
	}

	public boolean isExecutePriv() {
		return executePriv;
	}

	public void setExecutePriv(boolean executePriv) {
		this.executePriv = executePriv;
	}

	public boolean isGrantSelectPriv() {
		return grantSelectPriv;
	}

	public void setGrantSelectPriv(boolean grantSelectPriv) {
		this.grantSelectPriv = grantSelectPriv;
	}

	public boolean isGrantInsertPriv() {
		return grantInsertPriv;
	}

	public void setGrantInsertPriv(boolean grantInsertPriv) {
		this.grantInsertPriv = grantInsertPriv;
	}

	public boolean isGrantUpdatePriv() {
		return grantUpdatePriv;
	}

	public void setGrantUpdatePriv(boolean grantUpdatePriv) {
		this.grantUpdatePriv = grantUpdatePriv;
	}

	public boolean isGrantAlterPriv() {
		return grantAlterPriv;
	}

	public void setGrantAlterPriv(boolean grantAlterPriv) {
		this.grantAlterPriv = grantAlterPriv;
	}

	public boolean isGrantDeletePriv() {
		return grantDeletePriv;
	}

	public void setGrantDeletePriv(boolean grantDeletePriv) {
		this.grantDeletePriv = grantDeletePriv;
	}

	public boolean isGrantIndexPriv() {
		return grantIndexPriv;
	}

	public void setGrantIndexPriv(boolean grantIndexPriv) {
		this.grantIndexPriv = grantIndexPriv;
	}

	public boolean isGrantExecutePriv() {
		return grantExecutePriv;
	}

	public void setGrantExecutePriv(boolean grantExecutePriv) {
		this.grantExecutePriv = grantExecutePriv;
	}

	public boolean isAllPriv() {
		return allPriv;
	}

	public void setAllPriv(boolean allPriv) {
		this.allPriv = allPriv;
	}

	public boolean isGrantPriv() {
		return grantSelectPriv || grantInsertPriv || grantUpdatePriv || grantAlterPriv || grantDeletePriv
				|| grantIndexPriv || grantExecutePriv;
	}

	public boolean isPriv(String name) { // FIXME more simple
		name = name.toLowerCase();
		if ("select".equals(name)) {
			return grantSelectPriv;
		} else if ("insert".equals(name)) {
			return grantInsertPriv;
		} else if ("update".equals(name)) {
			return grantUpdatePriv;
		} else if ("alter".equals(name)) {
			return grantAlterPriv;
		} else if ("delete".equals(name)) {
			return grantDeletePriv;
		} else if ("index".equals(name)) {
			return grantIndexPriv;
		} else if ("execute".equals(name)) {
			return grantExecutePriv;
		} else if ("grant select".equals(name)) {
			return grantSelectPriv;
		} else if ("grant insert".equals(name)) {
			return grantInsertPriv;
		} else if ("grant update".equals(name)) {
			return grantUpdatePriv;
		} else if ("grant alter".equals(name)) {
			return grantAlterPriv;
		} else if ("grant delete".equals(name)) {
			return grantDeletePriv;
		} else if ("grant index".equals(name)) {
			return grantIndexPriv;
		} else if ("grant execute".equals(name)) {
			return grantExecutePriv;
		} else {
			return false;
		}
	}

	public boolean isRevokePriv(Map<String, Object> privs) { // FIXME more simple

		if ((Boolean) privs.get("1") && !selectPriv) {
			return false;
		}

		if ((Boolean) privs.get("2") && !insertPriv) {
			return false;
		}

		if ((Boolean) privs.get("3") && !updatePriv) {
			return false;
		}

		if ((Boolean) privs.get("4") && !alterPriv) {
			return false;
		}

		if ((Boolean) privs.get("5") && !deletePriv) {
			return false;
		}

		if ((Boolean) privs.get("6") && !indexPriv) {
			return false;
		}

		if ((Boolean) privs.get("7") && !executePriv) {
			return false;
		}
		return true;
	}

}
