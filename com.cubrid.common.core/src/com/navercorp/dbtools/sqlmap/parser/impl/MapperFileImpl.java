package com.navercorp.dbtools.sqlmap.parser.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.navercorp.dbtools.sqlmap.parser.MapperFile;
import com.navercorp.dbtools.sqlmap.parser.QueryCondition;
import com.nhn.dbtool.query.parser.sqlmap.model.DynamicTag;
import com.nhn.dbtool.query.parser.sqlmap.model.IsEmptyTag;
import com.nhn.dbtool.query.parser.sqlmap.model.IsNotEmptyTag;
import com.nhn.dbtool.query.parser.sqlmap.model.IterateTag;
import com.nhn.dbtool.query.parser.sqlmap.model.MyBatisTestCondition;
import com.nhn.dbtool.query.parser.sqlmap.model.SelectKeyTag;
import com.nhn.dbtool.query.parser.sqlmap.model.SqlMapCondition;
import com.nhn.dbtool.query.parser.sqlmap.model.SqlMapFile;
import com.nhn.dbtool.query.parser.sqlmap.model.SqlMapQuery;

public class MapperFileImpl implements
MapperFile {
	private final SqlMapFile sqlMapFile;

	public MapperFileImpl(SqlMapFile sqlMapFile) {
		this.sqlMapFile = sqlMapFile;
	}

	public String generateQuery(String queryId) {
		return sqlMapFile.createQuery(queryId);
	}

	public String generateQuery(String queryId, List<String> parameters) {
		return sqlMapFile.createQuery(queryId, parameters);
	}

	public String generateRawQuery(String queryId) {
		SqlMapQuery sqlMapQuery = sqlMapFile.getQuery(queryId);
		if (sqlMapQuery == null) {
			return null;
		}
		return sqlMapQuery.getIncludedQuery();
	}

	public List<QueryCondition> getConditionList(String queryId) {
		Map<String, QueryCondition> queryConditionMap = new HashMap<String, QueryCondition>();
		if (sqlMapFile.getQuery(queryId) == null) {
			return Collections.emptyList();
		}

		List<SqlMapCondition> sqlMapConditionList = sqlMapFile.getQuery(queryId).getConditionList();
		if (sqlMapConditionList == null) {
			sqlMapConditionList = Collections.emptyList();
		}

		prepareQueryConditionList(queryConditionMap, sqlMapConditionList);

		List<QueryCondition> queryConditionList = new ArrayList<QueryCondition>();
		for (Map.Entry<String, QueryCondition> entry : queryConditionMap.entrySet()) {
			queryConditionList.add(entry.getValue());
		}

		return queryConditionList;
	}

	private void prepareQueryConditionList(Map<String, QueryCondition> queryConditionMap,
			List<SqlMapCondition> sqlMapConditionList) {
		for (SqlMapCondition sqlMapCondition : sqlMapConditionList) {
			if (sqlMapCondition == null) {
				continue;
			}

			if (sqlMapCondition.getMyBatisTestConditions() == null || sqlMapCondition.getMyBatisTestConditions().size() == 0) {
				String value = sqlMapCondition.getValue();
				if (sqlMapCondition instanceof SelectKeyTag) {
					continue;
				}
				if (!(sqlMapCondition instanceof DynamicTag)) {
					if (sqlMapCondition instanceof IsNotEmptyTag) {
						value = "isNotEmpty";
					} else if (sqlMapCondition instanceof IsEmptyTag) {
						value = "isEmpty";
					} else if (sqlMapCondition.getCompareValue() != null && sqlMapCondition.getCompareValue().length() > 0) {
						value = sqlMapCondition.getCompareValue();
					}
					if (sqlMapCondition instanceof IterateTag) {
						// skip
					} else if (!"dynamic_param".equals(sqlMapCondition.getProperty())) {
						String key = sqlMapCondition.getProperty() + ":" + value;
						queryConditionMap.put(key, new QueryCondition(sqlMapCondition.getProperty(), value));
					}
				}
			} else {
				for (MyBatisTestCondition mybatisTestCondition : sqlMapCondition.getMyBatisTestConditions()) {
					if (mybatisTestCondition != null) {
						String key = mybatisTestCondition.getProperty() + ":"
								+ mybatisTestCondition.getValue();
						queryConditionMap.put(key,
								new QueryCondition(mybatisTestCondition.getProperty(),
										mybatisTestCondition.getValue()));
					}
				}
			}

			if (sqlMapCondition.getChildConditionList() != null) {
				prepareQueryConditionList(queryConditionMap,
						sqlMapCondition.getChildConditionList());
			}
		}

	}

	public List<String> getQueryIdList() {
		List<String> queryIdList = new ArrayList<String>();
		for (SqlMapQuery sqlMapQuery : sqlMapFile.getSqlMapQueryList()) {
			queryIdList.add(sqlMapQuery.getId());
		}
		return queryIdList;
	}
}
