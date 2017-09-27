package com.cubrid.common.ui.er.commands;

import org.eclipse.gef.commands.Command;

import com.cubrid.common.core.util.StringUtil;
import com.cubrid.common.ui.er.directedit.ColumnLabelHandler;
import com.cubrid.common.ui.er.model.ERTableColumn;
import com.cubrid.common.ui.spi.util.CommonUITool;
import com.cubrid.cubridmanager.core.cubrid.table.model.DataType;

public class ModifyColumnLabelCommand extends
Command {
	private ERTableColumn source;
	private String newName, oldName;
	private String newType, oldType;
	private String changedLogicalShowType = null;
	private String changedPhysicalShowType = null;
	private ColumnLabelHandler colHandler;
	private boolean isPhysical;

	public ModifyColumnLabelCommand(ERTableColumn source) {
		this.source = source;
		colHandler = new ColumnLabelHandler(source);
		isPhysical = source.getTable().getERSchema().isPhysicModel();
		oldName = source.getName(isPhysical);
		oldType = source.getShowType(isPhysical);
	}

	@Override
	public void execute() {
		source.setShowTypeAndFire(newType, isPhysical);
		source.modifyNameAndFire(newName, isPhysical);
		if(!isPhysical){//set logical name to desc
			if(StringUtil.isEqual(newName, source.getName(true))){
				source.setDescription("");
			}else{
				source.setDescription(newName);
			}
		}
	}

	@Override
	public boolean canExecute() {
		if (newName != null && newType != null) {
			return true;
		} else {
			newName = oldName;
			newType = oldType;
			return false;
		}
	}

	/**
	 * Sets the new Column name
	 * 
	 * @param labelString the new name
	 */
	public void changeNameType(String labelString) {
		if (labelString != null) {
			ERTableColumn newColumn = source.clone();
			String name = ERTableColumn.getName(labelString);
			String type = ERTableColumn.getType(labelString);
			newColumn.setName(name, isPhysical);
			newColumn.setShowType(type, isPhysical);

			if (isPhysical ) {
				String upperShowType = DataType.getUpperShowType(type);
				String realType = DataType.getRealType(upperShowType);
				if(realType.startsWith(DataType.getUpperEnumType())){
					realType = realType.replaceFirst(DataType.getUpperEnumType(), DataType.getLowerEnumType());
				}
				if(source.getERSchema().hasPhysicalTypeInMap(realType)){
					changedLogicalShowType = source.getERSchema().convert2LogicalShowType(realType);
					newColumn.setShowType(changedLogicalShowType, !isPhysical);
				}
			} else if (source.getERSchema().hasLogicalTypeInMap(type)) {
				changedPhysicalShowType = source.getERSchema().convert2UpPhysicalShowType(type);
				newColumn.setPhysicalDataType(changedPhysicalShowType);
			}
			String err = source.getTable().checkColumnChange(source, newColumn);
			if (!StringUtil.isEmpty(err)) {
				CommonUITool.openErrorBox(err);
			} else {
				newName = name;//physical name
				newType = type;//physical data type
				colHandler.setLatestData(labelString);
			}
		}
		if (this.newType == null || newName == null) {
			this.newName = oldName;
			this.newType = oldType;
			changedLogicalShowType = null;
			changedPhysicalShowType = null;
		}
		if (StringUtil.isNotEmpty(changedLogicalShowType)) {
			source.setShowType(changedLogicalShowType, false);
		} else if (StringUtil.isNotEmpty(changedPhysicalShowType)) {
			source.setPhysicalDataType(changedPhysicalShowType);
		}
		execute();
	}
}