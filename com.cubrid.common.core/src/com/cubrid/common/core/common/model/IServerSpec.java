package com.cubrid.common.core.common.model;

import com.cubrid.common.core.util.FileUtil;

public interface IServerSpec {
	public String getServerVersionKey();

	public FileUtil.OsInfoType getServerOsInfo();

	public String getCubridConfPara(String para, String databaseName);
}
