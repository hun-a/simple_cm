package com.cubrid.common.core.common.model;

import com.cubrid.common.core.util.FileUtil;

public interface IDatabaseSpec {
	public String getVersion();

	public boolean isShard();

	public void setShard(boolean isShard);

	public String wrapShardQuery(String sql);

	public FileUtil.OsInfoType getServerOsInfo();
}
